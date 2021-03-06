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

import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.impl.AlKhwarizmixDAOException;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@SuppressWarnings("PMD.MethodNamingConventions")
public class ClientDAOTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IClientDAO utClientDAO;

	private Transaction transaction;

	@Before
	public void setUp() throws AlKhwarizmixDAOException {
		transaction = utClientDAO.beginTransaction();
	}

	@After
	public void tearDown() throws AlKhwarizmixDAOException {
		utClientDAO.rollbackTransaction(transaction);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private Client newClient() {
		final Client client = new Client();
		client.setClientId("clientId");
		client.setName("userName");
		// client.setCreatorId("creatorId");
		return client;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test02_A_add_get_then_update_get_Client()
			throws AlKhwarizmixException {

		Assert.assertNull(utClientDAO.getClient(newClient()));

		utClientDAO.saveOrUpdate(newClient());

		Client savedClient = utClientDAO.getClient(newClient());
		Assert.assertNotNull(savedClient);
		Assert.assertEquals(newClient().getClientId(),
				savedClient.getClientId());
		Assert.assertEquals(newClient().getName(), savedClient.getName());
		// Assert.assertNotNull(savedClient.getDomainObject());

		savedClient.setName("updatedName");
		utClientDAO.saveOrUpdate(savedClient);

		savedClient = utClientDAO.getClient(newClient());
		Assert.assertEquals("updatedName", savedClient.getName());
	}

	// ----- -----

	@Test
	public void test02_B_add_get_then_update_get_Client_using_clear_and_flush()
			throws AlKhwarizmixException {

		Assert.assertNull(utClientDAO.getClient(newClient()));

		utClientDAO.saveOrUpdate(newClient());
		utClientDAO.flush();
		utClientDAO.clear();

		Client savedClient = utClientDAO.getClient(newClient());
		Assert.assertNotNull(savedClient);
		Assert.assertEquals(newClient().getClientId(),
				savedClient.getClientId());
		Assert.assertEquals(newClient().getName(), savedClient.getName());
		// Assert.assertNotNull(savedClient.getDomainObject());

		savedClient.setName("updatedName");
		utClientDAO.saveOrUpdate(savedClient);
		utClientDAO.flush();
		utClientDAO.clear();

		savedClient = utClientDAO.getClient(newClient());
		Assert.assertEquals("updatedName", savedClient.getName());
	}

	// ----- -----

	@Test
	public void test02_default_client_was_created()
			throws AlKhwarizmixException {

		final Client clientToFind = new Client("fares.belhaouas");
		final Client clientUser = utClientDAO.getClient(clientToFind);
		Assert.assertNotNull(clientUser);
		Assert.assertEquals("فارس بلحواس", clientUser.getName());
	}

} // Class