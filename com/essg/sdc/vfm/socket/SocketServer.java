package com.essg.sdc.vfm.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.essg.sdc.io.util.StreamUtil;

/**
 * ソケット通信サーバ（基底クラス）
 * @author Mine
 */
public abstract class SocketServer extends Thread {
	// ログ出力
	private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

	private ServerSocket _server = null;
	private Socket _sock = null;
	private boolean _ready = false;

	/**
	 * コンストラクタ
	 * @throws IOException
	 */
	public SocketServer() throws IOException {
		this(0);
	}

	/**
	 * コンストラクタ
	 * @param port ソケットポート番号
	 * @throws IOException
	 */
	public SocketServer(int port) throws IOException {
		_server = new ServerSocket(port);
		logger.debug("ポート番号 = " + getPort());
	}

	/**
	 * ポート番号取得
	 * @return ソケットポート番号
	 */
	public int getPort() {
		return _server != null ? _server.getLocalPort() : -1;
	}

	/**
	 * 入力ストリーム取得
	 * @return　入力ストリーム
	 * @throws IOException
	 */
	public abstract InputStream getInputStream() throws IOException;

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			//TODO 非同期IOの実装を行いたい
			logger.debug("接続待機中(" + getPort() + ")");
			_sock = _server.accept();
			logger.debug("接続されました(" + getPort() + ") : Remote=" + _sock.getRemoteSocketAddress());
			BufferedInputStream bis = StreamUtil.wrapBufferedInputStream(getInputStream());
			BufferedOutputStream bos = StreamUtil.wrapBufferedOutputStream(_sock.getOutputStream());

			_ready = true;
			StreamUtil.copy(bis, bos);
			StreamUtil.close(bos);
			StreamUtil.close(bis);
			_sock.close();
			logger.trace("run() - 終了");
		} catch (IOException e) {
			logger.error("run()", e);
		} finally {
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	@Override
	public synchronized void start() {
		logger.debug("Thread start");
		super.start();
	}

	/**
	 * 送信準備が出来ているか確認します
	 * @return true = 準備完了 / false = 準備中
	 */
	public boolean ready() {
		return _ready;
	}

	/**
	 * このサーバーに接続するソケットを取得します
	 *  （このメソッドは内部から呼び出してはいけません）
	 * @param millisec Timeoutまでの時間（ミリ秒）
	 * @return 取得したソケット
	 * @throws IOException
	 */
	public Socket getSocket(long millisec) throws IOException {
		Socket sock = new Socket();
		if (getState() == State.NEW) {
			start();
		}
		if (getState() == State.TERMINATED) {
			throw new IllegalStateException();
		}

		long start = System.currentTimeMillis();
		long expire = millisec + start;
		sock.connect(_server.getLocalSocketAddress());
		try {
			while (!ready()) {
				if (System.currentTimeMillis() > expire) {
					throw new TimeoutException();
				}
				Thread.sleep(10);
			}
		} catch (Exception e) {
			try {
				sock.close();
			} catch (Exception e2) {
			}
			if (e instanceof IOException) throw (IOException)e;
			throw new IOException(e.toString());
		}
		return sock;
	}
}
