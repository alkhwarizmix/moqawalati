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

package dz.alkhwarizmix.moqawalati.flex.modules.clientModule.facade
{

import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.ClientModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.controller.ClientGetListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.controller.ClientModuleStartupCommand;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiPureMVCTestCase;

import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  Test for ClientModuleFacade
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٢ جمادى الأول ١٤٣٥ (March 22, 2014)
 */
public class ClientModuleFacadeTestCase extends MoqawalatiPureMVCTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	[Before]
	override public function setUp():void
	{
		super.setUp();
	}
	
	[After]
	override public function tearDown():void
	{
		super.tearDown();
		removeFacadeCore(classUnderTestConstructorArg1);
	}
	
	override protected function get classUnderTest():Class
	{
		return ClientModuleFacade;
	}
	
	override protected function get classUnderTestConstructorArg1():*
	{
		return "ClientModuleFacadeTestCase_FACADE";
	}
	
	private function get utClientModuleFacade():ClientModuleFacade
	{
		return classInstanceUnderTest as ClientModuleFacade;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utClientModuleFacade);
	}
	
	[Test]
	public function test01_registeredCommands():void
	{
		assertTrue(newFacadeCommandClassWithKey(utClientModuleFacade,
			ClientModuleConstants.STARTUP) is ClientModuleStartupCommand);
		assertTrue(newFacadeCommandClassWithKey(utClientModuleFacade,
			ClientModuleConstants.CLIENT_GET_LIST) is ClientGetListCommand);
	}
	
} // class
} // package