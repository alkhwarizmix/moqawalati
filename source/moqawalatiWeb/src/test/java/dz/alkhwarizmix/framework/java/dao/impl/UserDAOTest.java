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

package dz.alkhwarizmix.framework.java.dao.impl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IUserDAO;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Password;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

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
		return newUser("user@dz.alkhwarizmix.com");
	}

	private User newUser(final String userId) {
		final User user = new User(userId, "userName");
		// user.setCreatorId("creatorId");
		return user;
	}

	private Password newPassword(final User user) {
		final Password password = new Password();
		password.setUser(user);
		password.setPassword("Mohamed");
		return password;
	}

	protected Password newPassword() {
		return newPassword(newUser());
	}

	private Password createPassword(final User user, final int lastUseMillis)
			throws AlKhwarizmixDAOException {
		final Password result = newPassword(user);
		if (lastUseMillis > 0)
			result.setLastUse(new Date(lastUseMillis));
		utUserDAO.saveOrUpdate(result);
		return result;
	}

	private User createUser(final String userId)
			throws AlKhwarizmixDAOException {
		final User result = newUser(userId);
		utUserDAO.saveOrUpdate(result);
		return result;
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

	@Test
	public void test02_default_users_were_created()
			throws AlKhwarizmixException {

		assertDefaultUser("fbelhaouas@icloud.com", "فارس بلحواس");
		assertDefaultUser("fares@dz.moqawalati.com", "Fares @ Moqawalati");
	}

	private void assertDefaultUser(final String userId, final String userName)
			throws AlKhwarizmixException {
		final User userToFind = new User(userId);
		final User defaultUser = utUserDAO.getUser(userToFind);
		Assert.assertNotNull(defaultUser);
		Assert.assertEquals(userName, defaultUser.getName());
	}

	// ----- -----

	@Test
	public void test03_A_getUserPasswords_should_return_1_latest_added_then_used()
			throws AlKhwarizmixException {
		final User user1 = createUser("user1@dz.alkhwarizmix.com");
		final User user2 = createUser("user2@dz.alkhwarizmix.com");

		createPassword(user1, 12346); // password1u1
		createPassword(user1, -1); // password2u1
		final Password password3u1 = createPassword(user1, 12345);

		createPassword(user2, -1); // password2u2

		final List<Password> result = utUserDAO
				.getUserPasswords(newUser("user1@dz.alkhwarizmix.com"));
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(password3u1, result.get(0));
	}

	@Test
	public void test03_B_getUserPasswords_should_return_2_latest_added_not_used_and_latest_added_used()
			throws AlKhwarizmixException {
		final User user1 = createUser("user1@dz.alkhwarizmix.com");
		final User user2 = createUser("user2@dz.alkhwarizmix.com");

		// Situation should not happen but good for microtest
		// create password first but has lastUse later
		createPassword(user1, 999999); // password1u1
		// create password second but has lastUse earlier
		final Password password2u1 = createPassword(user1, 111111);
		// Added not used, in case of forget password
		final Password password3u1 = createPassword(user1, -1);

		createPassword(user2, -1);

		final List<Password> result = utUserDAO
				.getUserPasswords(newUser("user1@dz.alkhwarizmix.com"));
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(password3u1, result.get(0));
		Assert.assertEquals(password2u1, result.get(1));
	}

} // Class
