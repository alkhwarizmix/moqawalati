////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.modules.userModule
{

import dz.alkhwarizmix.moqawalati.flex.modules.userModule.facade.UserModuleFacadeTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.view.components.UserListCanvasTestCase;

/**
 *  <p>
 *  Contains tests for UserModule
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٢ جمادى الأول ١٤٣٥ (March 22, 2014)
 */
[Suite]
[RunWith("org.flexunit.runners.Suite")]
public class UserModuleTestSuite
{
	// facade
	public var userModuleFacadeTestCase:UserModuleFacadeTestCase;
	
	// controller
	// public var moqawalatiBlazeDSGetDataCommandTestCase:MoqawalatiBlazeDSGetDataCommandTestCase;
	
	// model
	// public var moqawalatiConfigProxyTestCase:MoqawalatiConfigProxyTestCase;
	
	// view
	// public var moqawalatiHBoxTestCase:MoqawalatiHBoxTestCase;
	
	// view.components
	public var userListCanvasTestCase:UserListCanvasTestCase;
	
} // class
} // package
