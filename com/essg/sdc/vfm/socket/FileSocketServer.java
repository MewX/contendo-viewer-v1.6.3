package com.essg.sdc.vfm.socket;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.io.util.StreamUtil;

/**
 * ソケット通信サーバ（ファイル）
 * ソケット通信クライアントへファイルを送信します。
 * @author Mine
 */
public class FileSocketServer extends SocketServer {
	private final File _file;
	
	/**
	 * コンストラクタ
	 * @param file　送信対象のファイル
	 * @param port ソケットポート番号
	 * @throws IOException
	 */
	public FileSocketServer(File file, int port) throws IOException {
		super(port);
		_file = file;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.socket.ContentServer#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return StreamUtil.openInputStream(_file);
	}
}
