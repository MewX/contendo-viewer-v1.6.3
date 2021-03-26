package com.essg.sdc.vfm.file;

public interface FileBaseInterface extends Comparable<FileBaseInterface> {
	/**
	 * ファイルの絶対パスを取得します
	 * @return
	 */
	String getAbsolutePath();
}
