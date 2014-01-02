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

package dz.alkhwarizmix.moqawalati.java.services;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.services.AlKhwarizmixService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;
import dz.alkhwarizmix.moqawalati.java.interfaces.IClientService;
import dz.alkhwarizmix.moqawalati.java.interfaces.IMoqawalatiDAO;

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
		super();
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
	private IMoqawalatiDAO moqawalatiDAO;

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
	@Override
	public void addClient(Client client) throws MoqawalatiException {
		getLogger().trace("addClient");

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
	@Override
	public String addClientFromXML(String clientXml, String creatorId)
			throws MoqawalatiException {
		getLogger().trace("addClientFromXML");

		try {
			Client newClient = (Client) unmarshalObjectFromXML(clientXml);
			newClient.setCreatorId(creatorId);
			addClient(newClient);
			String result = marshalObjectToXML(newClient);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public AlKhwarizmixDomainObjectAbstract getObject(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");

		try {
			Client result = getMoqawalatiDAO().getClient((Client) object);
			return result;
		} catch (AlKhwarizmixException e) {
			throw e;
		}
	}

	/**
	 */
	@Override
	public Client getClient(Client client) throws MoqawalatiException {
		getLogger().trace("getClient");

		try {
			return (Client) getObject(client);
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public String getClientAsXML(Client client) throws MoqawalatiException {
		getLogger().trace("getClientAsXML 1");

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
	@Override
	public String getClientAsXML(String clientXml) throws MoqawalatiException {
		getLogger().trace("getClientAsXML 2");

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
	@Override
	public String getClientAsJSON(Client client) throws MoqawalatiException {
		getLogger().trace("getClientAsJSON");

		try {
			String result = getObjectAsJSON(client);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public Client updateClient(Client client) throws MoqawalatiException {
		getLogger().trace("updateClient");

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
	@Override
	public String updateClientFromXML(String clientXml, String updaterId)
			throws MoqawalatiException {
		getLogger().trace("updateClientFromXML");

		try {
			Client newClient = (Client) unmarshalObjectFromXML(clientXml);
			newClient.setCreatorId(updaterId);
			Client updatedClient = updateClient(newClient);
			String result = marshalObjectToXML(updatedClient);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getClientList(DetachedCriteria criteria,
			int firstResult, int maxResult) throws MoqawalatiException {
		getLogger().trace("getClientList");

		if (criteria == null) {
			criteria = DetachedCriteria.forClass(Client.class);
			criteria.addOrder(Order.asc(Client.CLIENTID));
		}

		try {
			List<Client> result = (List<Client>) (List<?>) getObjectList(
					criteria, firstResult, maxResult);
			return result;
		} catch (AlKhwarizmixException e) {
			MoqawalatiException ex = new MoqawalatiException(e);
			throw ex;
		}
	}

	/**
	 */
	@Override
	public String getClientListAsJSON(DetachedCriteria criteria,
			int firstResult, int maxResult) throws MoqawalatiException {
		getLogger().trace("getClientListAsJSON");

		String result = clientListToJSON(getClientList(criteria, firstResult,
				maxResult));
		return result;
	}

	/**
	 */
	@Override
	public String getClientListAsXML(DetachedCriteria criteria,
			int firstResult, int maxResult) throws MoqawalatiException {
		getLogger().trace("getClientListAsXML");

		String result = clientListToXML(getClientList(criteria, firstResult,
				maxResult));
		return result;
	}

	/**
	 */
	@Override
	public String clientListToJSON(List<Client> clientList) {

		String result = "{\"Clients\": {";
		result += objectListToJSON((List<AlKhwarizmixDomainObjectAbstract>) (List<?>) clientList);
		result += "}}";

		getLogger().trace("clientListToXML(): returns {}", result);
		return result;
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String clientListToXML(List<Client> clientList) {

		String result = "<Clients>";
		result += objectListToXML((List<AlKhwarizmixDomainObjectAbstract>) (List<?>) clientList);
		result += "</Clients>";

		getLogger().trace("clientListToXML(): returns {}", result);
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

	protected IMoqawalatiDAO getMoqawalatiDAO() {
		return moqawalatiDAO;
	}

	protected void setMoqawalatiDAO(IMoqawalatiDAO value) {
		moqawalatiDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
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