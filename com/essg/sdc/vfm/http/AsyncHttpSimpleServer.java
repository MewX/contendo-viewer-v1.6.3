package com.essg.sdc.vfm.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.essg.sdc.io.util.StreamUtil;
import com.essg.sdc.net.Base64;
import com.essg.sdc.nio.charset.CharSetUtils;
import com.essg.sdc.vfm.file.FileResource;
import com.essg.sdc.vfm.http.HttpRangeSpec.Range;
import com.essg.sdc.vfm.http.exceptions.HttpForbiddenException;
import com.essg.sdc.vfm.http.exceptions.HttpNotFoundException;
import com.essg.sdc.vfm.http.exceptions.HttpServerException;


/**
 * @author Mine
 */
public class AsyncHttpSimpleServer extends VfmHttpServer {

	private static final String SESSION_KEY = "_HttpContentServer_session";
	private static final int QPARM_PASSPORT_LEN = QPARM_PASSPORT.length();

	private static final int TIMEOUT = 60;
	private static final int MAX_CONTINE = 200;
	private static final int AUTH_TIMEOUT = 30000;
	private static final int BUFF_SIZE = 1024;
	private static final int MAX_CONNECTION_CACHE = 3;
	private static final int WRITE_BUFF_PER_CONN = 4;
	private static final int BUF_POOL_SIZE = 8;
	
	protected static final Logger logger = LoggerFactory.getLogger(AsyncHttpSimpleServer.class);
	protected static final Queue<Object> _buf_pool = new ConcurrentLinkedQueue<Object>();
	
//	int _port;
	private String _auth_realm = "HOGEHOGE";
//	private final HttpAuthorizer _authorizer;
	
	private final LinkedList<ConnectionThread> cacheConnection = new LinkedList<ConnectionThread>();
	private final ExecutorService executor = Executors.newCachedThreadPool();
	private ServerSocketChannel _svrChannel;
	private int _localPortNo = -1;
	
	/**
	 * コンストラクタ
	 * 自動的にポート番号を割り当てて認証無しでサーバを構成します。
	 * @param resolver　デフォルトのファイル参照を解決に使用するリゾルバー
	 * @throws IOException　ソケットの生成が出来ませんでした
	 */
	public AsyncHttpSimpleServer(HttpFileResolver resolver) throws IOException {
		this(resolver, 0, null);
	}

	/**
	 * コンストラクタ
	 * ポート番号を指定して認証無しでサーバを構成します。
	 * @param resolver デフォルトのファイル参照を解決に使用するリゾルバー
	 * @param port TCPポート番号、0を指定すると自動割り振り
	 * @throws IOException　ソケットの生成が出来ませんでした
	 */
	public AsyncHttpSimpleServer(HttpFileResolver resolver, int port) throws IOException {
		this(resolver, port, null);
	}

	/**
	 * コンストラクタ
	 * ポート番号を指定して認証有りでサーバを構成します。
	 * @param resolver デフォルトのファイル参照を解決に使用するリゾルバー
	 * @param port TCPポート番号、0を指定すると自動割り振り
	 * @param authorizer 認証オブジェクトを指定します。nullを指定する認証無しとなります。
	 * @throws IOException　ソケットの生成が出来ませんでした
	 */
	public AsyncHttpSimpleServer(HttpFileResolver resolver, int port, HttpAuthorizer authorizer) throws IOException {
		super(resolver, port, authorizer);
		_auth_realm = "Basic realm=\"" + _auth_realm  + "\"";
		_svrChannel = createServerSocket(_port);
	}

