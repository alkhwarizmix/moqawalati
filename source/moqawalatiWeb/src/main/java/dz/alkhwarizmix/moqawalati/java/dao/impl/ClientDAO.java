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

package dz.alkhwarizmix.moqawalati.java.dao.impl;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.dao.impl.AlKhwarizmixDAOException;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dao.IClientDAO;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ شعبان ١٤٣٥ (June 10, 2014)
 */
@Repository
public class ClientDAO extends MoqawalatiDAOForXMLMarshalling implements
		IClientDAO {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ClientDAO() {
		super();
	}

	@PostConstruct
	private void createDefaultClients() {
		final Client defaultClient = new Client("fares.belhaouas", "فارس بلحواس");
		Session session = null;
		Transaction trans = null;
		boolean isOpenSession = false;
		try {
			session = getHibernateCurrentSession();
			if (session == null) {
				session = getSessionFactory().openSession();
				isOpenSession = true;
			}
			trans = session.beginTransaction();
			saveOrUpdate(defaultClient, session);
			trans.commit();
			getLogger().info(
					"createDefaultClients: Created default client <{}>",
					defaultClient.getName());
		} catch (final AlKhwarizmixDAOException e) {
			trans.rollback();
			getLogger()
					.warn("createDefaultClients: default client <{}> already existing",
							defaultClient.getName());
		} finally {
			if (isOpenSession)
				session.close();
		}
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(ClientDAO.class);

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Client getClient(Client clientToGet) throws MoqawalatiException {
		getLogger().trace("getClient()");

		try {
			final String clientId = clientToGet.getClientId();
			final Criteria criteria = getHibernateCurrentSession()
					.createCriteria(Client.class);
			criteria.add(Restrictions.eq(Client.CLIENTID, clientId));
			clientToGet = (Client) criteria.uniqueResult();
			return clientToGet;
		} catch (final DataAccessException e) {
			final MoqawalatiException ex = new MoqawalatiException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

} // Class
