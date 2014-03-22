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

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiTestCase;

import org.flexunit.asserts.assertNotNull;

/**
 *  <p>
 *  Test for MoqawalatiStartupCommand
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢١ جمادى الأول ١٤٣٥ (March 21, 2014)
 */
public class MoqawalatiStartupCommandTestCase extends MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	[Before]
	override public function setUp():void
	{
		// registerMoqawalatiConfigProxy();
		
		super.setUp();
	}
	
	[After]
	override public function tearDown():void
	{
		super.tearDown();
		
		// removeMoqawalatiConfigProxy();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiStartupCommand;
	}
	
	private function get moqawalatiStartupCommand():MoqawalatiStartupCommand
	{
		return classInstanceUnderTest as MoqawalatiStartupCommand;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiStartupCommand);
	}
	
	[Ignore("REDO IN TDD")]
	[Test]
	public function test01_REDO_TDD():void
	{
		assertNotNull(null);
	}
	
} // class
} // package