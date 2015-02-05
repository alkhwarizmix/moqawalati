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

package dz.alkhwarizmix.framework.java.interfaces;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الثاني ١٤٣٦ (February 03, 2015)
 */
public interface IUserWebServiceForBlazeDS {

	/**
	 */
	public void addUser(User user) throws AlKhwarizmixException;

	/**
	 */
	public User getUser(User user) throws AlKhwarizmixException;

	/**
	 */
	public User updateUser(User user) throws AlKhwarizmixException;

	/**
	 */
	public User connect(User user) throws AlKhwarizmixException;

	/**
	 */
	public User login(User user, String password) throws AlKhwarizmixException;

	/**
	 */
	public void logout(User user) throws AlKhwarizmixException;

} // Class
