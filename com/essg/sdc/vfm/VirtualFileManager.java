package com.essg.sdc.vfm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.essg.sdc.net.Mime;
import com.essg.sdc.vfm.file.FileInterface;
import com.essg.sdc.vfm.file.FileResource;
import com.essg.sdc.vfm.file.FilteredFile.Creator;
import com.essg.sdc.vfm.http.HttpFileResolver;
import com.essg.sdc.vfm.http.IVfmHttpServer;
import com.essg.sdc.vfm.http.exceptions.HttpServerException;

/**
 * 仮想ファイルマネージャ
 * @author Mine
 */
public abstract class VirtualFileManager implements HttpFileResolver {
	protected static final Logger logger;

	// デバッグ用スイッチ
	protected static final boolean bDebug;
	
	// エンコード/デコードの文字種テーブル
	protected static final char[] _chartab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-()".toCharArray();

	// 仮想ディレクトリのMIMEタイプ
	public static final Mime TYPE_DIRECTORY = new Mime("application", "vdr.essg.sdc.direcory");
	// MIMEタイプが判定できない場合のMIMEタイプ
	public static final Mime TYPE_UNKNOWN = new Mime("application", "vdr.essg.sdc.unknown");

	// コンテンツマネージャを管理します
	private static WeakHashMap<Object, VirtualFileManager> _mapManager = new WeakHashMap<Object, VirtualFileManager>();

	// 乱数生成
	protected static Random _random = null;
	
	// キャッシュディレクトリ
	protected static File _cacheDir = null;

	private static IVirtualFileManagerFactory _factory = null;
	
	static {
		logger = LoggerFactory.getLogger(VirtualFileManager.class);
		bDebug = logger == null ? false : logger.isDebugEnabled();

		Random tr = new Random(System.currentTimeMillis());
		byte[] seeds = new byte[64];
		tr.nextBytes(seeds);
		_random = new SecureRandom(seeds);
	}
	
	/**
	 * コンテキストキャッシュ取得インターフェイス
	 * コンテキスト毎にキャッシュディレクトリを変更する為のインターフェイス
	 * @author Mine
	 */
	public interface IContextCacheResolver {
		/**
		 * キャッシュディレクトリを取得します
		 * コンテキストを使用して任意のディレクトリを
		 * @param obj コンテキスト
		 * @return
		 */
		File getContextCache(Object obj);
	}
	
	public interface IVFMCreateCallback<T> {
		T vfmCreateCallback(T vfm, Object context, KeyStore keyStore);
	}
	
	/**
	 * @param context
	 * @return
	 */
	public static VirtualFileManager getInstance(Object context) {
		return getInstance(context, null, null);
	}

	/**
	 * @param context
	 * @param callback
	 * @return
	 */
	public synchronized static VirtualFileManager getInstance(Object context, IVFMCreateCallback<VirtualFileManager> callback) {
		return getInstance(context, null, callback);
	}
	
	/**
	 * @param context
	 * @param keyStore
	 * @return
	 */
	public synchronized static VirtualFileManager getInstance(Object context, KeyStore keyStore) {
		return getInstance(context, keyStore, null);
	}
	
