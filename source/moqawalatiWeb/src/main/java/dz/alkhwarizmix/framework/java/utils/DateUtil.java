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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ جمادى الأولى ١٤٣٦ (March 03, 2015)
 */
public class DateUtil {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public DateUtil() {
		this(null);
	}

	protected DateUtil(final TimeZone timeZone) {
		this.timeZone = (timeZone != null)
				? timeZone
				: TimeZone.getTimeZone("UTC");
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private final TimeZone timeZone;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 *
	 * @return
	 */
	public Date newDate() {
		return new Date();
	}

	/**
	 *
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public Date newDate(final int year, final int month, final int dayOfMonth,
			final int hour, final int minute, final int second) {
		final Calendar calendar = Calendar.getInstance(timeZone);
		calendar.set(year, month - 1, dayOfMonth, hour, minute, second);
		return calendar.getTime();
	}

} // Class
