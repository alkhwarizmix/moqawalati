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
 * @since ٢٤ جمادى الأولى ١٤٣٦ (March 15, 2015)
 */
@SuppressWarnings("PMD.MethodNamingConventions")
public class AlKhwarizmixBlazeDSExceptionTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private AlKhwarizmixBlazeDSException utAlKhwarizmixBlazeDSException;

	@Before
	public void setUp() {
		utAlKhwarizmixBlazeDSException = newAlKhwarizmixBlazeDSException();
	}

	private AlKhwarizmixBlazeDSException newAlKhwarizmixBlazeDSException() {
		return new AlKhwarizmixBlazeDSException("");
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
	public void test00_constructor1() throws AlKhwarizmixBlazeDSException {
		Assert.assertNotNull(utAlKhwarizmixBlazeDSException);
	}

} // Class
