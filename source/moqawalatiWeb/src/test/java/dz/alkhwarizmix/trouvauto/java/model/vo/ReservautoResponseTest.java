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

package dz.alkhwarizmix.trouvauto.java.model.vo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.utils.impl.JSONUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الاول ١٤٣٧ (December 25, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class ReservautoResponseTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private final String DATA_AS_JSON = "{\"ExtensionData\":{},\"UserPosition\":{\"ExtensionData\":{},\"Lat\":45.5416714,\"Lon\":-73.5959934},\"Vehicules\":[{\"ExtensionData\":{},\"Id\":\"JTDKDTB32D1042014\",\"Name\":\"2646\",\"ModelName\":\"PRIUS-C\",\"Immat\":\"FJA9061\",\"EnergyLevel\":70,\"Position\":{\"ExtensionData\":{},\"Lat\":45.539428833333332,\"Lon\":-73.598429833333327}}]}";

	private ReservautoResponse utReservautoResponse;

	@Before
	public void setUp() {
		utReservautoResponse = new ReservautoResponse();
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
		Assert.assertNotNull(utReservautoResponse);
	}

	@Test
	public void test01_from_json_data() throws AlKhwarizmixException {
		final ReservautoResponse result = new ReservautoResponse(DATA_AS_JSON,
				new JSONUtil());
		Assert.assertNotNull(result.getUserPosition());
		Assert.assertNotNull(result.getUserPosition().getLat());
		Assert.assertEquals(45.5416714, result.getUserPosition().getLat(), 0);
		Assert.assertEquals(-73.5959934, result.getUserPosition().getLng(), 0);
		Assert.assertNotNull(result.getVehicules());
		Assert.assertEquals(1, result.getVehicules().size());
		Assert.assertEquals("2646", result.getVehicules().get(0).getName());
		Assert.assertEquals(45.539428833333332, result.getVehicules().get(0)
				.getPosition().getLat(), 0);
	}

	@Test
	public void test02_toJson() throws AlKhwarizmixException {
		utReservautoResponse.setUserPosition(new ReservautoPosition(2.3, 4.5));
		final JSONUtil jsonUtil = new JSONUtil();
		final String json = utReservautoResponse.toJson(jsonUtil);
		final ReservautoResponse result = new ReservautoResponse(json, jsonUtil);
		Assert.assertEquals(2.3, result.getUserPosition().getLat(), 0);
		Assert.assertEquals(4.5, result.getUserPosition().getLng(), 0);
	}

	@Ignore("TODO: TDD")
	@Test
	public void testXY() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
