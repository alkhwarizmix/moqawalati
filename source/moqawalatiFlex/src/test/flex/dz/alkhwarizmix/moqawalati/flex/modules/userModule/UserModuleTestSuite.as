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

import dz.alkhwarizmix.moqawalati.flex.modules.userModule.controller.UserGetListCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.controller.UserModuleStartupCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.facade.UserModuleFacadeTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.model.UserProxyTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.view.UserListMediatorTestCase;
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
	public var userModuleConstantsTestCase:UserModuleConstantsTestCase;
	
	// facade
	public var userModuleFacadeTestCase:UserModuleFacadeTestCase;
	
	// controller
	public var userGetListCommandTestCase:UserGetListCommandTestCase;
	public var userModuleStartupCommandTestCase:UserModuleStartupCommandTestCase;
	
	// model
	public var userProxyTestCase:UserProxyTestCase;
	
	// view
	public var userListMediatorTestCase:UserListMediatorTestCase;
	
	// view.components
	public var userModuleTestCase:UserModuleTestCase; 
	public var userListCanvasTestCase:UserListCanvasTestCase;
	
} // class
} // package
