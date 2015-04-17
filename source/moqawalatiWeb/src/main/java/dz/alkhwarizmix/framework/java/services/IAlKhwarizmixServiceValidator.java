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

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٩ صفر ١٤٣٦ (December 21, 2014)
 */
public interface IAlKhwarizmixServiceValidator {

	/**
	 * Validate before to add the object
	 */
	public void validateObjectToAdd(AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException;

	/**
	 * Validate before to update the object
	 */
	public void validateObjectToUpdate(AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException;

	/**
	 * Validate before to return the object
	 */
	public void validateObjectToPublish(
			AbstractAlKhwarizmixDomainObject object,
			AlKhwarizmixDomainObject objectOwner) throws AlKhwarizmixException;

} // Class
