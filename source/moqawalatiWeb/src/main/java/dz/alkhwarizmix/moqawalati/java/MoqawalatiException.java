////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public class MoqawalatiException extends AlKhwarizmixException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2987142306139512150L;

	/**
	 * constructor
	 * 
	 * @param message
	 *            {@link String} the exception's description
	 */
	public MoqawalatiException(String message) {
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
	public MoqawalatiException(String message, Exception nested) {
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
	public MoqawalatiException(String message, AlKhwarizmixErrorCode errorCode,
			Throwable nested) {
		super(message, errorCode, nested);
	}

	/**
	 * constructor
	 * 
	 * @param message
	 *            {@link String} the exception's description
	 * @param errorCode
	 *            {@link AlKhwarizmixErrorCode} the code for the exception
	 */
	public MoqawalatiException(String message, AlKhwarizmixErrorCode errorCode) {
		super(message, errorCode);
	}

	/**
	 * constructor
	 * 
	 * @param errorCode
	 *            {@link AlKhwarizmixErrorCode} the code for the exception
	 */
	public MoqawalatiException(AlKhwarizmixErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * constructor
	 * 
	 * @param errorCode
	 *            {@link AlKhwarizmixErrorCode} the code for the exception
	 * @param nested
	 *            {@link Exception} the exception's cause
	 */
	public MoqawalatiException(AlKhwarizmixErrorCode errorCode, Exception nested) {
		super(errorCode, nested);
	}

	/**
	 * constructor
	 * 
	 * @param nested
	 *            {@link AlKhwarizmixException} the exception's cause
	 */
	public MoqawalatiException(AlKhwarizmixException nested) {
		super(nested.getMessage(), nested);
		setErrorCode(nested.getErrorCode());
		setValidationErrors(nested.getValidationErrors());
		setRecoverable(nested.isRecoverable());
	}

} // Class
