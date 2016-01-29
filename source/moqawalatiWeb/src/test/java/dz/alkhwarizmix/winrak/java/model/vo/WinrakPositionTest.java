////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2016 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.winrak.java.model.vo;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٣ ربيع الاول ١٤٣٧ (January 02, 2016)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class WinrakPositionTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private WinrakPosition utWinrakPosition;

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
	public void test00_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utWinrakPosition);
	}

	@Test
	public void test01_distanceTo() throws AlKhwarizmixException {
		final WinrakPosition pos1 = new WinrakPosition(38.898556, -77.037852);
		final WinrakPosition pos2 = new WinrakPosition(38.897147, -77.043934);
		Assert.assertEquals(549, pos1.distanceTo(pos2), 0);
		Assert.assertEquals(549, pos2.distanceTo(pos1), 0);
	}

	@Test
	public void test02_directionTo() throws AlKhwarizmixException {
		final WinrakPosition pos1 = new WinrakPosition(38.898556, -77.037852);
		final WinrakPosition pos2 = new WinrakPosition(38.897147, -77.043934);
		Assert.assertEquals("SW", pos1.directionTo(pos2));
		Assert.assertEquals("NE", pos2.directionTo(pos1));
	}

	@Test
	public void test03_should_implements_IWinrakPosition()
			throws AlKhwarizmixException {
		Assert.assertTrue(utWinrakPosition instanceof IWinrakPosition);
	}

	@Test
	public void test04_distanceTo_equlas_1_meter() throws AlKhwarizmixException {
		final WinrakPosition pos1 = new WinrakPosition(0.0, -0.00001);
		WinrakPosition pos2 = new WinrakPosition(0.0, -0.00002);
		Assert.assertEquals(1, pos1.distanceTo(pos2));
		pos2 = new WinrakPosition(0.00001, -0.00001);
		Assert.assertEquals(1, pos1.distanceTo(pos2));
	}

	@Ignore("TODO: TDD")
	@Test
	public void testXY() throws AlKhwarizmixException {
		Assert.assertTrue(false);
	}

} // Class
