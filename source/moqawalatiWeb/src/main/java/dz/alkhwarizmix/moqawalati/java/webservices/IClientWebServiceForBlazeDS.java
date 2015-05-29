////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.webservices;

import java.util.List;

import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (February 04, 2014) TODO: HIDJRI
 */
public interface IClientWebServiceForBlazeDS {

	/**
	 */
	public void addClient(Client client) throws MoqawalatiException;

	/**
	 */
	public Client getClient(Client client) throws MoqawalatiException;

	/**
	 */
	public Client updateClient(Client client) throws MoqawalatiException;

	/**
	 */
	public List<Client> getClientList(int firstResult, int maxResult)
			throws MoqawalatiException;

} // Class
