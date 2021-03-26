package com.essg.sdc.vfm.http;

import java.io.IOException;
import java.net.URI;

import com.essg.sdc.vfm.file.FileResource;
import com.essg.sdc.vfm.http.exceptions.HttpServerException;

public interface IVfmHttpServer {

	static final String LOCALHOST = "127.0.0.1";
	static final String QPARM_PASSPORT = "?passport=";

	/**
	 * サーバーの待ちうけポート番号を取得します
	 * @return ポート番号
	 */
	int getPort();

	/**
	 * @return
	 */
	URI getUri();

	/**
	 * @return
	 */
	String getScheme();

	/**
	 * このサーバのホスト名を取得します
	 * @return
	 */
	String getHostName();

	/**
	 * @return
	 */
	String getRealm();

	/**
	 * ファイル解決の為のリゾルバーを登録します
	 * 弱参照による保持の為、追加したリゾルバーは呼び出し側で保持する必要があります。
	 * @param resolver
	 */
	void registResolver(HttpFileResolver resolver);

	/**
	 * ファイル解決の為のリゾルバーの登録を解除します
	 * @param resolver
	 */
	void unregistResolver(HttpFileResolver resolver);

	/**
	 * @param otp
	 * @return
	 */
	Object obtainPassport(String otp);

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	void run();

	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	void start();

	/**
	 * デフォルトファイルリゾルバー
	 * @param url
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws HttpServerException 
	 */
	FileResource resolveResource(String url, String path)
			throws HttpServerException;

	/**
	 * @return
	 */
	long getSessionTimeout();
	
	/**
	 * 
	 */
	void setSessionTimeout(long time);
}