////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.services.AbstractAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.moqawalati.java.interfaces.IClientServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٩ صفر ١٤٣٦ (December 21, 2014)
 */
@Component
public class ClientServiceValidator extends
		AbstractAlKhwarizmixServiceValidator implements IClientServiceValidator {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ClientServiceValidator() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(ClientServiceValidator.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * Validate before to add the object
	 */
	@Override
	public void validateObjectToAdd(AbstractAlKhwarizmixDomainObject object) {
		super.validateObjectToAdd(object);
	}

	/**
	 * Validate before to update the object
	 */
	@Override
	public void validateObjectToUpdate(AbstractAlKhwarizmixDomainObject object) {
		super.validateObjectToUpdate(object);
	}

	/**
	 * Validate before to return the object
	 */
	@Override
	public void validateObjectToPublish(AbstractAlKhwarizmixDomainObject object) {
		super.validateObjectToPublish(object);
	}

} // Class
