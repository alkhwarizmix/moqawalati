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

package dz.alkhwarizmix.framework.java.interfaces;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٧ ربيع الثاني ١٤٣٦ (January 27, 2015)
 */
public interface IEMailService extends IAlKhwarizmixService {

	/**
	 */
	public void addEMail(EMail email) throws AlKhwarizmixException;

	/**
	 */
	public EMail getEMail(EMail email) throws AlKhwarizmixException;

	/**
	 */
	public EMail getPendingEMail() throws AlKhwarizmixException;

	/**
	 */
	public void sendEMail(EMail email) throws AlKhwarizmixException;

	/**
	 */
	public EMail updateEMail(EMail email) throws AlKhwarizmixException;

} // Interface
