package com.essg.sdc.vfm.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.essg.sdc.io.util.StreamUtil;
import com.essg.sdc.vfm.file.FileResource;
import com.essg.sdc.vfm.http.HttpRangeSpec.Range;
import com.essg.sdc.vfm.http.exceptions.HttpForbiddenException;
import com.essg.sdc.vfm.http.exceptions.HttpNotFoundException;


public class HttpSimpleServer extends VfmHttpServer {

	private static final String CLASS_NAME = HttpSimpleServer.class.getSimpleName();
	private static final String SESSION_KEY = "_HttpContentServer_session";
	public static final String QPARM_PASSPORT = "?passport=";
	private static final int QPARM_PASSPORT_LEN = QPARM_PASSPORT.length();
	
	private static final String HTTP_VER = "HTTP/";

	private static final int TIMEOUT = 100;
	private static final int MAX_CONTINE = 0;
	private static final int AUTH_TIMEOUT = 30000;
	private static final int BUFF_SIZE = 8192;
	private static final int MAX_CONNECTION_CACHE = 3;
	
	protected ServerSocket _server = null;
	
	static final Logger logger = LoggerFactory.getLogger(HttpSimpleServer.class);
	
	private String _auth_realm = "HOGEHOGE";
	
	private final LinkedList<ConnectionThread> cacheConnection = new LinkedList<ConnectionThread>();
	private final ExecutorService executor = Executors.newCachedThreadPool();
	
	//TODO セッション解放処理
	static class SessionSweeper extends Thread {

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// ブロッキングを抑止する為にスナップショットを作成します
			Session[] sessions;
			synchronized (_sessions) {
				sessions = new Session[_sessions.size()];
				int i = 0;
				for (Session s : _sessions.values()) {
					sessions[i++] = s;
				}
			}
			
