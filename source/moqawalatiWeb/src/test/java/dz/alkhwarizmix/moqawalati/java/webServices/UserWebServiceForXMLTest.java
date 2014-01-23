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

package dz.alkhwarizmix.moqawalati.java.webServices;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.context.request.ServletRequestAttributes;

import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.interfaces.IUserService;

/**
 * <p>
 * Unit Test for UserWebServiceForXML
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٨ ربيع الأول ١٤٣٥ (January 19, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
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
		Mockito.when(mockHttpServletRequest.getRemoteAddr()).thenReturn("");
		setupMockServletRequestAttributes();
		setupMockUserService();
	}

	private void setupMockServletRequestAttributes() {
		mockServletRequestAttributes = new ServletRequestAttributes(
				mockHttpServletRequest);
		utUserWebServiceForXML
				.setServletRequestAttributes(mockServletRequestAttributes);
	}

	private void setupMockUserService() throws MoqawalatiException {
		Mockito.when(
				mockUserService.loginFromXML(any(String.class),
						any(String.class))).thenReturn("");
		utUserWebServiceForXML.setUserService(mockUserService);
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_login_should_call_service_loginFromXML()
			throws Exception {

		utUserWebServiceForXML.login("");
		verify(mockUserService, times(1)).loginFromXML(any(String.class),
				any(String.class));
	}

} // Class 