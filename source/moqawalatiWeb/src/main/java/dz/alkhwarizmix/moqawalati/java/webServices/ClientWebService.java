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

package dz.alkhwarizmix.moqawalati.java.webServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.webServices.AlKhwarizmixWebService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;
import dz.alkhwarizmix.moqawalati.java.interfaces.IClientService;

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
public class ClientWebService extends AlKhwarizmixWebService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ClientWebService() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(ClientWebService.class);

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
	 * @param xmlValue
	 *            {@link String} the client as xml
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addClient(
			@RequestParam("client") String xmlValue) throws MoqawalatiException {
		LOG.debug("addClient({})", xmlValue);

		try {
			String result = clientService.addClientFromXML(xmlValue,
					getCurrentRequestRemoteAddress());
			StringBuilder sBuilder = new StringBuilder(result);
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
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
	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	public ResponseEntity<String> getClientById(
			@PathVariable("clientId") String clientId)
			throws MoqawalatiException {
		LOG.debug("getClientById({})", clientId);

		try {
			Client clientToGet = new Client();
			clientToGet.setClientId(clientId);
			StringBuilder sBuilder = new StringBuilder(
					clientService.getClientAsXML(clientToGet));
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
		}
	}

	/**
	 * update the client in database
	 * 
	 * @param xmlValue
	 *            {@link String} the client as xml
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	@RequestMapping(value = "/{clientId}", method = RequestMethod.POST)
	public ResponseEntity<String> updateClient(
			@PathVariable("clientId") String clientId,
			@RequestParam("client") String xmlValue) throws MoqawalatiException {
		LOG.debug("updateClient({})", xmlValue);

		try {
			StringBuilder sBuilder = new StringBuilder(
					clientService.updateClientFromXML(xmlValue,
							getCurrentRequestRemoteAddress()));
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
		}
	}

	/**
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getClientList(
			@RequestParam("firstResult") int firstResult,
			@RequestParam("maxResult") int maxResult) {
		StringBuilder result = new StringBuilder();

		try {
			result.append(clientService.getClientListAsXML(null, firstResult,
					maxResult));

			return successResponse(result);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
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

	protected IAlKhwarizmixService getService() {
		return clientService;
	}

} // Class