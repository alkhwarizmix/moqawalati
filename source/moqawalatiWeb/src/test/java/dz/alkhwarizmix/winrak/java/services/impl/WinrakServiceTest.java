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

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٧ ربيع الاول ١٤٣٧ (December 28, 2015)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@SuppressWarnings("PMD.MethodNamingConventions")
public class WinrakServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Autowired
	private WinrakService utWinrakService;

	@Mock
	private GoogleGeoApiWrapper mockGoogleGeoApiWrapper;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		utWinrakService.setGoogleGeoApiWrapper(mockGoogleGeoApiWrapper);
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
	public void test01_convertPositionToAddress_should_be_cacheable()
			throws AlKhwarizmixException {
		final String expected = "1234, My Address";
		doReturn(expected).when(mockGoogleGeoApiWrapper)
				.convertPositionToAddress(1.1, 2.2, 3000);
		String result = utWinrakService
				.convertPositionToAddress(1.1, 2.2, 3000);
		Assert.assertEquals(expected, result);
		verify(mockGoogleGeoApiWrapper, times(1)).convertPositionToAddress(1.1,
				2.2, 3000);
		result = utWinrakService.convertPositionToAddress(1.1, 2.2, 3000);
		Assert.assertEquals(expected, result);
		verify(mockGoogleGeoApiWrapper, times(1)).convertPositionToAddress(1.1,
				2.2, 3000);
	}

	@Ignore("TODO: TDD")
	@Test
	public void testXY() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
