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

package dz.alkhwarizmix.winrak.java.model;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٣ ربيع الاول ١٤٣٧ (January 02, 2016)
 */
public interface IWinrakPosition {

	/**
	 *
	 * @param value
	 *            the address to set
	 */
	public void setAddress(final String value);

	/**
	 *
	 * @return the address
	 */
	public String getAddress();

	/**
	 *
	 * @return the latitude
	 */
	public Double getLat();

	/**
	 *
	 * @return the longitude
	 */
	public Double getLng();

	/**
	 *
	 * @param pos2
	 * @return
	 */
	public String directionTo(final IWinrakPosition pos2);

	/**
	 *
	 * @param pos2
	 * @return
	 */
	public int distanceTo(final IWinrakPosition pos2);

} // Class
