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

package dz.alkhwarizmix.framework.java.dao;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 *  <p>
 *  TODO: Javadoc
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public class AlKhwarizmixDAOException extends AlKhwarizmixException
{
	private static final long serialVersionUID = 4419505404277029946L;
	
	/* ****** ***** ***** ***** ***** ***** ***** ***** ***** ***** ******/  /**
	 * constructor
	 * @param message {@link String} the exception's description
	 */
	public AlKhwarizmixDAOException(String message)
	{
		super(message);
		setErrorCode(AlKhwarizmixErrorCode.ERROR_DATABASE);
	}
	
	/* ****** ***** ***** ***** ***** ***** ***** ***** ***** ***** ******/  /**
	 * constructor
	 * @param message {@link String} the exception's description
	 * @param nested {@link Exception} the exception's cause
	 */
	public AlKhwarizmixDAOException(String message, Exception nested)
	{
		super(message, nested);
		setErrorCode(AlKhwarizmixErrorCode.ERROR_DATABASE);
	}
	
	/**
	 * constructor
	 * @param errorCode {@link AlKhwarizmixErrorCode} the code for the exception
	 * @param nested {@link Exception} the exception's cause
	 */
	public AlKhwarizmixDAOException(Exception nested)
	{
		super(AlKhwarizmixErrorCode.ERROR_DATABASE.getDescription(), nested);
		setErrorCode(AlKhwarizmixErrorCode.ERROR_DATABASE);
	}
	
} // Class