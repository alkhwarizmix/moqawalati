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
 * @since ٢٧ رجب ١٤٣٦ (May 15, 2015)
 */
@SuppressWarnings("PMD.MethodNamingConventions")
public class AlKhwarizmixApplicationContextHolderTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private AlKhwarizmixApplicationContextHolder utAlKhwarizmixApplicationContextHolder;

	@Before
	public void setUp() {
		utAlKhwarizmixApplicationContextHolder = newAlKhwarizmixApplicationContextHolder();
	}

	private AlKhwarizmixApplicationContextHolder newAlKhwarizmixApplicationContextHolder() {
		return new AlKhwarizmixApplicationContextHolder();
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
		Assert.assertNotNull(utAlKhwarizmixApplicationContextHolder);
	}

} // Class
