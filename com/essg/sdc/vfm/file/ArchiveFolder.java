package com.essg.sdc.vfm.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.essg.sdc.io.zip.GZipWrapper;
import com.essg.sdc.net.Mime;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.vfm.StandardIOHandler;
import com.essg.sdc.vfm.extractor.ExtractFileInterface;
import com.essg.sdc.vfm.extractor.FileExtractor;
import com.essg.sdc.vfm.extractor.ZipExtractor;

/**
 * 書庫フォルダークラス
 * 書庫フォルダーはファイルとフォルダーの両方のインターフェイスを持っています。
 * @author Mine
 */
public class ArchiveFolder extends FolderAbstract implements FileInterface {
	// 書庫の解凍処理オブジェクト
	FileExtractor _extractor;
	
	// IOハンドラ
	StandardIOHandler _handler;
	
	// 親フォルダ
	File _parent;
	
	//TODO Androidでクラス一覧を取得する方法が apkやdexから以外では見つからなかったので暫定対応
	private static final String[] classlist =  {
		ZipExtractor.class.getName(),
	};
	
	static ArrayList<Class<? extends FileExtractor>> _extractorList = new ArrayList<Class<? extends FileExtractor>>();
	static SoftReference<ArrayList<Mime>> _mimeList_ref = new SoftReference<ArrayList<Mime>>(null);
	
	/**
	 * 書庫ファイルクラス
	 * @author Mine
	 */
	private class ArchiveFile extends FileAbstract {

		ExtractFileInterface _exfile;

