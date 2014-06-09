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

package dz.alkhwarizmix.moqawalati.java.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.security.access.annotation.Secured;

import dz.alkhwarizmix.framework.java.dtos.user.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
// extends UserDetailsService
public interface IUserService extends IAlKhwarizmixService {

	/**
	 */
	public void addUser(User user) throws MoqawalatiException;

	/**
	 */
	public String addUserFromXML(String userXml, String creatorId)
			throws MoqawalatiException;

	/**
	 */
	public User getUser(User user) throws MoqawalatiException;

	/**
	 */
	public String getUserAsXML(User user) throws MoqawalatiException;

	/**
	 */
	public String getUserAsXML(String partialXml) throws MoqawalatiException;

	/**
	 */
	public User updateUser(User user) throws MoqawalatiException;

	/**
	 */
	public String updateUserFromXML(String userXml, String updaterId)
			throws MoqawalatiException;

	/**
	 */
	@Secured("ROLE_TELLER")
	public String getUserListAsXML(DetachedCriteria criteria, int firstResult,
			int maxResult) throws MoqawalatiException;

	/**
	 */
	public String userListToXML(List<User> userList);

	/**
	 */
	public User login(User user) throws MoqawalatiException;

	/**
	 */
	public String loginFromXML(String userXml, String loggerId)
			throws MoqawalatiException;

	/**
	 */
	public void logout(User user) throws MoqawalatiException;

} // Class