			int count = sessions.length;
			long current = System.currentTimeMillis();
			if (count > 0) {
				for (int i = 0; i < count; i++) {
				}
				long min = sessions[0]._expired_time;
			}
		}
	}
	
	/**
	 * コンストラクタ
	 * 自動的にポート番号を割り当てて認証無しでサーバを構成します。
	 * @param resolver　デフォルトのファイル参照を解決に使用するリゾルバー
	 * @throws IOException　ソケットの生成が出来ませんでした
	 */
	public HttpSimpleServer(HttpFileResolver resolver) throws IOException {
		this(resolver, 0, null);
	}

	/**
	 * コンストラクタ
	 * ポート番号を指定して認証無しでサーバを構成します。
	 * @param resolver デフォルトのファイル参照を解決に使用するリゾルバー
	 * @param port TCPポート番号、0を指定すると自動割り振り
	 * @throws IOException　ソケットの生成が出来ませんでした
	 */
	public HttpSimpleServer(HttpFileResolver resolver, int port) throws IOException {
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
	public HttpSimpleServer(HttpFileResolver resolver, int port, HttpAuthorizer authorizer) throws IOException {
		super(resolver, port, authorizer);

		_auth_realm = "Basic realm=\"" + _auth_realm  + "\"";

		_server = createServerSocket(_port);
	}

	/**
	 * サーバーソケットを生成します
	 * @param port
	 * @return
	 * @throws IOException
	 */
	protected ServerSocket createServerSocket(int port) throws IOException {
		return new ServerSocket(port);
	}

	/**
	 * @return
	 */
	public int getPort() {
		return _server != null ? _server.getLocalPort() : -1;
	}

	/**
	 * @return
	 */
	public URI getUri() {
		return URI.create(getScheme() + "://" + getHostName() + ":" + getPort() +"/");
	}

	/**
	 * @return
	 */
	public String getScheme() {
		return "http";
	}

	/**
	 * このサーバのホスト名を取得します
	 * @return
	 */
	public String getHostName() {
		return "127.0.0.1";
	}

	/**
	 * @return
	 */
	public String getRealm() {
		return _auth_realm;
	}

	/**
	 * @param otp
	 * @return
	 */
	public Object obtainPassport(String otp) {
		Passport pass = new Passport();
		String auth = _authorizer == null ? otp : _authorizer.authorize(otp);
		if (auth != null) {
			synchronized (_passport) {
				_passport.put(pass, auth);
			}
		} else {
			logger.warn("obtainPassport() 認証NG");
		}
		return pass;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void execService() throws Exception {
		try {
			// 接続待ちして、接続要求があれば子スレッドを作成して処理を行います
			while (!interrupted()) {
				// 接続待ち
				//Log.d(CLASS_NAME, "接続待機中(" + getPort() + ")");
				Socket _sock = _server.accept();
				if (COMMUNICATE_LOG )
					logger.info("接続されました(" + getPort() + ") : Remote=" + _sock.getRemoteSocketAddress());

				// スレッド作成
				connect(_sock);
			}
		} finally {
			_server = null;
		}
	}
	
	protected void connect(Socket sock) throws IOException {
		ConnectionThread thread = null;
		if (!cacheConnection.isEmpty()) {
			synchronized (cacheConnection) {
				if (!cacheConnection.isEmpty()) thread = cacheConnection.removeFirst();
			}
		}
		if (thread == null) thread = new ConnectionThread();
		thread.init(this, sock);
		executor.execute(thread);
	}
	
	protected void recycle(ConnectionThread thread) {
		thread.term();
		if (cacheConnection.size() < MAX_CONNECTION_CACHE) {
			synchronized (cacheConnection) {
				cacheConnection.add(thread);
			}
		}
	}
	
	protected ConnectionThread newConnectionThread() {
		return new ConnectionThread();
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	@Override
	public synchronized void start() {
		logger.info("Thread start");
		super.start();
	}

	/**
	 * @author ess0083
	 *
	 */
	static class ConnectionThread implements Runnable {
		HttpSimpleServer _httpsvr = null;;
		Socket _sock = null;
		BufferedReader _reader = null;
		OutputStream _ostream = null;

		// REQUEST を格納する map
		private final HashMap<String, String> _request = new HashMap<String, String>();
		private String _command = null;
		private String _client_version = null;
		private String _referer = null;

		int _remain = 0;
		boolean _support_gzip = false;
		
//		final String _sock_addr;
		private final HashMap<String,String> _cookies = new HashMap<String,String>();
		
		public void init(HttpSimpleServer svr, Socket sock) throws IOException {
			try {
				_httpsvr = svr;
				_sock = sock;
				
	            _reader = new BufferedReader(new InputStreamReader(sock.getInputStream()), BUFF_SIZE);
	            _ostream = new BufferedOutputStream(sock.getOutputStream(), BUFF_SIZE);
			} catch (IOException e) {
				term();
				throw e;
			}
		}
		
		public void term() {
			_request.clear();
			_cookies.clear();
			_command = null;
			_client_version = null;
			_referer = null;
			_remain = 0;
			_support_gzip = false;
			
			if (_ostream != null) {
				StreamUtil.close(_ostream);
				_ostream = null;
			}
			if (_reader != null) {
				StreamUtil.close(_reader);
				_reader = null;
			}
			if (_sock != null) {
				try {
					_sock.close();
				} catch (IOException e) {
				}
				_sock = null;
			}
			_httpsvr = null;
		}

		/**
		 *
		 */
		@Override
		public void run() {
			try {

				Session session = null;
				Boolean first = true;
				Boolean keep_alive = true;
				_remain = Math.max(MAX_CONTINE, 1);
				
				while (keep_alive && _remain > 0 /*&& _reader.ready()*/) {
					long currenttime = System.currentTimeMillis();

					_cookies.clear();
					
                	// REQUESTを処理します

    				// REQUEST を一旦MAPに格納します
					requestToMap();
					if (_command == null) break;

    				String value;
					
    				// Cookieを処理します
    				value = _request.get(COOKIE);
					if (COMMUNICATE_LOG)
						logger.debug("Cookie = " + value);
    				if (value != null) {
						String[] wk = value.split("[;,]");
						for (String s : wk) {
							int n = s.indexOf("=");
							String k = s.substring(0, n).trim().intern();
							String v = s.substring(n + 1).trim().intern();
    						_cookies.put(k, v);
						}
    				}

    				if (first) {
    					first = false;
						// Keep-Aliveの指定を判別します
	    				value = _request.get(CONNECTION);
	    				if (_client_version.compareTo("1.1") >= 0) {
							if (value != null) {
								if (value.toLowerCase().indexOf(CLOSE) >= 0) {
			    					keep_alive = false;
								}
							}
	    				} else {
	    					keep_alive = false;
	    				}
    				}
    				
    				value = _request.get(ACCEPT_ENCODING);
    				if (value != null) {
    					_support_gzip = (value.indexOf("gzip") >= 0);
    				}

    				_referer = _request.get(REFERER);
    				
					Response response = new Response();

					_remain--;
					response.setHeader(KEEP_ALIVE, String.format("timeout=%d, max=%d", TIMEOUT, _remain));
					if (keep_alive && _remain > 0) {
						response.setHeader(CONNECTION, KEEP_ALIVE);
					} else {
						response.setHeader(CONNECTION, CLOSE);
					}

					String agent = _request.get(USER_AGENT);
					
					// セッションもどき処理
					if (_cookies.isEmpty()) {
					} else {
						String key = _cookies.get(SESSION_KEY);
						if (key != null) {
							synchronized (_sessions) {
								session = _sessions.get(key);
								if (session != null) {
									long timeout = _httpsvr.getSessionTimeout();
									if (timeout > 0 && currenttime > session._expired_time) {
										// 期限切れ
										if (COMMUNICATE_LOG)
											logger.warn("期限切れ");
										// 期限切れの場合はクリアします
										_sessions.remove(key);
										if (session._passportRef != null)
											session._passportRef.clear();
										session = null;
									} else if (!agent.equals(session._agent)) {
										// Agentからの接続の場合は新しいSessionとして扱います
										if (COMMUNICATE_LOG)
											logger.warn("Agentが異なります");
										session = null;
									} else {
										if (COMMUNICATE_LOG)
											logger.warn("セッションが見つかりました key = " + key);
										// 他のスレッドから削除されない様にこのタイミングで延長を行います
										if (timeout > 0) {
											session._expired_time = currenttime + timeout;
										}
									}
								}
							}
							if (session == null && COMMUNICATE_LOG)
								logger.warn("セッションが見つかりません key = " + key);
						}
					}
					if (session == null) {
						// 新しいセッションを作成します
						session = new Session(_httpsvr, agent);
					}
//					response.setHeader(SET_COOKIE, SESSION_KEY + "=" + session._id + "; expires=" + cookie_date_format.format(new Date(session._expired_time)) + "; path=/");
					response.setHeader(SET_COOKIE, SESSION_KEY + "=" + session._id + "; ; path=/");

					// 認証情報判定（パスポートが無い場合は毎回判定します）
					boolean bAuth = false;
					if (session._passportRef != null) {
						Object passport = session._passportRef.get();
						if (passport != null) {
							bAuth = true;
						}
					}
					if (!bAuth) {
						if (session._authkey == null || session._auth_expired_time < currenttime) {
		    				value = _request.get(AUTHORIZATION);
		    				if (value != null) {
		    					if (_httpsvr._authorizer == null) {
		    						session._authkey = value;
		    					} else {
		    						session._authkey = _httpsvr._authorizer.authorize(value);
		    					}
		    				}
						}
	    				if (session._authkey != null) {
	    					bAuth = true;
	    					session._auth_expired_time = currenttime + AUTH_TIMEOUT;
	    				}
					}
					
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
            	                				session._passportRef = new WeakReference<Passport>(pass);
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
    	            		response.setHeader(WWW_AUTHENTICATE, _httpsvr._auth_realm);
    	            	} else {
    	            		http_Get(response);
    	            	}
    	            } else if (_command.startsWith(HEAD)) {
    	            	http_Head(response);
    	            } else {
    	            	response.setStatus(STATUS_METHOD_NOT_ALLOWED);
    	            	response.setHeader(ALLOW, "GET, HEAD");
    	            }
    	            response.write(_ostream);
				}
			} catch (IOException e) {
                logger.error("run()", e);
//			} catch (InterruptedException e) {
//                logger.warn("run()", e);
			} finally {
				_httpsvr.recycle(this);
			}
		}

		/**
		 * リクエストヘッダーの内容をMAPに格納します
		 * @throws IOException
		 */
		private void requestToMap() throws IOException {
			_request.clear();
			_command = null;
			String buf;
			while (/* reader.ready() && */(buf = _reader.readLine()) != null) {
				if (buf.length() == 0) break;
				if (COMMUNICATE_LOG) logger.debug("request = " + buf);
				if (_command == null) {
					_command = buf;
					int n = _command.indexOf(HTTP_VER);
					if (n >= 0) {
						_client_version = _command.substring(n + HTTP_VER.length());
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
					_request.put(key.intern(),  value);
				}
			}
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
    				if (_client_version.compareTo("1.1") >= 0) {
    					String host = _request.get("Host");
    					_referer = "http://" + host + "/" + path;
    				} else {
    					_referer = "http://127.0.0.1:" + _httpsvr.getPort() + "/" + path;
    				}
				}

            	FileResource file = _httpsvr.resolveResource(_referer, path);
            	
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

	static class Response {
		private static final String MULTI_PART_SEPARATOR = "THIS_STRING_SEPARATES";
		private static Logger logger = LoggerFactory.getLogger(Response.class);
		private String _status = null;
		private LinkedList<String> _listKeys = new LinkedList<String>();
		private HashMap<String, String> _mapHeader = new HashMap<String, String>();
		HttpRangeSpec _range = null;

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
			PrintStream ps = new PrintStream(out) {
				StringBuilder sb = new StringBuilder();
				boolean bLog = logger.isDebugEnabled();
				
    			@Override
				public void print(String s) {
    				if (bLog) sb.append(s);
					super.print(s);
				}

				@Override
    			public synchronized void println() {
    				if (bLog && COMMUNICATE_LOG) {
    					logger.debug("response = " + sb.toString());
    					sb = new StringBuilder();
    				}
    				super.print(CRLF);
    			}
    			
    			@Override
    			public synchronized void println(String x) {
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
			ps.flush();

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
