package com.essg.sdc.vfm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import com.essg.sdc.net.Base64;
import com.essg.sdc.net.Mime;
import com.essg.sdc.net.UriUtils;
import com.essg.sdc.util.random.RandomUtil;
import com.essg.sdc.vfm.exceptions.AuthorityException;
import com.essg.sdc.vfm.file.ArchiveFolder;
import com.essg.sdc.vfm.file.FileInterface;
import com.essg.sdc.vfm.file.FileResource;
import com.essg.sdc.vfm.file.FilteredFile;
import com.essg.sdc.vfm.file.FolderInterface;
import com.essg.sdc.vfm.file.PhysicalFolder;
import com.essg.sdc.vfm.file.FilteredFile.Creator;
import com.essg.sdc.vfm.file.cache.CacheFolder;
import com.essg.sdc.vfm.http.AsyncHttpSimpleServer;
import com.essg.sdc.vfm.http.HttpAuthorizer;
import com.essg.sdc.vfm.http.HttpFileResolver;
import com.essg.sdc.vfm.http.HttpSimpleServer;
import com.essg.sdc.vfm.http.HttpsSimpleServer;
import com.essg.sdc.vfm.http.IVfmHttpServer;
import com.essg.sdc.vfm.http.OneTimeKeyInterface;
import com.essg.sdc.vfm.http.VfmHttpServer;
import com.essg.sdc.vfm.http.exceptions.HttpForbiddenException;
import com.essg.sdc.vfm.http.exceptions.HttpNotFoundException;
import com.essg.sdc.vfm.http.exceptions.HttpServerException;

/**
 * 仮想ファイルマネージャ
 * @author Mine
 */
public class VirtualFileManagerImpl extends VirtualFileManager {

	private static final boolean USE_HTTP_ASYNC = true;

	// コンテキスト
	private WeakReference<Object> _contextRef;

	//　キャッシュパス（コンテキスト毎にユニーク）
	private File _cachePath = null;

	// フォルダキャッシュ
	private Map<String, FolderInterface> _mapFolderCache = new WeakHashMap<String, FolderInterface>();
	private File _basePath;

	// ファイルキャッシュ
	private Map<String, SoftReference<FileInterface>> _mapFileCache = Collections.synchronizedMap(new HashMap<String, SoftReference<FileInterface>>());

	// Alias管理
	private WeakHashMap<String, Collection<String>> _mapAlias = new WeakHashMap<String, Collection<String>>();

	// インスタンス毎にHTTPサーバーを保持
	private VfmHttpServer _http_server = null;
	private HttpAuthorizer _http_authorizer;

	/**
	 * ワンタイムキークラス
	 * @author Mine
	 */
	private static class OneTimeKey implements OneTimeKeyInterface {
		// ワンタイムキーの有効期間（ミリ秒）
		private static final int ONE_TIME_KEY_LEASE_TIME = 1000;
		
		// 失効期限（現在時刻＋有効期間）
		private final long _time;
		
		// ユーザーID
		final String _uid;
		
		// キーデータ
		final byte[] _key;

