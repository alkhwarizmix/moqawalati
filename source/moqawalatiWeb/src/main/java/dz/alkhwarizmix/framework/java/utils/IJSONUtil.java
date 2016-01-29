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

package dz.alkhwarizmix.framework.java.utils;

import java.util.List;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.IAlKhwarizmixJsonObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ربيع الاول ١٤٣٧ (December 19, 2015)
 */
public interface IJSONUtil {

	/**
	 */
	public String objectListToJSON(
			final List<IAlKhwarizmixJsonObject> objectList);

	/**
	 */
	public String marshalObjectToJSON(final IAlKhwarizmixJsonObject object)
			throws AlKhwarizmixException;

	/**
	 */
	public IAlKhwarizmixJsonObject unmarshalObjectFromJSON(
			final String jsonValue, final Class<?> clazz)
			throws AlKhwarizmixException;

} // Interface
