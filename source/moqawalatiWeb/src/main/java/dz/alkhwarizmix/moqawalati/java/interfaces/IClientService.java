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

import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;

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
	public Client addClient(Client client, boolean validateObjectToPublish)
			throws MoqawalatiException;

	/**
	 */
	public String addClientFromXML(String clientAsXML, String creatorId)
			throws MoqawalatiException;

	/**
	 */
	public Client getClient(Client client, boolean validateObjectToPublish)
			throws MoqawalatiException;

	/**
	 */
	public String getClientAsXML(Client client) throws MoqawalatiException;

	/**
	 */
	public String getClientAsJSON(Client client) throws MoqawalatiException;

	/**
	 */
	public Client updateClient(Client client, boolean validateObjectToPublish)
			throws MoqawalatiException;

	/**
	 */
	public String updateClientFromXML(String clientAsXML, String updaterId)
			throws MoqawalatiException;

	/**
	 */
	public List<Client> getClientList(DetachedCriteria criteria,
			int firstResult, int maxResult, boolean validateObjectToPublish)
			throws MoqawalatiException;

	/**
	 */
	public String getClientListAsJSON(DetachedCriteria criteria,
			int firstResult, int maxResult) throws MoqawalatiException;

	/**
	 */
	public String getClientListAsXML(DetachedCriteria criteria,
			int firstResult, int maxResult) throws MoqawalatiException;

} // Class
