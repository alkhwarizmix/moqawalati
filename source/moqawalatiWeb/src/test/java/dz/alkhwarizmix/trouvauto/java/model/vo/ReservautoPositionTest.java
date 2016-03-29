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
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.utils.DateUtil;
import dz.alkhwarizmix.winrak.java.model.vo.WinrakPosition;

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
public class ReservautoPositionTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private ReservautoPosition utReservautoPosition;

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

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private void setDataForReservautoPositionWithId(
			final ReservautoPosition obj, final int id) {
		obj.setId(new Long(id));
		obj.setLat(2.0 * id);
		obj.setLng(3.0 * id);
		obj.setAddress("Address" + id);
	}

	private void assertEqualReservautoPositions(
			final ReservautoPosition expected, final ReservautoPosition clone,
			final boolean testDeep) {
		Assert.assertEquals("id", expected.getId(), clone.getId());
		Assert.assertEquals("Lat", expected.getLat(), clone.getLat());
		Assert.assertEquals("Lon", expected.getLng(), clone.getLng());
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utReservautoPosition);
	}

	@Test
	public void test00_B_Cloneable() {
		Assert.assertTrue(utReservautoPosition instanceof Cloneable);
	}

	@Test
	public void test00_C_equals_empty_instance() {
		final ReservautoPosition obj1 = new ReservautoPosition();
		final ReservautoPosition obj2 = new ReservautoPosition();
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test00_D_equals_different_class() {
		Assert.assertFalse(utReservautoPosition.equals(new WinrakPosition()));
	}

	@Test
	public void test01_distanceTo() throws AlKhwarizmixException {
		final ReservautoPosition pos1 = new ReservautoPosition(38.898556,
				-77.037852);
		final ReservautoPosition pos2 = new ReservautoPosition(38.897147,
				-77.043934);
		Assert.assertEquals(549, pos1.distanceTo(pos2), 0);
		Assert.assertEquals(549, pos2.distanceTo(pos1), 0);
	}

	@Test
	public void test02_directionTo() throws AlKhwarizmixException {
		final ReservautoPosition pos1 = new ReservautoPosition(38.898556,
				-77.037852);
		final ReservautoPosition pos2 = new ReservautoPosition(38.897147,
				-77.043934);
		Assert.assertEquals("SW", pos1.directionTo(pos2));
		Assert.assertEquals("NE", pos2.directionTo(pos1));
	}

	@Test
	public void test03_A_Lat_setAndGet() {
		final Double valueToSet = 4.323;
		utReservautoPosition.setLat(valueToSet);
		Assert.assertEquals(valueToSet, utReservautoPosition.getLat());
	}

	@Test
	public void test03_B_Lat_equals() {
		final ReservautoPosition obj1 = new ReservautoPosition();
		final ReservautoPosition obj2 = new ReservautoPosition();
		final Double lat = 4.534;
		obj1.setLat(lat);
		Assert.assertFalse(obj1.equals(obj2));
		obj2.setLat(lat);
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test04_A_Lon_setAndGet() {
		final Double valueToSet = 2.34;
		utReservautoPosition.setLng(valueToSet);
		Assert.assertEquals(valueToSet, utReservautoPosition.getLng());
	}

	@Test
	public void test04_B_Lon_equals() {
		final ReservautoPosition obj1 = new ReservautoPosition();
		final ReservautoPosition obj2 = new ReservautoPosition();
		final Double lng = 1.34;
		obj1.setLng(lng);
		Assert.assertFalse(obj1.equals(obj2));
		obj2.setLng(lng);
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test05_A_address_setAndGet() {
		final String valueToSet = "Address8976";
		utReservautoPosition.setAddress(valueToSet);
		Assert.assertEquals(valueToSet, utReservautoPosition.getAddress());
	}

	@Test
	public void test05_B_address_equals() {
		final ReservautoPosition obj1 = new ReservautoPosition();
		final ReservautoPosition obj2 = new ReservautoPosition();
		final String address = "Address123";
		obj1.setAddress(address);
		Assert.assertFalse(obj1.equals(obj2));
		obj2.setAddress(address);
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test06_A_clone_null_properties() {
		// SetUp
		final ReservautoPosition expected = new ReservautoPosition();
		utReservautoPosition = new ReservautoPosition();
		// Test
		final ReservautoPosition clone = (ReservautoPosition) utReservautoPosition
				.clone();
		// Others
		setDataForReservautoPositionWithId(utReservautoPosition, 1567);
		// Asserts
		assertEqualReservautoPositions(expected, clone, false);
	}

	@Test
	public void test06_B_clone() {
		// SetUp
		final ReservautoPosition expected = new ReservautoPosition();
		setDataForReservautoPositionWithId(expected, 7651);
		setDataForReservautoPositionWithId(utReservautoPosition, 7651);
		// Test
		final ReservautoPosition clone = (ReservautoPosition) utReservautoPosition
				.clone();
		// Others
		setDataForReservautoPositionWithId(utReservautoPosition, 1569);
		// Asserts
		assertEqualReservautoPositions(expected, clone, true);
	}

	@Test
	public void test07_hashCode() {
		Assert.assertEquals(887503681, utReservautoPosition.hashCode());
		setDataForReservautoPositionWithId(utReservautoPosition, 7953);
		Assert.assertEquals(577494116, utReservautoPosition.hashCode());
	}

	@Test
	public void test08_toString() {
		setDataForReservautoPositionWithId(utReservautoPosition, 7301);
		final String result = utReservautoPosition.toString();
		Assert.assertTrue(result.contains("id=7301"));
		Assert.assertTrue(result.contains("vo.ReservautoPosition"));
		Assert.assertTrue(result.contains("Lat=14602."));
		Assert.assertTrue(result.contains("Lng=21903."));
		Assert.assertTrue(result.contains("address=Address7301"));
	}

} // Class
