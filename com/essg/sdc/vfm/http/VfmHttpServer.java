package com.essg.sdc.vfm.http;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.TimeZone;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.essg.sdc.util.random.MersenneTwister;
import com.essg.sdc.util.random.RandomUtil;
import com.essg.sdc.vfm.file.FileResource;
import com.essg.sdc.vfm.http.exceptions.HttpServerException;

public abstract class VfmHttpServer extends Thread implements IVfmHttpServer {

	protected static boolean HTTP_AUTHORITY_ENABLE = true;
	protected static boolean COMMUNICATE_LOG = false;
	private static final int DEFAULT_SESSION_TIMEOUT = 3600000;
	private long _sessionTimeOut = DEFAULT_SESSION_TIMEOUT; 
	
	protected static final String STATUS_OK = "HTTP/1.1 200 OK";
	protected static final String STATUS_PARTIAL_CONTENT = "HTTP/1.1 206 Partial content";
	protected static final String STATUS_UNAUTHORIZED = "HTTP/1.1 401 Unauthorized";
	protected static final String STATUS_FORBIDDEN = "HTTP/1.1 403 Forbidden";
	protected static final String STATUS_NOT_FOUND = "HTTP/1.1 404 Not Found";
	protected static final String STATUS_METHOD_NOT_ALLOWED = "HTTP/1.1 405 Method Not Allowed";
	protected static final String STATUS_RANGE_NOT_SATISFIABLE = "HTTP/1.1 416 Requested range not satisfiable";

	protected static final String HTTP_VER = "HTTP/";

	protected static final String CONNECTION = "Connection";
	protected static final String KEEP_ALIVE = "Keep-Alive";
	protected static final String CONTENT_LENGTH = "Content-Length";
	protected static final String CONTENT_LANGUAGE = "Content-Language";
	protected static final String HTTP_DATE_FMT = "EEE, dd MMM yyyy HH:mm:ss zzz";
	protected static final String COOKIE_DATE_FMT = "EEE, dd-MMM-yyyy HH:mm:ss z";
	protected static final String RFC1123_DATE_FMT = "EEE, dd MMM yyyyy HH:mm:ss z";
	protected static final String RFC1036_DATE_FMT = "EEEEEEEEE, dd-MMM-yy HH:mm:ss z";
	protected static final String MIME_VERSION = "MIME-Version";
	protected static final String WWW_AUTHENTICATE = "WWW-Authenticate";
	protected static final String CONTENT_ENCODING = "Content-Encoding";
	protected static final String DATE = "Date";
	protected static final String SERVER = "Server";
	protected static final String ALLOW = "Allow";
	protected static final String LAST_MODIFIED = "Last-Modified";
	protected static final String EXPIRE = "Expire";
	protected static final String CONTENT_TYPE = "Content-Type";
	protected static final String CONTENT_RANGE = "Content-Range";
	protected static final String CONTENT_MD5 = "Content-MD5";
	protected static final String CONTENT_LOCATION = "Content-Location";
	protected static final String ACCEPT_RANGES = "Accept-Ranges";
	protected static final String AUTHORIZATION = "Authorization";
	protected static final String ACCEPT_ENCODING = "Accept-Encoding";
	protected static final String CLOSE = "close";
	protected static final String PRAGMA = "Pragma";
	protected static final String CACHE_CONTROL = "Cache-Control";
	protected static final String RANGE = "Range";
	protected static final String COOKIE = "Cookie";
	protected static final String SET_COOKIE = "Set-Cookie";
	protected static final String USER_AGENT = "User-Agent";
	protected static final String REFERER = "Referer";

	protected static final String HEAD = "HEAD";
	protected static final String GET = "GET";
	
	protected static final String CRLF = "\r\n";

	protected static final String[] RESPONSE_LIST = {
		MIME_VERSION,
		WWW_AUTHENTICATE,
		ALLOW,
		ACCEPT_RANGES,
		SERVER,
		DATE,
		CONTENT_ENCODING,
		CONTENT_LANGUAGE,
		CONTENT_LENGTH,
		CONTENT_LOCATION,
		CONTENT_MD5,
		CONTENT_RANGE,
		CONTENT_TYPE,
		CACHE_CONTROL,
		PRAGMA,
		CONTENT_TYPE,
		EXPIRE,
		LAST_MODIFIED,
		SET_COOKIE,
		KEEP_ALIVE,
		CONNECTION,
	};

	protected static final DateFormat http_date_format = new SimpleDateFormat(HTTP_DATE_FMT, java.util.Locale.US);
	protected static final DateFormat cookie_date_format = new SimpleDateFormat(COOKIE_DATE_FMT, java.util.Locale.US);
	protected static final HashMap<String, Session> _sessions = new HashMap<String, Session>(89);
	protected static final WeakHashMap<Passport, String> _passport = new WeakHashMap<Passport, String>(89);
	
	protected static final Random _rnd = new MersenneTwister();

