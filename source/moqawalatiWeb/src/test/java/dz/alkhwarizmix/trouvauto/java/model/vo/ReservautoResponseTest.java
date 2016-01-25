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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.utils.DateUtil;
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

	@BeforeClass
	static public void static_setUp() {
		final DateUtil mockDateUtil = mock(DateUtil.class);
		when(mockDateUtil.newDate()).thenReturn(new Date(1234));
		AbstractAlKhwarizmixDomainObject.dateUtil = mockDateUtil;
	}

	@AfterClass
	static public void static_tearDown() {
		AbstractAlKhwarizmixDomainObject.dateUtil = null;
	}

	@Before
	public void setUp() {
		utReservautoResponse = new ReservautoResponse();
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private void setDataForReservautoResponseWithId(
			final ReservautoResponse response, final int id) {
		response.setId(new Long(id));
		response.setUserPosition(new ReservautoPosition(1.2 * id, 1.2 * id));
		final ReservautoVehiculeList vehicules = new ReservautoVehiculeList();
		final ReservautoVehicule vehicule = new ReservautoVehicule();
		vehicule.setPosition(new ReservautoPosition(1.3 * id, 1.3 * id));
		vehicules.add(vehicule);
		response.setVehicules(vehicules);
	}

	private void assertEqualReservautoResponses(
			final ReservautoResponse expected, final ReservautoResponse clone,
			final boolean testDeep) {
		Assert.assertEquals("id", expected.getId(), clone.getId());
		Assert.assertEquals("ExtensionData", expected.getExtensionData(),
				clone.getExtensionData());
		Assert.assertEquals("UserPosition", expected.getUserPosition(),
				clone.getUserPosition());
		Assert.assertEquals("Vehicules", expected.getVehicules(),
				clone.getVehicules());
	}

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
	public void test00_B_Cloneable() {
		Assert.assertTrue(utReservautoResponse instanceof Cloneable);
	}

	@Test
	public void test00_C_equals_empty_instance() {
		final ReservautoResponse obj1 = new ReservautoResponse();
		final ReservautoResponse obj2 = new ReservautoResponse();
		Assert.assertTrue(obj1.equals(obj2));
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

	@Test
	public void test03_A_ExtensionData_setAndGet() {
		final ReservautoExtensionData valueToSet = new ReservautoExtensionData();
		utReservautoResponse.setExtensionData(valueToSet);
		Assert.assertEquals(valueToSet, utReservautoResponse.getExtensionData());
	}

	@Test
	public void test03_B_ExtensionData_equals() {
		final ReservautoResponse obj1 = new ReservautoResponse();
		final ReservautoResponse obj2 = new ReservautoResponse();
		final ReservautoExtensionData reservautoExtensionData = mock(ReservautoExtensionData.class);
		obj1.setExtensionData(reservautoExtensionData);
		Assert.assertFalse(obj1.equals(obj2));
		obj2.setExtensionData(reservautoExtensionData);
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test04_A_UserPosition_setAndGet() {
		final ReservautoPosition valueToSet = mock(ReservautoPosition.class);
		utReservautoResponse.setUserPosition(valueToSet);
		Assert.assertEquals(valueToSet, utReservautoResponse.getUserPosition());
	}

	@Test
	public void test04_B_UserPosition_equals() {
		final ReservautoResponse obj1 = new ReservautoResponse();
		final ReservautoResponse obj2 = new ReservautoResponse();
		final ReservautoPosition userPosition = new ReservautoPosition(1.2, 3.4);
		obj1.setUserPosition(userPosition);
		Assert.assertFalse(obj1.equals(obj2));
		obj2.setUserPosition(userPosition);
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test05_A_Vehicules_setAndGet() {
		final ReservautoVehiculeList valueToSet = mock(ReservautoVehiculeList.class);
		utReservautoResponse.setVehicules(valueToSet);
		Assert.assertEquals(valueToSet, utReservautoResponse.getVehicules());
	}

	@Test
	public void test05_B_Vehicules_equals() {
		final ReservautoResponse obj1 = new ReservautoResponse();
		final ReservautoResponse obj2 = new ReservautoResponse();
		final ReservautoVehiculeList vehicules = mock(ReservautoVehiculeList.class);
		obj1.setVehicules(vehicules);
		Assert.assertFalse(obj1.equals(obj2));
		obj2.setVehicules(vehicules);
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test06_A_clone_null_properties() {
		// SetUp
		final ReservautoResponse expected = new ReservautoResponse();
		utReservautoResponse = new ReservautoResponse();
		// Test
		final ReservautoResponse clone = (ReservautoResponse) utReservautoResponse
				.clone();
		// Others
		setDataForReservautoResponseWithId(utReservautoResponse, 1567);
		// Asserts
		assertEqualReservautoResponses(expected, clone, false);
	}

	@Test
	public void test06_B_clone() {
		// SetUp
		final ReservautoResponse expected = new ReservautoResponse();
		setDataForReservautoResponseWithId(expected, 7651);
		setDataForReservautoResponseWithId(utReservautoResponse, 7651);
		// Test
		final ReservautoResponse clone = (ReservautoResponse) utReservautoResponse
				.clone();
		// Others
		setDataForReservautoResponseWithId(utReservautoResponse, 1569);
		// Asserts
		assertEqualReservautoResponses(expected, clone, true);
	}

	@Test
	public void test07_hashCode() {
		Assert.assertEquals(1705692209, utReservautoResponse.hashCode());
		setDataForReservautoResponseWithId(utReservautoResponse, 7953);
		Assert.assertEquals(1887784336, utReservautoResponse.hashCode());
	}

} // Class
