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

package dz.alkhwarizmix.moqawalati.flex.modules.userModule.facade
{

import dz.alkhwarizmix.moqawalati.flex.modules.userModule.UserModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.controller.UserGetListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.controller.UserModuleStartupCommand;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiTestCase;

import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  Test for UserModuleFacade
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٢ جمادى الأول ١٤٣٥ (March 22, 2014)
 */
public class UserModuleFacadeTestCase extends MoqawalatiTestCase
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
		return UserModuleFacade;
	}
	
	override protected function get classUnderTestConstructorArg1():*
	{
		return "UserModuleFacadeTestCase_FACADE";
	}
	
	private function get utUserModuleFacade():UserModuleFacade
	{
		return classInstanceUnderTest as UserModuleFacade;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utUserModuleFacade);
	}
	
	[Test]
	public function test01_registeredCommands():void
	{
		assertTrue(newFacadeCommandClassWithKey(utUserModuleFacade,
			UserModuleConstants.STARTUP) is UserModuleStartupCommand);
		assertTrue(newFacadeCommandClassWithKey(utUserModuleFacade,
			UserModuleConstants.USER_GET_LIST) is UserGetListCommand);
	}
	
} // class
} // package