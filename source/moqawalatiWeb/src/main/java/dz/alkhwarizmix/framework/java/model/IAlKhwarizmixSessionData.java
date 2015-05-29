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

package dz.alkhwarizmix.framework.java.model;

import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٦ جمادى الثانية ١٤٣٦ (April 16, 2015)
 */
public interface IAlKhwarizmixSessionData {

	/**
	 */
	public User getConnectedUser();

	/**
	 */
	public void setConnectedUser(final User value);

	/**
	 */
	public User getLoggedUser();

	/**
	 */
	public void setLoggedUser(final User value);

	/**
	 */
	public AlKhwarizmixDomainObject getSessionOwner();

	/**
	 */
	public void setSessionOwner(final AlKhwarizmixDomainObject value);

	/**
	 */
	public void resetConnectedUser();

	/**
	 */
	public void resetLoggedUser();

	/**
	 */
	public void resetSessionOwner();

} // Interface
