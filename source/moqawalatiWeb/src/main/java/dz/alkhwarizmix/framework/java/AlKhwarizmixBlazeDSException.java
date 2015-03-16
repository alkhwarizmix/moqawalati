////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٩ جمادى الأولى ١٤٣٦ (February 28, 2015)
 */
public class AlKhwarizmixBlazeDSException extends AlKhwarizmixException {

	private static final long serialVersionUID = 3759807484901026239L;

	/**
	 * constructor
	 *
	 * @param message
	 *            {@link String} the exception's description
	 */
	public AlKhwarizmixBlazeDSException(final String message) {
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
	public AlKhwarizmixBlazeDSException(final String message,
			final Exception nested) {
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
	public AlKhwarizmixBlazeDSException(final String message,
			final AlKhwarizmixErrorCode errorCode, final Throwable nested) {
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
	public AlKhwarizmixBlazeDSException(final String message,
			final AlKhwarizmixErrorCode errorCode) {
		super(message, errorCode);
	}

	/**
	 * constructor
	 *
	 * @param errorCode
	 *            {@link AlKhwarizmixErrorCode} the code for the exception
	 */
	public AlKhwarizmixBlazeDSException(final AlKhwarizmixErrorCode errorCode) {
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
	public AlKhwarizmixBlazeDSException(final AlKhwarizmixErrorCode errorCode,
			final Exception nested) {
		super(errorCode, nested);
	}

} // Class
