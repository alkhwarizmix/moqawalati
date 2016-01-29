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

package dz.alkhwarizmix.framework.java.utils.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.IAlKhwarizmixJsonObject;
import dz.alkhwarizmix.framework.java.utils.IJSONUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ربيع الاول ١٤٣٧ (December 19, 2015)
 */
@SuppressWarnings("PMD.MethodNamingConventions")
public class JSONUtilTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private final String JSON = "{a: \"aVal\", b: {b1: {}, lat: 2.0, lon: 3.0}, vehicules: []}";

	private IJSONUtil utJSONUtil;

	@Before
	public void setUp() {
		utJSONUtil = new JSONUtil(null);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	final class JsonObject implements IAlKhwarizmixJsonObject {
		private String a;

		/**
		 * @return the a
		 */
		public String getA() {
			return a;
		}

		/**
		 * @param value
		 *            the a to set
		 */
		public void setA(final String value) {
			a = value;
		}
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor1() throws AlKhwarizmixException {
		Assert.assertNotNull(utJSONUtil);
	}

	@Test
	public void test01_unmarshalObjectFromJSON() throws AlKhwarizmixException {
		final JsonObject result = (JsonObject) utJSONUtil
				.unmarshalObjectFromJSON(JSON, JsonObject.class);
		Assert.assertEquals("aVal", result.getA());
	}

	@Test
	public void test02_marshalObjectToJSON() throws AlKhwarizmixException {
		final JsonObject object = new JsonObject();
		object.setA("aVal2");
		final String result = utJSONUtil.marshalObjectToJSON(object);
		Assert.assertEquals("{\"a\":\"aVal2\"}", result);
	}

} // Class