		/**
		 * コンストラクタ
		 */
		OneTimeKey() {
			byte[] b = new byte[32];
			_random.nextBytes(b);
			_uid = Base64.encodeToString(b, Base64.URL_SAFE |Base64.NO_WRAP);
			_key = (_uid).getBytes();

//			Log.d(CLASS_NAME, "OneTimeKey=" + _uid + ":" + _pwd);
			_time = System.currentTimeMillis() + ONE_TIME_KEY_LEASE_TIME;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.http.OneTimeKeyInterface#getKey()
		 */
		@Override
		public byte[] getKey() {
			return _key;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.http.OneTimeKeyInterface#isExpired(long)
		 */
		@Override
		public boolean isExpired(long time) {
			return _time < time;
		}
	}

	// 発行されたワンタイムキーの管理リスト
	private LinkedList<OneTimeKeyInterface> listOneTimeKey = new LinkedList<OneTimeKeyInterface>();

	// コンテキストキャッシュ取得インターフェイス
	private IContextCacheResolver _cacheResolver = null;

	// コンテキストキャシュ
	private File _contextCacheDir = null;
	
	public static class DefaultFactory implements IVirtualFileManagerFactory {

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.IVirtualFileManagerFactory#create(java.lang.Object, com.essg.sdc.vfm.VirtualFileManager.KeyStore)
		 */
		@Override
		public VirtualFileManager create(Object context, KeyStore keyStore)	throws IOException {
			return new VirtualFileManagerImpl(context, keyStore);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		try {
			// Httpサーバーを停止します
			if (_http_server != null) {
				_http_server.interrupt();
				_http_server = null;
				_http_authorizer = null;
			}
			if (_mapFolderCache != null) {
				for (String key : _mapFolderCache.keySet()) {
					synchronized (key) {
						FolderInterface fo = _mapFolderCache.get(key);
						if (fo != null) {
							fo.abandon();
						}
					}
				}
				_mapFolderCache.clear();
				_mapFolderCache = null;
			}
			if (_mapFileCache != null) {
				_mapFileCache.clear();
				_mapFileCache = null;
			}
		} finally {
			super.finalize();
		}
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getContextCacheDirectory()
	 */
	@Override
	public File getContextCacheDirectory() {
		if (_cachePath == null) {
			Object context = _contextRef.get();
			_cachePath = getContextCacheDirectory(context);
		}
		return _cachePath;
	}

	/**
	 * @param context
	 * @return
	 */
	protected File getContextCacheDirectory(Object context) {
		if (_cacheResolver != null) {
			return _cacheResolver.getContextCache(context);
		} else if (context instanceof IContextCacheResolver) {
			return ((IContextCacheResolver)context).getContextCache(context);
		} else if (_contextCacheDir != null) {
			return _contextCacheDir;
		}
		return getCacheDir();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#setContextCacheDirectory(java.io.File)
	 */
	@Override
	public void setContextCacheDirectory(File cacheDir) {
		_contextCacheDir = cacheDir;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#setCacheResolver(com.essg.sdc.vfm.VirtualFileManager.IContextCacheResolver)
	 */
	@Override
	public void setCacheResolver(IContextCacheResolver resolver) {
		_cacheResolver = resolver;
	}
	
	public static class DefaultKeyStore implements KeyStore {
		private String _path;
		private char[] _password;
		
		/**
		 * コンストラクタ
		 * @param path
		 * @param password
		 */
		public DefaultKeyStore(String path, char[] password) {
			_path = path;
			_password = password;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.ContentManager.KeyStore#getKeyStoreAsStream(java.lang.Object)
		 */
		@Override
		public InputStream getKeyStoreAsStream(Object context) {
			return this.getClass().getClassLoader().getResourceAsStream(_path);
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.ContentManager.KeyStore#getKeyStorePassword(java.lang.Object)
		 */
		@Override
		public char[] getKeyStorePassword(Object context) {
			return _password;
		}
	}
	
	final KeyStore _keyStore;

	private LinkedList<Creator> listFilterCreator = null;

	/**
	 * コンストラクタ
	 * @param context コンテキスト
	 * @throws IOException
	 */
	private VirtualFileManagerImpl(Object context, KeyStore keystore) throws IOException {
		_basePath = new File("/");
		_contextRef = new WeakReference<Object>(context);
		_keyStore = keystore;

		_http_authorizer = new HttpAuthorizer() {
			@Override
			public synchronized String authorize(String text) {
				long time = System.currentTimeMillis();
				String key = null;

				String[] parse = text.split(" ");
				int cnt = 0;
				for (String v : parse) {
					if (v.length() == 0) continue;
					switch (cnt++) {
					case 0:
						if (!v.equalsIgnoreCase("Basic")) {
							return null;
						}
						break;
					case 1:
						key = v;
						break;
					}
				}

				if (key != null) {
					byte[] b = Base64.decode(key, Base64.URL_SAFE |Base64.NO_WRAP);

					ListIterator<OneTimeKeyInterface> it = listOneTimeKey.listIterator();
					while (it.hasNext()) {
						OneTimeKeyInterface otk = it.next();
						if (otk.isExpired(time)) {
							it.remove();
						} else if (compare(otk.getKey(), b)) {
							return "OK";
						}
					}
				}

				return null;
			}
			
		    private boolean compare(byte[] a, byte[] b) {
		        if (a==b)
		            return true;
		        if (a==null || b==null)
		            return false;

		        int length = a.length;
		        if (b.length < length)
		            return false;

		        for (int i=0; i<length; i++)
		            if (a[i] != b[i])
		                return false;

		        return true;
		    }

			/* (non-Javadoc)
			 * @see com.essg.sdc.android.bookreader.contents.http.HttpAuthorizer#getOnetimeKey()
			 */
			@Override
			public synchronized String[] getOnetimeKey() {
				OneTimeKey otk = new OneTimeKey();
				listOneTimeKey.add(otk);
				
				// パスワード無しでユーザーIDのみにしているが、強度的には同じ長さなので変わりません
				return new String[]{ _http_server.getHostName(), _http_server.getRealm(), otk._uid, "" };
			}
		};

		if (_keyStore != null) {
			InputStream is = _keyStore.getKeyStoreAsStream(context);
			if (is != null) {
				try {
					logger.warn("Keystore found.");
					_http_server = new HttpsSimpleServer(this, 0, _http_authorizer);
					((HttpsSimpleServer)_http_server).setKeyStore(is, _keyStore.getKeyStorePassword(context));
					_http_server.start();
					if (!connectHttpForTest()) {
						_http_server = null;
					}
				} catch (Exception e) {
					_http_server = null;
				} finally {
					is.close();
				}
			}
		}
		if (_http_server == null || !_http_server.isAlive()) {
			if (USE_HTTP_ASYNC) {
				_http_server = new AsyncHttpSimpleServer(this, 0, _http_authorizer);
			} else {
				_http_server = new HttpSimpleServer(this, 0, _http_authorizer);
			}
			_http_server.start();
		}
	}
	
	/**
	 * HTTPサーバー確認スレッド
	 *  AndroidはMainスレッドで通信すると怒られるのでスレッド化が必要です
	 */
	private static class HttpConnectChecker extends Thread {
		final IVfmHttpServer _http_server;
		boolean result = false;
		
		HttpConnectChecker(IVfmHttpServer server) {
			_http_server = server;
		}
		
		@Override
		public void run() {
			try {
				URL url = new URL(_http_server.getUri().toString());
				logger.debug("Connect test : " + url.toString());
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				if (con != null) {
					try {
						con.setRequestMethod("HEAD");
						con.setConnectTimeout(1000);
						con.connect();
						result = true;
					} finally {
						con.disconnect();
					}
				}
				logger.debug("Connect test : OK");
			} catch (Exception e) {
				logger.warn("Connect test : " + e.toString());
			} 
		}
		
		public boolean isSuccess() {
			return result;
		}
	}
	
	/**
	 * HTTPサーバーに接続確認を行います
	 * @return
	 */
	private boolean connectHttpForTest() {
		HttpConnectChecker thread = new HttpConnectChecker(_http_server);
		
		thread.start();
		while (_http_server.isAlive() && thread.isAlive()) {
			try {
				thread.join(200);
			} catch (InterruptedException e) {
			}
		}
		if (thread.isAlive()) thread.interrupt();
		
		return thread.isSuccess();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getList(java.io.File, boolean, boolean)
	 */
	@Override
	public List<FileInterface> getList(File file, boolean subdir, boolean extract) throws IOException {
		return getListImpl(new ArrayList<FileInterface>(), getFile(file), subdir, 10);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getList(java.lang.String, boolean, boolean)
	 */
	@Override
	public List<FileInterface> getList(String path, boolean subdir, boolean extract) throws IOException {
		return getListImpl(new ArrayList<FileInterface>(), getFile(path), subdir, 10);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getList(java.net.URI, boolean, boolean)
	 */
	@Override
	public List<FileInterface> getList(URI uri, boolean subdir, boolean extract) throws IOException {
		return getListImpl(new ArrayList<FileInterface>(), getFile(uri), subdir, 10);
	}

	/**
	 * @param list
	 * @param file
	 * @param subdir
	 * @param extract
	 * @return
	 * @throws IOException
	 */
	protected List<FileInterface> getListImpl(List<FileInterface> list, FileInterface file, boolean subdir, int extract) throws IOException {
		return getListImpl(list, file, file.getFolder(), subdir, extract);
	}

	/**
	 * @param list
	 * @param file
	 * @param folder
	 * @param subdir
	 * @param extract
	 * @return
	 * @throws IOException
	 */
	protected List<FileInterface> getListImpl(List<FileInterface> list, FileInterface file, FolderInterface folder, boolean subdir, int extract) throws IOException {
		// file が解凍可能な場合、
		if (extract > 0 && file != null && ArchiveFolder.isExtractable(file.getType())) {
			String path = file.getAbsolutePath();
			ArchiveFolder archive = ArchiveFolder.newInstance(path, file, getIOHandler(path, file));
			if (archive != null) {
				if (subdir) {
					list = getListImpl(list, null, archive, subdir, extract - 1);
				} else {
					list.add(archive);
				}
				return list;
			}
		}
		
		String[] l = folder.getList(file, subdir);
		if (l.length == 0) {
			if (file == null) {
				if (folder instanceof FileInterface) {
					list.add((FileInterface) folder);
				}
			} else {
				list.add(file);
			}
		} else {
			for (String n : l) {
				FileInterface fi = getFile(folder, n);
				list.add(fi);
			}
		}

		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#toHttpUri(java.lang.String)
	 */
	@Override
	public URI toHttpUri(String path) {
		return UriUtils.withAppendedPath(getHttpServerUri(), path);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#toHttpUri(java.net.URI)
	 */
	@Override
	public URI toHttpUri(URI uri) {
		String path = uri.getPath();
		String query = uri.getQuery();
		if (query != null) path = path + "?" + query;
		return toHttpUri(path);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getFile(java.lang.String)
	 */
	@Override
	public FileInterface getFile(String path) throws IOException {
		return getFile(new File(path));
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getFile(java.io.File)
	 */
	@Override
	public FileInterface getFile(File file) throws IOException {
		return getFile(file.toURI());
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getFile(java.net.URI)
	 */
	@Override
	public FileInterface getFile(URI uri) throws IOException {
//		Log.d(CLASS_NAME, "getFile - start - " + path);

		try {
//			path = URLEncoder.encode(path, "utf-8").intern();
			URI current = new File(".").toURI();
			uri = current.relativize(uri);
			String path = uri.getPath().intern();

			// コストが高いかもしれないが、手抜き処理
			List<String> segments = UriUtils.getPathSegments(uri);

			// Aliasを変換します
			List<String> t = aliasToOriginal(segments);
			if (t != segments) {
				segments = t;
				path = segmentsToPath(t).intern();
			}
			
			synchronized (path) {
				// 最初にキャッシュ内を検索します
				SoftReference<FileInterface> ref = _mapFileCache.get(path);
				if (ref != null) {
					FileInterface rtn = ref.get();
					if (rtn != null) {
						if (bDebug) logger.debug("File Cache Hit. path = " + path);
						return rtn;
					}

					// 空のリファレンスは消しておきます
					_mapFileCache.remove(ref);
				}

				FileInterface finfo = null;

				// Path
				File rfile = _basePath;

				// 仮想Path
				File vfile = null;

				FolderInterface folder = null;

				int max = segments.size();
				int start = max;
				int skip = 0;

				// フォルダキャッシュを検索します
				for (File f = new File(path); f != null; f = f.getParentFile(), start--) {
					String p = f.getAbsolutePath().intern();
					folder = _mapFolderCache.get(p);
					if (folder != null) {
						if (bDebug) logger.debug("Folder Cache Hit. path = " + p);
						break;
					}
					if (f.isDirectory()) {
						rfile = f;
						skip = start;
						break;
					}
				}

				// フォルダが見つからないかった場合
				if (folder == null) {
					// 物理ファイル
					folder = new PhysicalFolder(rfile);
				}

				finfo = getFile(folder, "");

				//
				int cnt = 0;
				for (String seg : segments) {
					if (cnt >= skip) {
						rfile = new File(rfile, seg);
						if (cnt >= start) {
							vfile = new File(vfile, seg);

							finfo = getFile(folder, vfile.getPath());

							// ファイルが見つからない場合はエラー
							if (finfo == null) {
								if (bDebug) logger.debug("getFile() path=" + path + " File not found. folder=" + folder.getClass().getSimpleName());
								throw new FileNotFoundException();
							}

							// ファイルかつパスの最後でない場合、アーカイヴの解凍を試みます
							// 以降の処理でアーカイヴファイルはフォルダとして扱います
							if (!finfo.isDirectory() && (cnt < (max - 1))) {
								String folder_path = rfile.getAbsolutePath().intern();

								// 同じフォルダを同時に処理しない為のブロック
								synchronized (folder_path) {
									// Cache を確認します
									folder = _mapFolderCache.get(folder_path);

									// Cacheに無い場合、解凍クラスを探します
									if (folder == null) {
										folder = unpackFile(finfo, rfile.getAbsolutePath());

										// 見つかった場合はCacheに登録します
										if (folder != null) {
											_mapFolderCache.put(folder_path, folder);
										}
									}
								}

								// 見つからない場合はエラー
								if (folder == null) {
									if (bDebug) {
										logger.debug("getFile() path=" + path + " Archive not found. folder=" + folder_path + " , file=" + finfo.getClass().getSimpleName());
									}
									throw new FileNotFoundException();
								}

								vfile = null;
							}
						}
					}
					cnt++;
				}

				// キャッシュに登録します
				if (finfo != null) {
					_mapFileCache.put(path, new SoftReference<FileInterface>(finfo));
				}

				return finfo;
			}
		} catch (IOException ioe) {
			throw ioe;
		} catch (Throwable th) {
			if (bDebug) logger.error("getFile()", th);
			throw new IOException(th.toString());
		} finally {
//			Log.d(CLASS_NAME, "getFile - end - " + path);
		}
	}

	private FileInterface getFile(FolderInterface folder, String path) throws IOException {
		FileInterface file = folder.getFile(path);
		if (file != null && listFilterCreator != null && !listFilterCreator.isEmpty()) {
			// TODO 同じ判定を行わない仕組みを構築した方が良い・・・
			file = FilteredFile.filter(file, _contextRef.get(), listFilterCreator);
		}
		return file;
	}

	/**
	 * Aliasからオリジナルのパスを取得します
	 * @param list
	 * @return
	 */
	private List<String> aliasToOriginal(List<String> list) {
		if (list != null && !list.isEmpty()) {
			String path = list.get(0).intern();
			Collection<String> orig = getAlias(path);
			if (orig != null) {
				List<String> tmp = new ArrayList<String>();
				tmp.addAll(orig);
				tmp.addAll(list.subList(1, list.size()));
				list = tmp;
				if (bDebug) {
					logger.debug("aliasToOriginal() " + path + " -> " + orig);
				}
			}
		}
		return list;
	}

	private Collection<String> getAlias(String segment) {
		synchronized (_mapAlias) {
			return _mapAlias.get(segment);
		}
	}

	private String segmentsToPath(List<String> list) {
		StringBuilder sb = new StringBuilder();
		if (list != null) {
			for (String s : list) {
				if (sb.length() != 0) sb.append("/");
				sb.append(s);
			}
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#registerHttpResolver(com.essg.sdc.vfm.http.HttpFileResolver)
	 */
	@Override
	public void registerHttpResolver(HttpFileResolver resolver) {
		_http_server.registResolver(resolver);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#unregisterHttpResolver(com.essg.sdc.vfm.http.HttpFileResolver)
	 */
	@Override
	public void unregisterHttpResolver(HttpFileResolver resolver) {
		_http_server.unregistResolver(resolver);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getHttpServerUri()
	 */
	@Override
	public URI getHttpServerUri() {
		return _http_server == null ? null : _http_server.getUri();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getHttpServerHost()
	 */
	@Override
	public String getHttpServerHost() {
		return _http_server == null ? null : _http_server.getHostName();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getAlias(java.net.URI)
	 */
	@Override
	public String getAlias(URI uri) throws IOException {
		// Uriの指定がAliasの場合はそのまま返します
		String path = uri.getPath();
		Collection<String> org = getAlias(uri.getPath());
		if (org != null) {
			return path;
		}

		// 相対パスに変換します
		URI current = new File(".").toURI();
		uri = current.relativize(uri);
		
		FileInterface file = getFile(uri);
		if (file != null) {
			List<String> segments = UriUtils.getPathSegments(uri);
			int size = segments.size();
			
			synchronized (_mapAlias) {
				// 既に登録済みの場合は設定されているAliasを返します
				for (Entry<String, Collection<String>>  entry : _mapAlias.entrySet()) {
					Collection<String> tmp = entry.getValue(); 
					if (tmp.size() == size) {
						Iterator<String> it1 = segments.iterator();
						Iterator<String> it2 = tmp.iterator();
						boolean match = true;
						for (int i = 0; i < size; i++) {
							if (it1.next().equals(it2.next())) {
								match = false;
								break;
							}
						}
						if (match) return entry.getKey();
					}
				}
				
				// 拡張子を残す為に分離します
				String exttmp = segments.get(size - 1);
				int n = exttmp.lastIndexOf(".");
				String ext = "";
				if (n >= 0) {
					ext = exttmp.substring(n);
				}

				// 新しいAliasを生成して返します
				for (;;) {
					String alias = getRandomString(8, 32) + ext;
					if (!_mapAlias.containsKey(alias) && !(new File(alias).exists())) {
						_mapAlias.put(alias, segments);
						return alias;
					}
				}
			}
		}
		throw new FileNotFoundException();
	}

	/**
	 * 指定された長さの出鱈目は文字列を生成します
	 * @param minlen 生成文字列の最低長
	 * @param maxlen 生成文字列の最大長
	 * @return 生成した文字列
	 */
	private String getRandomString(int minlen, int maxlen) {
		return RandomUtil.randomString(_random, minlen, maxlen, _chartab);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getHttpOneTimeKey()
	 */
	@Override
	public String[] getHttpOneTimeKey() {
		return _http_authorizer == null ? null : _http_authorizer.getOnetimeKey();
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#obtainHttpPassport(java.lang.String[])
	 */
	@Override
	public Object obtainHttpPassport(String[] onetimepass) {
		// Basic認証と同じ状態にする為に Base64エンコードを実施しています
		return _http_authorizer == null ? null :
			_http_server.obtainPassport("Basic " + Base64.encodeToString(onetimepass[2].getBytes(), Base64.URL_SAFE |Base64.NO_WRAP));
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#getMimeType(java.net.URI)
	 */
	@Override
	public Collection<Mime> getMimeType(URI uri) {
		Collection<Mime> result = null;
		try {
			FileInterface file = getFile(uri);
			if (file != null) {
				if (file.isDirectory()) {
					result = Arrays.asList(new Mime[]{ VirtualFileManager.TYPE_DIRECTORY });
				} else {
					result = Mime.getMimeType(file.toURL());
				}
			}
		} catch (IOException e) {
		}
		return result;
	}

	/**
	 * アーカイブの解凍を行います
	 * @param archive 対象ファイル
	 * @param path 対象ファイルの存在するパス
	 * @return
	 * @throws FileNotFoundException
	 */
	private FolderInterface unpackFile(FileInterface archive, String path) throws FileNotFoundException {
		FolderInterface folder = null;
		if (ArchiveFolder.isExtractable(archive.getType())) {
			// 展開先のディレクトリを用意します
			folder = ArchiveFolder.newInstance(path, archive, getIOHandler(path, archive));
		}
		return folder;
	}

	private StandardIOHandler getIOHandler(String path, FileInterface file) {
//		File workDir = new File(_cachePath, String.format("%h", path.intern()));
		return new StandardIOHandler(file, new CacheFolder(getContextCacheDirectory()));
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#isTarget(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isTarget(String url, String path) {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#resolveResource(java.lang.String, java.lang.String)
	 */
	@Override
	public FileResource resolveResource(String url, String path)
			throws HttpServerException {
		try {
			return getFile(path);
		} catch (FileNotFoundException fnfe) {
			throw new HttpNotFoundException(fnfe);
		} catch (AuthorityException ae) {
			throw new HttpForbiddenException(ae);
		} catch (IOException e) {
			throw new HttpServerException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IVirtualFileManager#addFileFilter(com.essg.sdc.vfm.file.FilteredFile.Creator)
	 */
	@Override
	public synchronized void addFileFilter(Creator creator) {
		if (listFilterCreator == null) {
			listFilterCreator = new LinkedList<Creator>();
		}
		listFilterCreator.remove(creator);
		listFilterCreator.addFirst(creator);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.VirtualFileManager#getHttpServer()
	 */
	@Override
	public IVfmHttpServer getHttpServer() {
		return _http_server;
	}
}