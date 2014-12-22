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

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.user.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IUserServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٩ صفر ١٤٣٦ (December 21, 2014)
 */
@Component
public class UserServiceValidator extends AbstractAlKhwarizmixServiceValidator
		implements IUserServiceValidator {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public UserServiceValidator() {
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
			logger = LoggerFactory.getLogger(UserServiceValidator.class);
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
	 * Validate before to return the object
	 */
	@Override
	public void validateObjectToPublish(AbstractAlKhwarizmixDomainObject object) {
		super.validateObjectToPublish(object);
		nullifyUserDomainObject(object);
	}

	/**
	 * TODO: Javadoc
	 */
	private void nullifyUserDomainObject(AbstractAlKhwarizmixDomainObject object) {
		User user = (User) object;
		if (user != null) {
			user.setDomainObject(null);
		}
	}

} // Class
