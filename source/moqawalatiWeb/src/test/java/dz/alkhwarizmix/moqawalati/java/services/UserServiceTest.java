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

package dz.alkhwarizmix.moqawalati.java.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.dao.MoqawalatiDAO;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.userModule.model.vo.User;
import dz.alkhwarizmix.moqawalati.java.testutils.HelperTestUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private UserService utUserService;

	@Mock
	private UserService mockUserService;

	@Mock
	private MoqawalatiDAO mockMoqawalatiDAO;

	@Mock
	private Jaxb2Marshaller mockJaxb2Marshaller;

	@Spy
	private AlKhwarizmixSessionData spySessionData;

	@Before
	public void setUp() throws MoqawalatiException {
		setupUtUserService();
		setupMockJaxb2Marshaller();
		setupMockUserService();
	}

	private void setupUtUserService() {
		utUserService.setMoqawalatiDAO(mockMoqawalatiDAO);
		utUserService.setSessionData(spySessionData);
		utUserService.setJaxb2Marshaller(mockJaxb2Marshaller);
	}

	private void setupMockJaxb2Marshaller() {
		Mockito.when(mockJaxb2Marshaller.unmarshal(any(Source.class)))
				.thenReturn(new User());
	}

	private void setupMockUserService() {
		Mockito.when(mockUserService.getLogger()).thenCallRealMethod();
		Mockito.when(mockUserService.getSessionData()).thenReturn(
				spySessionData);
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
		String userAsXML = "<User id=\"1\"><Name>User1</Name></User>";
		utUserService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		User user = (User) utUserService.unmarshalObjectFromXML(userAsXML); // TEST
		Assert.assertEquals("1", user.getUserId());
		Assert.assertEquals("User1", user.getName());
	}

	@Test
	public void testA02_marshalObjectToXML() throws AlKhwarizmixException {
		utUserService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		String userAsXML = utUserService.marshalObjectToXML(new User("746")); // TEST
		Assert.assertEquals("<User id=\"746\"/>", userAsXML);
	}

	@Test
	public void testA03_addUser_calls_dao_saveOrUpdate()
			throws AlKhwarizmixException {
		utUserService.addUser(new User()); // TEST
		verify(mockMoqawalatiDAO, times(1)).saveOrUpdate(
				any(AlKhwarizmixDomainObjectAbstract.class));
	}

	@Test
	public void testA04_getObject_should_call_dao_getUser()
			throws AlKhwarizmixException {
		utUserService.getObject(new User()); // TEST
		verify(mockMoqawalatiDAO, times(1)).getUser(any(User.class));
	}

	@Test
	public void testA05_getUser_should_not_return_id()
			throws MoqawalatiException {
		User expectedUser = new User();
		expectedUser.setId(324L);
		Mockito.when(mockMoqawalatiDAO.getUser(any(User.class))).thenReturn(
				expectedUser);
		User foundUser = utUserService.getUser(new User()); // TEST
		Assert.assertNull(foundUser.getId());
	}

	@Test
	public void testB01_getUser_should_not_return_domainObject()
			throws MoqawalatiException {
		User expectedUser = new User();
		expectedUser.setDomainObject(new AlKhwarizmixDomainObject());
		Mockito.when(mockMoqawalatiDAO.getUser(any(User.class))).thenReturn(
				expectedUser);
		User foundUser = utUserService.getUser(new User()); // TEST
		Assert.assertNull(foundUser.getDomainObject());
	}

	@Test
	public void testC01_loginFromXML_should_call_unmarshal_marshal()
			throws MoqawalatiException {
		Mockito.when(mockMoqawalatiDAO.getUser(any(User.class))).thenReturn(
				new User());
		utUserService.loginFromXML("", ""); // TEST
		verify(mockJaxb2Marshaller, times(1)).unmarshal(any(Source.class));
		verify(mockJaxb2Marshaller, times(1)).marshal(any(Object.class),
				any(Result.class));
	}

	@Test
	public void testC02_login_should_return_internal_getUser_not_null_result()
			throws MoqawalatiException {
		User userToFind = new User("user124");
		User existingUser = new User("user124");
		Mockito.when(mockUserService.internal_getUser(any(User.class)))
				.thenReturn(existingUser);
		Mockito.when(mockUserService.login(any(User.class)))
				.thenCallRealMethod();
		User loggedUser = mockUserService.login(userToFind); // TEST
		Assert.assertEquals(existingUser, loggedUser);
		verify(mockUserService, times(1)).internal_getUser(
				Mockito.eq(userToFind));
	}

	@Test(expected = MoqawalatiException.class)
	public void testC03_login_should_throw_exception_when_internal_getUser_return_null()
			throws MoqawalatiException {
		User userToFind = new User("user124");
		Mockito.when(mockUserService.internal_getUser(Mockito.eq(userToFind)))
				.thenReturn(null);
		Mockito.when(mockUserService.login(any(User.class)))
				.thenCallRealMethod();
		mockUserService.login(userToFind); // TEST
	}

	@Test
	public void testC04_login_should_set_session_customizer()
			throws MoqawalatiException {
		User userToLogin = new User();
		userToLogin.setDomainObject(new AlKhwarizmixDomainObject());
		Mockito.when(mockMoqawalatiDAO.getUser(any(User.class))).thenReturn(
				userToLogin);
		utUserService.login(userToLogin); // TEST
		Assert.assertEquals(userToLogin.getDomainObject(),
				spySessionData.getCustomizer());
	}

} // Class