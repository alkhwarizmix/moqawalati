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
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.services.impl.AbstractAlKhwarizmixService;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;
import dz.alkhwarizmix.moqawalati.java.interfaces.IClientDAO;
import dz.alkhwarizmix.moqawalati.java.interfaces.IClientService;
import dz.alkhwarizmix.moqawalati.java.interfaces.IClientServiceValidator;

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
public class ClientService extends AbstractAlKhwarizmixService implements
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

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(ClientService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IClientDAO clientDAO;

	@Autowired
	private IClientServiceValidator clientValidator;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public Client addClient(final Client client,
			final boolean validateObjectToPublish) throws MoqawalatiException {
		getLogger().trace("addClient");
		try {
			setupObjectExtendedDataXMLValue(client);
			final Client result = (Client) addObject(client,
					validateObjectToPublish);
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String addClientFromXML(final String clientAsXML,
			final String creatorId) throws MoqawalatiException {
		getLogger().trace("addClientFromXML");

		try {
			final Client newClient = (Client) unmarshalObjectFromXML(clientAsXML);
			// newClient.setCreatorId(creatorId);
			final Client addedClient = addClient(newClient, true);
			final String result = marshalObjectToXML(addedClient);
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			final AbstractAlKhwarizmixDomainObject object,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("getObject");

		try {
			Client result = getClientDAO().getClient((Client) object);
			updateObjectFromExtendedDataXML(result);
			if (validateObjectToPublish && (result != null)) {
				result = (Client) result.clone();
				getServiceValidator().validateObjectToPublish(result,
						getSessionOwner());
			}
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client getClient(final Client client,
			final boolean validateObjectToPublish) throws MoqawalatiException {
		getLogger().trace("getClient");

		try {
			final Client result = (Client) getObject(client,
					validateObjectToPublish);
			getLogger().trace("getClient: return {}", result);
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClientAsXML(final Client client)
			throws MoqawalatiException {
		getLogger().trace("getClientAsXML 1");

		try {
			final String result = getObjectAsXML(client);
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClientAsJSON(final Client client)
			throws MoqawalatiException {
		getLogger().trace("getClientAsJSON");

		try {
			final String result = getObjectAsJSON(client);
			getLogger().trace("getClientAsJSON: return {}", result);
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public Client updateClient(final Client client,
			final boolean validateObjectToPublish) throws MoqawalatiException {
		getLogger().trace("updateClient");

		try {
			setupObjectExtendedDataXMLValue(client);
			final Client result = (Client) updateObject(client,
					getSessionOwner(), validateObjectToPublish);
			getLogger().trace("updateClient: return {}", result);
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String updateClientFromXML(final String clientAsXML,
			final String updaterId) throws MoqawalatiException {
		getLogger().trace("updateClientFromXML");

		try {
			final Client newClient = (Client) unmarshalObjectFromXML(clientAsXML);
			// newClient.setCreatorId(updaterId);
			final Client updatedClient = updateClient(newClient, true);
			final String result = marshalObjectToXML(updatedClient);
			getLogger().trace("updateClientFromXML: return {}", result);
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getClientList(DetachedCriteria criteriaToUse,
			final int firstResult, final int maxResult,
			final boolean validateObjectToPublish) throws MoqawalatiException {
		getLogger().trace("getClientList");

		if (criteriaToUse == null) {
			criteriaToUse = DetachedCriteria.forClass(Client.class);
			criteriaToUse.addOrder(Order.asc(Client.CLIENTID));
		}

		try {
			final List<Client> result = (List<Client>) (List<?>) getObjectList(
					criteriaToUse, firstResult, maxResult,
					validateObjectToPublish);
			return result;
		} catch (final AlKhwarizmixException e) {
			throw new MoqawalatiException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClientListAsJSON(final DetachedCriteria criteria,
			final int firstResult, final int maxResult)
			throws MoqawalatiException {
		getLogger().trace("getClientListAsJSON");

		final String result = clientListToJSON(getClientList(criteria,
				firstResult, maxResult, true));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClientListAsXML(final DetachedCriteria criteria,
			final int firstResult, final int maxResult)
			throws MoqawalatiException {
		getLogger().trace("getClientListAsXML");

		final String result = clientListToXML(getClientList(criteria,
				firstResult, maxResult, true));
		return result;
	}

	/**
	 */
	private String clientListToJSON(final List<Client> clientList) {
		String result = "{\"Clients\": {";
		result += objectListToJSON((List<AbstractAlKhwarizmixDomainObject>) (List<?>) clientList);
		result += "}}";

		getLogger().trace("clientListToXML(): return {}", result);
		return result;
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	private String clientListToXML(final List<Client> clientList) {
		String result = "<Clients>";
		result += objectListToXML((List<AbstractAlKhwarizmixDomainObject>) (List<?>) clientList);
		result += "</Clients>";

		getLogger().trace("clientListToXML(): return {}", result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AlKhwarizmixDomainObject getSessionOwner() {
		final AlKhwarizmixDomainObject result = new AlKhwarizmixDomainObject();
		result.setId(-1L);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// clientDAO
	// ----------------------------------

	private final IClientDAO getClientDAO() {
		return clientDAO;
	}

	protected final void setClientDAO(final IClientDAO value) {
		clientDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return clientDAO;
	}

	// ----------------------------------
	// clientValidator
	// ----------------------------------

	protected final void setClientValidator(final IClientServiceValidator value) {
		clientValidator = value;
	}

	@Override
	protected IAlKhwarizmixServiceValidator getServiceValidator() {
		return clientValidator;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	@Override
	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	@Override
	protected void setJaxb2Marshaller(final Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class
