package com.essg.sdc.vfm.file;

import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.net.Mime;
import com.essg.sdc.vfm.VirtualFileManager;

public abstract class FileResourceAbstract implements FileResource {
	protected Mime _mimetype = null;
	
	/**
	 * @param file
	 * @return
	 */
	public static Mime getMimeType(FileResource file) {
		Mime mimetype = null;
		try {
			mimetype = Mime.getFirst(Mime.getMimeType(file.openStream()));
		} catch (IOException e) {
		}
		if (mimetype == null) {
			mimetype = VirtualFileManager.TYPE_UNKNOWN;
		}
		return mimetype;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#getType()
	 */
	@Override
	public Mime getType() {
		if (_mimetype == null) {
			synchronized (this) {
				if (_mimetype == null) {
					_mimetype = getMimeType(this);
				}
			}
		}
		return _mimetype;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#isDeflated()
	 */
	@Override
	public boolean isSupportGZip() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#openDeflatedStream()
	 */
	@Override
	public InputStream openGZipStream() throws IOException {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#gzipStreamLength()
	 */
	public long gzipStreamLength() {
		return -1;
	}
}
