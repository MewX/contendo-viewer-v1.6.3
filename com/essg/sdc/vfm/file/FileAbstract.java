package com.essg.sdc.vfm.file;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.essg.sdc.net.Mime;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.vfm.VirtualFileManager;
import com.essg.sdc.vfm.io.FileByteBuffer;

public abstract class FileAbstract extends FileResourceAbstract implements FileInterface {
	protected FolderInterface _folder;
	protected String _path;
	
	/**
	 * コンストラクタ
	 * @param folder
	 * @param path
	 */
	public FileAbstract(FolderInterface folder, String path) {
		_folder = folder;
		int s = 0;
		int e = path.length();
		if (path.startsWith("/")) s++; 
		if (path.endsWith("/")) e--; 
		_path = path.substring(s, e).intern();
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.file.FileResource#getBuffer()
	 */
	@Override
	public ByteBuffer getBuffer() throws IOException {
		return new FileByteBuffer(this);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#getName()
	 */
	public String getName() {
		return _path;
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#ready()
	 */
	@Override
	public boolean ready() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#prepare()
	 */
	@Override
	public boolean prepare() {
		return ready();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#getFolder()
	 */
	@Override
	public FolderInterface getFolder() {
		return _folder;
	}
	
	//public File getFile();
	
	//public long getSize();
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#getAbsolutePath()
	 */
	@Override
	public String getAbsolutePath() {
		return _folder.getAbsolutePath(_path);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#isDirectory()
	 */
//	@Override
//	public boolean isDirectory() {
//		return false;
//	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FileBaseInterface o) {
		if (o instanceof FileInterface){
			FileInterface f = (FileInterface)o;
			int c = getFolder().compareTo(f.getFolder());
			if (c == 0) {
				if (o instanceof FileAbstract) {
					c = _path.compareTo(((FileAbstract)o)._path);
				} else {
					c = getAbsolutePath().compareTo(f.getAbsolutePath());
				}
			}
			return c;
		}
		return getAbsolutePath().compareTo(o.getAbsolutePath());
	}
	
	/**
	 * @param file
	 * @return
	 */
	protected static Mime getType(FileInterface file) {
		Mime mimetype = null;
		if (file.isDirectory()) {
			mimetype = VirtualFileManager.TYPE_DIRECTORY;
		} else {
			try {
				URL url = file.toURL();
				mimetype = Mime.getFirst(Mime.getMimeType(url));
			} catch (IOException e) {
			}
			if (mimetype == null) {
				mimetype = VirtualFileManager.TYPE_UNKNOWN;
			}
		}
		return mimetype;
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#getType()
	 */
	public Mime getType() {
		if (_mimetype == null) {
			_mimetype = FileAbstract.getType(this);
		}
		return _mimetype;
	}
	
	/**
	 * @param file
	 * @return
	 * @throws MalformedURLException
	 */
	protected static URL toURL(FileInterface file) throws MalformedURLException {
		return new URL("vfile", "localhost", 0, file.getAbsolutePath(), new FileStreamHandler(file));
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FileInterface#toURL()
	 */
	@Override
	public URL toURL() throws MalformedURLException {
		return FileAbstract.toURL(this);
	}
	
}