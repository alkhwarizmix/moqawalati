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

import dz.alkhwarizmix.framework.flex.dtos.AlKhwarizmixDTOsToInclude;
import dz.alkhwarizmix.moqawalati.flex.dtos.modules.clientModule.model.vo.ClientVO;
import dz.alkhwarizmix.moqawalati.flex.dtos.modules.userModule.model.vo.UserVO;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.model.vo.ProductVO;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
public class MoqawalatiDTOsToInclude extends AlKhwarizmixDTOsToInclude
{
	/**
	 * TODO: ASDOC Definition of registerNeededClasses
	 */
	override protected function registerNeededClasses():void
	{
		super.registerNeededClasses();
		
		registerClass(ClientVO);
		registerClass(UserVO);
		registerClass(ProductVO);
	}
	
} // Class
} // Package
