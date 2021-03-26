package com.essg.sdc.vfm.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import com.essg.sdc.io.util.StreamUtil;
import com.essg.sdc.nio.ByteBuffer;

public class PhysicalFileInfo extends FileAbstract {
	protected File _file;

	/**
	 * @param folder フォルダ
	 * @param path フォルダ以下の仮想パス
	 * @param file ファイルの実体のパス
	 * @throws FileNotFoundException
	 */
	public PhysicalFileInfo(FolderInterface folder, String path, File file) throws FileNotFoundException {
		super(folder, path);
		_file = file;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#openStream()
	 */
	@Override
	public InputStream openStream() throws IOException {
		return StreamUtil.openInputStream(_file);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileResource#getBuffer()
	 */
	public ByteBuffer getBuffer() throws IOException {
		return ByteBuffer.NEW(new RandomAccessFile(_file, "r"));
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileInterface#getSize()
	 */
	@Override
	public long getSize() {
		return _file.length();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileAbstract#isDirectory()
	 */
	@Override
	public boolean isDirectory() {
		return _file.isDirectory();
	}
}