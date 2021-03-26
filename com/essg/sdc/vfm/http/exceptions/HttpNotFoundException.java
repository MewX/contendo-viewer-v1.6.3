package com.essg.sdc.vfm.http.exceptions;

public class HttpNotFoundException extends HttpServerException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public HttpNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HttpNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public HttpNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HttpNotFoundException(Throwable cause) {
		super(cause);
	}
}
