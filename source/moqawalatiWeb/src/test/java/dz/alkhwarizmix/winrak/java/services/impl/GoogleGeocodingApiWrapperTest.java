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

package dz.alkhwarizmix.winrak.java.services.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٦ ربيع الاول ١٤٣٧ (January 05, 2016)
 */
@SuppressWarnings("PMD.MethodNamingConventions")
@RunWith(MockitoJUnitRunner.class)
public class GoogleGeocodingApiWrapperTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private GoogleGeocodingApiWrapper utGoogleGeocodingApiWrapper;

	@Before
	public void setUp() {
		utGoogleGeocodingApiWrapper = new GoogleGeocodingApiWrapper();
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
		Assert.assertNotNull(utGoogleGeocodingApiWrapper);
	}

	@Ignore("TODO: TDD")
	@Test
	public void testXY() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
