package com.essg.sdc.vfm.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsSimpleServer extends HttpSimpleServer {

	private static final String SSL_PROTOCOL = "TLS";
	private static final String KEYMANAGER_ALGORITHM = "X509";
	private static final String KEYSTORE_TYPE = "BKS";

	KeyStore _keystore = null;
	char[] _password = null;

	/**
	 * コンストラクタ
	 * @param manager
	 * @param port
	 * @param authorizer
	 * @throws IOException
	 */
	public HttpsSimpleServer(HttpFileResolver manager, int port,
			HttpAuthorizer authorizer) throws IOException {
		super(manager, port, authorizer);
	}

	/**
	 * コンストラクタ
	 * @param manager
	 * @param port
	 * @throws IOException
	 */
	public HttpsSimpleServer(HttpFileResolver manager, int port)
			throws IOException {
		super(manager, port);
	}

	/**
	 * コンストラクタ
	 * @param manager
	 * @throws IOException
	 */
	public HttpsSimpleServer(HttpFileResolver manager) throws IOException {
		super(manager);
	}

	/**
	 * キーストアをセットします
	 * @param istream
	 * @param password
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public HttpsSimpleServer setKeyStore(InputStream istream, char[] password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		_keystore = KeyStore.getInstance(KEYSTORE_TYPE);
		_keystore.load(istream, password);
		_password = password;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.http.HttpContentServer#getScheme()
	 */
	@Override
	public String getScheme() {
		return "https";
	}

	/**
	 * 
	 */
	private TrustManager[] trustAllCerts = new TrustManager[] { 
	    new X509TrustManager() { 
	        public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
	            return null; 
	        } 
	        public void checkClientTrusted( 
	            java.security.cert.X509Certificate[] certs, String authType) { 
	        } 
	        public void checkServerTrusted( 
	            java.security.cert.X509Certificate[] certs, String authType) { 
	        } 
	    } 
	};  

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.http.HttpContentServer#createServerSocket(int)
	 */
	@Override
	protected ServerSocket createServerSocket(int port) throws IOException {
		try {
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KEYMANAGER_ALGORITHM);
			kmf.init(_keystore, _password);
			
//			TrustManagerFactory tmf = TrustManagerFactory.getInstance(KEYMANAGER_ALGORITHM);
//			tmf.init(_keystore);

			SSLContext ssl = SSLContext.getInstance(SSL_PROTOCOL);
			ssl.init(kmf.getKeyManagers(), trustAllCerts, new SecureRandom());

			HttpsURLConnection.setDefaultHostnameVerifier(
					new HostnameVerifier(){
						@Override
						public boolean verify(String paramString,
								SSLSession paramSSLSession) {
							return true;
						}
					}
				);
			
			SSLServerSocketFactory ssf = ssl.getServerSocketFactory();
			SSLServerSocket sock = (SSLServerSocket) ssf.createServerSocket(port);

//			Method m = sock.getClass().getMethod("setNeedClientAuth", boolean.class);
//			Method m = sock.getClass().getMethod("setWantClientAuth", Boolean.class);
//			if (m != null) {
//				m.invoke(sock, true);
//			}
			return sock;
		} catch (Exception e) {
			throw new IOException("Create socket error!!", e);
		}
	}

}
