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

package dz.alkhwarizmix.framework.java.webservices.impl;

import org.slf4j.Logger;

import dz.alkhwarizmix.framework.java.AlKhwarizmixBlazeDSException;
import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٩ جمادى الأولى ١٤٣٦ (February 28, 2015)
 */
public abstract class AbstractAlKhwarizmixWebServiceForBlazeDS {

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	protected abstract Logger getLogger();

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	protected final AlKhwarizmixBlazeDSException getAlKhwarizmixBlazeDSException(
			final Exception exception, final String message) {
		getLogger().error("{}: {}", exception.getLocalizedMessage(),
				exception.getStackTrace());
		AlKhwarizmixBlazeDSException result = null;
		if (exception instanceof AlKhwarizmixException)
			result = new AlKhwarizmixBlazeDSException(message, exception);
		else
			result = new AlKhwarizmixBlazeDSException(message,
					AlKhwarizmixErrorCode.SERVER_INTERNAL_ERROR);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	/**
	 * get the service
	 */
	protected abstract IAlKhwarizmixService getService();

} // Class
