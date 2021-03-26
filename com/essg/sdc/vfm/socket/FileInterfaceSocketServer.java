package com.essg.sdc.vfm.socket;

import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.vfm.file.FileInterface;

/**
 * ソケット通信サーバ（ファイルインターフェイス）
 * ファイルインターフェイスを使用してソケット通信クライアントへファイルを送信します。
 * @author Mine
 */
public class FileInterfaceSocketServer extends SocketServer {
	private final FileInterface _file;
	
	/**
	 * コンストラクタ
	 * @param file　送信対象のファイルインターフェイス
	 * @param port ソケットポート番号
	 * @throws IOException
	 */
	public FileInterfaceSocketServer(FileInterface file, int port) throws IOException {
		super(port);
		_file = file;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.socket.ContentServer#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return _file.openStream();
	}
}
