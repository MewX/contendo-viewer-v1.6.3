package com.essg.sdc.vfm.http.exceptions;

import java.io.FileNotFoundException;

public class HttpServerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static HttpServerException wrap(Throwable e) {
		if (e instanceof FileNotFoundException) return new HttpNotFoundException(e);
		return new HttpServerException(e);
	}

	/**
	 * コンストラクタ
	 */
	public HttpServerException() {
	}

	/**
	 * コンストラクタ
	 * @param message
	 */
	public HttpServerException(String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * @param cause
	 */
	public HttpServerException(Throwable cause) {
		super(cause);
	}

	/**
	 * コンストラクタ
	 * @param message
	 * @param cause
	 */
	public HttpServerException(String message, Throwable cause) {
		super(message, cause);
	}

}
