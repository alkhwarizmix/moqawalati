////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.winrak.java.services.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٧ ربيع الاول ١٤٣٧ (December 28, 2015)
 */
@SuppressWarnings("PMD.MethodNamingConventions")
@RunWith(MockitoJUnitRunner.class)
public class WinrakServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static final String ADDRESS = "1234, My Address";

	private WinrakService utWinrakService;

	@Mock
	private GoogleGeocodingService mockGoogleGeocodingService;

	@Before
	public void setUp() throws AlKhwarizmixException {
		MockitoAnnotations.initMocks(this);
		utWinrakService = new WinrakService();
		utWinrakService.setGoogleGeocodingService(mockGoogleGeocodingService);
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
		Assert.assertNotNull(utWinrakService);
	}

	@Test
	public void test01_convertPositionToAddress_should_call_googleGeocodingService()
			throws AlKhwarizmixException {
		Mockito.doReturn(ADDRESS).when(mockGoogleGeocodingService)
				.convertPositionToAddress(1.1, 2.2, 3000);
		final String result = utWinrakService.convertPositionToAddress(1.1,
				2.2, 3000);
		Assert.assertEquals(ADDRESS, result);
		Mockito.verify(mockGoogleGeocodingService, Mockito.times(1))
				.convertPositionToAddress(1.1, 2.2, 3000);
	}

	@Test
	public void test02_convertPositionToAddress_should_call_with_rounded_latitude_longitude()
			throws AlKhwarizmixException {
		Mockito.doReturn(ADDRESS).when(mockGoogleGeocodingService)
				.convertPositionToAddress(1.10253, 2.23456, 3000);
		final String result = utWinrakService.convertPositionToAddress(
				1.102534, 2.234556, 3000);
		Assert.assertEquals(ADDRESS, result);
		Mockito.verify(mockGoogleGeocodingService, Mockito.times(1))
				.convertPositionToAddress(1.10253, 2.23456, 3000);
	}

	@Ignore("TODO: TDD")
	@Test
	public void testXY() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
