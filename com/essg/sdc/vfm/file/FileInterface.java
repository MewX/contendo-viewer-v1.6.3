package com.essg.sdc.vfm.file;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 仮想ファイルインターフェイス
 * @author ess0083
 */
public interface FileInterface extends FileResource, FileBaseInterface {
	
	/**
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * 所属するフォルダを取得します
	 * @return フォルダ
	 */
	FolderInterface getFolder();
	
	/**
	 * ディレクトリか判定します
	 * @return true = ディレクトリ / false = ファイル
	 */
	boolean isDirectory();
	
	/**
	 * 即読取可能な状態か確認します
	 *  
	 * @return
	 */
	boolean ready();

	/**
	 * 読取可能な状態の準備をします
	 * @return
	 */
	boolean prepare();
	
	/**
	 * @return
	 * @throws MalformedURLException 
	 */
	URL toURL() throws MalformedURLException;
}