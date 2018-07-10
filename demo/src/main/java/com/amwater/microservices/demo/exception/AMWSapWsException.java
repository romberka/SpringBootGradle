package com.amwater.microservices.demo.exception;

import java.io.Serializable;

/**
 *
 *
 */
public class AMWSapWsException extends AMWEnterpriseException implements Serializable {

	/**
	 * The serial version unique identifier for this class. The value assigned to this class variable needs to be updated whenever modifications to this class have an impact on serialization.
	 * <p>
	 * See Sun docs for <a href=http://java.sun.com/j2se/1.5.0/docs/guide/serialization/spec/version.html#9419> details. </a>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 *
	 */
	public AMWSapWsException() {}

	/**
	 *
	 *
	 * @param message
	 */
	public AMWSapWsException(String message, String errorCode) { // NO_UCD (unused code)
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 *
	 *
	 * @param cause
	 */
	public AMWSapWsException(Throwable cause) { // NO_UCD (unused code)
		super(cause);
	}

	/**
	 *
	 *
	 * @param message
	 * @param cause
	 * @param errorCode
	 */
	public AMWSapWsException(String message, String errorCode, Throwable cause) {
		super(message, errorCode, cause);
		this.errorCode = errorCode;
	}

	/**
	 * If a corresponding AMW exception is not found, the exception is wrapped in an AMWBusinessException.
	 *
	 * @param msg
	 *            - the message string
	 * @param e
	 *            - the root cause
	 */
	public static void throwAMWSapWsException(String msg, String errorCode, Exception e) {

		if (e instanceof AMWSapWsException) {
			throw (AMWSapWsException) e;
		}
		throw new AMWSapWsException(msg, errorCode, e);
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
}
