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

package dz.alkhwarizmix.moqawalati.flex
{

import dz.alkhwarizmix.moqawalati.flex.dtos.MoqawalatiDTOsToIncludeTestCase;
import dz.alkhwarizmix.moqawalati.flex.dtos.modules.clientModule.model.vo.ClientVOTestCase;
import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiMainFacadeTestCase;

[Suite]
[RunWith("org.flexunit.runners.Suite")]
public class MoqawalatiTestSuite
{
	public var mMoqawalatiFlexTestCase:MoqawalatiFlexTestCase;
	
	// facade
	public var moqawalatiMainFacadeTestCase:MoqawalatiMainFacadeTestCase;
	
	// messaging
	
	// controller
	
	// event
	
	// model
	
	// dtos
	public var moqawalatiDTOsToIncludeTestCase:MoqawalatiDTOsToIncludeTestCase;
	public var clientVOTestCase:ClientVOTestCase;
	
	// resources
	
	// view
	
	// view.components
	
	// view.containers
	
} // class
} // package
