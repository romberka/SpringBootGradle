package com.amwater.microservices.demo.exception;

import java.io.Serializable;

/**
 *
 *
 */
public class AMWEnterpriseException extends RuntimeException implements Serializable {

	/**
	 * The serial version unique identifier for this class. The value assigned to this class variable needs to be updated whenever modifications to this class have an impact on serialization.
	 * <p>
	 * See Sun docs for <a href=http://java.sun.com/j2se/1.5.0/docs/guide/serialization/spec/version.html#9419> details. </a>
	 */
	private static final long serialVersionUID = 1L;

	protected String errorCode;

	/**
	 *
	 *
	 */
	public AMWEnterpriseException() {}

	/**
	 *
	 *
	 * @param message
	 */
	public AMWEnterpriseException(String message) {
		super(message);
	}

	/**
	 *
	 *
	 * @param message
	 */
	public AMWEnterpriseException(String message, String errorCode) { // NO_UCD (unused code)
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 *
	 *
	 * @param cause
	 */
	public AMWEnterpriseException(Throwable cause) { // NO_UCD (use default)
		super(cause);
	}

	/**
	 *
	 *
	 * @param message
	 * @param cause
	 */
	public AMWEnterpriseException(String message, String errorCode, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * If a corresponding AMW exception is not found, the exception is wrapped in an AMWBusinessException. There may be other types of exceptions added later. Keep here for now.
	 *
	 * @param msg
	 *            - the message string
	 * @param e
	 *            - the root cause
	 */
	public static void throwAMWException(String msg, String errorCode, Exception e) {

		if (e instanceof AMWSapWsException) {
			throw (AMWSapWsException) e;
		}

		if (e instanceof AMWEnterpriseException) {
			throw (AMWEnterpriseException) e;
		}
		throw new AMWEnterpriseException(msg, errorCode, e);
	}
}