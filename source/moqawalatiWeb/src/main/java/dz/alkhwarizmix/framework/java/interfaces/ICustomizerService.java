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

package dz.alkhwarizmix.framework.java.interfaces;

import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;

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
	public void setCustomData(CustomData customData) throws MoqawalatiException;

	/**
	 */
	public String setCustomData(String customDataXml)
			throws MoqawalatiException;

	/**
	 */
	public CustomData getCustomData(CustomData customData)
			throws MoqawalatiException;

	/**
	 */
	public String getCustomDataAsXML(CustomData customData)
			throws MoqawalatiException;

	/**
	 */
	public String getCustomDataAsXML(String partialXml)
			throws MoqawalatiException;

} // Class