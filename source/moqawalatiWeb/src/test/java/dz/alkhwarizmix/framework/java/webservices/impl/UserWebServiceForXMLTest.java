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

package dz.alkhwarizmix.framework.java.webservices.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletRequestAttributes;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.services.IUserService;

/**
 * <p>
 * Unit Test for UserWebServiceForXML
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٨ ربيع الأول ١٤٣٥ (January 19, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class UserWebServiceForXMLTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private UserWebServiceForXML utUserWebServiceForXML;

	@Mock
	private IUserService mockUserService;

	public ServletRequestAttributes mockServletRequestAttributes;

	@Mock
	public HttpServletRequest mockHttpServletRequest;

	@Before
	public void setUp() throws Exception {
		when(mockHttpServletRequest.getRemoteAddr()).thenReturn("");
		setupMockServletRequestAttributes();
		setupMockUserService();
	}

	private void setupMockServletRequestAttributes() {
		mockServletRequestAttributes = new ServletRequestAttributes(
				mockHttpServletRequest);
		utUserWebServiceForXML
				.setServletRequestAttributes(mockServletRequestAttributes);
	}

	private void setupMockUserService() throws AlKhwarizmixException {
		when(mockUserService.loginFromXML(any(String.class), any(String.class)))
				.thenReturn("");
		when(mockUserService.connectFromXML(any(String.class))).thenReturn("");
		utUserWebServiceForXML.setUserService(mockUserService);
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_A_login_should_call_service_loginFromXML()
			throws Exception {
		final String userAsXML = "<User/>";
		final String password = "PasswordIsNotImportant";
		utUserWebServiceForXML.login(userAsXML, password);
		verify(mockUserService, times(1)).loginFromXML(eq(userAsXML),
				eq(password));
	}

	@Test
	public void test01_B_login_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null)).when(mockUserService)
				.loginFromXML(anyString(), anyString());
		final ResponseEntity<String> result = utUserWebServiceForXML.login("",
				"");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Test
	public void test02_A_connect_should_call_service_connectFromXML()
			throws Exception {
		final String userAsXML = "<User/>";
		utUserWebServiceForXML.connect(userAsXML);
		verify(mockUserService, times(1)).connectFromXML(eq(userAsXML));
	}

	@Test
	public void test02_B_connect_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null)).when(mockUserService)
				.connectFromXML(anyString());
		final ResponseEntity<String> result = utUserWebServiceForXML
				.connect("");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Test
	public void test03_A_logout_should_call_service_logoutFromXML()
			throws Exception {
		final String userAsXML = "<User/>";
		utUserWebServiceForXML.logout(userAsXML);
		verify(mockUserService, times(1)).logoutFromXML(eq(userAsXML));
	}

	@Test
	public void test03_B_logout_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null)).when(mockUserService)
				.logoutFromXML(anyString());
		final ResponseEntity<String> result = utUserWebServiceForXML.logout("");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Test
	public void test04_A_subscribe_should_call_service_subscribeFromXML()
			throws Exception {
		final String userAsXML = "<User/>";
		utUserWebServiceForXML.subscribe(userAsXML);
		verify(mockUserService, times(1)).subscribeFromXML(eq(userAsXML));
	}

	@Test
	public void test04_B_subscribe_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null)).when(mockUserService)
				.subscribeFromXML(anyString());
		final ResponseEntity<String> result = utUserWebServiceForXML
				.subscribe("");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

} // Class
