package com.essg.sdc.vfm.socket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ソケット通信サーバ（ファイルインターフェイス）
 * バイト配列データをソケット通信クライアントに送信します。
 * @author Mine
 */
public class ByteArraySocketServer extends SocketServer {
	byte[] _buf;
	int _offset;
	int _length;

	/**
	 * コンストラクタ
	 * @param buf バイト配列データ
	 * @param port ソケットポート番号
	 * @throws IOException
	 */
	public ByteArraySocketServer(byte[] buf, int port) throws IOException {
		this(buf, 0, buf.length, port);
	}

	/**
	 * コンストラクタ
	 * @param buf バイト配列データ
	 * @param offset オフセット
	 * @param length 長さ
	 * @param port ソケットポート番号
	 * @throws IOException
	 */
	public ByteArraySocketServer(byte[] buf, int offset, int length, int port) throws IOException {
		super(port);
		_buf = buf;
		_offset = offset;
		_length = length;
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.ContentServer#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(_buf, _offset, _length);
	}
}
