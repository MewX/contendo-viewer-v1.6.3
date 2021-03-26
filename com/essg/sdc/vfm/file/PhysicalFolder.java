package com.essg.sdc.vfm.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ess0083
 *　実ファイルシステム上の物理ディレクトリを仮想フォルダとして扱います。
 */
public class PhysicalFolder extends FolderAbstract {
	protected File _dir;
	int _folder_path_len;
	
	/**
	 * コンストラクタ
	 * @param dir 物理ディレクトリ
	 */
	public PhysicalFolder(File dir) {
		_dir = dir;
		_folder_path_len = _dir.getAbsolutePath().length();
		if (!_dir.getAbsolutePath().endsWith(File.separator)) {
			_folder_path_len++;
		}
	}
	
	/**
	 * 元となっている物理ディレクトリを取得します
	 * @return
	 */
	protected File getDirectory() {
		return _dir;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FolderInterface#getFile(java.lang.String)
	 */
	public FileInterface getFile(String path) throws FileNotFoundException {
		return new PhysicalFileInfo(this, path, new File(_dir, path));
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FolderInterface#abandon()
	 */
	@Override
	public void abandon() {
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FolderInterface#getAbsolutePath(java.lang.String)
	 */
	@Override
	public String getAbsolutePath(String path) {
		if (path == null || path.length() == 0) {
			return "";
		}
		return path;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FolderInterface#getList(java.lang.String)
	 */
	@Override
	public String[] getList(String path, boolean subdir) {
		List<String> list = getListImp(new ArrayList<String>(), _dir, separatePath(path), 0, subdir);
		return list.toArray(new String[list.size()]);
	}
	
	/**
	 * @param list
	 * @param dir
	 * @param path
	 * @param depth
	 * @return
	 */
	protected List<String> getListImp(List<String> list, File dir, String[] path, int depth, boolean subdir) {
		if (path.length > depth) {
			return getListImp(list, new File(dir, path[depth]), path, depth + 1, subdir);
		}
		
		File[] files = dir.listFiles();
		for (File f : files) {
			if (subdir && f.isDirectory()) {
				list = getListImp(list, f, path, depth + 1, subdir);
			} else {
				list.add(f.getAbsolutePath().substring(_folder_path_len));
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.file.FolderInterface#getParent()
	 */
	@Override
	public FileInterface getParent() {
		return null;
	}
}