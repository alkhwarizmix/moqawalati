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

package dz.alkhwarizmix.framework.java.services;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Group;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IUserDAO;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
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
@SuppressWarnings("PMD.MethodNamingConventions")
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
	private IUserDAO mockUserDAO;

	@InjectMocks
	private UserServiceValidator userValidator;

	@Mock
	private UserServiceValidator mockUserValidator;

	@Mock
	private Jaxb2Marshaller mockJaxb2Marshaller;

	@Spy
	private AlKhwarizmixSessionData spySessionData;

	@Before
	public void setUp() throws AlKhwarizmixException {
		setupUtUserService();
		setupMockJaxb2Marshaller();
		setupMockUserService();
		when(mockUserValidator.isValidUserId(any(User.class))).thenReturn(true);
	}

	private void setupUtUserService() {
		utUserService.setUserDAO(mockUserDAO);
		utUserService.setUserServiceValidator(userValidator);
		utUserService.setSessionData(spySessionData);
		utUserService.setJaxb2Marshaller(mockJaxb2Marshaller);
	}

	private void setupMockJaxb2Marshaller() {
		when(mockJaxb2Marshaller.unmarshal(any(Source.class))).thenReturn(
				new User());
	}

	private void setupMockUserService() {
		when(mockUserService.getLogger()).thenCallRealMethod();
		mockUserService.setUserServiceValidator(userValidator);
		mockUserService.setSessionData(spySessionData);
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

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test01_unmarshalObjectFromXML() throws AlKhwarizmixException {
		String userAsXML = "<User id=\"1\"><Name>User1</Name></User>";
		utUserService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		User user = (User) utUserService.unmarshalObjectFromXML(userAsXML); // TEST
		Assert.assertEquals("1", user.getUserId());
		Assert.assertEquals("User1", user.getName());
	}

	@Test
	public void test02_marshalObjectToXML() throws AlKhwarizmixException {
		utUserService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		String userAsXML = utUserService.marshalObjectToXML(new User("746")); // TEST
		Assert.assertEquals("<User id=\"746\"/>", userAsXML);
	}

	@Test
	public void test03_addUser_calls_dao_saveOrUpdate()
			throws AlKhwarizmixException {
		utUserService.addUser(new User()); // TEST
		verify(mockUserDAO, times(1)).saveOrUpdate(
				any(AbstractAlKhwarizmixDomainObject.class));
	}

	@Test
	public void test04_getObject_should_call_dao_getUser()
			throws AlKhwarizmixException {
		utUserService.getObject(new User()); // TEST
		verify(mockUserDAO, times(1)).getUser(any(User.class));
	}

	@Test
	public void test05_A_getUser_should_not_return_id()
			throws AlKhwarizmixException {
		User expectedUser = new User();
		expectedUser.setId(324L);
		when(mockUserDAO.getUser(any(User.class))).thenReturn(expectedUser);
		User foundUser = utUserService.getUser(new User()); // TEST
		Assert.assertNull(foundUser.getId());
	}

	@Test
	public void test05_B_getUser_should_not_return_domainObject()
			throws AlKhwarizmixException {
		User expectedUser = new User();
		expectedUser.setDomainObject(new AlKhwarizmixDomainObject());
		when(mockUserDAO.getUser(any(User.class))).thenReturn(expectedUser);
		User foundUser = utUserService.getUser(new User()); // TEST
		Assert.assertNull(foundUser.getDomainObject());
	}

	@Test
	public void test06_loginFromXML_should_call_unmarshal_marshal()
			throws AlKhwarizmixException {
		when(mockUserDAO.getUser(any(User.class))).thenReturn(new User());
		utUserService.setUserServiceValidator(mockUserValidator);
		utUserService.loginFromXML("", "Mohamed"); // TEST
		verify(mockJaxb2Marshaller, times(1)).unmarshal(any(Source.class));
		verify(mockJaxb2Marshaller, times(1)).marshal(any(Object.class),
				any(Result.class));
	}

	@Test
	public void test07_A_login_should_return_internal_getUser_not_null_result()
			throws AlKhwarizmixException {
		User userToFind = new User("u1@alkhwarizmix.com");
		User existingUser = new User("u2@alkhwarizmix.com");
		when(mockUserService.internal_getUser(any(User.class))).thenReturn(
				existingUser);
		when(mockUserService.login(any(User.class), anyString()))
				.thenCallRealMethod();
		User loggedUser = mockUserService.login(userToFind, "Mohamed"); // TEST
		Assert.assertEquals(existingUser, loggedUser);
		verify(mockUserService, times(1)).internal_getUser(
				Mockito.eq(userToFind));
	}

	@Test
	public void test07_B_login_should_throw_exception_when_internal_getUser_return_null()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("login2.");
		User userToFind = new User("u1@alkhwarizmix.com");
		when(mockUserService.internal_getUser(Mockito.eq(userToFind)))
				.thenReturn(null);
		when(mockUserService.login(any(User.class), anyString()))
				.thenCallRealMethod();
		mockUserService.login(userToFind, "Mohamed"); // TEST
	}

	@Test
	public void test7_C_login_should_set_session_customizer()
			throws AlKhwarizmixException {
		User userToLogin = new User("u1@alkhwarizmix.com");
		userToLogin.setDomainObject(new AlKhwarizmixDomainObject());
		when(mockUserDAO.getUser(any(User.class))).thenReturn(userToLogin);
		utUserService.login(userToLogin, "Mohamed"); // TEST
		Assert.assertEquals(userToLogin.getDomainObject(),
				spySessionData.getSessionOwner());
	}

	@Test
	public void test07_D_login_should_throw_exception_if_user_is_null()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("login1.");
		utUserService.login(null, "Mohamed"); // TEST
	}

	public void test07_E_login_should_throw_exception_if_userId_is_not_email_address()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("login1.");
		User userToLogin = new User("id@@alkhwarizmix.com");
		utUserService.login(userToLogin, "Mohamed"); // TEST
	}

	@Test
	public void testD01_logout_should_reset_customizer()
			throws AlKhwarizmixException {
		User userToLogin = new User();
		userToLogin.setDomainObject(new AlKhwarizmixDomainObject());
		when(mockUserDAO.getUser(any(User.class))).thenReturn(userToLogin);
		utUserService.logout(userToLogin); // TEST
		verify(spySessionData, times(1)).resetSessionOwner();
	}

	@Test
	public void test02_A_connect_should_not_connect_if_already_connected()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("connect1.");
		User userToLogin = new User();
		userToLogin.setDomainObject(new AlKhwarizmixDomainObject());
		when(spySessionData.getConnectedUser()).thenReturn(new User());
		utUserService.connect(userToLogin); // TEST
	}

	@Test
	public void test02_B_connect_should_return_internal_getUser_not_null_result()
			throws AlKhwarizmixException {
		User userToFind = new User("u1@alkhwarizmix.com", "User 1");
		User existingUser = new User("u1@alkhwarizmix.com", "User 2");
		existingUser.setGroup(new Group());
		when(mockUserService.internal_getUser(any(User.class))).thenReturn(
				existingUser);
		when(mockUserService.connect(any(User.class))).thenCallRealMethod();
		User connectedUser = mockUserService.connect(userToFind); // TEST
		Assert.assertNotSame(existingUser, connectedUser);
		Assert.assertEquals(existingUser.getUserId(), connectedUser.getUserId());
		Assert.assertEquals("User 2", connectedUser.getName());
		Assert.assertNull(connectedUser.getGroup());
		verify(mockUserService, times(1)).internal_getUser(
				Mockito.eq(userToFind));
	}

	@Test
	public void test02_C_connect_should_throw_exception_if_user_is_null()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("connect2.");
		utUserService.connect(null); // TEST
	}

	@Test
	public void test02_D_connect_should_throw_exception_if_userId_is_not_email_address()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("connect2.");
		User userToLogin = new User("id@@alkhwarizmix.com");
		utUserService.connect(userToLogin); // TEST
	}

	@Test
	public void test02_E_connect_should_save_connectedUser_in_sessionData()
			throws AlKhwarizmixException {
		User userToLogin = new User("fares@alkhwarizmix.com");
		User existingUser = new User("fares@alkhwarizmix.com");
		when(mockUserDAO.getUser(Mockito.eq(userToLogin))).thenReturn(
				existingUser);
		utUserService.connect(userToLogin); // TEST
		verify(spySessionData, times(1)).setConnectedUser(
				Mockito.eq(existingUser));
		Assert.assertEquals(userToLogin, spySessionData.getConnectedUser());
	}

} // Class
