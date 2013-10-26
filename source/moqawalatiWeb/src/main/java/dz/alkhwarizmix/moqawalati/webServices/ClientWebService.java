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

package dz.alkhwarizmix.moqawalati.webServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.framework.interfaces.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.webServices.AlKhwarizmixWebService;
import dz.alkhwarizmix.moqawalati.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.interfaces.IClientService;
import dz.alkhwarizmix.moqawalati.java.modules.client.model.vo.Client;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
@Controller
@RequestMapping("moqawalati/client")
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
	@RequestMapping("/add")
	public ResponseEntity<String> addClient(
			@RequestParam("client") String xmlValue) throws MoqawalatiException {
		LOG.debug("addClient({})", xmlValue);

		try {
			String result = clientService.addClient(xmlValue,
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
	 * @param xmlValue
	 *            {@link String} partial client xml containing clientId
	 * @return {@link ResponseEntity}
	 * @throws MoqawalatiException
	 */
	@RequestMapping("/get")
	public ResponseEntity<String> getClientByXml(
			@RequestParam("client") String xmlValue) throws MoqawalatiException {
		LOG.debug("getClientByXml({})", xmlValue);

		try {
			StringBuilder sBuilder = new StringBuilder(
					clientService.getClientAsXML(xmlValue));
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
	@RequestMapping("/{clientId}")
	public ResponseEntity<String> getClientById(
			@PathVariable("clientId") String clientId)
			throws MoqawalatiException {
		LOG.debug("getClientById({})", clientId);

		try {
			Client clientToGet = new Client();
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
	@RequestMapping("/update")
	public ResponseEntity<String> updateClient(
			@RequestParam("client") String xmlValue) throws MoqawalatiException {
		LOG.debug("updateClient({})", xmlValue);

		try {
			StringBuilder sBuilder = new StringBuilder(
					clientService.updateClient(xmlValue,
							getCurrentRequestRemoteAddress()));
			return successResponse(sBuilder);
		} catch (MoqawalatiException e) {
			return errorResponse(e);
		}
	}

	/**
	 */
	@RequestMapping("/list")
	public ResponseEntity<String> getClientList() {
		StringBuilder result = new StringBuilder();

		try {
			result.append(clientService.getClientListAsXML(null));

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