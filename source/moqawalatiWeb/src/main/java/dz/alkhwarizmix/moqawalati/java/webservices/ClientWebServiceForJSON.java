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

import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.webservices.AlKhwarizmixWebServiceForJSON;
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
 * @since ٠٨ صفر ١٤٣٥ (December 10, 2013)
 */
@Controller
@RequestMapping("moqawalati/json/client")
public class ClientWebServiceForJSON extends AlKhwarizmixWebServiceForJSON
		implements IClientWebServiceForRest {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ClientWebServiceForJSON() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(ClientWebServiceForJSON.class);

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
	 * @param clientAsJSON
	 *            {@link String} the client as json
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	public ResponseEntity<String> addClient(
			@RequestParam("client") String clientAsJSON)
			throws MoqawalatiException {
		getLogger().trace("addClient({})", clientAsJSON);

		try {
			String result = getClientService().addClientFromXML(clientAsJSON,
					getCurrentRequestRemoteAddress());
			StringBuilder sBuilder = new StringBuilder(result);
			return successResponseForJSON(sBuilder);
		} catch (MoqawalatiException exception) {
			return errorResponseAsJSON(exception);
		}
	}

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
		getLogger().trace("getClientById({})", clientId);

		try {
			Client clientToGet = new Client();
			clientToGet.setClientId(clientId);
			StringBuilder sBuilder = new StringBuilder(getClientService()
					.getClientAsJSON(clientToGet));
			return successResponseForJSON(sBuilder);
		} catch (MoqawalatiException exception) {
			return errorResponseAsJSON(exception);
		}
	}

	/**
	 * update the client in database
	 * 
	 * @param clientAsJSON
	 *            {@link String} the client as xml
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	public ResponseEntity<String> updateClient(
			@PathVariable("clientId") String clientId,
			@RequestParam("client") String clientAsJSON)
			throws MoqawalatiException {
		getLogger().trace("updateClient({}, {})", clientId, clientAsJSON);

		try {
			StringBuilder sBuilder = new StringBuilder(getClientService()
					.updateClientFromXML(clientAsJSON,
							getCurrentRequestRemoteAddress()));
			return successResponseForJSON(sBuilder);
		} catch (MoqawalatiException exception) {
			return errorResponseAsJSON(exception);
		}
	}

	/**
	 */
	public ResponseEntity<String> getClientList(
			@RequestParam("firstResult") int firstResult,
			@RequestParam("maxResult") int maxResult) {
		getLogger().trace("getClientList({}, {})", firstResult, maxResult);

		StringBuilder result = new StringBuilder();
		try {
			result.append(getClientService().getClientListAsJSON(null,
					firstResult, maxResult));

			return successResponseForJSON(result);
		} catch (MoqawalatiException exception) {
			return errorResponseAsJSON(exception);
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
