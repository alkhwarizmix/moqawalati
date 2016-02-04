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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

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
public class AbstractAlKhwarizmixWebServiceForJSONTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Mock
	private AbstractAlKhwarizmixWebServiceForJSON mockAbstractAlKhwarizmixWebServiceForJSON;

	@Mock
	private Logger mockLogger;

	@Before
	public void setUp() throws Exception {
		Mockito.when(mockAbstractAlKhwarizmixWebServiceForJSON.getLogger())
				.thenReturn(mockLogger);
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
		Assert.assertNotNull(mockAbstractAlKhwarizmixWebServiceForJSON);
	}

	@Test
	public void test01_successResponseForJSON_should_return_right_json()
			throws AlKhwarizmixException {
		final StringBuilder sBuilder = new StringBuilder("{}");
		final ResponseEntity<String> result = mockAbstractAlKhwarizmixWebServiceForJSON
				.successResponseForJSON(sBuilder);
		Assert.assertEquals(
				"{\"response\":{\"status\":\"SUCCESSFUL\",\"result\":{}}}",
				result.getBody());
	}

	@Test
	public void test02_errorResponseForJSON_should_return_right_json()
			throws AlKhwarizmixException {
		final ResponseEntity<String> result = mockAbstractAlKhwarizmixWebServiceForJSON
				.errorResponseForJSON(new AlKhwarizmixException(
						AlKhwarizmixErrorCode.SERVER_INTERNAL_ERROR));
		Assert.assertEquals(
				"{\"response\":{\"status\":\"ERROR\",\"error\":{\"code\":\"40500\"}}}",
				result.getBody());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test01() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
