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

package dz.alkhwarizmix.framework.java.services.impl;

import org.slf4j.Logger;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٩ صفر ١٤٣٦ (December 21, 2014)
 */
public abstract class AbstractAlKhwarizmixServiceValidator implements
		IAlKhwarizmixServiceValidator {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public AbstractAlKhwarizmixServiceValidator() {
		getLogger().trace("Constructor");
	}

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
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToAdd(AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException {
		validateObjectOwner(objectOwner);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToUpdate(AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException {
		validateObjectOwner(objectOwner);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToPublish(
			AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException {
		validateObjectOwner(objectOwner);
		nullifyObjectId(object);
	}

	/**
	 * TODO: Javadoc
	 */
	private void validateObjectOwner(AlKhwarizmixDomainObject objectOwner)
			throws AlKhwarizmixException {
		if (objectOwner == null || objectOwner.getId() == null) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_UNAUTHORIZED);
		}
	}

	/**
	 * TODO: Javadoc
	 */
	private void nullifyObjectId(AbstractAlKhwarizmixDomainObject object) {
		if (object != null)
			object.setId(null);
	}

} // Class
