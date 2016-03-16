////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.winrak.java.services;

import java.util.List;
import java.util.Map;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٧ ربيع الاول ١٤٣٧ (December 28, 2015)
 */
public interface IWinrakService extends IAlKhwarizmixService {

	/**
	 *
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws AlKhwarizmixException
	 */
	public String convertPositionToAddress(final Double latitude,
			final Double longitude, long timeout_ms)
			throws AlKhwarizmixException;

	/**
	 *
	 * @param origin
	 * @param destinations
	 * @param timeout_ms
	 * @return
	 * @throws AlKhwarizmixException
	 */
	public Map<IWinrakPosition, Long> getDistances(
			final IWinrakPosition origin,
			final List<IWinrakPosition> destinations, final long timeout_ms)
			throws AlKhwarizmixException;

	/**
	 *
	 * @param position
	 * @param count
	 * @return
	 * @throws AlKhwarizmixException
	 */
	public String trouvauto(IWinrakPosition position, int count)
			throws AlKhwarizmixException;

} // Class
