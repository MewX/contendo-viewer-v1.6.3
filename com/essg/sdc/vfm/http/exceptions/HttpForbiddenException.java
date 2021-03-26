package com.essg.sdc.vfm.http.exceptions;

public class HttpForbiddenException extends HttpServerException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public HttpForbiddenException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HttpForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public HttpForbiddenException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HttpForbiddenException(Throwable cause) {
		super(cause);
	}
}
