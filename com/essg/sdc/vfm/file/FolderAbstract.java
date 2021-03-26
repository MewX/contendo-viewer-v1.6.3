package com.essg.sdc.vfm.file;

import java.io.File;
import java.util.ArrayList;

public abstract class FolderAbstract implements FolderInterface {
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FileBaseInterface o) {
		return getAbsolutePath().compareTo(o.getAbsolutePath());
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FolderInterface#getList(com.essg.sdc.android.bookreader.contents.FileInterface)
	 */
	@Override
	public String[] getList() {
		return getList("", true);
	}

	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FolderInterface#getList(com.essg.sdc.android.bookreader.contents.FileInterface)
	 */
	@Override
	public String[] getList(FileInterface file, boolean subdir) {
		return getList(file == null ? "" : file.getName(), subdir);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileBaseInterface#getAbsolutePath()
	 */
	@Override
	public String getAbsolutePath() {
		return getAbsolutePath(null);
	}

	/**
	 * @param path
	 * @return
	 */
	protected String[] separatePath(String path) {
		if (path == null  || path.length() == 0) return new String[0];
		return separatePath(new File(path));
	}
	
	/**
	 * @param f
	 * @return
	 */
	protected String[] separatePath(File f) {
		ArrayList<String> list = new ArrayList<String>();
		while (f != null) {
			list.add(f.getName());
			f = f.getParentFile();
		}

		int i = list.size();
		String[] tmp = new String[i];
		for (String n : list) tmp[--i] = n;
		
		return tmp;
	}
}
