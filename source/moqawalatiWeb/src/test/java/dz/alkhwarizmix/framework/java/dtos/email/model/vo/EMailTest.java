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

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

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

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	// EMPTY

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
		User valueToSet = new User("senderId1");
		utEMail.setSender(valueToSet);
		Assert.assertEquals(valueToSet, utEMail.getSender());
	}

	@Test
	public void test02_receiver_setAndGet() {
		User valueToSet = new User("receiverId1");
		utEMail.setReceiver(valueToSet);
		Assert.assertEquals(valueToSet, utEMail.getReceiver());
	}

	@Test
	public void test03_body_setAndGet() {
		String valueToSet = "Body";
		utEMail.setBody(valueToSet);
		Assert.assertEquals(valueToSet, utEMail.getBody());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test02_clone() {
		Assert.assertTrue(false);
	}

} // Class
