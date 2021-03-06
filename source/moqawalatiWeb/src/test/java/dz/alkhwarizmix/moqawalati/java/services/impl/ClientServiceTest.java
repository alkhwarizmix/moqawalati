////////////////////////////////////////////////////////////////////////////////
//بسم الله الرحمن الرحيم
//
//حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)
//كافة الحقوق محفوظة (All Rights Reserved)
//
//NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.services.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.xml.transform.Source;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dao.IClientDAO;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client;
import dz.alkhwarizmix.moqawalati.java.testutils.HelperTestUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ذو الحجة ١٤٣٤ (October 12, 2013)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class ClientServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private ClientService utClientService;

	@Mock
	private IClientDAO mockClientDAO;

	@InjectMocks
	private ClientServiceValidator clientValidator;

	@Mock
	private Jaxb2Marshaller mockJaxb2Marshaller;

	@Before
	public void setUp() throws MoqawalatiException {
		setupUtClientService();
		setupMockJaxb2Marshaller();
	}

	private void setupUtClientService() {
		utClientService.setClientDAO(mockClientDAO);
		utClientService.setClientValidator(clientValidator);
		utClientService.setJaxb2Marshaller(mockJaxb2Marshaller);
	}

	private void setupMockJaxb2Marshaller() {
		Mockito.when(mockJaxb2Marshaller.unmarshal(any(Source.class)))
				.thenReturn(new User());
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private Jaxb2Marshaller getRealJaxb2Marshaller() {
		return new HelperTestUtil().getRealJaxb2Marshaller();
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void testA01_unmarshalObjectFromXML() throws AlKhwarizmixException {
		final String clientAsXML = "<Client id=\"1\"><Name>Client1</Name></Client>";
		utClientService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		final Client client = (Client) utClientService
				.unmarshalObjectFromXML(clientAsXML); // TEST
		Assert.assertEquals("1", client.getClientId());
		Assert.assertEquals("Client1", client.getName());
	}

	@Test
	public void testA02_marshalObjectToXML() throws AlKhwarizmixException {
		utClientService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		final String clientAsXML = utClientService
				.marshalObjectToXML(new Client("746")); // TEST
		Assert.assertEquals("<Client id=\"746\"/>", clientAsXML);
	}

	@Test
	public void testA03_addClient_calls_dao_saveOrUpdate()
			throws AlKhwarizmixException {
		utClientService.addClient(new Client(), true); // TEST
		verify(mockClientDAO, times(1)).saveOrUpdate(
				any(AbstractAlKhwarizmixDomainObject.class));
	}

	@Test
	public void testA04_getObject_should_call_dao_getUser()
			throws AlKhwarizmixException {
		utClientService.getObject(new Client(), true); // TEST
		verify(mockClientDAO, times(1)).getClient(any(Client.class));
	}

	@Test
	public void testA05_getClient_should_not_return_id()
			throws MoqawalatiException {
		final Client expectedClient = new Client();
		expectedClient.setId(324L);
		Mockito.when(mockClientDAO.getClient(any(Client.class))).thenReturn(
				expectedClient);
		final Client foundClient = utClientService
				.getClient(new Client(), true); // TEST
		Assert.assertNull(foundClient.getId());
	}

	@Test
	public void testZ01_getClientAsJSON() throws Exception {
		final Client client = new Client();
		client.setName("Fares");
		final String s = utClientService.getClientAsJSON(client);
		Assert.assertNotNull(s);
	}

} // Class