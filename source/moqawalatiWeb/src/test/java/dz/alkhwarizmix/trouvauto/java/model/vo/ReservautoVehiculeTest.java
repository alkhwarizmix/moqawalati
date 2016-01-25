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
public class ReservautoVehiculeTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private ReservautoVehicule utReservautoVehicule;

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

	private void setDataForReservautoVehiculeWithId(
			final ReservautoVehicule obj, final int id) {
		obj.setId(new Long(id));
		obj.setName("Name" + id);
		obj.setPosition(new ReservautoPosition(1.2 * id, 1.2 * id));
	}

	private void assertEqualReservautoVehicules(
			final ReservautoVehicule expected, final ReservautoVehicule clone,
			final boolean testDeep) {
		Assert.assertEquals("id", expected.getId(), clone.getId());
		Assert.assertEquals("Name", expected.getName(), clone.getName());
		Assert.assertEquals("Position", expected.getPosition(),
				clone.getPosition());
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utReservautoVehicule);
	}

	@Test
	public void test00_B_Cloneable() {
		Assert.assertTrue(utReservautoVehicule instanceof Cloneable);
	}

	@Test
	public void test00_C_equals_empty_instance() {
		final ReservautoVehicule obj1 = new ReservautoVehicule();
		final ReservautoVehicule obj2 = new ReservautoVehicule();
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test01_A_Name_setAndGet() {
		final String valueToSet = "Name8976";
		utReservautoVehicule.setName(valueToSet);
		Assert.assertEquals(valueToSet, utReservautoVehicule.getName());
	}

	@Test
	public void test01_B_Name_equals() {
		final ReservautoVehicule obj1 = new ReservautoVehicule();
		final ReservautoVehicule obj2 = new ReservautoVehicule();
		final String name = "Name123";
		obj1.setName(name);
		Assert.assertFalse(obj1.equals(obj2));
		obj2.setName(name);
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test02_A_Position_setAndGet() {
		final ReservautoPosition valueToSet = mock(ReservautoPosition.class);
		utReservautoVehicule.setPosition(valueToSet);
		Assert.assertEquals(valueToSet, utReservautoVehicule.getPosition());
	}

	@Test
	public void test02_B_Position_equals() {
		final ReservautoVehicule obj1 = new ReservautoVehicule();
		final ReservautoVehicule obj2 = new ReservautoVehicule();
		final ReservautoPosition position = new ReservautoPosition(1.2, 3.4);
		obj1.setPosition(position);
		Assert.assertFalse(obj1.equals(obj2));
		obj2.setPosition(position);
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test03_A_clone_null_properties() {
		// SetUp
		final ReservautoVehicule expected = new ReservautoVehicule();
		utReservautoVehicule = new ReservautoVehicule();
		// Test
		final ReservautoVehicule clone = (ReservautoVehicule) utReservautoVehicule
				.clone();
		// Others
		setDataForReservautoVehiculeWithId(utReservautoVehicule, 1567);
		// Asserts
		assertEqualReservautoVehicules(expected, clone, false);
	}

	@Test
	public void test03_B_clone() {
		// SetUp
		final ReservautoVehicule expected = new ReservautoVehicule();
		setDataForReservautoVehiculeWithId(expected, 7651);
		setDataForReservautoVehiculeWithId(utReservautoVehicule, 7651);
		// Test
		final ReservautoVehicule clone = (ReservautoVehicule) utReservautoVehicule
				.clone();
		// Others
		setDataForReservautoVehiculeWithId(utReservautoVehicule, 1569);
		// Asserts
		assertEqualReservautoVehicules(expected, clone, true);
	}

	@Test
	public void test03_hashCode() {
		Assert.assertEquals(887503681, utReservautoVehicule.hashCode());
		setDataForReservautoVehiculeWithId(utReservautoVehicule, 7953);
		Assert.assertEquals(-1865065128, utReservautoVehicule.hashCode());
	}

} // Class
