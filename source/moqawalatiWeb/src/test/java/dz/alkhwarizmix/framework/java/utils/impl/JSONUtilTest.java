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

import com.google.gson.GsonBuilder;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

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

	private JSONUtil utJSONUtil;

	@Before
	public void setUp() {
		utJSONUtil = newJSONUtil();
	}

	private JSONUtil newJSONUtil() {
		return new JSONUtil(null);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	public class JsonObject extends AbstractAlKhwarizmixDomainObject {
		private static final long serialVersionUID = -2144541830173827403L;
		private String a;

		/**
		 * @return the a
		 */
		public String getA() {
			return a;
		}

		@Override
		public Object clone() {
			return null;
		}

		@Override
		public void beforeDaoSaveOrUpdate(
				final AbstractAlKhwarizmixDomainObject object) {
		}

		@Override
		public void updateFrom(final Object sourceObject)
				throws AlKhwarizmixException {
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
		final GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(JsonObject.class, new JsonObject());
		final JsonObject result = (JsonObject) utJSONUtil
				.unmarshalObjectFromJSON(JSON);
		Assert.assertEquals("aVal", result.getA());
	}

} // Class
