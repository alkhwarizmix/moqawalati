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
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Group;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Password;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IEMailService;
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

	@InjectMocks
	private AlKhwarizmixSessionData sessionData;

	@Mock
	private IEMailService mockEmailService;

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
		utUserService.setSessionData(sessionData);
		utUserService.setJaxb2Marshaller(mockJaxb2Marshaller);
	}

	private void setupMockJaxb2Marshaller() {
		when(mockJaxb2Marshaller.unmarshal(any(Source.class))).thenReturn(
				new User());
	}

	private void setupMockUserService() {
		mockUserService.setUserServiceValidator(userValidator);
		mockUserService.setSessionData(sessionData);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private Jaxb2Marshaller getRealJaxb2Marshaller() {
		return new HelperTestUtil().getRealJaxb2Marshaller();
	}

	private void setupMockUserDAO_getUserPasswords(final String password)
			throws AlKhwarizmixException {
		final Password userPassword = new Password();
		userPassword.setPassword(password);
		final List<Password> userPasswords = new ArrayList<Password>();
		userPasswords.add(userPassword);
		when(mockUserDAO.getUserPasswords(any(User.class))).thenReturn(
				userPasswords);
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
		final String userAsXML = "<User id=\"1\"><Name>User1</Name></User>";
		utUserService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		final User user = (User) utUserService
				.unmarshalObjectFromXML(userAsXML); // TEST
		Assert.assertEquals("1", user.getUserId());
		Assert.assertEquals("User1", user.getName());
	}

	@Test
	public void test02_marshalObjectToXML() throws AlKhwarizmixException {
		utUserService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		final String userAsXML = utUserService.marshalObjectToXML(new User(
				"746")); // TEST
		Assert.assertEquals("<User id=\"746\"/>", userAsXML);
	}

	@Test
	public void test03_addUser_calls_dao_saveOrUpdate()
			throws AlKhwarizmixException {
		utUserService.addUser(new User(), true); // TEST
		verify(mockUserDAO, times(1)).saveOrUpdate(
				any(AbstractAlKhwarizmixDomainObject.class));
	}

	@Test
	public void test04_getObject_should_call_dao_getUser()
			throws AlKhwarizmixException {
		utUserService.getObject(new User(), true); // TEST
		verify(mockUserDAO, times(1)).getUser(any(User.class));
	}

	@Test
	public void test05_A_getUser_should_not_return_id()
			throws AlKhwarizmixException {
		final User expectedUser = new User();
		expectedUser.setId(324L);
		when(mockUserDAO.getUser(any(User.class))).thenReturn(expectedUser);
		final User foundUser = utUserService.getUser(new User(), true); // TEST
		Assert.assertNull(foundUser.getId());
	}

	@Test
	public void test05_B_getUser_should_not_return_domainObject()
			throws AlKhwarizmixException {
		final User expectedUser = new User();
		expectedUser.setDomainObject(new AlKhwarizmixDomainObject());
		when(mockUserDAO.getUser(any(User.class))).thenReturn(expectedUser);
		final User foundUser = utUserService.getUser(new User(), true); // TEST
		Assert.assertNull(foundUser.getDomainObject());
	}

	@Test
	public void test06_loginFromXML_should_call_unmarshal_marshal()
			throws AlKhwarizmixException {
		// SETUP
		when(mockUserDAO.getUser(any(User.class))).thenReturn(new User());
		utUserService.setUserServiceValidator(mockUserValidator);
		sessionData.setConnectedUser(new User());
		setupMockUserDAO_getUserPasswords("Mohamed");
		// TEST
		utUserService.loginFromXML("", "Mohamed");
		// ASSERTS
		verify(mockJaxb2Marshaller, times(1)).unmarshal(any(Source.class));
		verify(mockJaxb2Marshaller, times(1)).marshal(any(Object.class),
				any(Result.class));
	}

	@Test
	public void test07_A_login_should_return_internal_getUser_not_null_result()
			throws AlKhwarizmixException {
		// SETUP
		final User userToFind = new User("u1@dz.alkhwarizmix.com");
		final User existingUser = new User("u2@dz.alkhwarizmix.com");
		when(mockUserService.getUser(any(User.class), anyBoolean()))
				.thenReturn(existingUser);
		when(mockUserService.login(any(User.class), anyString(), anyBoolean()))
				.thenCallRealMethod();
		sessionData.setConnectedUser(new User("u1@dz.alkhwarizmix.com"));
		mockUserService.setUserDAO(mockUserDAO);
		setupMockUserDAO_getUserPasswords("Mohamed");
		// TEST
		final User loggedUser = mockUserService.login(userToFind, "Mohamed",
				false);
		// ASSERTS
		Assert.assertEquals(existingUser, loggedUser);
		verify(mockUserService, times(1)).getUser(eq(userToFind), eq(false));
	}

	@Ignore("TODO: TDD")
	@Test
	public void test07_B_login_should_add_user_and_send_email_when_internal_getUser_return_null()
			throws AlKhwarizmixException {
		// SETUP
		final User userToFind = new User("u1@dz.alkhwarizmix.com");
		when(mockUserService.getUser(eq(userToFind), anyBoolean())).thenReturn(
				null);
		when(mockUserService.login(any(User.class), anyString(), anyBoolean()))
				.thenCallRealMethod();
		sessionData.setConnectedUser(new User("u1@dz.alkhwarizmix.com"));
		mockUserService.setEmailService(mockEmailService);
		// TEST
		mockUserService.login(userToFind, "PasswordIsNotImportant", true);
		// ASSERTS
		verify(mockEmailService, times(1)).sendEMail(any(EMail.class));
		verify(mockUserService, times(1)).addObject(eq(userToFind), eq(true));
	}

	@Test
	public void test07_C_login_should_set_logged_user_and_session_owner()
			throws AlKhwarizmixException {
		// SETUP
		final User userToLogin = new User("u1@dz.alkhwarizmix.com");
		final AlKhwarizmixDomainObject userToLoginDomainObject = new AlKhwarizmixDomainObject();
		userToLogin.setDomainObject(userToLoginDomainObject);
		when(mockUserDAO.getUser(any(User.class))).thenReturn(userToLogin);
		sessionData.setConnectedUser(new User("u1@dz.alkhwarizmix.com"));
		setupMockUserDAO_getUserPasswords("Mohamed");
		// TEST
		utUserService.login(userToLogin, "Mohamed", true);
		// ASSERTS
		Assert.assertEquals(userToLogin, sessionData.getLoggedUser());
		Assert.assertEquals(userToLoginDomainObject,
				sessionData.getSessionOwner());
	}

	@Test
	public void test07_D_login_should_throw_exception_if_user_is_null()
			throws AlKhwarizmixException {
		// EXPECTED EXCEPTION
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("login1.");
		// TEST
		utUserService.login(null, "PasswordIsNotImportant", true);
	}

	@Test
	public void test07_E_login_should_throw_exception_if_userId_is_not_email_address()
			throws AlKhwarizmixException {
		// EXPECTED EXCEPTION
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("login1.");
		final User userToLogin = new User("wrong@@dz.alkhwarizmix.com");
		// TEST
		utUserService.login(userToLogin, "PasswordIsNotImportant", true);
	}

	@Test
	public void test07_F_login_should_throw_exception_if_user_is_not_connected()
			throws AlKhwarizmixException {
		// EXPECTED EXCEPTION
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("login3.");
		// SETUP
		sessionData.setConnectedUser(new User("u2@dz.alkhwarizmix.com"));
		final User userToLogin = new User("u1@dz.alkhwarizmix.com");
		// TEST
		utUserService.login(userToLogin, "PasswordIsNotImportant", true);
	}

	@Test
	public void test07_G_login_should_throw_exception_if_a_user_is_logged()
			throws AlKhwarizmixException {
		// EXPECTED EXCEPTION
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("login2.");
		// SETUP
		sessionData.setLoggedUser(new User(""));
		final User userToLogin = new User("u1@dz.alkhwarizmix.com");
		// TEST
		utUserService.login(userToLogin, "PasswordIsNotImportant", true);
	}

	@Test
	public void test07_H_login_should_return_logged_user_validated_before_to_publish()
			throws AlKhwarizmixException {
		// SETUP
		final User userToLogin = new User("u1@dz.alkhwarizmix.com");
		when(mockUserDAO.getUser(any(User.class))).thenReturn(userToLogin);
		sessionData.setConnectedUser(new User("u1@dz.alkhwarizmix.com"));
		utUserService.setUserServiceValidator(mockUserValidator);
		setupMockUserDAO_getUserPasswords("Mohamed");
		// TEST
		utUserService.login(userToLogin, "Mohamed", true);
		// ASSERTS
		verify(mockUserValidator, times(1)).validateObjectToPublish(
				eq(userToLogin), any(AlKhwarizmixDomainObject.class));
	}

	@Test
	public void test07_I_login_should_validate_password_using_dao_getUserPasswords()
			throws AlKhwarizmixException {
		// SETUP
		final User userToLogin = new User("u1@dz.alkhwarizmix.com");
		when(mockUserDAO.getUser(any(User.class))).thenReturn(userToLogin);
		sessionData.setConnectedUser(new User("u1@dz.alkhwarizmix.com"));
		utUserService.setUserServiceValidator(mockUserValidator);
		setupMockUserDAO_getUserPasswords("Good Password");
		// TEST
		utUserService.login(userToLogin, "Good Password", true);
		// ASSERTS
		verify(mockUserDAO, times(1)).getUserPasswords(eq(userToLogin));
	}

	@Test
	public void test07_J_login_should_throw_exception_if_password_is_worng()
			throws AlKhwarizmixException {
		// EXPECTED EXCEPTION
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("login4.");
		// SETUP
		final User userToLogin = new User("u1@dz.alkhwarizmix.com");
		when(mockUserDAO.getUser(any(User.class))).thenReturn(userToLogin);
		sessionData.setConnectedUser(new User("u1@dz.alkhwarizmix.com"));
		utUserService.setUserServiceValidator(mockUserValidator);
		setupMockUserDAO_getUserPasswords("Good Password");
		// TEST
		utUserService.login(userToLogin, "Wrong Password", true);
	}

	@Test
	public void test08_logout_should_reset_session()
			throws AlKhwarizmixException {
		// SETUP
		final User userToLogin = new User();
		userToLogin.setDomainObject(new AlKhwarizmixDomainObject());
		when(mockUserDAO.getUser(any(User.class))).thenReturn(userToLogin);
		final AlKhwarizmixDomainObject sessionOwner = new AlKhwarizmixDomainObject();
		sessionData.setSessionOwner(sessionOwner);
		sessionData.setLoggedUser(userToLogin);
		sessionData.setConnectedUser(userToLogin);
		// TEST
		utUserService.logout(userToLogin);
		// ASSERTS
		Assert.assertNull(sessionData.getLoggedUser());
		Assert.assertNull(sessionData.getConnectedUser());
		Assert.assertNotSame(sessionOwner, sessionData.getSessionOwner());
	}

	@Test
	public void test09_A_connect_should_not_connect_if_already_connected()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("connect1.");
		final User userToLogin = new User();
		userToLogin.setDomainObject(new AlKhwarizmixDomainObject());
		sessionData.setConnectedUser(new User());
		utUserService.connect(userToLogin, true); // TEST
	}

	@Test
	public void test09_B_connect_should_return_internal_getUser_not_null_result()
			throws AlKhwarizmixException {
		// SETUP
		final User userToFind = new User("u1@dz.alkhwarizmix.com", "User 1");
		final User existingUser = new User("u1@dz.alkhwarizmix.com", "User 2");
		existingUser.setGroup(new Group());
		when(mockUserService.getUser(any(User.class), anyBoolean()))
				.thenReturn(existingUser);
		when(mockUserService.connect(any(User.class), anyBoolean()))
				.thenCallRealMethod();
		// TEST
		final User connectedUser = mockUserService.connect(userToFind, true);
		// ASSERTS
		Assert.assertNotSame(existingUser, connectedUser);
		Assert.assertEquals(existingUser.getUserId(), connectedUser.getUserId());
		Assert.assertEquals("User 2", connectedUser.getName());
		Assert.assertNull(connectedUser.getGroup());
		verify(mockUserService, times(1)).getUser(eq(userToFind), eq(false));
	}

	@Test
	public void test09_C_connect_should_throw_exception_if_user_is_null()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("connect2.");
		utUserService.connect(null, true); // TEST
	}

	@Test
	public void test09_D_connect_should_throw_exception_if_userId_is_not_email_address()
			throws AlKhwarizmixException {
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("connect2.");
		final User userToLogin = new User("id@@dz.alkhwarizmix.com");
		utUserService.connect(userToLogin, true); // TEST
	}

	@Test
	public void test09_E_connect_should_save_connectedUser_in_sessionData()
			throws AlKhwarizmixException {
		final User userToLogin = new User("fares@dz.alkhwarizmix.com");
		final User existingUser = new User("fares@dz.alkhwarizmix.com");
		when(mockUserDAO.getUser(eq(userToLogin))).thenReturn(existingUser);
		utUserService.connect(userToLogin, true); // TEST
		Assert.assertEquals(userToLogin, sessionData.getConnectedUser());
	}

	@Test
	public void test10_logoutFromXML_should_call_unmarshal()
			throws AlKhwarizmixException {
		// SETUP
		when(mockUserDAO.getUser(any(User.class))).thenReturn(new User());
		utUserService.setUserServiceValidator(mockUserValidator);
		sessionData.setConnectedUser(new User());
		// TEST
		utUserService.logoutFromXML("");
		// ASSERTS
		verify(mockJaxb2Marshaller, times(1)).unmarshal(any(Source.class));
	}

	@Test
	public void test11_A_suscribe() throws AlKhwarizmixException {
		// SETUP
		final User userToSuscribe = new User("u1@dz.alkhwarizmix.com",
				"Fares Belhaouas");
		when(mockUserService.getUser(eq(userToSuscribe), anyBoolean()))
				.thenReturn(null);
		when(mockUserService.subscribe(any(User.class), anyBoolean()))
				.thenCallRealMethod();
		when(mockUserService.addUser(any(User.class), anyBoolean()))
				.thenReturn(userToSuscribe);
		sessionData.setConnectedUser(new User("u1@dz.alkhwarizmix.com"));
		mockUserService.setEmailService(mockEmailService);
		when(mockUserService.addObject(any(Password.class), eq(false)))
				.thenReturn(new Password());
		// TEST
		mockUserService.subscribe(userToSuscribe, true);
		// ASSERTS
		verify(mockUserService, times(1))
				.addUser(eq(userToSuscribe), eq(false));
		verify(mockEmailService, times(1))
				.addEMail(any(EMail.class), eq(false));
	}

	@Ignore("TODO: TDD")
	@Test
	public void test11_B_suscribe_TDD() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test12_suscribeFromXML_TDD() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

	@Test
	public void test13_validateUserPasswordForJMeter()
			throws AlKhwarizmixException {
		Assert.assertTrue(utUserService.validateUserPasswordForJMeter(new User(
				"UTEST1234-1@dz.alkhwarizmix.com"), "Mohamed"));
		Assert.assertFalse(utUserService.validateUserPasswordForJMeter(
				new User("UTEST1234-1@dz.alkhwarizmix.com"), "Wrong Password"));
		Assert.assertFalse(utUserService.validateUserPasswordForJMeter(
				new User("U1234-1@dz.alkhwarizmix.com"), "Mohamed"));
		Assert.assertFalse(utUserService.validateUserPasswordForJMeter(
				new User("UTEST1234-1@dzz.alkhwarizmix.com"), "Mohamed"));
	}

} // Class