	/**
	 * @param context
	 * @param keyStore
	 * @param callback
	 * @return
	 */
	public synchronized static VirtualFileManager getInstance(Object context, KeyStore keyStore, IVFMCreateCallback<VirtualFileManager> callback) {
		// getInstance中、dropInstance中の重複処理を防止します
		VirtualFileManager manager = _mapManager.get(context);
		if (manager == null) {
			logger.debug("create instance (" + context.toString() + ")");
			try {
				manager = getFactory().create(context, keyStore);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			if (callback != null) {
				manager = callback.vfmCreateCallback(manager, context, keyStore);
			}
			_mapManager.put(context, manager);
		}
		return manager;
	}
	
	/**
	 * @return
	 */
	public synchronized static IVirtualFileManagerFactory getFactory() {
		if (_factory == null) _factory = new VirtualFileManagerImpl.DefaultFactory();
		return _factory;
	}

	/**
	 * @param factory
	 */
	public synchronized static void setFactory(IVirtualFileManagerFactory factory) {
		_factory = factory;
	}
	
	/**
	 * システムキャッシュディレクトリ設定
	 * @param cacheDir 新しいシステムキャッシュディレクトリ
	 */
	public static void setCacheDir(File cacheDir) {
		_cacheDir = cacheDir;
	}

	/**
	 * システムキャッシュディレクトリ取得
	 * @return システムキャッシュディレクトリ
	 */
	protected static File getCacheDir() {
		if (_cacheDir == null) {
			String path = System.getProperty("java.io.tmpdir");
			if (path == null || path.length() == 0) {
				path = ".";
			}
			return new File(path);
		}
		return _cacheDir;
	}
	
	public interface KeyStore {
		InputStream getKeyStoreAsStream(Object context);
		char[] getKeyStorePassword(Object context);
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
	
	/**
	 * @return
	 */
	public abstract File getContextCacheDirectory();

	/**
	 * コンテキストキャッシュディレクトリ設定
	 * @param cacheDir 新しいコンテキストキャッシュディレクトリ
	 */
	public abstract void setContextCacheDirectory(File cacheDir);

	/**
	 * コンテキスト単位で使用されるキャッシュフォルダを解決するクラスをセットします
	 * コンテキスト自身に実装した場合は、循環参照となる為、メモリリークが発生します。
	 * その場合、終了時に確実にnullでクリアしてください。
	 * @param resolver コンテキストキャッシュ取得インターフェイス
	 */
	public abstract void setCacheResolver(IContextCacheResolver resolver);

	/**
	 * @param file
	 * @param subdir
	 * @param extract
	 * @return
	 * @throws IOException
	 */
	public abstract List<FileInterface> getList(File file, boolean subdir,
			boolean extract) throws IOException;

	/**
	 * @param path
	 * @param subdir
	 * @param extract
	 * @return
	 * @throws IOException
	 */
	public abstract List<FileInterface> getList(String path, boolean subdir,
			boolean extract) throws IOException;

	/**
	 * @param uri
	 * @param subdir
	 * @param extract
	 * @return
	 * @throws IOException
	 */
	public abstract List<FileInterface> getList(URI uri, boolean subdir,
			boolean extract) throws IOException;

	/**
	 * @param path
	 * @return
	 */
	public abstract URI toHttpUri(String path);

	/**
	 * @param uri
	 * @return
	 */
	public abstract URI toHttpUri(URI uri);

	/**
	 * @param segments
	 * @return
	 * @throws IOException
	 */
	public abstract FileInterface getFile(String path) throws IOException;

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public abstract FileInterface getFile(File file) throws IOException;

	/**
	 * @param segments
	 * @return
	 * @throws IOException
	 */
	public abstract FileInterface getFile(URI uri) throws IOException;

	/**
	 * @param resolver
	 */
	public abstract void registerHttpResolver(HttpFileResolver resolver);

	/**
	 * @param resolver
	 */
	public abstract void unregisterHttpResolver(HttpFileResolver resolver);

	/**
	 * @return
	 */
	public abstract URI getHttpServerUri();

	/**
	 * @return
	 */
	public abstract String getHttpServerHost();

	/**
	 * 指定されたUriから別名(Alias)を取得します
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	public abstract String getAlias(URI uri) throws IOException;

	/**
	 * @return
	 */
	public abstract String[] getHttpOneTimeKey();

	/**
	 * 先行して認証を行えるパスポートを発行します
	 * パスポートを持っている間はObject.toString()で取得できる文字列をパラメータとしてURLに添付する事により
	 * 認証動作をパスさせる事が可能です。
	 * @param onetimepass
	 * @return
	 */
	public abstract Object obtainHttpPassport(String[] onetimepass);

	/**
	 * @param uri
	 * @return
	 */
	public abstract Collection<Mime> getMimeType(URI uri);

	/* (non-Javadoc)
	 * @see com.essg.sdc.contentsmanager.http.HttpContentResolver#isTarget(java.lang.String, java.lang.String)
	 */
	public abstract boolean isTarget(String url, String path);

	/* (non-Javadoc)
	 * @see com.essg.sdc.contentsmanager.http.HttpContentResolver#resolveResource(java.lang.String, java.lang.String)
	 */
	public abstract FileResource resolveResource(String url, String path)
			throws HttpServerException;

	/**
	 * @param creator
	 */
	public abstract void addFileFilter(Creator creator);
	
	/**
	 * Httpサーバーのインスタンスを取得します
	 * @return Httpサーバ
	 */
	public abstract IVfmHttpServer getHttpServer();
}