		/**
		 * コンストラクタ
		 * @param folder
		 * @param path
		 * @param exfile
		 */
		public ArchiveFile(FolderInterface folder, String path, ExtractFileInterface exfile) {
			super(folder, path);
			_exfile = exfile;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileResource#openStream()
		 */
		@Override
		public InputStream openStream() throws IOException {
			FileInterface f = _handler.getFile(_path);
			if (f != null) {
				return f.openStream();
			}
			
			//TODO 一度読み込んだものをキャッシュ化する方法を考える
			return _exfile.openStream(_handler);
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileResource#getBuffer()
		 */
		@Override
		public ByteBuffer getBuffer() throws IOException {
			FileInterface f = _handler.getFile(_path);
			if (f != null) {
				return f.getBuffer();
			}
			ByteBuffer buf = _exfile.getBuffer(_handler);
			if (buf != null) return buf;
			return super.getBuffer();
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileResource#getSize()
		 */
		@Override
		public long getSize() {
			return _exfile.getSize(_handler);
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileInterface#isDirectory()
		 */
		@Override
		public boolean isDirectory() {
			return _exfile.isDirectory(_handler);
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileAbstract#ready()
		 */
		@Override
		public boolean ready() {
			return false;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileAbstract#prepare()
		 */
		@Override
		public boolean prepare() {
			Thread th = new Thread() {
			};
			th.start();
			
			return super.prepare();
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileResourceAbstract#isSupportGZip()
		 */
		@Override
		public boolean isSupportGZip() {
			return _exfile.isDeflated(_handler);
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileResourceAbstract#openGZipStream()
		 */
		@Override
		public InputStream openGZipStream() throws IOException {
			if (isSupportGZip()) {
				return GZipWrapper.wrap(_exfile.openDeflatedStream(_handler),
						_exfile.getDeflatedInfo(_handler), null);
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.vfm.file.FileResourceAbstract#gzipStreamLength()
		 */
		@Override
		public long gzipStreamLength() {
			try {
				if (isSupportGZip()) {
					return GZipWrapper.streamLength(_exfile.getDeflatedInfo(_handler), null);
				}
			} catch (IOException e) {
			}
			return -1;
		}
		
	}
	
	/**
	 * 対象ファイルが書庫フォルダかを判定して、処理可能な書庫フォルダを生成します。
	 * @param path 対象ファイルの存在するパス
	 * @param archive 対象ファイル
	 * @param handler 入出力を制御するハンドラー（一時ファイルの展開及びキャッシュに使用します）
	 * @return 生成した書庫フォルダインターフェイス、処理ができない場合は null を返します。
	 */
	public static ArchiveFolder newInstance(String path, FileInterface archive, StandardIOHandler handler) {
		try {
			//TODO 現在はZipのみ対応している、将来的にはクラスを検索して複数種類に対応する
			FileExtractor extractor = new ZipExtractor();
			if (extractor.isExtractable(handler)) {
				return new ArchiveFolder(path, extractor, handler);
			}
		} finally {
		}
		return null;
	}
	
	/**
	 * FileExtractorを動的に検索してインスタンス化します(現在は未使用)
	 * @param name クラス名
	 * @return 生成したインスタンス
	 */
	FileExtractor getExtractor(String name) {
		FileExtractor ext = null;
		try {
			Class<?> cls = Class.forName(name);
			if (!cls.isAssignableFrom(FileExtractor.class)) {
				ext = (FileExtractor) cls.newInstance();
			}
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		return ext;
	}
	
	/**
	 * コンストラクタ
	 * @param extractor 展開クラス
	 * @param handler IOハンドラー
	 */
	ArchiveFolder(String path, FileExtractor extractor, StandardIOHandler handler) {
		_parent = new File(path);
		_extractor = extractor;
		_handler = handler;
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FolderInterface#getAbsolutePath(java.lang.String)
	 */
	@Override
	public String getAbsolutePath(String path) {
		return new File(_parent, path).getAbsolutePath();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FolderInterface#getFile(java.lang.String)
	 */
	public FileInterface getFile(String path) throws FileNotFoundException {
		if (path == null || path.length() == 0) {
			return getParent();
		}
		
		FileInterface file = null;
		if (_handler != null && _extractor != null) {
			ExtractFileInterface exfile = _extractor.extract(_handler, new File(path));
			if (exfile != null) {
				file = new ArchiveFile(this, path, exfile);
			}
		}
		return file;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FolderInterface#abandon()
	 */
	@Override
	public void abandon() {
		if (_handler != null) {
			_handler.abandon();
			_handler = null;
		}
		_extractor = null;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FolderInterface#getList(java.lang.String, boolean)
	 */
	@Override
	public String[] getList(String path, boolean subdir) {
		File f = null;
		if (path != null && path.length() > 0) {
			f = new File(path);
		}
		File[] list = _extractor.list(_handler, f);
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < list.length; i++) {
			if (f == null || !f.equals(list[i])) {
				//TODO subdir = false　の場合の判定が未実装
				result.add(list[i].getAbsolutePath());
			}
		}
		return result.toArray(new String[result.size()]);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileInterface#getName()
	 */
	@Override
	public String getName() {
		return _handler.getFile(null).getName();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileInterface#getFolder()
	 */
	@Override
	public FolderInterface getFolder() {
		return _handler.getFile(null).getFolder();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileResource#openStream()
	 */
	@Override
	public InputStream openStream() throws IOException {
		return _handler.getFile(null).openStream();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileResource#getBuffer()
	 */
	@Override
	public ByteBuffer getBuffer() throws IOException {
		return _handler.getInputBuffer();
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileResource#getSize()
	 */
	@Override
	public long getSize() {
		return _handler.getFile(null).getSize();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileInterface#isDirectory()
	 */
	@Override
	public boolean isDirectory() {
		return _handler.getFile(null).isDirectory();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileInterface#ready()
	 */
	@Override
	public boolean ready() {
		return _handler.getFile(null).ready();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileInterface#prepare()
	 */
	@Override
	public boolean prepare() {
		return _handler.getFile(null).prepare();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileResource#getType()
	 */
	@Override
	public Mime getType() {
		return _handler.getFile(null).getType();
	}

	/**
	 * この書庫フォルダが扱えるMIMEタイプを判定します
	 * @param type 判定するMIMEタイプ
	 * @return true=扱える可能性があります（必ずしも処理可能とは限りません）、 false=扱えません
	 */
	public static boolean isExtractable(Mime type) {
		for (Mime mime : getMimeList()) {
			if (mime.equals(type)) return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static ArrayList<Class<? extends FileExtractor>> getExtractorList() {
		synchronized(_extractorList) {
			if (_extractorList.isEmpty()) {
				ClassLoader cl = Thread.currentThread().getContextClassLoader();
				try {
					for (String name : classlist) {
						try {
							Class<?> clazz = cl.loadClass(name);
							if (!clazz.isInterface() && FileExtractor.class.isAssignableFrom(clazz)) {
								_extractorList.add((Class<? extends FileExtractor>) clazz);
							}
						} catch (Exception e) {
						}
					}
				} finally {
				}
			}
		}
		return _extractorList;
	}
	
	/**
	 * @return
	 */
	private static ArrayList<Mime> getMimeList() {
		ArrayList<Mime> list = null;
		
		// 同期は厳密でなくて良いので、同時に作成してしまう無駄さえ省ければ良い
		synchronized (_mimeList_ref) {
			list = _mimeList_ref.get();
			if (list == null) {
				list = new ArrayList<Mime>();
				
				for (Class<? extends FileExtractor> cls : getExtractorList()) {
					try {
						FileExtractor extractor = cls.newInstance();
						list.addAll(extractor.getTypes());
					} catch (Exception e) {
					}
				}
			}
			_mimeList_ref = new SoftReference<ArrayList<Mime>>(list);
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileResource#isSupportGZip()
	 */
	@Override
	public boolean isSupportGZip() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileResource#openGZipStream()
	 */
	@Override
	public InputStream openGZipStream() throws IOException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileResource#gzipStreamLength()
	 */
	@Override
	public long gzipStreamLength() {
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FolderInterface#getParent()
	 */
	@Override
	public FileInterface getParent() {
		return _handler.getFile(null);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileInterface#toURL()
	 */
	@Override
	public URL toURL() throws MalformedURLException {
		return FileAbstract.toURL(this);
	}
}