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

package dz.alkhwarizmix.moqawalati.flex.modules.clientModule
{

import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.controller.ClientGetListCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.controller.ClientModuleStartupCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.facade.ClientModuleFacadeTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.model.ClientProxyTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.view.ClientListMediatorTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.view.components.ClientListCanvasTestCase;

/**
 *  <p>
 *  Contains tests for ClientModule
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٢ جمادى الأول ١٤٣٥ (March 22, 2014)
 */
[Suite]
[RunWith("org.flexunit.runners.Suite")]
public class ClientModuleTestSuite
{
	public var clientModuleTestCase:ClientModuleTestCase;
	public var clientModuleConstantsTestCase:ClientModuleConstantsTestCase;
	
	// facade
	public var clientModuleFacadeTestCase:ClientModuleFacadeTestCase;
	
	// controller
	public var clientModuleStartupCommandTestCase:ClientModuleStartupCommandTestCase;
	public var clientGetListCommandTestCase:ClientGetListCommandTestCase;
	
	// model
	public var clientProxyTestCase:ClientProxyTestCase;
	
	// view
	public var clientListMediatorTestCase:ClientListMediatorTestCase;
	
	// view.components
	public var clientListCanvasTestCase:ClientListCanvasTestCase;
	
} // class
} // package
