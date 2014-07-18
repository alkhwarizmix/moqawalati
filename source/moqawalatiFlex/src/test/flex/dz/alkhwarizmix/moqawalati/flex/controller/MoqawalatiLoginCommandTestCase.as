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
 *  Test for MoqawalatiLoginCommand
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٠ جمادى الأول ١٤٣٥ (March 20, 2014)
 */
public class MoqawalatiLoginCommandTestCase extends MoqawalatiPureMVCTestCase
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
		return MoqawalatiLoginCommand;
	}
	
	private function get moqawalatiLoginCommand():MoqawalatiLoginCommand
	{
		return classInstanceUnderTest as MoqawalatiLoginCommand;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiLoginCommand);
	}
	
	[Test]
	public function test01_destination():void
	{
		assertEquals("userService", moqawalatiLoginCommand.destination);
	}
	
	[Test]
	public function test02_operationName():void
	{
		assertEquals("login", moqawalatiLoginCommand.operationName);
	}
	
	[Test]
	public function test03_proxyName():void
	{
		assertEquals("MoqawalatiLoginUserProxy", moqawalatiLoginCommand.proxyName);
	}
	
} // class
} // package
