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

package dz.alkhwarizmix.framework.java.services;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.interfaces.IEMailDAO;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ ربيع الثاني ١٤٣٦ (February 01, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class EMailServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private EMailService utEMailService;

	@Mock
	private AlKhwarizmixSessionData mockSessionData;

	@Mock
	private IEMailDAO mockEMailDAO;

	@Mock
	private EMailServiceValidator mockEmailServiceValidator;

	@Before
	public void setUp() {
		setupUtEMailService();
	}

	private void setupUtEMailService() {
		utEMailService.setSessionData(mockSessionData);
		utEMailService.setEMailDAO(mockEMailDAO);
		utEMailService.setEMailValidator(mockEmailServiceValidator);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Ignore("TODO: TDD")
	@Test
	public void test01_addEMail() {
		Assert.assertTrue(false);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test02_getEMail() {
		Assert.assertTrue(false);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test03_getEMail() {
		Assert.assertTrue(false);
	}

	@Test
	public void test04_A_getPendingEMail_should_return_first_added_email()
			throws AlKhwarizmixException {
		final EMail email1 = new EMail();
		final EMail email2 = new EMail();
		utEMailService.addEMail(email1, true);
		utEMailService.addEMail(email2, true);
		// TEST
		final EMail result = utEMailService.getPendingEMail(false);
		// ASSERT
		Assert.assertEquals(email1, result);
	}

	@Test
	public void test04_B_getPendingEMail_should_not_return_sent_email()
			throws AlKhwarizmixException {
		final EMail email1 = new EMail();
		final EMail email2 = new EMail();
		final EMail email3 = new EMail();
		utEMailService.addEMail(email1, true);
		utEMailService.addEMail(email2, true);
		utEMailService.addEMail(email3, true);
		// TEST 1
		email1.setSentAt(new Date());
		EMail result = utEMailService.getPendingEMail(false);
		// ASSERT 1
		Assert.assertEquals(email2, result);
		// TEST 2
		email1.setSentAt(null);
		result = utEMailService.getPendingEMail(false);
		// ASSERT 1
		Assert.assertEquals(email2, result);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test05_sendEMail() {
		Assert.assertTrue(false);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test06_updateEMail() {
		Assert.assertTrue(false);
	}

} // Class