	static {
		cookie_date_format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	static class Passport {
		final String _id;
		private static HashSet<String> _entrys = null;
		
		Passport() {
			synchronized (Passport.class) {
				if (_entrys == null) {
					_entrys = new HashSet<String>();
				}
				String id;
				do {
					id = RandomUtil.randomString(_rnd, 16, 16, null);
				} while (_entrys.contains(id));
				_entrys.add(id);
				_id = id;
			}
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#finalize()
		 */
		@Override
		protected void finalize() throws Throwable {
			try {
				synchronized (Passport.class) {
					_entrys.remove(_id);
					if (_entrys.isEmpty()) _entrys = null; 
				}
			} finally {
				super.finalize();
			}
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return _id;
		}
	}
	
	protected static class Session {
		final long _create_time;
		final String _agent;
		final String _id;
		long _expired_time;
		long _auth_expired_time = 0;
		String _authkey;
		WeakReference<Passport> _passportRef;
		HashMap<String, Object> _map;
		
		Session(VfmHttpServer svr, String agent) {
			_create_time = System.currentTimeMillis();
			_expired_time = _create_time + svr.getSessionTimeout();
			_agent = agent;
			_id = svr.createSessionKey(this);
		}
	}

	protected final Logger logger;
	protected HttpFileResolver defaultResolver;
	protected HttpAuthorizer _authorizer;
	protected int _port;
	LinkedList<WeakReference<HttpFileResolver>> listResolver = new LinkedList<WeakReference<HttpFileResolver>>();

	/**
	 * コンストラクタ
	 */
	public VfmHttpServer(HttpFileResolver resolver, int port, HttpAuthorizer authorizer) {
		defaultResolver = resolver;
		_port = port;
		_authorizer = authorizer;
		logger = LoggerFactory.getLogger(getClass());
//		COMMUNICATE_LOG = logger.isDebugEnabled();
	}

	@Override
	public void run() {
		try {
			logger.info("開始 ポート番号 = " + getPort());
			execService();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("終了 ポート番号 = " + getPort());
			defaultResolver = null;
			_authorizer = null;
		}
	}
	
	protected abstract void execService() throws Exception;
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#getSessionTimeout()
	 */
	@Override
	public long getSessionTimeout() {
		return _sessionTimeOut;
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#setSessionTimeout(long)
	 */
	@Override
	public void setSessionTimeout(long time) {
		_sessionTimeOut = time;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#obtainPassport(java.lang.String)
	 */
	@Override
	public Object obtainPassport(String otp) {
		Passport pass = null;
		String auth = _authorizer == null ? otp : _authorizer.authorize(otp);
		if (auth != null) {
			pass = new Passport();
			logger.debug("パスポート発行 id=" + pass._id);
			synchronized (_passport) {
				_passport.put(pass, auth);
			}
		} else {
			logger.warn("obtainPassport() 認証NG");
		}
		return pass;
	}
	
	/**
	 * 新しいセッションキーを生成します
	 * @param s
	 * @return
	 */
	String createSessionKey(Session s) {
		String key;
		synchronized (_sessions) {
			do {
				key = RandomUtil.randomString(_rnd, 16, 16, null);
			} while (_sessions.containsKey(key));
			_sessions.put(key, s);
			logger.warn("createSessionKey count = " + _sessions.size());
		}
		return key;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#registResolver(com.essg.sdc.vfm.http.HttpFileResolver)
	 */
	public void registResolver(HttpFileResolver resolver) {
		synchronized (listResolver) {
			listResolver.addFirst(new WeakReference<HttpFileResolver>(resolver));
		}
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#unregistResolver(com.essg.sdc.vfm.http.HttpFileResolver)
	 */
	public void unregistResolver(HttpFileResolver resolver) {
		synchronized (listResolver) {
			ListIterator<WeakReference<HttpFileResolver>> it = listResolver.listIterator();
			while (it.hasNext()) {
				WeakReference<HttpFileResolver> ref = it.next();
				HttpFileResolver res = ref.get();
				if (res == null || res == resolver) it.remove();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.http.IVfmHttpServer#resolveResource(java.lang.String, java.lang.String)
	 */
	@Override
	public FileResource resolveResource(String url, String path) throws HttpServerException {
		FileResource file = null;
		if (path != null) {
    		if (!listResolver.isEmpty()) {
    	    	synchronized (listResolver) {
    	    		if (!listResolver.isEmpty()) {
    		    		ListIterator<WeakReference<HttpFileResolver>> it = listResolver.listIterator();
    		    		while (it.hasNext()) {
    		    			WeakReference<HttpFileResolver> ref = it.next();
    		    			HttpFileResolver r = ref.get();
    		    			if (r == null) {
    		    	    		// 不要なリゾルバーの登録を削除します
    		    				it.remove();
    		    			} else {
    		    				// 対象のリゾルバーを判定します
    		    				if (r.isTarget(url, path)) {
    		    					// ファイルリソースを取得します
    		                		file = r.resolveResource(url, path);
    		    					break;
    		    				}
    		    			}
    		    		}
    	    		}
    	    	}
    		}
	    	if (file == null) {
		    	file = defaultResolver.resolveResource(url, path);
	    	}
		}
		return file;
	}
}
