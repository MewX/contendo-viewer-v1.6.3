package com.essg.sdc.vfm.file;

import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.net.Mime;
import com.essg.sdc.nio.ByteBuffer;

/**
 * 仮想ファイルインターフェイス
 * @author ess0083
 */
public interface FileResource {
	
	/**
	 * 入力ストリームをOpenします
	 *  open()及びgetFile()を使用せずに、このmethodを使用することを推奨します。
	 * @return
	 * @throws IOException
	 */
	InputStream openStream() throws IOException;
	
	/**
	 * 入力データのバッファを取得します
	 * @return 取得したバイトバッファ
	 * @throws IOException
	 */
	ByteBuffer getBuffer() throws IOException;
	
	/**
	 * ファイルサイズを取得します
	 * @return ファイルサイズ
	 */
	long getSize();

	/**
	 * MIMEタイプを取得します
	 * @return
	 */
	Mime getType();
	
	/**
	 * 元のファイルがDeflateアルゴリズムにより圧縮されているか、または高速にDeflateが可能かを取得します。
	 * trueを返した場合、openGZipStreamメソッドでGzip形式(Deflate)を取得できなければなりません。
	 * 
	 * @return true=Deflateまたは高速にDeflate可能, false=Deflateでは無い
	 */
	boolean isSupportGZip();
	
	/**
	 * Deflateされた入力ストリームをGzip形式でOpenします
	 * @return
	 * @throws IOException
	 */
	InputStream openGZipStream() throws IOException;
	
	/**
	 * @return
	 */
	long gzipStreamLength();
}