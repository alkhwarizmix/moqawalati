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

package dz.alkhwarizmix.framework.java.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.services.impl.UserServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٩ ربيع الثاني ١٤٣٦ (February 18, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class UserServiceValidatorTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	UserServiceValidator utUserServiceValidator;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_isValidUserId_email_address_is_valid()
			throws AlKhwarizmixException {
		User user1 = new User("u1@alkhwarizmix.com");
		Assert.assertTrue(utUserServiceValidator.isValidUserId(user1));
	}

	@Test
	public void test02_isValidUserId_null_user_is_not_valid()
			throws AlKhwarizmixException {
		Assert.assertFalse(utUserServiceValidator.isValidUserId(null));
	}

	@Test
	public void test03_isValidUserId_wrong_email_address_is_not_valid()
			throws AlKhwarizmixException {
		User user1 = new User("u1@@alkhwarizmix.com");
		Assert.assertFalse(utUserServiceValidator.isValidUserId(user1));
	}

} // Class
