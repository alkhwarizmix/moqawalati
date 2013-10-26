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

package dz.alkhwarizmix.moqawalati.interfaces;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.security.access.annotation.Secured;

import dz.alkhwarizmix.framework.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.moqawalati.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.modules.client.model.vo.Client;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public interface IClientService extends IAlKhwarizmixService {
	/**
	 */
	public void addClient(Client client) throws MoqawalatiException;

	/**
	 */
	public String addClient(String clientXml, String creatorId)
			throws MoqawalatiException;

	/**
	 */
	public Client getClient(Client client) throws MoqawalatiException;

	/**
	 */
	public String getClientAsXML(Client client) throws MoqawalatiException;

	/**
	 */
	public String getClientAsXML(String partialXml) throws MoqawalatiException;

	/**
	 */
	public Client updateClient(Client client) throws MoqawalatiException;

	/**
	 */
	public String updateClient(String clientXml, String updaterId)
			throws MoqawalatiException;

	/**
	 */
	@Secured("ROLE_TELLER")
	public String getClientListAsXML(DetachedCriteria criteria)
			throws MoqawalatiException;

	/**
	 */
	public String clientListToXML(List<Client> clientList);

} // Class