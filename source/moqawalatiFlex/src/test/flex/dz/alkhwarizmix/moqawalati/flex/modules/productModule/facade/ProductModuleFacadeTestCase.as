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

package dz.alkhwarizmix.moqawalati.flex.modules.productModule.facade
{

import dz.alkhwarizmix.framework.flex.AlKhwarizmixConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.ProductModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductCommitListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductGetListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductModuleStartupCommand;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiPureMVCTestCase;

import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  Test for ProductModuleFacade
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ صفر ١٤٣٦ (December 17, 2014)
 */
public class ProductModuleFacadeTestCase extends MoqawalatiPureMVCTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	override protected function setUp():void
	{
		super.setUp();
	}
	
	override protected function tearDown():void
	{
		removeFacadeCore(classUnderTestConstructorArg1);
		
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return ProductModuleFacade;
	}
	
	override protected function get classUnderTestConstructorArg1():*
	{
		return "ProductModuleFacadeTestCase_FACADE";
	}
	
	private function get utProductModuleFacade():ProductModuleFacade
	{
		return classInstanceUnderTest as ProductModuleFacade;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utProductModuleFacade);
	}
	
	[Test]
	public function test01_registeredCommands():void
	{
		assertTrue(newFacadeCommandClassWithKey(utProductModuleFacade,
			ProductModuleConstants.STARTUP) is ProductModuleStartupCommand);
		assertTrue(newFacadeCommandClassWithKey(utProductModuleFacade,
			AlKhwarizmixConstants.GET_RECORD_LIST) is ProductGetListCommand);
		assertTrue(newFacadeCommandClassWithKey(utProductModuleFacade,
			AlKhwarizmixConstants.COMMIT_RECORD_LIST) is ProductCommitListCommand);
	}
	
} // class
} // package
