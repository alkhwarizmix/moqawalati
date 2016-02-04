////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2016 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.winrak.java.webservices.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import javax.management.RuntimeErrorException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.trouvauto.java.model.vo.ReservautoPosition;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;
import dz.alkhwarizmix.winrak.java.services.IWinrakService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٣٠ ربيع الاول ١٤٣٧ (January 10, 2016)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class WinrakWebServiceForJSONTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private WinrakWebServiceForJSON utWinrakWebServiceForJSON;

	@Mock
	private IWinrakService mockWinrakService;

	@Before
	public void setUp() throws AlKhwarizmixException {
		utWinrakWebServiceForJSON = new WinrakWebServiceForJSON();
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
		Assert.assertNotNull(utWinrakWebServiceForJSON);
		Assert.assertNotNull(utWinrakWebServiceForJSON.getLogger());
		Assert.assertEquals(
				"dz.alkhwarizmix.winrak.java.webservices.impl.WinrakWebServiceForJSON",
				utWinrakWebServiceForJSON.getLogger().getName());
	}

	@Test
	public void test01_set_get_winrakService() throws AlKhwarizmixException {
		utWinrakWebServiceForJSON.setWinrakService(mockWinrakService);
		Assert.assertEquals(mockWinrakService,
				utWinrakWebServiceForJSON.getWinrakService());
		Assert.assertEquals(mockWinrakService,
				utWinrakWebServiceForJSON.getService());
	}

	@Test
	public void test02_trouvauto_success() throws AlKhwarizmixException {
		when(
				mockWinrakService.trouvauto(
						Mockito.any(ReservautoPosition.class), Mockito.anyInt()))
				.thenReturn("{}");
		utWinrakWebServiceForJSON.setWinrakService(mockWinrakService);
		final ResponseEntity<String> result = utWinrakWebServiceForJSON
				.trouvauto(1.1, 2.2, 3);
		Assert.assertEquals("<200 OK,"
				+ "{\"response\":{\"status\":\"SUCCESSFUL\",\"result\":{}}},"
				+ "{Content-Type=[application/json; charset=UTF-8], "
				+ "Pragma=[no-cache], " + "Cache-Control=[no-cache], "
				+ "Expires=[Thu, 01 Jan 1970 00:00:00 GMT], "
				+ "Accept-Charset=[utf-8]}" + ">", result.toString());
	}

	@Ignore
	@Test
	public void test03_trouvauto_fail() throws AlKhwarizmixException {
		when(
				mockWinrakService.trouvauto(
						Mockito.any(ReservautoPosition.class), Mockito.anyInt()))
				.thenReturn("{}");
		utWinrakWebServiceForJSON.setWinrakService(mockWinrakService);
		final ResponseEntity<String> result = utWinrakWebServiceForJSON
				.trouvauto(1.1, 2.2, 3);
		Assert.assertEquals("", result.toString());
	}

	@Test
	public void test04_trouvauto_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		utWinrakWebServiceForJSON.setWinrakService(mockWinrakService);
		Mockito.doThrow(new RuntimeErrorException(null))
				.when(mockWinrakService)
				.trouvauto(any(IWinrakPosition.class), anyInt());
		final ResponseEntity<String> result = utWinrakWebServiceForJSON
				.trouvauto(0.0, 0.0, 3);
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test01() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
