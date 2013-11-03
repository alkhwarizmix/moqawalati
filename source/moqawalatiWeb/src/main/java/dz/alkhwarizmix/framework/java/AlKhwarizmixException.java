////////////////////////////////////////////////////////////////////////////////
//
//  Fares Belhaouas
//  Copyright 2011 Fares Belhaouas
//  All Rights Reserved.
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java;

import org.springframework.validation.Errors;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public class AlKhwarizmixException extends Exception {
	private static final long serialVersionUID = 3434782654593101683L;

	// private Order order;
	private AlKhwarizmixErrorCode errorCode;
	private Errors validationErrors;
	private boolean recoverable;

	/**
	 * constructor
	 * 
	 * @param message
	 *            {@link String} the exception's description
	 */
	public AlKhwarizmixException(String message) {
		super(message);
	}

	/**
	 * constructor
	 * 
	 * @param message
	 *            {@link String} the exception's description
	 * @param nested
	 *            {@link Exception} the exception's cause
	 */
	public AlKhwarizmixException(String message, Exception nested) {
		super(message, nested);
	}

	/**
	 * constructor
	 * 
	 * @param message
	 *            {@link String} the exception's description
	 * @param errorCode
	 *            {@link AlKhwarizmixErrorCode} the code for the exception
	 * @param nested
	 *            {@link Throwable} the exception's cause
	 */
	public AlKhwarizmixException(String message,
			AlKhwarizmixErrorCode errorCode, Throwable nested) {
		super(message, nested);
		setErrorCode(errorCode);
	}

	/**
	 * constructor
	 * 
	 * @param message
	 *            {@link String} the exception's description
	 * @param errorCode
	 *            {@link AlKhwarizmixErrorCode} the code for the exception
	 */
	public AlKhwarizmixException(String message, AlKhwarizmixErrorCode errorCode) {
		super(message);
		setErrorCode(errorCode);
	}

	/**
	 * constructor
	 * 
	 * @param errorCode
	 *            {@link AlKhwarizmixErrorCode} the code for the exception
	 */
	public AlKhwarizmixException(AlKhwarizmixErrorCode errorCode) {
		super(errorCode.getDescription());
		setErrorCode(errorCode);
	}

	/**
	 * constructor
	 * 
	 * @param errorCode
	 *            {@link AlKhwarizmixErrorCode} the code for the exception
	 * @param nested
	 *            {@link Exception} the exception's cause
	 */
	public AlKhwarizmixException(AlKhwarizmixErrorCode errorCode,
			Exception nested) {
		super(errorCode.getDescription(), nested);
		setErrorCode(errorCode);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	/**
	 * get the validationErrors
	 * 
	 * @return validationErrors {@link Errors} the validationErrors
	 */
	public Errors getValidationErrors() {
		return validationErrors;
	}

	/**
	 * set the validationErrors param value {@link Errors} the validationErrors
	 */
	public void setValidationErrors(Errors value) {
		this.validationErrors = value;
	}

	/**
	 * get the errorCode
	 * 
	 * @return errorCode {@link AlKhwarizmixErrorCode} the errorCode
	 */
	public AlKhwarizmixErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * set the errorCode param value {@link AlKhwarizmixErrorCode} the errorCode
	 */
	public void setErrorCode(AlKhwarizmixErrorCode value) {
		this.errorCode = value;
	}

	/**
	 * get the recoverable
	 * 
	 * @return recoverable {@link boolean} the recoverable
	 */
	public boolean isRecoverable() {
		return recoverable;
	}

	/**
	 * set the recoverable param value {@link boolean} the recoverable
	 */
	public void setRecoverable(boolean value) {
		this.recoverable = value;
	}

} // Class
