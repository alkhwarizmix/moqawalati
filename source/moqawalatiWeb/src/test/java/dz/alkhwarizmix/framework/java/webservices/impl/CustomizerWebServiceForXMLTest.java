////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.webservices.impl;

import static org.mockito.Matchers.anyString;

import javax.management.RuntimeErrorException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.services.ICustomizerService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ شعبان ١٤٣٥ (June 26, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class CustomizerWebServiceForXMLTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private CustomizerWebServiceForXML utCustomizerWebServiceForXML;

	@Mock
	private ICustomizerService mockCustomizerService;

	@Before
	public void setUp() throws Exception {
		utCustomizerWebServiceForXML
				.setCustomizerService(mockCustomizerService);
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
		Assert.assertNotNull(utCustomizerWebServiceForXML);
	}

	@Test
	public void test01_setCustomData_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null))
				.when(mockCustomizerService).setCustomDataFromXML(anyString());
		final ResponseEntity<String> result = utCustomizerWebServiceForXML
				.setCustomData("");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Test
	public void test02_getCustomDataById_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null))
				.when(mockCustomizerService).getCustomDataAsXML(anyString());
		final ResponseEntity<String> result = utCustomizerWebServiceForXML
				.getCustomDataById("");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

} // Class
