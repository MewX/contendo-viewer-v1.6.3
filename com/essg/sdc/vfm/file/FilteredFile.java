package com.essg.sdc.vfm.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.essg.sdc.net.Mime;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.vfm.exceptions.AuthorityException;

public abstract class FilteredFile implements FileInterface {
	
	private static final Logger logger = LoggerFactory.getLogger(FilteredFile.class);
	
	// Filter　クラスの生成クラスのリスト
//	private static final Creator[] creatores = { /*new TFGFile.Creator(),*/ };

	
	protected final FileInterface _file;
	protected final Object _context;
	protected Mime _mimetype = null;
	
	public static FileInterface filter(FileInterface source, Object context, Collection<Creator> creators) throws AuthorityException {
		boolean bDir = source.isDirectory();
		
		FileInterface fi = null;
		do {
			for (Creator cr : creators) {
				if ((bDir && cr.isApplicableDirectory()) || (!bDir && cr.isApplicableFile())) {
					try {
						fi = cr.create(source, context);
						if (fi != null) {
//							Log.d(TAG, "filter() " + source.getClass().getSimpleName() + " -> " + fi.getClass().getSimpleName());
							source = fi;
							break;
						}
					} catch (AuthorityException ae) {
						throw ae;
					} catch (Exception e) {
						logger.error("filter()", e);
					}
				}
			}
		} while (fi != null);
//		Log.d(TAG, "filter() " + source.getClass().getSimpleName() + " path=" + source.getName());
		return source;
	}

	/**
	 * FilteredFile 生成クラスのインターフェイス定義
	 * @author ess0083
	 */
	public interface Creator {
		/**
		 * フィルター適用後のファイルインターフェイス作成して取得します
		 * 実装する時に処理対象で無い場合は必ず null を返す必要があります。
		 * @param source
		 * @param context
		 * @return sourceがフィルターの対象でない場合は null、 対象の場合は 生成したファイルインターフェイス
		 * @throws IOException
		 */
		FileInterface create(FileInterface source, Object context) throws IOException;
		
		/**
		 * 生成するフィルターがディレクトリに対して適用されるか
		 * @return true == ディレクトリが対象の場合 / false == 対象でない場合
		 */
		boolean isApplicableDirectory();
		
		/**
		 * 生成するフィルターがファイルに対して適用されるか
		 * @return true == ファイルが対象の場合 / false == 対象でない場合
		 */
		boolean isApplicableFile();
	}
	
	/**
	 * コンストラクタ
	 * @param file
	 * @param context
	 */
	protected FilteredFile(FileInterface file, Object context) {
		_file = file;
//		_context = context.getApplicationContext();
		_context = context;
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileResource#openStream()
	 */
	@Override
	public InputStream openStream() throws IOException {
		return _file.openStream();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileResource#getBuffer()
	 */
	@Override
	public ByteBuffer getBuffer() throws IOException {
		return _file.getBuffer();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileResource#getSize()
	 */
	@Override
	public long getSize() {
		return _file.getSize();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileResource#getType()
	 */
	@Override
	public Mime getType() {
		if (_mimetype == null) {
			_mimetype = FileResourceAbstract.getMimeType(this);
		}
		return _mimetype;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileResource#isSupportGZip()
	 */
	@Override
	public boolean isSupportGZip() {
		return _file.isSupportGZip();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileResource#openGZipStream()
	 */
	@Override
	public InputStream openGZipStream() throws IOException {
		return _file.openGZipStream();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileResource#gzipStreamLength()
	 */
	@Override
	public long gzipStreamLength() {
		return _file.gzipStreamLength();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileBaseInterface#getAbsolutePath()
	 */
	@Override
	public String getAbsolutePath() {
		return _file.getAbsolutePath();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FileBaseInterface o) {
		return _file.compareTo(o);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileInterface#getName()
	 */
	@Override
	public String getName() {
		return _file.getName();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileInterface#getFolder()
	 */
	@Override
	public FolderInterface getFolder() {
		return _file.getFolder();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileInterface#isDirectory()
	 */
	@Override
	public boolean isDirectory() {
		return _file.isDirectory();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileInterface#ready()
	 */
	@Override
	public boolean ready() {
		return _file.ready();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileInterface#prepare()
	 */
	@Override
	public boolean prepare() {
		return _file.prepare();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileInterface#toURL()
	 */
	@Override
	public URL toURL() throws MalformedURLException {
		return FileAbstract.toURL(this);
	}
	
}
