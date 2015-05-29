////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.utils.DateUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٥ ربيع الثاني ١٤٣٦ (February 04, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class PasswordTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private Password utPassword;

	@BeforeClass
	static public void setUp() {
		final DateUtil mockDateUtil = Mockito.mock(DateUtil.class);
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

	private void setDataForPasswordWithId(final Password password, final int id) {
		password.setId(new Long(id));

		password.setEncryption(new Encryption("Encryption" + id));
		password.getEncryption().setId(new Long(id + 1));
		password.setPassword("ThePassword" + (id + 2));
		password.setUser(new User("User" + (id + 3)));
		password.getUser().setId(new Long(id + 4));
		password.setLastUse(new Date(id + 5));
	}

	private void assertEqualPasswords(final Password expectedPassword,
			final Password clonePassword, final boolean testDeep) {
		Assert.assertEquals("encryption", expectedPassword.getEncryption(),
				clonePassword.getEncryption());
		Assert.assertEquals("password", expectedPassword.getPassword(),
				clonePassword.getPassword());
		Assert.assertEquals("user", expectedPassword.getUser(),
				clonePassword.getUser());
		Assert.assertEquals("lastUse", expectedPassword.getLastUse(),
				clonePassword.getLastUse());
		if (testDeep) {
			Assert.assertEquals("encryption id", expectedPassword
					.getEncryption().getId(), clonePassword.getEncryption()
					.getId());
			Assert.assertEquals("user id", expectedPassword.getUser().getId(),
					clonePassword.getUser().getId());
		}
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_set_then_get_encryption() {
		final Encryption value = new Encryption("Encryption1");
		utPassword.setEncryption(value);
		assertEquals(value, utPassword.getEncryption());
	}

	@Test
	public void test02_set_then_get_password() {
		final String value = "Password1";
		utPassword.setPassword(value);
		assertEquals(value, utPassword.getPassword());
	}

	@Test
	public void test03_set_then_get_user() {
		final User value = new User("User1");
		utPassword.setUser(value);
		assertEquals(value, utPassword.getUser());
	}

	@Test
	public void test04_A_clone_null_properties() {
		// SetUp
		final Password expectedPassword = new Password();
		utPassword = new Password();
		// Test
		final Password clonePassword = (Password) utPassword.clone();
		// Others
		setDataForPasswordWithId(utPassword, 19857);
		// Asserts
		assertEqualPasswords(expectedPassword, clonePassword, false);
	}

	@Test
	public void test04_B_clone() {
		// SetUp
		final Password expectedPassword = new Password();
		setDataForPasswordWithId(expectedPassword, 19853);
		setDataForPasswordWithId(utPassword, 19853);
		// Test
		final Password clonePassword = (Password) utPassword.clone();
		// Others
		setDataForPasswordWithId(utPassword, 17753);
		// Asserts
		assertEqualPasswords(expectedPassword, clonePassword, true);
	}

} // Class
