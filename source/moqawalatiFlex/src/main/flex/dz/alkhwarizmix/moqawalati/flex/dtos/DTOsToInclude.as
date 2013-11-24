////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.dtos
{

import dz.alkhwarizmix.moqawalati.flex.dtos.modules.clientModule.model.vo.ClientVO;
import dz.alkhwarizmix.moqawalati.flex.dtos.modules.userModule.model.vo.UserVO;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
public class DTOsToInclude
{
	/**
	 * TODO: ASDOC Definition of registerNeededClasses
	 */
	public function registerNeededClasses():void
	{
		var clientVO:ClientVO = new ClientVO();
		var userVO:UserVO = new UserVO();
	}
	
} // Class
} // Package