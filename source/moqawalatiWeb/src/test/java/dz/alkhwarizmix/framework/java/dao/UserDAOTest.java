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

package dz.alkhwarizmix.framework.java.dao;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IUserDAO;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ شعبان ١٤٣٥ (June 10, 2014)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@SuppressWarnings("PMD.MethodNamingConventions")
public class UserDAOTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IUserDAO utUserDAO;

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private User newUser() {
		User user = new User("userId", "userName");
		// user.setCreatorId("creatorId");
		return user;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	// ----- -----

	@Test
	public void test01_A_add_get_then_update_get_User()
			throws AlKhwarizmixException {

		Assert.assertNull(utUserDAO.getUser(newUser()));

		utUserDAO.saveOrUpdate(newUser());

		User savedUser = utUserDAO.getUser(newUser());
		Assert.assertNotNull(savedUser);
		Assert.assertEquals(newUser().getUserId(), savedUser.getUserId());
		Assert.assertEquals(newUser().getName(), savedUser.getName());
		Assert.assertNotNull(savedUser.getDomainObject());

		savedUser.setName("updatedName");
		utUserDAO.saveOrUpdate(savedUser);

		savedUser = utUserDAO.getUser(newUser());
		Assert.assertEquals("updatedName", savedUser.getName());
	}

	// ----- -----

	@Test
	public void test01_B_add_get_then_update_get_User_using_clear_and_flush()
			throws AlKhwarizmixException {

		Assert.assertNull(utUserDAO.getUser(newUser()));

		utUserDAO.saveOrUpdate(newUser());
		utUserDAO.flush();
		utUserDAO.clear();

		User savedUser = utUserDAO.getUser(newUser());
		Assert.assertNotNull(savedUser);
		Assert.assertEquals(newUser().getUserId(), savedUser.getUserId());
		Assert.assertEquals(newUser().getName(), savedUser.getName());
		Assert.assertNotNull(savedUser.getDomainObject());

		savedUser.setName("updatedName");
		utUserDAO.saveOrUpdate(savedUser);
		utUserDAO.flush();
		utUserDAO.clear();

		savedUser = utUserDAO.getUser(newUser());
		Assert.assertEquals("updatedName", savedUser.getName());
	}

	// ----- -----

	@Ignore("Find another way to create default user")
	@Test
	public void test02_default_user_was_created() throws AlKhwarizmixException {

		User userToFind = new User("fares.belhaouas");
		User defaultUser = utUserDAO.getUser(userToFind);
		Assert.assertNotNull(defaultUser);
		Assert.assertEquals("فارس بلحواس", defaultUser.getName());
	}

} // Class