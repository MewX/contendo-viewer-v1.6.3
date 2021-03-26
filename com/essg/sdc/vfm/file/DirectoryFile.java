package com.essg.sdc.vfm.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.nio.ByteBuffer;

public class DirectoryFile extends FileAbstract {
	
	/**
	 * コンストラクタ
	 * @param folder
	 * @param path
	 */
	public DirectoryFile(FolderInterface folder, String path) {
		super(folder, path);
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#openStream()
	 */
	@Override
	public InputStream openStream() throws IOException {
		throw new FileNotFoundException();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#getFile()
	 */
//	@Override
//	public File getFile() {
//		return null;
//	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#getBuffer()
	 */
	@Override
	public ByteBuffer getBuffer() throws IOException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#getSize()
	 */
	@Override
	public long getSize() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileAbstract#isDirectory()
	 */
	@Override
	public boolean isDirectory() {
		return true;
	}
}
