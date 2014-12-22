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

package dz.alkhwarizmix.moqawalati.java.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.webservices.AlKhwarizmixWebServiceForXML;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;
import dz.alkhwarizmix.moqawalati.java.interfaces.IClientService;
import dz.alkhwarizmix.moqawalati.java.interfaces.IClientWebServiceForRest;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
@Controller
@RequestMapping("moqawalati/xml/client")
public class ClientWebServiceForXML extends AlKhwarizmixWebServiceForXML
		implements IClientWebServiceForRest {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ClientWebServiceForXML() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(ClientWebServiceForXML.class);

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IClientService clientService;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * add the client to database
	 * 
	 * @param clientAsXML
	 *            {@link String} the client as xml
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	public ResponseEntity<String> addClient(
			@RequestParam("client") String clientAsXML)
			throws MoqawalatiException {
		getLogger().debug("addClient({})", clientAsXML);

		try {
			String result = getClientService().addClientFromXML(clientAsXML,
					getCurrentRequestRemoteAddress());
			StringBuilder sBuilder = new StringBuilder(result);
			return successResponseForXML(sBuilder);
		} catch (MoqawalatiException exception) {
			return errorResponseForXML(exception);
		}
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST) public Client
	 * addClient1(Client client) throws MoqawalatiException {
	 * getLogger().debug("addClient1({})", client);
	 * 
	 * return client; }
	 */

	/**
	 * get the client from database
	 * 
	 * @param clientId
	 *            {@link Long} clientId
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	public ResponseEntity<String> getClientById(
			@PathVariable("clientId") String clientId)
			throws MoqawalatiException {
		getLogger().debug("getClientById({})", clientId);

		try {
			Client clientToGet = new Client();
			clientToGet.setClientId(clientId);
			StringBuilder sBuilder = new StringBuilder(getClientService()
					.getClientAsXML(clientToGet));
			return successResponseForXML(sBuilder);
		} catch (MoqawalatiException exception) {
			return errorResponseForXML(exception);
		}
	}

	/**
	 * update the client in database
	 * 
	 * @param clientAsXML
	 *            {@link String} the client as xml
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	public ResponseEntity<String> updateClient(
			@PathVariable("clientId") String clientId,
			@RequestParam("client") String clientAsXML)
			throws MoqawalatiException {
		getLogger().debug("updateClient({})", clientAsXML);

		try {
			StringBuilder sBuilder = new StringBuilder(getClientService()
					.updateClientFromXML(clientAsXML,
							getCurrentRequestRemoteAddress()));
			return successResponseForXML(sBuilder);
		} catch (MoqawalatiException exception) {
			return errorResponseForXML(exception);
		}
	}

	/**
	 */
	public ResponseEntity<String> getClientList(
			@RequestParam("firstResult") int firstResult,
			@RequestParam("maxResult") int maxResult) {
		StringBuilder result = new StringBuilder();

		try {
			result.append(getClientService().getClientListAsXML(null,
					firstResult, maxResult));

			return successResponseForXML(result);
		} catch (MoqawalatiException exception) {
			return errorResponseForXML(exception);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// clientService
	// ----------------------------------

	protected IClientService getClientService() {
		return clientService;
	}

	protected void setClientService(IClientService value) {
		clientService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	@Override
	protected IAlKhwarizmixService getService() {
		return clientService;
	}

} // Class
