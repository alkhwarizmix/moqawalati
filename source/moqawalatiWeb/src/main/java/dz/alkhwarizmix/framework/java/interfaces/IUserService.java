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

package dz.alkhwarizmix.framework.java.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.security.access.annotation.Secured;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.user.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public interface IUserService extends IAlKhwarizmixService {

	/**
	 */
	public void addUser(User user) throws AlKhwarizmixException;

	/**
	 */
	public String addUserFromXML(String userXml, String creatorId)
			throws AlKhwarizmixException;

	/**
	 */
	public User getUser(User user) throws AlKhwarizmixException;

	/**
	 */
	public String getUserAsXML(User user) throws AlKhwarizmixException;

	/**
	 */
	public String getUserAsXML(String partialXml) throws AlKhwarizmixException;

	/**
	 */
	public User updateUser(User user) throws AlKhwarizmixException;

	/**
	 */
	public String updateUserFromXML(String userXml, String updaterId)
			throws AlKhwarizmixException;

	/**
	 */
	@Secured("ROLE_TELLER")
	public String getUserListAsXML(DetachedCriteria criteria, int firstResult,
			int maxResult) throws AlKhwarizmixException;

	/**
	 */
	public String userListToXML(List<User> userList);

	/**
	 */
	public User login(User user) throws AlKhwarizmixException;

	/**
	 */
	public String loginFromXML(String userXml, String loggerId)
			throws AlKhwarizmixException;

	/**
	 */
	public void logout(User user) throws AlKhwarizmixException;

} // Interface
