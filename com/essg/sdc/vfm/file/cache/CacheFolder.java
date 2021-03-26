package com.essg.sdc.vfm.file.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.essg.sdc.io.util.FileUtil;
import com.essg.sdc.vfm.file.FileInterface;
import com.essg.sdc.vfm.file.PhysicalFolder;
import com.essg.sdc.vfm.file.WriteableFileInterface;

/**
 * @author ess0083
 *
 */
public class CacheFolder extends PhysicalFolder {
	
	private final String name;
	private final ArrayList<File> list = new ArrayList<File>();
	
	/**
	 * @param cacheDir
	 */
	public CacheFolder(File cacheDir) {
		super(cacheDir);
		name = String.format("%h", this);
	}
	
	/**
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public WriteableFileInterface getWriteableFile(String path) throws FileNotFoundException {
		path = name + "." + String.format("%h", path);
		if (!getDirectory().exists()) {
			getDirectory().mkdirs();
		}
		File f = new File(getDirectory(), path);
		list.add(f);
		return new CacheFile(this, path, f);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.PhysicalFolder#getFile(java.lang.String)
	 */
	@Override
	public FileInterface getFile(String path) throws FileNotFoundException {
		return getWriteableFile(path);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.PhysicalFolder#abandon()
	 */
	@Override
	public synchronized void abandon() {
		erase();
		super.abandon();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		try {
			erase();
		} finally {
			super.finalize();
		}
	}
	
	/**
	 * 
	 */
	private void erase() {
		if (list != null) {
			while (!list.isEmpty()) {
				FileUtil.removeFile(list.remove(0));
			}
		}
	}
	
	
}
