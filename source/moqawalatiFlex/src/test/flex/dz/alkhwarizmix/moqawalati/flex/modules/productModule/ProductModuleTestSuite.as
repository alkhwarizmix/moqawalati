////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.modules.productModule
{

import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductCommitListCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductGetListCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductModuleStartupCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.RemoteServerErrorCommandTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.facade.ProductModuleFacadeTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.model.vo.ProductVOTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.view.ProductListMediatorTestCase;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.view.components.ProductListCanvasTestCase;

/**
 *  <p>
 *  Contains tests for ProductModule
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ صفر ١٤٣٦ (December 17, 2014)
 */
[Suite]
[RunWith("org.flexunit.runners.Suite")]
public class ProductModuleTestSuite
{
	public var productModuleConstantsTestCase:ProductModuleConstantsTestCase;
	
	// facade
	public var productModuleFacadeTestCase:ProductModuleFacadeTestCase;
	
	// controller
	public var productModuleStartupCommandTestCase:ProductModuleStartupCommandTestCase;
	public var productGetListCommandTestCase:ProductGetListCommandTestCase;
	public var productCommitListCommandTestCase:ProductCommitListCommandTestCase;
	public var remoteServerErrorCommandTestCase:RemoteServerErrorCommandTestCase;
	
	// model
	// public var moqawalatiConfigProxyTestCase:MoqawalatiConfigProxyTestCase;
	public var productVOTestCase:ProductVOTestCase;
	
	// view
	public var productListMediatorTestCase:ProductListMediatorTestCase;
	
	// view.components
	public var productModuleTestCase:ProductModuleTestCase;
	public var productListCanvasTestCase:ProductListCanvasTestCase;
	
} // class
} // package
