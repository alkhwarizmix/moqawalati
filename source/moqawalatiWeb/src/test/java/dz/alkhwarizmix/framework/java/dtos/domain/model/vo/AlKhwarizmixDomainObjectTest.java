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

package dz.alkhwarizmix.framework.java.dtos.domain.model.vo;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
 * @since ٢٨ شعبان ١٤٣٥ (June 26, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class AlKhwarizmixDomainObjectTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private AlKhwarizmixDomainObject utAlKhwarizmixDomainObject;

	@BeforeClass
	static public void setUp() {
		DateUtil mockDateUtil = Mockito.mock(DateUtil.class);
		Mockito.when(mockDateUtil.newDate()).thenReturn(new Date(1234));
		AbstractAlKhwarizmixDomainObject.dateUtil = mockDateUtil;
	}

	@AfterClass
	static public void tearDown() {
		AbstractAlKhwarizmixDomainObject.dateUtil = null;
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private AlKhwarizmixDomainObject getObjectWithId(int id) {
		return new AlKhwarizmixDomainObject(new Long(id), id, new Date(id),
				new Date(id + 1));
	}

	private void assertEqualObjects(AlKhwarizmixDomainObject expectedObject,
			AlKhwarizmixDomainObject cloneObject) {
		Assert.assertEquals(expectedObject.getId(), cloneObject.getId());
		Assert.assertEquals(expectedObject.getVersion(),
				cloneObject.getVersion());
		Assert.assertEquals(expectedObject.getCreated(),
				cloneObject.getCreated());
		Assert.assertEquals(expectedObject.getModified(),
				cloneObject.getModified());
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_A_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utAlKhwarizmixDomainObject);
	}

	@Test
	public void test00_B_Cloneable() {
		Assert.assertTrue(utAlKhwarizmixDomainObject instanceof Cloneable);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test01() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

	@Test
	public void test03_A_clone_null_properties() {
		// SetUp
		AlKhwarizmixDomainObject expectedObject = new AlKhwarizmixDomainObject();
		utAlKhwarizmixDomainObject = new AlKhwarizmixDomainObject();
		// Test
		AlKhwarizmixDomainObject cloneObject = (AlKhwarizmixDomainObject) utAlKhwarizmixDomainObject
				.clone();
		// Others
		utAlKhwarizmixDomainObject.setId(1567L);
		// Asserts
		assertEqualObjects(expectedObject, cloneObject);
	}

	@Test
	public void test03_B_clone() {
		// SetUp
		AlKhwarizmixDomainObject expectedObject = getObjectWithId(7651);
		utAlKhwarizmixDomainObject = getObjectWithId(7651);
		// Test
		AlKhwarizmixDomainObject cloneObject = (AlKhwarizmixDomainObject) utAlKhwarizmixDomainObject
				.clone();
		// Others
		utAlKhwarizmixDomainObject.setId(1568L);
		// Asserts
		assertEqualObjects(expectedObject, cloneObject);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test04_toString_TDD() {
		Assert.assertTrue(false);
	}

	@Test
	public void test05_hashCode_TDD() {
		Assert.assertEquals(923521, utAlKhwarizmixDomainObject.hashCode());
		utAlKhwarizmixDomainObject = getObjectWithId(3677);
		Assert.assertEquals(114116320, utAlKhwarizmixDomainObject.hashCode());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test06_equals_TDD() {
		Assert.assertTrue(false);
	}

} // Class
