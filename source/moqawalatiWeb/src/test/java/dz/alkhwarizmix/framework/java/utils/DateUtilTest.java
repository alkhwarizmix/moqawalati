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

package dz.alkhwarizmix.framework.java.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ جمادى الأولى ١٤٣٦ (March 03, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class DateUtilTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private DateUtil utDateUtil;

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
	public void test00_constructor1() {
		Assert.assertNotNull(utDateUtil);
	}

	@Test
	public void test01_newDate() {
		final Date expectedBeforeDate = new Date();
		final Date result = utDateUtil.newDate();
		final Date expectedAfterDate = new Date();
		Assert.assertTrue(expectedBeforeDate.before(result)
				|| expectedBeforeDate.equals(result));
		Assert.assertTrue(expectedAfterDate.after(result)
				|| expectedAfterDate.equals(result));
	}

	@Test
	public void test02_newDate_should_be_UTC_Date() {
		final Date result = utDateUtil.newDate(2015, 12, 31, 23, 59, 59);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Assert.assertEquals("2015-12-31 23:59:59", sdf.format(result));
	}

} // Class