	/**
	 * 非同期サーバーソケットチャネルを生成します
	 * @param port
	 * @return
	 * @throws IOException
	 */
	protected ServerSocketChannel createServerSocket(int port) throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		InetSocketAddress endpoint = new InetSocketAddress(LOCALHOST, port); 
		channel.socket().bind(endpoint);
		return channel;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#getPort()
	 */
	@Override
	public int getPort() {
		if (_localPortNo < 0 && _svrChannel != null) {
			_localPortNo = _svrChannel.socket().getLocalPort();
		}
		return _localPortNo;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#getUri()
	 */
	@Override
	public URI getUri() {
		return URI.create(getScheme() + "://" + getHostName() + ":" + getPort() +"/");
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#getScheme()
	 */
	@Override
	public String getScheme() {
		return "http";
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#getHostName()
	 */
	@Override
	public String getHostName() {
		return LOCALHOST;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#getRealm()
	 */
	@Override
	public String getRealm() {
		return _auth_realm;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.VfmHttpServer#execService()
	 */
	@Override
	public void execService() throws Exception {
		try {
			// x86版 Androidエミュレータでエラーになるのでエラー時はIPv6をDisableしてリトライします
			Selector selector = null;
			for (int i = 0; i < 2; i++) {
				try {
					selector = Selector.open();
					break;
				} catch (Exception e) {
					if (i > 0) {
						throw e;
					}
					System.setProperty("java.net.preferIPv6Addresses", "false");
				}
			}
			_svrChannel.register(selector, SelectionKey.OP_ACCEPT);
			
			// 接続待ちして、接続要求があれば子スレッドを作成して処理を行います
			while (!interrupted() && selector.select() > 0) {
				Iterator<SelectionKey> itSelection = selector.selectedKeys().iterator();
				while (itSelection.hasNext()) {
					SelectionKey selKey = itSelection.next();
					itSelection.remove();
					
					if (selKey.isAcceptable()) {
						SocketChannel channel = _svrChannel.accept();
						if (COMMUNICATE_LOG)
							logger.info("接続されました(" + getPort() + ") : Remote=" + channel.socket().getRemoteSocketAddress());

						// スレッド作成
						connect(channel);
					}
				}
			}
		} finally {
			if (_svrChannel != null && _svrChannel.isOpen()) {
				StreamUtil.close(_svrChannel);
			}
			if (!executor.isShutdown()) executor.shutdown();
		}
	}
	
	protected void connect(SocketChannel channel) throws IOException {
		ConnectionThread thread = null;
		if (!cacheConnection.isEmpty()) {
			synchronized (cacheConnection) {
				if (!cacheConnection.isEmpty()) thread = cacheConnection.removeFirst();
			}
		}
		if (thread == null) thread = new ConnectionThread();
		thread.init(this, channel);
		executor.execute(thread);
	}
	
	protected void recycle(ConnectionThread thread) {
		if (cacheConnection.size() < MAX_CONNECTION_CACHE) {
			synchronized (cacheConnection) {
				cacheConnection.add(thread);
			}
		}
	}
	
	protected ConnectionThread newConnectionThread() {
		return new ConnectionThread();
	}

	/**
	 * バッファリング処理の為のByteBufferを取得します
	 * @return
	 */
	public static ByteBuffer obtainByteBuffer() {
		ByteBuffer buf = null;
		while (buf == null) {
			Object obj = _buf_pool.poll();
			if (obj == null) break;
			if (obj instanceof ByteBuffer) {
				buf = (ByteBuffer)obj;
			} else if (obj instanceof Reference) {
				obj = ((Reference<?>) obj).get();
				if (obj instanceof ByteBuffer) {
					buf = (ByteBuffer)obj;
				}
			}
		} 
		if (buf == null) {
//			logger.warn("Allocate new buffer");
			buf = ByteBuffer.allocate(BUFF_SIZE);
		}
		return buf;
	}

	public static void recycleByteBuffer(Collection<ByteBuffer> bufs) {
		for (ByteBuffer buf : bufs) recycleByteBuffer(buf);
	}
	
	public static void recycleByteBuffer(ByteBuffer buf) {
		if (buf != null && buf.capacity() == BUFF_SIZE) {
			buf.clear();
			if (_buf_pool.size() < BUF_POOL_SIZE) {
				_buf_pool.add(buf);
			} else {
				_buf_pool.add(new WeakReference<ByteBuffer>(buf));
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#start()
	 */
	@Override
	public synchronized void start() {
		logger.info("Thread start");
		super.start();
	}

	static class Request implements Runnable {
		ConnectionThread _conn = null;
//		private final LinkedList<ByteBuffer> listBuffer = new LinkedList<ByteBuffer>();
		private final LinkedBlockingQueue<ByteBuffer> writeQueue = new LinkedBlockingQueue<ByteBuffer>(WRITE_BUFF_PER_CONN);
		// REQUEST を格納する map
		private final HashMap<String, String> _request = new HashMap<String, String>();
		private final HashMap<String,String> _cookies = new HashMap<String,String>();
		
		// コマンド文字列を保持
		private String _command = null;
		
		// クライアントバージョン文字列を保持
		private String _client_version = null;
		
		// クライアントのHTTPバージョンが1.1以上かを判断
		private boolean _client_ver_1_1;
		
		private String _referer = null;

		int _remain = 0;
		boolean keep_alive = true;
		boolean _support_gzip = false;
		Session _session = null;
		private boolean connectionClose;
		boolean bAuth = false;
		
		static class ResponseStream extends OutputStream {
			private static final String CLOSED_EXCPTION_MSG = "OutputStream is already closed.";
			Request _req;
			ByteBuffer _buf = null;
			boolean _closed = false;
			
			/**
			 * @param req
			 */
			ResponseStream(Request req) {
				_req = req;
			}

			/**
			 * @param b
			 * @throws IOException
			 */
			@Override
			public void write(int b) throws IOException {
				enforce();
				_buf.put((byte)b);
			}

			/**
			 * @param b
			 * @param off
			 * @param len
			 * @throws IOException
			 */
			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				enforce();
				while (len > 0) {
					if (_buf == null) {
						_buf = obtainByteBuffer();
					}
					int r = _buf.remaining();
					int l = Math.min(len, r);
					if (l > 0) {
						_buf.put(b, off, l);
					}
					if (r == l) flush();
					off += l;
					len -= l;
				}
			}
			
			/**
			 * @throws IOException
			 */
			private void enforce() throws IOException {
				if (_closed) throw new IOException(CLOSED_EXCPTION_MSG);
				if (_buf == null) {
					_buf = obtainByteBuffer();
				} else if (!_buf.hasRemaining()) {
					flush();
					_buf = obtainByteBuffer();
				}
			}

			/* (non-Javadoc)
			 * @see java.io.OutputStream#flush()
			 */
			@Override
			public void flush() throws IOException {
				flush(false);
			}

			/**
			 * @param close
			 * @throws IOException
			 */
			private void flush(boolean close) throws IOException {
				if (_closed) throw new IOException(CLOSED_EXCPTION_MSG);
				try {
					if (_buf != null) {
						_buf.flip();
						if (_buf.hasRemaining()) {
							_req.write(_buf);
							_buf = null;
						} else {
							_buf.clear();
						}
					}
				} finally {
					if (close) {
						_closed = true;
						
						// EOFのマークとして長さ０のバッファを書き込みます
						if (_buf == null) {
							_buf = ByteBuffer.wrap(new byte[0]);
						}
						_buf.limit(0);
						_req.write(_buf);
					}
				}
			}
			
			/* (non-Javadoc)
			 * @see java.io.OutputStream#close()
			 */
			@Override
			public void close() throws IOException {
				flush(true);
				_buf = null;
				_req = null;
			}
			
		}

		void term() {
			_conn = null;
			_request.clear();
			_cookies.clear();
			_command = null;
			_client_version = null;
			_referer = null;
			_support_gzip = false;
			_session = null;
			keep_alive = true;
			for (ByteBuffer buf; (buf = writeQueue.poll()) != null;) {
				recycleByteBuffer(buf);
			}
		}

		void requestToMap(String buf) {
			// 最初のテキストはコマンド行
			if (_command == null) {
				_command = buf;
				int n = _command.indexOf(HTTP_VER);
				_client_ver_1_1 = false;
				if (n >= 0) {
					_client_version = _command.substring(n + HTTP_VER.length());
					if (_client_version.compareTo("1.1") >= 0) {
						_client_ver_1_1 = true;
					}
				} else {
					_client_version = "1.0";	//TODO 無ければ1.0とみなして良いのか・・・
				}
			} else {
				int n = buf.indexOf(":");
				String key = buf;
				String value = "";
				if (n >= 0) {
					key = buf.substring(0, n).trim();
					value = buf.substring(n + 1).trim();
				}

				// Cookieを処理します
				if (COOKIE.equals(key)) {
    				if (value != null) {
						String[] wk = value.split("[;,]");
						for (String s : wk) {
							n = s.indexOf("=");
							String k = s.substring(0, n).trim().intern();
							String v = s.substring(n + 1).trim().intern();
    						_cookies.put(k, v);
						}
    				}
				} else {
					// その他の値はMapに格納します
					_request.put(key.intern(),  value);
				}
			}
		}
		
		String clientVersion() {
			return _client_version;
		}
		
		boolean keepAlive() {
			String value = _request.get(CONNECTION);
			if (_client_ver_1_1) {
				if (value != null) {
					if (value.toLowerCase().indexOf(CLOSE) >= 0) {
    					keep_alive = false;
					}
				}
			} else {
				keep_alive = false;
			}
			return keep_alive;
		}

		void init(ConnectionThread conn, int remain) {
			_conn = conn;
			_remain = remain;

			if (_remain > 0 && keepAlive()) {
				connectionClose = false;
			} else {
				connectionClose = true;
			}

			String value;
			long currenttime = System.currentTimeMillis();
			
			// セッションもどき処理
			_session = conn.getSession();	// Keep-aliveの間は保存したセッションを有効にします
			long timeout = conn.getSessionTimeout();
			if (_session == null) {
				String agent = _request.get(USER_AGENT);
				if (_cookies.isEmpty()) {
				} else {
					String key = _cookies.get(SESSION_KEY);
					if (key != null) {
						synchronized (_sessions) {
							_session = _sessions.get(key);
							if (_session != null) {
								if (timeout > 0 && currenttime > _session._expired_time) {
									// 期限切れ
									if (COMMUNICATE_LOG)
										logger.warn("期限切れ");
									// 期限切れの場合はクリアします
									_sessions.remove(key);
									if (_session._passportRef != null)
										_session._passportRef.clear();
									_session = null;
								} else if (!agent.equals(_session._agent)) {
									// Agentからの接続の場合は新しいSessionとして扱います
									if (COMMUNICATE_LOG)
										logger.warn("Agentが異なります");
									_session = null;
								} else {
									if (COMMUNICATE_LOG)
										logger.warn("セッションが見つかりました key = " + key);
								}
							}
						}
						if (_session == null && COMMUNICATE_LOG)
							logger.warn("セッションが見つかりません key = " + key);
					}
				}
				if (_session == null) {
					// 新しいセッションを作成します
					_session = _conn.newSession(agent);
				}
			}
			// 他のスレッドから削除されない様にこのタイミングで延長を行います
			_session._expired_time = currenttime + timeout;
			
			_conn.saveSession(_session);

			// 認証情報判定（パスポートが無い場合は毎回判定します）
			bAuth = false;
			if (_session._passportRef != null) {
				Object passport = _session._passportRef.get();
				if (passport != null) {
					bAuth = true;
				}
			}
			if (!bAuth) {
				if (_session._authkey == null || _session._auth_expired_time < currenttime) {
    				value = _request.get(AUTHORIZATION);
    				if (value != null) {
    					_session._authkey = _conn.authorize(value);
    				}
				}
				if (_session._authkey != null) {
					bAuth = true;
					_session._auth_expired_time = currenttime + AUTH_TIMEOUT;
				}
			}
			
			value = _request.get(ACCEPT_ENCODING);
			if (value != null) {
				_support_gzip = (value.indexOf("gzip") >= 0);
			}

			_referer = _request.get(REFERER);
		}
		
		boolean isConnectionClose() {
			return connectionClose;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			OutputStream out = null;
			try {
				Response response = new Response();
	            out = new ResponseStream(this);
				
				response.setHeader(KEEP_ALIVE, String.format("timeout=%d, max=%d", TIMEOUT, _remain));
				if (connectionClose) {
					response.setHeader(CONNECTION, CLOSE);
				} else {
					response.setHeader(CONNECTION, KEEP_ALIVE);
				}
				
//				response.setHeader(SET_COOKIE, SESSION_KEY + "=" + session._id + "; expires=" + cookie_date_format.format(new Date(session._expired_time)) + "; path=/");
				response.setHeader(SET_COOKIE, SESSION_KEY + "=" + _session._id + "; ; path=/");

				// リクエストコマンド判定
	            if (_command.startsWith(GET)) {
	        		// パラメータで設定した場合の処理
	            	// （URL指定のサンプル）
	            	//  Uri uri = Uri.withAppendedPath(_manager.getHttpServerUri(), audiopath
					//   + "?passport=" + passport.toString();
	            	if (!bAuth && HTTP_AUTHORITY_ENABLE) {
	            		try {
		                	String[] p = _command.split(" ");
		                	String path = URLDecoder.decode(p[1], "utf-8");
		                	int n = path.lastIndexOf(QPARM_PASSPORT);
		                	if (n >= 0) {
		                		String id = path.substring(n + QPARM_PASSPORT_LEN);
								if (COMMUNICATE_LOG)
									logger.debug("パスポート検索 id=" + id);
		                		synchronized (_passport) {
	    	                		for (Passport pass : _passport.keySet()) {
										if (COMMUNICATE_LOG)
											logger.debug(id + " == " + pass._id);
	    	                			if (id.equals(pass._id)) {
	    	                				_session._passportRef = new WeakReference<Passport>(pass);
	    	                				bAuth = true;
											if (COMMUNICATE_LOG)
												logger.debug("パスポート検索 見つかりました");
	    	                				break;
	    	                			}
	    	                		}
		                		}
		                	}
	            		} catch (Exception e) {
	            		}
	            	}
	            	if (!bAuth && HTTP_AUTHORITY_ENABLE) {
	            		response.setStatus(STATUS_UNAUTHORIZED);
	            		response.setHeader(WWW_AUTHENTICATE, _conn.getRealm());
	            	} else {
	            		http_Get(response);
	            	}
	            } else if (_command.startsWith(HEAD)) {
	            	http_Head(response);
	            } else {
	            	response.setStatus(STATUS_METHOD_NOT_ALLOWED);
	            	response.setHeader(ALLOW, "GET, HEAD");
	            }
	            
	            try {
		            response.write(out);
	            } finally {
	            }
			} catch (Exception e) {
			} finally {
				if (out != null)
	            	StreamUtil.close(out);
			}
		}

		private void write(ByteBuffer buf) throws IOException {
//			logger.debug("Request.write() -start- " + buf);
			try {
				ConnectionThread conn = _conn;
				if (Thread.interrupted() && buf.hasRemaining()) throw new IOException("Aborted!!");
				try {
					while (!writeQueue.offer(buf, 500l, TimeUnit.MILLISECONDS)) {
						if (Thread.interrupted()) throw new IOException("Aborted!!");
					}
				} catch (Exception e) {
					throw new IOException(e.toString());
				} finally {
				}
				if (conn != null) {
					conn.notifyReadyData(this);
				}
			} finally {
//				logger.debug("Request.write() -end-");
			}
		}
		
		public ByteBuffer getNextBuffer() {
			ByteBuffer buf = writeQueue.poll();
//			logger.debug("Request.getNextBuffer() " + buf);
			return buf;
		}
		
		public boolean isWriteDataReady() {
			return !writeQueue.isEmpty();
		}

		/**
		 * HEADコマンド処理
		 * @param res
		 */
		private void http_Head(Response res) {
			res.setStatus(STATUS_OK);
			res.setHeader(MIME_VERSION, "1.0");
		}

		/**
		 * GETコマンド処理
		 * @param res
		 */
		private void http_Get(Response res) {
        	String[] p = _command.split(" ");
        	String pathSegment = p[1];
        	int n = pathSegment.lastIndexOf("?");
        	if (n >= 0) {
        		pathSegment = pathSegment.substring(0, n);
        	}

        	try {
            	String path = URLDecoder.decode(pathSegment.substring(1), "UTF-8");

            	// 機種（状況？）によっては Referer が空なので作成します
				if (_referer == null) {
    				if (_client_ver_1_1) {
    					String host = _request.get("Host");
    					_referer = "http://" + host + "/" + path;
    				} else {
    					_referer = "http://" + LOCALHOST + ":" + _conn.getPort() + "/" + path;
    				}
				}

            	FileResource file = _conn.resolveResource(_referer, path);
            	
            	// ファイルが見つからない場合はエラー
            	if (file == null) {
            		throw new HttpNotFoundException();
            	}
            	
            	// Sizeを取得します
            	long filesize = file.getSize();

            	// Range指定を判定します
            	try {
                	HttpRangeSpec range = HttpRangeSpec.getInstance(file, _support_gzip, _request.get(RANGE));
                	res.setHeader(CONTENT_TYPE, file.getType().toString());
                	res.setBody(range);
            	} catch (HttpException e) {
            		// Range指定は無しか１つのみしか許されていない
            		// Range指定内容が不正
            		res.setStatus(STATUS_RANGE_NOT_SATISFIABLE);
                	res.setHeader(CONTENT_RANGE, "*");
                	res.setHeader(CONTENT_LENGTH, String.valueOf(filesize));
            	}
        	} catch (HttpForbiddenException e) {
        		res.setStatus(STATUS_FORBIDDEN);
        	} catch (HttpNotFoundException e) {
        		res.setStatus(STATUS_NOT_FOUND);
        	} catch (Exception e) {
        		if (COMMUNICATE_LOG)
        			logger.warn("Get", e);
        		else
        			logger.warn("Get " + e.getClass().getSimpleName());
        		res.setStatus(STATUS_NOT_FOUND);
        	} finally {
        	}
		}
	}

	/**
	 *
	 * @author ess0083
	 */
	static class ConnectionThread implements Runnable {
		AsyncHttpSimpleServer _httpsvr = null;
		SocketChannel _channel = null;
		Selector _selector = null;
		private final Charset _charset;
		private final CharsetDecoder _decoder;
		
		private boolean _bSelectWrite = false;
		
		private ExecutorService executor = Executors.newSingleThreadExecutor();
		
		private final Queue<Request> _requestQueue = new LinkedList<Request>();
		
		private Session _saveSession = null;
		private Request _currentRequest;

		/**
		 * 
		 */
		public ConnectionThread() {
			_charset = Charset.forName("utf-8");
			_decoder = _charset.newDecoder();
		}

		/**
		 * @param session
		 */
		public void saveSession(Session session) {
			_saveSession = session;
		}
		
		/**
		 * @return
		 */
		public Session getSession() {
			return _saveSession;
		}
		
		/**
		 * @return
		 */
		public long getSessionTimeout() {
			return _httpsvr.getSessionTimeout();
		}
		
		/**
		 * @param svr
		 * @param channel
		 * @throws IOException
		 */
		public void init(AsyncHttpSimpleServer svr, SocketChannel channel) throws IOException {
			_httpsvr = svr;
			_channel = channel;
			_decoder.reset();
		}
		
		/**
		 * 
		 */
		public void term() {
            if (_selector != null) {
            	if (_selector.isOpen()) {
            		try { _selector.close(); } catch (Exception e){};
            	}
    			_selector = null;
            }
			if (_channel != null) {
				if (_channel.isOpen()) StreamUtil.close(_channel);
				_channel = null;
			}
			_httpsvr = null;
			_saveSession = null;
		}

		/**
		 * @param value
		 * @return
		 */
		public String authorize(String value) {
			String authkey;
			if (_httpsvr._authorizer == null) {
				authkey = value;
			} else {
				authkey = _httpsvr._authorizer.authorize(value);
			}
			return authkey;
		}

		public FileResource resolveResource(String url, String path)
				throws HttpServerException {
			return _httpsvr.resolveResource(url, path);
		}

		public int getPort() {
			return _httpsvr.getPort();
		}

		public String getRealm() {
			return _httpsvr.getRealm();
		}

		Session newSession(String agent) {
			return new Session(_httpsvr, agent);
		}
		
		/**
		 * 書き込みバッファをリクエストから順次取得します
		 * @return 書き込み可能なデータを含むバッファ、データが無い場合は null を返します
		 */
		ByteBuffer getNextBuffer() {
			ByteBuffer buf = null;
			while (buf == null) {
				buf = _currentRequest.getNextBuffer();
				if (buf == null) {
					// 有効なデータが無い場合
					break;
				}
				if (!buf.hasRemaining()) {
					if (COMMUNICATE_LOG)
						logger.debug("getNextBuffer() Request EOF");
					_currentRequest.term();
					_currentRequest = _requestQueue.poll();
					if (_currentRequest == null) {
						break;
					}
					buf = null;
				}
			}
			return buf;
		}
		
		/**
		 * 書き込み可能なバッファの準備が出来た事をリクエストから通知を受けます
		 * このメソッドはリクエスト側のスレッドから呼び出されます。
		 * @param request 通知元のリクエスト
		 */
		public void notifyReadyData(Request request) {
//			logger.debug("notifyReadyData");
			
			// リクエストが現在処理中（先頭）の場合、セレクターを起動してバッファの取得を促します
			if (_currentRequest == request) {
				if (!_bSelectWrite) _selector.wakeup();
			}
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {
				_selector = Selector.open();

				_channel.configureBlocking(false);
				_channel.register(_selector, SelectionKey.OP_READ);
				
	            ByteBuffer readBuf = obtainByteBuffer();
	            CharBuffer reqestBuf = CharBuffer.allocate(1024);
	            StringBuilder sb = new StringBuilder();
	            
	            Request req = null;
	            boolean endRead = false;
	            ByteBuffer writeBuf = null;
				int _remain = Math.max(MAX_CONTINE, 1);
				_bSelectWrite = false;
				_currentRequest = null;
				
				if (executor == null || executor.isShutdown()) {
					executor = Executors.newSingleThreadExecutor();
				}
				
				boolean timeout = false;
				while (!Thread.interrupted() && !endRead) {
					int nSelect = _selector.select(TIMEOUT * 1000);
//					logger.debug("nSelect = " + nSelect + " req=" + (_currentRequest != null) + " wbuf=" + (writeBuf != null));
					if (nSelect == 0 && _currentRequest == null && writeBuf != null) {
						if (timeout) {
							logger.trace("Timeout");
							break;
						}
						timeout = true;
					} else {
						timeout = false;
					}

					if (Thread.interrupted()) break;
					if (!_bSelectWrite && _currentRequest != null) {
						writeBuf = getNextBuffer();
						if (writeBuf != null) {
							_bSelectWrite = true;
							_channel.register(_selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
						}
					}
					
					Iterator<SelectionKey> itSelection = _selector.selectedKeys().iterator();
					while (itSelection.hasNext()) {
						SelectionKey selkey = itSelection.next();
						itSelection.remove();
						
						if (selkey.isWritable()) {
							do {
								// バッファの残量が無い場合は次のバッファを読込みます
								if (writeBuf != null && !writeBuf.hasRemaining()) {
									recycleByteBuffer(writeBuf);
									writeBuf = getNextBuffer();
								}
								if (writeBuf == null) {
									_bSelectWrite = false;
									_channel.register(_selector, SelectionKey.OP_READ);
								} else {
									try {
//										logger.debug("write " + writeBuf + ", " + selkey.channel() + ", " + selkey.readyOps());
										_channel.write(writeBuf);
									} catch (Throwable th) {
										logger.error("buf = " + writeBuf);
										throw th;
									}
									if (writeBuf.hasRemaining()) break;
								}
							} while (writeBuf != null);
						}
						if (selkey.isReadable()) {
							boolean bRead = false;
							if (_channel.isOpen()) {
								while (_channel.read(readBuf) > 0) {
									bRead = true;
//									logger.debug("read n= " + nRead + ", " + selkey.channel() + ", " + selkey.readyOps());
									
									readBuf.flip();
									while (CharSetUtils.decodeLine(sb, _decoder, readBuf, reqestBuf, false)) {
										String line = sb.toString();
										sb = new StringBuilder();
										if (COMMUNICATE_LOG && logger.isTraceEnabled()) {
											logger.trace("request=" + line);
										}
										if (line.length() == 0) {
											if (req != null) {
												if (req._command != null) {
													if (_remain > 0) _remain--; 
													req.init(this, _remain);
													if (_currentRequest == null) {
														_currentRequest = req;
													} else {
														_requestQueue.add(req);
													}
													executor.execute(req);
													req = null;
												}
											}
										} else {
											if (req == null) {
												req = new Request();
											}
											req.requestToMap(line);
										}
									}
								}
							}
							if (!bRead) {
								logger.debug("_channel is closed");
								endRead = true;
								break;
							}
						}
					}
				}
			} catch (Throwable e) {
                logger.warn("run()", e.toString());
			} finally {
                logger.debug("finally");
                
				// Reqestの実行を停止します
                List<Runnable> list = executor.shutdownNow();
                executor = null;
                
                // 未実行分のRequestを解放します
                for (Runnable r : list) {
                	if (r instanceof Request) {
                		((Request)r).term();
                		_requestQueue.remove(r);
                	}
                }
                if (_currentRequest != null) {
                	_currentRequest.term();
                	_currentRequest = null;
                }
				AsyncHttpSimpleServer svr = _httpsvr; 
				term();
                logger.debug("recycle");
                svr.recycle(this);
			}
		}
		
	}
	
	static class Response {
		private static final String MULTI_PART_SEPARATOR = "THIS_STRING_SEPARATES";
		private static Logger logger = LoggerFactory.getLogger(Response.class);
		private String _status = null;
		private LinkedList<String> _listKeys = new LinkedList<String>();
		private HashMap<String, String> _mapHeader = new HashMap<String, String>();
		HttpRangeSpec _range = null;

		ByteBuffer bufHeadder = null;
		String multiPartType = null;
		
		public Response() {
		}

		public void setStatus(String status) {
			_status = status;
		}
		
		public String getHeader(String key) {
			return _mapHeader.get(key);
		}

		public void setHeader(String key, String value) {
			String old = _mapHeader.put(key, value);
			if (old != null && !old.equals(value)) {
				_listKeys.remove(key);
			}
			_listKeys.add(key);
		}

		/**
		 * 入力ストリームをレスポンスとして書き込みます
		 *  このメソッドは即終了しますが、書き込みは遅延で行われます。
		 *  呼び出し側でストリームのclose()は行わないで下さい。
		 * @param range
		 * @throws IOException
		 */
		public void setBody(HttpRangeSpec range) throws IOException {
			_range = range;
		}
		
		/**
		 * 応答を確定して送信します
		 * @throws IOException
		 */
		public void write(OutputStream out) throws IOException {
			// プリントストリームを作成します
			PrintStream ps = new PrintStream(out, false, "utf-8") {
				StringBuilder sb = COMMUNICATE_LOG ? new StringBuilder() : null;
				
    			@Override
				public void print(String s) {
    				if (COMMUNICATE_LOG) sb.append(s);
					super.print(s);
				}

				@Override
    			public void println() {
    				if (COMMUNICATE_LOG) {
    					logger.trace("response = " + sb.toString());
    					sb = new StringBuilder();
    				}
    				super.print(CRLF);
    			}
    			
    			@Override
    			public void println(String x) {
    				print(x);
    				println();
    			}
    		};

    		_mapHeader.put(MIME_VERSION, "1.1");
    		_mapHeader.put(DATE, http_date_format.format(new Date()));
    		_mapHeader.put(ACCEPT_RANGES, "bytes");
    		
    		// キャッシュを無効にセットします（後はブラウザ次第・・・）
    		_mapHeader.put(CACHE_CONTROL, "no-store");
    		_mapHeader.put(PRAGMA, "no-cache");
    		_mapHeader.put(EXPIRE, "-1");

    		String multiPartType = null;
    		if (_range != null) {
    			String contentLength = String.valueOf(_range.contentLength());
    			switch (_range.size()) {
    			case 0:
            		setStatus(STATUS_OK);
                	setHeader(CONTENT_LENGTH, contentLength);
                	if (_range.isEncoded()) {
                    	setHeader(CONTENT_ENCODING, _range.getEncode());
                	}
                	break;
    			case 1:
            		setStatus(STATUS_PARTIAL_CONTENT);
                	setHeader(CONTENT_RANGE, "bytes " + _range.toString() + "/" + String.valueOf(_range.totalLength()));
                	setHeader(CONTENT_LENGTH, contentLength);
                	break;
    			default:
    				multiPartType = getHeader(CONTENT_TYPE);
            		setStatus(STATUS_PARTIAL_CONTENT);
            		setHeader(CONTENT_TYPE, null);
//    				setHeader(CONTENT_TYPE, "multipart/byteranges; boundary=" + MULTI_PART_SEPARATOR);
    			}
    		}
    		
    		// 確定分のヘッダーを作成します
			ps.println(_status);
			for (String key : RESPONSE_LIST) {
				String val = _mapHeader.remove(key);
				if (val != null) {
					ps.println(String.format("%s: %s", key, val));
				}
			}
			for (String key : _listKeys) {
				String val = _mapHeader.get(key);
				if (val != null) {
					ps.println(String.format("%s: %s", key, val));
				}
			}
			if (multiPartType != null) {
				ps.println(String.format("%s: %s", CONTENT_TYPE, "multipart/byteranges; boundary=" + MULTI_PART_SEPARATOR));
			}
			ps.println();
//			ps.flush();

			// BODYを出力します
			if (_range != null) {
				if (multiPartType == null) {
					InputStream is = _range.getStream();
					try {
						StreamUtil.copy(is, ps);
					} finally {
						StreamUtil.close(is);
					}
				} else {
					String totalLength = String.valueOf(_range.totalLength());
					printSepartor(ps);
					for (Range r : _range.getRanges()) {
						ps.println();
						ps.print(CONTENT_TYPE);
						ps.print(": ");
						ps.println(multiPartType);
						ps.print(CONTENT_RANGE);
						ps.print(": bytes ");
						ps.print(r.getRangeText());
						ps.print("/");
						ps.println(totalLength);
						ps.println();
						ps.flush();
						
						InputStream is = _range.getStream();
						try {
							StreamUtil.copy(is, ps);
						} finally {
							StreamUtil.close(is);
						}
						printSepartor(ps);
					}
				}
				_range = null;
				ps.flush();
			}
		}
		
		void printSepartor(PrintStream ps) {
			ps.print("--");
			ps.println(MULTI_PART_SEPARATOR);
		}
	}

}
