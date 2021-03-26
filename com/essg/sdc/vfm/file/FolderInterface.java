package com.essg.sdc.vfm.file;

import java.io.FileNotFoundException;

/**
 * フォルダーインターフェイスは複数のファイルインターフェイスを包括した、仮想的なファイルシステムを構築します
 * 
 * @author ess0083
 */
public interface FolderInterface extends FileBaseInterface {
	/**
	 * 指定されたPathに該当するファイルインターフェイスを取得します
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public FileInterface getFile(String path) throws FileNotFoundException;

	/**
	 * 保持している情報を破棄します（呼出し後は再使用できません）
	 */
	public void abandon();

	/**
	 * 指定されたPathを含む絶対Pathを取得します
	 * @param path
	 * @return
	 */
	public String getAbsolutePath(String path);

	/**
	 * 全てのメンバのPathのリストを取得します
	 * @return
	 */
	public String[] getList();
	
	/**
	 * 指定されたPathを含むメンバへのPathのリストを取得します
	 * @param file
	 * @return
	 */
	public String[] getList(FileInterface file, boolean subdir);

	/**
	 * 指定されたPathを含むメンバへのPathのリストを取得します
	 * @param path
	 * @return
	 */
	public String[] getList(String path, boolean subdir);

	public FileInterface getParent();
}