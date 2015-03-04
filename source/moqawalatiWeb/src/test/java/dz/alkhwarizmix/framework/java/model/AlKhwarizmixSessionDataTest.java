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

package dz.alkhwarizmix.framework.java.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ جمادى الأول ١٤٣٥ (March 25, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class AlKhwarizmixSessionDataTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	AlKhwarizmixSessionData utAlKhwarizmixSessionData;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_resetSessionOwner_should_nullify_sessionOwner() {
		// SETUP
		AlKhwarizmixDomainObject sessionOwner = new AlKhwarizmixDomainObject();
		utAlKhwarizmixSessionData.setSessionOwner(sessionOwner);
		// TEST
		utAlKhwarizmixSessionData.resetSessionOwner();
		// ASSERTS
		Assert.assertNotSame(sessionOwner,
				utAlKhwarizmixSessionData.getSessionOwner());
	}

	@Test
	public void test02_set_then_get_connectedUser() {
		User valueToSet = new User("userId135");
		// TEST
		utAlKhwarizmixSessionData.setConnectedUser(valueToSet);
		// ASSERTS
		Assert.assertEquals(valueToSet,
				utAlKhwarizmixSessionData.getConnectedUser());
	}

	@Test
	public void test03_set_then_get_loggedUser() {
		User valueToSet = new User("userId1375");
		// TEST
		utAlKhwarizmixSessionData.setLoggedUser(valueToSet);
		// ASSERTS
		Assert.assertEquals(valueToSet,
				utAlKhwarizmixSessionData.getLoggedUser());
	}

	@Test
	public void test04_resetConnectedUser_should_nullify_connectedUser() {
		// SETUP
		utAlKhwarizmixSessionData.setConnectedUser(new User());
		Assert.assertNotNull(utAlKhwarizmixSessionData.getConnectedUser());
		// TEST
		utAlKhwarizmixSessionData.resetConnectedUser();
		// ASSERTS
		Assert.assertNull(utAlKhwarizmixSessionData.getConnectedUser());
	}

	@Test
	public void test05_resetLoggedUser_should_nullify_connectedUser() {
		// SETUP
		utAlKhwarizmixSessionData.setLoggedUser(new User());
		Assert.assertNotNull(utAlKhwarizmixSessionData.getLoggedUser());
		// TEST
		utAlKhwarizmixSessionData.resetLoggedUser();
		// ASSERTS
		Assert.assertNull(utAlKhwarizmixSessionData.getLoggedUser());
	}

} // Class
