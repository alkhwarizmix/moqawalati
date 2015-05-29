////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.security.model.vo;

import static org.junit.Assert.assertEquals;

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

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.utils.DateUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class UserTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private User utUser;

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

	private void setDataForUserWithId(User user, int id) {
		user.setId(new Long(id));
		user.setUserId("userId" + id);
		user.setName("Name" + id);
		user.setGroup(new Group("GroupId" + id));
		user.setDomainObject(new AlKhwarizmixDomainObject());
		user.getDomainObject().setId(new Long(id + 1));
	}

	private void assertEqualUsers(User expectedUser, User cloneUser,
			boolean testDeep) {
		Assert.assertEquals(expectedUser.getUserId(), cloneUser.getUserId());
		Assert.assertEquals(expectedUser.getName(), cloneUser.getName());
		Assert.assertEquals(expectedUser.getGroup(), cloneUser.getGroup());
		Assert.assertEquals(expectedUser.getDomainObject(),
				cloneUser.getDomainObject());
		if (testDeep) {
			Assert.assertEquals(expectedUser.getGroup().getGroupId(), cloneUser
					.getGroup().getGroupId());
			Assert.assertEquals(expectedUser.getDomainObject().getId(),
					cloneUser.getDomainObject().getId());
		}
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_A_constructor() {
		Assert.assertNotNull(utUser);
	}

	@Test
	public void test00_B_Cloneable() {
		Assert.assertTrue(utUser instanceof Cloneable);
	}

	@Test
	public void test01_set_then_get_UserId() {
		String value = "UserTest";
		utUser.setUserId(value);
		assertEquals(value, utUser.getUserId());
	}

	@Test
	public void test02_set_then_get_DomainObject() {
		AlKhwarizmixDomainObject value = new AlKhwarizmixDomainObject();
		utUser.setDomainObject(value);
		assertEquals(value, utUser.getDomainObject());
	}

	@Test
	public void test03_A_clone_null_properties() {
		// SetUp
		User expectedUser = new User();
		utUser = new User();
		// Test
		User cloneUser = (User) utUser.clone();
		// Others
		setDataForUserWithId(utUser, 1567);
		// Asserts
		assertEqualUsers(expectedUser, cloneUser, false);
	}

	@Test
	public void test03_B_clone() {
		// SetUp
		User expectedUser = new User();
		setDataForUserWithId(expectedUser, 7651);
		setDataForUserWithId(utUser, 7651);
		// Test
		User cloneUser = (User) utUser.clone();
		// Others
		setDataForUserWithId(utUser, 1569);
		// Asserts
		assertEqualUsers(expectedUser, cloneUser, true);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test04_toString_TDD() {
		Assert.assertTrue(false);
	}

	@Test
	public void test05_hashCode_TDD() {
		Assert.assertEquals(1336850927, utUser.hashCode());
		setDataForUserWithId(utUser, 7357);
		Assert.assertEquals(-337850297, utUser.hashCode());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test06_equals_TDD() {
		Assert.assertTrue(false);
	}

} // Class
