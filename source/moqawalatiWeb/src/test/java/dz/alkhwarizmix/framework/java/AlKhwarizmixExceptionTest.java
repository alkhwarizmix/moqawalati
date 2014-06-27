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

package dz.alkhwarizmix.framework.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ شعبان ١٤٣٥ (June 26, 2014)
 */
public class AlKhwarizmixExceptionTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private AlKhwarizmixException utAlKhwarizmixException;

	@Before
	public void setUp() {
		utAlKhwarizmixException = newAlKhwarizmixException();
	}

	private AlKhwarizmixException newAlKhwarizmixException() {
		return new AlKhwarizmixException("");
	}

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
	public void test00_constructor1() throws AlKhwarizmixException {
		Assert.assertNotNull(utAlKhwarizmixException);
	}

} // Class
