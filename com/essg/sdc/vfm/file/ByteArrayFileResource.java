package com.essg.sdc.vfm.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.nio.ByteBuffer;

public class ByteArrayFileResource extends FileResourceAbstract {
	byte[] buf;
	
	/**
	 * コンストラクタ
	 * @param b
	 */
	public ByteArrayFileResource(byte[] b) {
		buf = b;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#openStream()
	 */
	@Override
	public InputStream openStream() throws IOException {
		return new ByteArrayInputStream(buf);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#getFile()
	 */
//	@Override
//	public File getFile() {
//		return null;
//	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#getSize()
	 */
	@Override
	public long getSize() {
		return buf.length;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#getBuffer()
	 */
	@Override
	public ByteBuffer getBuffer() throws IOException {
		return ByteBuffer.wrap(buf);
	}

}
