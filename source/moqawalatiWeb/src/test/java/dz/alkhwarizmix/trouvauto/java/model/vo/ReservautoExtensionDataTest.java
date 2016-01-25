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
public class ReservautoExtensionDataTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private ReservautoExtensionData utReservautoExtensionData;

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

	private void setDataForReservautoExtensionDataWithId(
			final ReservautoExtensionData obj, final int id) {
		obj.setId(new Long(id));
	}

	private void assertEqualReservautoExtensionDatas(
			final ReservautoExtensionData expected,
			final ReservautoExtensionData clone, final boolean testDeep) {
		Assert.assertEquals("id", expected.getId(), clone.getId());
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utReservautoExtensionData);
	}

	@Test
	public void test00_B_Cloneable() {
		Assert.assertTrue(utReservautoExtensionData instanceof Cloneable);
	}

	@Test
	public void test00_C_equals_empty_instance() {
		final ReservautoExtensionData obj1 = new ReservautoExtensionData();
		final ReservautoExtensionData obj2 = new ReservautoExtensionData();
		Assert.assertTrue(obj1.equals(obj2));
	}

	@Test
	public void test00_D_equals_different_class() {
		Assert.assertFalse(utReservautoExtensionData
				.equals(mock(AbstractAlKhwarizmixDomainObject.class)));
	}

	@Test
	public void test01_A_clone_null_properties() {
		// SetUp
		final ReservautoExtensionData expected = new ReservautoExtensionData();
		utReservautoExtensionData = new ReservautoExtensionData();
		// Test
		final ReservautoExtensionData clone = (ReservautoExtensionData) utReservautoExtensionData
				.clone();
		// Others
		setDataForReservautoExtensionDataWithId(utReservautoExtensionData, 1567);
		// Asserts
		assertEqualReservautoExtensionDatas(expected, clone, false);
	}

	@Test
	public void test01_B_clone() {
		// SetUp
		final ReservautoExtensionData expected = new ReservautoExtensionData();
		setDataForReservautoExtensionDataWithId(expected, 7651);
		setDataForReservautoExtensionDataWithId(utReservautoExtensionData, 7651);
		// Test
		final ReservautoExtensionData clone = (ReservautoExtensionData) utReservautoExtensionData
				.clone();
		// Others
		setDataForReservautoExtensionDataWithId(utReservautoExtensionData, 1569);
		// Asserts
		assertEqualReservautoExtensionDatas(expected, clone, true);
	}

	@Test
	public void test02_hashCode() {
		Assert.assertEquals(923521, utReservautoExtensionData.hashCode());
		setDataForReservautoExtensionDataWithId(utReservautoExtensionData, 7953);
		Assert.assertEquals(8566354, utReservautoExtensionData.hashCode());
	}

	@Test
	public void test08_toString() {
		setDataForReservautoExtensionDataWithId(utReservautoExtensionData, 7304);
		final String result = utReservautoExtensionData.toString();
		Assert.assertTrue(result.contains("id=7304"));
		Assert.assertTrue(result.contains("vo.ReservautoExtensionData"));
	}

} // Class
