package com.essg.sdc.vfm.file;

import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.nio.stream.ByteBufferInputStream;

public class ByteBufferFileResource extends FileResourceAbstract {
	ByteBuffer buf;
	
	/**
	 * コンストラクタ
	 * @param b
	 */
	public ByteBufferFileResource(java.nio.ByteBuffer b) {
		buf = ByteBuffer.fromNIO(b);
	}

	/**
	 * コンストラクタ
	 * @param b
	 */
	public ByteBufferFileResource(ByteBuffer b) {
		buf = b.duplicate();
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#openStream()
	 */
	@Override
	public InputStream openStream() throws IOException {
		return new ByteBufferInputStream(buf.duplicate());
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
		return buf.limit();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#getBuffer()
	 */
	@Override
	public ByteBuffer getBuffer() throws IOException {
		return buf.duplicate();
	}

}
