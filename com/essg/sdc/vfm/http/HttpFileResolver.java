package com.essg.sdc.vfm.http;

import com.essg.sdc.vfm.file.FileResource;
import com.essg.sdc.vfm.http.exceptions.HttpServerException;

public interface HttpFileResolver {
	/**
	 * 指定されたpathが対象のコンテンツか判定します
	 * @param url リクエスト元のurl
	 * @param path
	 * @return true=対象のコンテンツ / false=対象のコンテンツでは無い
	 */
	boolean isTarget(String url, String path);
	
	/**
	 * pathで指定されたリソースを解決します
	 * @param url　リクエスト元のurl
	 * @param path
	 * @return　取得したファイルリソース
	 * @throws HttpServerException
	 */
	FileResource resolveResource(String url, String path) throws HttpServerException;
}
