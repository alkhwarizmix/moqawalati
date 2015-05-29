////////////////////////////////////////////////////////////////////////////////
//بسم الله الرحمن الرحيم
//
//حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)
//كافة الحقوق محفوظة (All Rights Reserved)
//
//NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.webservices.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;
import dz.alkhwarizmix.moqawalati.java.services.IClientService;
import dz.alkhwarizmix.moqawalati.java.webservices.IClientWebServiceForBlazeDS;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (February 04, 2014) TODO: HIDJRI
 */
public class ClientWebServiceForBlazeDS implements IClientWebServiceForBlazeDS {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ClientWebServiceForBlazeDS() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(ClientWebServiceForBlazeDS.class);

	// @Override
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
	 */
	@Override
	public void addClient(final Client client) throws MoqawalatiException {
		getLogger().trace("addClient");
		getClientService().addClient(client, true);
	}

	/**
	 */
	@Override
	public Client getClient(final Client client) throws MoqawalatiException {
		getLogger().trace("getClient");
		return getClientService().getClient(client, true);
	}

	/**
	 */
	@Override
	public Client updateClient(final Client client) throws MoqawalatiException {
		getLogger().trace("updateClient");
		return getClientService().updateClient(client, true);
	}

	/**
	 */
	@Override
	public List<Client> getClientList(final int firstResult, final int maxResult)
			throws MoqawalatiException {
		getLogger().trace("getClientList");
		return getClientService().getClientList(null, firstResult, maxResult,
				true);
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

	protected void setClientService(final IClientService value) {
		clientService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	// @Override
	protected IAlKhwarizmixService getService() {
		return clientService;
	}

} // Class
