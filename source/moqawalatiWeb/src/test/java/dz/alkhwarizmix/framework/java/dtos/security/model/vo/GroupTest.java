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

package dz.alkhwarizmix.framework.java.dtos.security.model.vo;

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
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
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
public class GroupTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private Group utGroup;

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

	private void setDataForGroupWithId(Group group, int id) {
		group.setId(new Long(id));
		group.setGroupId("groupId" + id);
		group.setDomainObject(new AlKhwarizmixDomainObject());
		group.getDomainObject().setId(new Long(id + 1));
	}

	private void assertEqualGroups(Group expectedGroup, Group cloneGroup,
			boolean testDeep) {
		Assert.assertEquals(expectedGroup.getGroupId(), cloneGroup.getGroupId());
		Assert.assertEquals(expectedGroup.getDomainObject(),
				cloneGroup.getDomainObject());
		if (testDeep) {
			Assert.assertEquals(expectedGroup.getDomainObject().getId(),
					cloneGroup.getDomainObject().getId());
		}
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_A_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utGroup);
	}

	@Test
	public void test00_B_Cloneable() {
		Assert.assertTrue(utGroup instanceof Cloneable);
	}

	@Test
	public void test03_A_clone_null_properties() {
		// SetUp
		Group expectedGroup = new Group();
		utGroup = new Group();
		// Test
		Group cloneGroup = (Group) utGroup.clone();
		// Others
		setDataForGroupWithId(utGroup, 1567);
		// Asserts
		assertEqualGroups(expectedGroup, cloneGroup, false);
	}

	@Test
	public void test03_B_clone() {
		// SetUp
		Group expectedGroup = new Group();
		setDataForGroupWithId(expectedGroup, 7651);
		setDataForGroupWithId(utGroup, 7651);
		// Test
		Group cloneGroup = (Group) utGroup.clone();
		// Others
		setDataForGroupWithId(utGroup, 1569);
		// Asserts
		assertEqualGroups(expectedGroup, cloneGroup, true);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test04_toString_TDD() {
		Assert.assertTrue(false);
	}

	@Ignore("TODO: REWORK")
	@Test
	public void test05_hashCode() {
		Assert.assertEquals(1856137647, utGroup.hashCode());
		setDataForGroupWithId(utGroup, 7753);
		Assert.assertEquals(1735975737, utGroup.hashCode());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test06_equals_TDD() {
		Assert.assertTrue(false);
	}

} // Class
