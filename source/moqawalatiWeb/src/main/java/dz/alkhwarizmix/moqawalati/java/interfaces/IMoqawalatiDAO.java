////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.interfaces;

import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.userModule.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٤ محرم ١٤٣٥ (November 28, 2013)
 */
public interface IMoqawalatiDAO extends IAlKhwarizmixDAO {
	/**
	 */
	public Client getClient(Client client) throws MoqawalatiException;

	/**
	 */
	public User getUser(User user) throws MoqawalatiException;

	/**
	 */
	public CustomData getCustomData(CustomData customData)
			throws MoqawalatiException;

} // Class