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

package dz.alkhwarizmix.framework.java.services;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ربيع الاول ١٤٣٧ (December 19, 2015)
 */
public interface IPrototypeService extends IAlKhwarizmixService {

	/**
	 */
	public String protoPost(String param1) throws AlKhwarizmixException;

	/**
	 */
	public String protoGet(String param1) throws AlKhwarizmixException;

	/**
	 */
	public String position(IWinrakPosition position, int count)
			throws AlKhwarizmixException;

} // Interface
