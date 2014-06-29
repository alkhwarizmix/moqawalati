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

package dz.alkhwarizmix.moqawalati.flex.controller
{

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiPureMVCTestCase;

import org.flexunit.asserts.assertEquals;
import org.flexunit.asserts.assertNotNull;

/**
 *  <p>
 *  Test for MoqawalatiLogoutCommand
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ جمادى الأول ١٤٣٥ (March 25, 2014)
 */
public class MoqawalatiLogoutCommandTestCase extends MoqawalatiPureMVCTestCase
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
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiLogoutCommand;
	}
	
	private function get moqawalatiLogoutCommand():MoqawalatiLogoutCommand
	{
		return classInstanceUnderTest as MoqawalatiLogoutCommand;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiLogoutCommand);
	}
	
	[Test]
	public function test01_destination():void
	{
		assertEquals("userService", moqawalatiLogoutCommand.destination);
	}
	
	[Test]
	public function test02_operationName():void
	{
		assertEquals("logout", moqawalatiLogoutCommand.operationName);
	}
	
	[Test]
	public function test03_proxyName():void
	{
		assertEquals("MoqawalatiLoginUserProxy", moqawalatiLogoutCommand.proxyName);
	}
	
} // class
} // package
