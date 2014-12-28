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

package dz.alkhwarizmix.framework.java.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.interfaces.ICustomizerServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٩ صفر ١٤٣٦ (December 21, 2014)
 */
@Component
public class CustomizerServiceValidator extends
		AbstractAlKhwarizmixServiceValidator implements
		ICustomizerServiceValidator {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public CustomizerServiceValidator() {
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
			logger = LoggerFactory.getLogger(CustomizerServiceValidator.class);
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
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToUpdate(AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException {
		super.validateObjectToUpdate(object, objectOwner);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToPublish(
			AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException {
		super.validateObjectToPublish(object, objectOwner);
	}

} // Class