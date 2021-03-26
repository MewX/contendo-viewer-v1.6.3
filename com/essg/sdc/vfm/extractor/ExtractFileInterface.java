package com.essg.sdc.vfm.extractor;

import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.io.zip.DeflateInfo;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.vfm.IOHandlerInterface;

/**
 * 展開処理用の仮想ファイルインターフェイス
 * @author Mine
 */
public interface ExtractFileInterface {
	
	/**
	 * 入力ストリームをOpenします
	 * @param handler IOハンドラー
	 * @return 入力ストリーム
	 * @throws IOException
	 */
	InputStream openStream(IOHandlerInterface handler) throws IOException;
	
	/**
	 * バイトバッファを取得します
	 * @param handler IOハンドラー
	 * @return 取得したByteBuffer、このメソッドをサポートできない場合は null を返せます
	 * @throws IOException
	 * @see ByteBuffer()
	 */
	ByteBuffer getBuffer(IOHandlerInterface handler) throws IOException;
	
	/**
	 * ファイルを展開します
	 * 展開したファイルはIOハンドラーを通して出力されます。
	 * @param handler IOハンドラー
	 * @throws IOException
	 */
	void getFile(IOHandlerInterface handler) throws IOException;
	
	/**
	 * ファイルサイズを取得します
	 * @return ファイルサイズ
	 */
	long getSize(IOHandlerInterface handler);

	/**
	 * 圧縮時のファイルサイズを取得します
	 * @return ファイルサイズ
	 */
	long getCSize(IOHandlerInterface handler);
	
	/**
	 * ディレクトリか判定します
	 * @return true = ディレクトリ / false = ファイル
	 */
	boolean isDirectory(IOHandlerInterface handler);
	
	/**
	 * Deflate圧縮されているか判定します
	 * @param handler IOハンドラー
	 * @return true = Deflate圧縮されている / false = Deflate圧縮されていない
	 * @see #openDeflatedStream(IOHandlerInterface)
	 */
	boolean isDeflated(IOHandlerInterface handler);
	
	/**
	 * Deflate圧縮されたままの入力ストリームを取得します
	 * このメソッドをサポートできない場合はisDeflated()でtrueを返しません。
	 * @param handler IOハンドラー
	 * @return 取得したストリーム
	 * @throws IOException
	 * @see #isDeflated(IOHandlerInterface handler)
	 */
	InputStream openDeflatedStream(IOHandlerInterface handler) throws IOException;
	
	/**
	 * Deflate圧縮の情報を取得します
	 * このメソッドをサポートできない場合はisDeflated()でtrueを返しません。
	 * @param handler IOハンドラー
	 * @return Deflate圧縮の情報
	 * @throws IOException
	 * @see #isDeflated(IOHandlerInterface handler)
	 */
	DeflateInfo getDeflatedInfo(IOHandlerInterface handler) throws IOException;
}