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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٦ ربيع الاول ١٤٣٧ (January 05, 2016)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@SuppressWarnings("PMD.MethodNamingConventions")
public class GoogleGeocodingServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static final String GOOGLE_API_KEY = "AIzaBlaBla";

	private static final String ADDRESS = "1234, My Address";

	@Autowired
	private GoogleGeocodingService utGoogleGeocodingService;

	@Mock
	private GoogleGeocodingApiWrapper mockGoogleGeocodingApiWrapper;

	@Mock
	private GeoApiContext mockGeoApiContext;

	@Before
	public void setUp() throws AlKhwarizmixException {
		MockitoAnnotations.initMocks(this);
		utGoogleGeocodingService.setGoogleAPIKey(GOOGLE_API_KEY);
		utGoogleGeocodingService
				.setGoogleGeocodingApiWrapper(mockGoogleGeocodingApiWrapper);
		Mockito.doReturn(mockGeoApiContext).when(mockGoogleGeocodingApiWrapper)
				.getGeoApiContext(GOOGLE_API_KEY, 3000);
		final GeocodingResult[] geocodingResults = { new GeocodingResult() };
		geocodingResults[0].formattedAddress = ADDRESS;
		Mockito.doReturn(geocodingResults)
				.when(mockGoogleGeocodingApiWrapper)
				.reverseGeocode(Mockito.eq(mockGeoApiContext),
						Mockito.any(LatLng.class));
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
		Assert.assertNotNull(utGoogleGeocodingService);
	}

	@Test
	public void test01_convertPositionToAddress_should_be_cacheable()
			throws AlKhwarizmixException {
		final String expected = ADDRESS;
		String result = utGoogleGeocodingService.convertPositionToAddress(1.1,
				2.2, 3000);
		Assert.assertEquals(expected, result);
		Mockito.verify(mockGoogleGeocodingApiWrapper, Mockito.times(1))
				.reverseGeocode(Mockito.any(GeoApiContext.class),
						Mockito.any(LatLng.class));
		result = utGoogleGeocodingService.convertPositionToAddress(1.1, 2.2,
				3000);
		Assert.assertEquals(expected, result);
		Mockito.verify(mockGoogleGeocodingApiWrapper, Mockito.times(1))
				.reverseGeocode(Mockito.any(GeoApiContext.class),
						Mockito.any(LatLng.class));
	}

	@Ignore("TODO: TDD")
	@Test
	public void testXY() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
