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

package dz.alkhwarizmix.framework.java.webservices.impl;

import static org.mockito.Matchers.anyString;

import javax.management.RuntimeErrorException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.services.IPrototypeService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ربيع الاول ١٤٣٧ (December 19, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class PrototypeWebServiceForJSONTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private PrototypeWebServiceForJSON utPrototypeWebServiceForJSON;

	@Mock
	private IPrototypeService mockPrototypeService;

	@Before
	public void setUp() throws Exception {
		utPrototypeWebServiceForJSON.setPrototypeService(mockPrototypeService);
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
		Assert.assertNotNull(utPrototypeWebServiceForJSON);
	}

	@Test
	public void test01_protoPost_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null))
				.when(mockPrototypeService).protoPost(anyString());
		final ResponseEntity<String> result = utPrototypeWebServiceForJSON
				.protoPost("");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Test
	public void test02_protoGet_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null))
				.when(mockPrototypeService).protoGet(anyString());
		final ResponseEntity<String> result = utPrototypeWebServiceForJSON
				.protoGet("");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test01() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
