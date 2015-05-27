////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.services;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٩ محرم ١٤٣٥ (November 13, 2013)
 */
public interface ICustomizerService extends IAlKhwarizmixService {

	/**
	 */
	public CustomData setCustomData(CustomData customData,
			boolean validateObjectToPublish) throws AlKhwarizmixException;

	/**
	 */
	public String setCustomDataFromXML(String customDataXml)
			throws AlKhwarizmixException;

	/**
	 */
	public CustomData getCustomData(CustomData customData,
			boolean validateObjectToPublish) throws AlKhwarizmixException;

	/**
	 */
	public String getCustomDataAsXML(CustomData customData)
			throws AlKhwarizmixException;

	/**
	 */
	public String getCustomDataAsXML(String partialXml)
			throws AlKhwarizmixException;

} // Interface
