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

package dz.alkhwarizmix.framework.java.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.services.IEMailServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ربيع الثاني ١٤٣٦ (January 28, 2015)
 */
@Component
public class EMailServiceValidator extends AbstractAlKhwarizmixServiceValidator
		implements IEMailServiceValidator {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public EMailServiceValidator() {
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
			logger = LoggerFactory.getLogger(EMailServiceValidator.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToAdd(AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException {
		super.validateObjectToAdd(object, objectOwner);
		EMail email = (EMail) object;
		if (email != null)
			nullifyPropertiesForEMailToAdd(email);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToUpdate(AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException {
		super.validateObjectToUpdate(object, objectOwner);
		EMail email = (EMail) object;
		if (email != null)
			nullifyPropertiesForEMailToUpdate(email);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToPublish(
			AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException {
		super.validateObjectToPublish(object, objectOwner);
		EMail email = (EMail) object;
		if (email != null)
			nullifyPropertiesForEMailToPublish(email);
	}

	/**
	 */
	private void nullifyPropertiesForEMailToAdd(EMail email) {
		// NOOP
	}

	/**
	 */
	private void nullifyPropertiesForEMailToUpdate(EMail email) {
		// NOOP
	}

	/**
	 */
	private void nullifyPropertiesForEMailToPublish(EMail email) {
		// NOOP
	}

} // Class
