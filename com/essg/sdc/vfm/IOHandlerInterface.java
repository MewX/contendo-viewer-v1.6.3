package com.essg.sdc.vfm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.essg.sdc.nio.ByteBuffer;

/**
 * @author ess0083
 *
 */
public interface IOHandlerInterface {
	
	/**
	 * アーカイブファイルへの入力ストリームを取得します
	 * @return アーカイブファイルへの入力ストリーム
	 * @throws FileNotFoundException
	 */
	public InputStream getInputStream() throws IOException;
	
	/**
	 * @return
	 * @throws IOException
	 */
	public ByteBuffer getInputBuffer() throws IOException;
	
	/**
	 * 入力ストリームを取得します
	 * @return アーカイブファイルへの入力ストリーム
	 * @throws FileNotFoundException
	 */
	public InputStream getInputStream(File file) throws IOException;
	
	/**
	 * @return
	 * @throws IOException
	 */
	public ByteBuffer getInputBuffer(File file) throws IOException;
	

	/**
	 * 出力ストリームを取得します
	 *  必要なディレクトリは自動的に作成されます。
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public OutputStream getOutputStream(File file) throws IOException;
	
	/**
	 * ディレクトリを作成します
	 *  実際のディレクトリはファイル出力時に処理されますので、ここでは情報のみの登録を行います。 
	 * @param file　作成するディレクトリのパス
	 * @throws FileNotFoundException パスが不正な場合、既に同名のファイルが存在する場合
	 */
	public void mkdir(File file) throws FileNotFoundException;

	/**
	 * ディレクトリを作成します
	 *  実際のディレクトリはファイル出力時に処理されますので、ここでは情報のみの登録を行います 。
	 * @param path 作成するディレクトリのパス
	 * @throws FileNotFoundException パスが不正な場合、既に同名のファイルが存在する場合
	 */
	public void mkdir(String path) throws FileNotFoundException;
	
	/**
	 * 出力中のストリームを破棄してクローズします
	 *  出力内容が不正な場合、中断の場合、closeの前に呼び出さなければいけません。
	 * @param stream 対象のOutputStream
	 */
	public void abortOutput(OutputStream stream);
	
	/**
	 * 出力中のストリームを破棄してクローズします
	 *  出力内容が不正な場合、中断の場合、closeの前に呼び出さなければいけません。
	 * @param file　対象のPathを含むFile
	 */
	public void abortOutput(File file);
	
	/**
	 * 出力中のストリームを破棄してクローズします
	 *  出力内容が不正な場合、中断の場合、closeの前に呼び出さなければいけません。
	 * @param path 対象のPath
	 */
	public void abortOutput(String path);
	
	/**
	 * ストリームをファイルに出力します
	 *  コピー終了後、ストリームのクローズは行いません。
	 * @param istream
	 * @param opath
	 */
	public void copyStream(InputStream istream, String opath) throws IOException;

	/**
	 * ストリームをファイルに出力します
	 *  コピー終了後、ストリームのクローズは行いません。
	 * @param istream
	 * @param ofile
	 */
	public void copyStream(InputStream istream, File ofile) throws IOException;

	/**
	 * ストリーム間でコピーを行います
	 *  コピー終了後、ストリームのクローズは行いません。
	 * @param istream
	 * @param ostream
	 */
	public void copyStream(InputStream istream, OutputStream ostream) throws IOException;

	/**
	 * 全ての処理を中断して、資源を解放します 
	 */
	public void abandon();
}