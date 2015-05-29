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

package dz.alkhwarizmix.framework.java.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IEMailDAO;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ ربيع الثاني ١٤٣٦ (February 01, 2015)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@SuppressWarnings("PMD.MethodNamingConventions")
public class EMailDAOTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IEMailDAO utEMailDAO;

	User sender1;
	User sender2;

	User receiver1;
	User receiver2;

	@Before
	public void setUp() {
		sender1 = new User();
		receiver1 = new User();
		sender2 = new User();
		receiver2 = new User();
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private EMail newEMail(User sender, User receiver) {
		EMail email = new EMail();
		email.setSender(sender);
		email.setReceiver(receiver);
		return email;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	// ----- -----

	@Test
	public void test01_A_add_get_then_update_get_EMail()
			throws AlKhwarizmixException {
		/*Assert.assertNull(utEMailDAO.getEMail(newEMail()));
		// add
		utEMailDAO.saveOrUpdate(newEMail());
		// get
		EMail savedEMail = utEMailDAO.getEMail(newEMail());
		Assert.assertNotNull(savedEMail);
		Assert.assertEquals(newEMail().getEMailId(), savedEMail.getEMailId());
		Assert.assertNotNull(savedEMail.getParent());
		Assert.assertNotNull(savedEMail.getParent().getParent());
		// update
		savedEMail.setData("Updated Data 2546");
		utEMailDAO.saveOrUpdate(savedEMail);
		// get
		savedEMail = utEMailDAO.getEMail(newEMail());
		Assert.assertEquals("Updated Data 2546", savedEMail.getData());*/
	}

	@Test
	public void test01_B_add_get_then_update_get_EMail_using_clear_and_flush()
			throws AlKhwarizmixException {
		/*Assert.assertNull(utEMailDAO.getEMail(newEMail()));
		// add
		utEMailDAO.saveOrUpdate(newEMail());
		utEMailDAO.flush();
		utEMailDAO.clear();
		// get
		EMail savedEMail = utEMailDAO.getEMail(newEMail());
		Assert.assertNotNull(savedEMail);
		Assert.assertEquals(newEMail().getEMailId(), savedEMail.getEMailId());
		Assert.assertEquals(newEMail().getData(), savedEMail.getData());
		// update
		savedEMail.setData("Update Data 3445");
		utEMailDAO.saveOrUpdate(savedEMail);
		utEMailDAO.flush();
		utEMailDAO.clear();
		// get
		savedEMail = utEMailDAO.getEMail(newEMail());
		Assert.assertEquals("Update Data 3445", savedEMail.getData());*/
	}

} // Class
