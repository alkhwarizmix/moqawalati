////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.webservices;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.services.IUserService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الثاني ١٤٣٦ (February 03, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class UserWebServiceForBlazeDSTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private UserWebServiceForBlazeDS utUserWebServiceForBlazeDS;

	@Mock
	private User mockUser;

	@Mock
	private IUserService mockUserService;

	@Before
	public void setUp() throws Exception {
		setupMockUserService();
	}

	private void setupMockUserService() throws AlKhwarizmixException {
		when(
				mockUserService.login(any(User.class), any(String.class),
						anyBoolean())).thenReturn(null);
		when(mockUserService.connect(any(User.class), anyBoolean()))
				.thenReturn(null);
		utUserWebServiceForBlazeDS.setUserService(mockUserService);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utUserWebServiceForBlazeDS);
	}

	@Test
	public void test01_login_should_call_service_login() throws Exception {
		final String password = "PasswordIsNotImportant";
		utUserWebServiceForBlazeDS.login(mockUser, password);
		verify(mockUserService, times(1)).login(eq(mockUser), eq(password),
				eq(true));
	}

	@Test
	public void test02_connect_should_call_service_connect() throws Exception {
		utUserWebServiceForBlazeDS.connect(mockUser);
		verify(mockUserService, times(1)).connect(eq(mockUser), eq(true));
	}

	@Test
	public void test03_logout_should_call_service_logout() throws Exception {
		utUserWebServiceForBlazeDS.logout(mockUser);
		verify(mockUserService, times(1)).logout(eq(mockUser));
	}

	@Test
	public void test04_subscribe_should_call_service_subscribe()
			throws Exception {
		utUserWebServiceForBlazeDS.subscribe(mockUser);
		verify(mockUserService, times(1)).subscribe(eq(mockUser), eq(true));
	}

} // Class
