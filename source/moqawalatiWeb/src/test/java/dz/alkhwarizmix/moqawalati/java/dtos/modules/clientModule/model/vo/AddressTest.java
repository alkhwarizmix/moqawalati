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

package dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ شعبان ١٤٣٥ (June 26, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class AddressTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private Address utAddress;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Ignore("TODO: TDD")
	@Test
	public void test00() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

	@Test
	public void test01_set_then_get_addressId() {
		String value = "AddressTest";
		utAddress.setAddressId(value);
		assertEquals(value, utAddress.getAddressId());
	}

} // Class
