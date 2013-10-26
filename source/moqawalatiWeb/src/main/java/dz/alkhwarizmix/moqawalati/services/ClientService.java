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

package dz.alkhwarizmix.moqawalati.services;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.AlKhwarizmixException;
import dz.alkhwarizmix.framework.dao.AlKhwarizmixDAO;
import dz.alkhwarizmix.framework.domain.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.services.AlKhwarizmixService;
import dz.alkhwarizmix.moqawalati.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.dao.MoqawalatiDAO;
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
@Service
@Transactional(readOnly = true)
public class ClientService extends AlKhwarizmixService implements
		IClientService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ClientService() {
		// NOOP
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(ClientService.class);

	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private MoqawalatiDAO moqawalatiDAO;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Transactional(readOnly = false)
	public void addClient(Client client) throws MoqawalatiException {
		try {
			addObject(client);
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	public String addClient(String clientXml, String creatorId)
			throws MoqawalatiException {
		try {
			Client newClient = (Client) unmarshalObject(clientXml);
			newClient.setCreatorId(creatorId);
			addClient(newClient);
			String result = marshalObject(newClient);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	public AlKhwarizmixDomainObject getObject(AlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		try {
			Client result = getMoqawalatiDAO().getClient((Client) object);
			return result;
		} catch (AlKhwarizmixException e) {
			throw e;
		}
	}

	/**
	 */
	public Client getClient(Client client) throws MoqawalatiException {
		try {
			return (Client) getObject(client);
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	public String getClientAsXML(Client client) throws MoqawalatiException {
		try {
			String result = getObjectAsXML(client);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	public String getClientAsXML(String clientXml) throws MoqawalatiException {
		try {
			String result = getObjectAsXML(clientXml);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	public Client updateClient(Client client) throws MoqawalatiException {
		LOG.debug("updateClient");
		try {
			Client result = (Client) updateObject(client);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	public String updateClient(String clientXml, String updaterId)
			throws MoqawalatiException {
		LOG.debug("updateClient");
		try {
			Client newClient = (Client) unmarshalObject(clientXml);
			newClient.setCreatorId(updaterId);
			Client updatedClient = updateClient(newClient);
			String result = marshalObject(updatedClient);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	public List<Client> getClientList(DetachedCriteria criteria)
			throws MoqawalatiException {
		if (criteria == null) {
			criteria = DetachedCriteria.forClass(Client.class);
			criteria.addOrder(Order.asc(Client.CLIENTID));
		}

		try {
			List<Client> result = (List<Client>) (List<?>) getObjectList(criteria);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	public String getClientListAsXML(DetachedCriteria criteria)
			throws MoqawalatiException {
		String result = clientListToXML(getClientList(criteria));
		return result;
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	public String clientListToXML(List<Client> clientList) {
		String result = "<Clients>";
		result += objectListToXML((List<AlKhwarizmixDomainObject>) (List<?>) clientList);
		result += "</Clients>";

		LOG.trace("clientListToXML(): returns {}", result);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// moqawalatiDAO
	// ----------------------------------

	protected MoqawalatiDAO getMoqawalatiDAO() {
		return moqawalatiDAO;
	}

	protected void setMoqawalatiDAO(MoqawalatiDAO value) {
		moqawalatiDAO = value;
	}

	protected AlKhwarizmixDAO getServiceDAO() {
		return moqawalatiDAO;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	protected void setJaxb2Marshaller(Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class