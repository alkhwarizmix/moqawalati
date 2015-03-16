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

package dz.alkhwarizmix.framework.java.dtos.email.model.vo;

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
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.utils.DateUtil;

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
public class EMailTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private EMail utEMail;

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

	private void setDataForEMailWithId(final EMail email, final int id) {
		email.setId(new Long(id));
		email.setSender(new User("Sender" + id));
		email.getSender().setId(new Long(id + 1));
		email.setReceiver(new User("Receiver" + id));
		email.getReceiver().setId(new Long(id + 2));
		email.setBody("Body" + id);
		email.setSentAt(new Date(65748 + id));
	}

	private void assertEqualEMails(final EMail expectedEMail,
			final EMail cloneEMail, final boolean testDeep) {
		Assert.assertEquals("sender", expectedEMail.getSender(),
				cloneEMail.getSender());
		Assert.assertEquals("receiver", expectedEMail.getReceiver(),
				cloneEMail.getReceiver());
		Assert.assertEquals("body", expectedEMail.getBody(),
				cloneEMail.getBody());
		Assert.assertEquals("sentAt", expectedEMail.getSentAt(),
				cloneEMail.getSentAt());
		if (testDeep) {
			Assert.assertEquals("sender id", expectedEMail.getSender().getId(),
					cloneEMail.getSender().getId());
			Assert.assertEquals("receiver id", expectedEMail.getReceiver()
					.getId(), cloneEMail.getReceiver().getId());
		}
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() {
		Assert.assertNotNull(utEMail);
	}

	@Test
	public void test01_sender_setAndGet() {
		final User valueToSet = new User("senderId1");
		utEMail.setSender(valueToSet);
		Assert.assertEquals(valueToSet, utEMail.getSender());
	}

	@Test
	public void test02_receiver_setAndGet() {
		final User valueToSet = new User("receiverId1");
		utEMail.setReceiver(valueToSet);
		Assert.assertEquals(valueToSet, utEMail.getReceiver());
	}

	@Test
	public void test03_body_setAndGet() {
		final String valueToSet = "Body";
		utEMail.setBody(valueToSet);
		Assert.assertEquals(valueToSet, utEMail.getBody());
	}

	@Test
	public void test04_A_clone_null_properties() {
		// SetUp
		final EMail expectedEMail = new EMail();
		utEMail = new EMail();
		// Test
		final EMail cloneEMail = (EMail) utEMail.clone();
		// Others
		setDataForEMailWithId(utEMail, 1567);
		// Asserts
		assertEqualEMails(expectedEMail, cloneEMail, false);
	}

	@Test
	public void test04_B_clone() {
		// SetUp
		final EMail expectedEMail = new EMail();
		setDataForEMailWithId(expectedEMail, 7651);
		setDataForEMailWithId(utEMail, 7651);
		// Test
		final EMail cloneEMail = (EMail) utEMail.clone();
		// Others
		setDataForEMailWithId(utEMail, 1569);
		// Asserts
		assertEqualEMails(expectedEMail, cloneEMail, true);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test05_toString_TDD() {
		Assert.assertTrue(false);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test06_hashCode() {
		Assert.assertTrue(false);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test07_equals_TDD() {
		Assert.assertTrue(false);
	}

} // Class
