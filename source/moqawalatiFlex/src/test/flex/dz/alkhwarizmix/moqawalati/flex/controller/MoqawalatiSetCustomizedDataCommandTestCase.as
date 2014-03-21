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

import org.flexunit.asserts.assertEquals;
import org.flexunit.asserts.assertNotNull;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٠ جمادى الأول ١٤٣٥ (March 20, 2014)
 */
public class MoqawalatiSetCustomizedDataCommandTestCase extends MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	[Before]
	override public function setUp():void
	{
		registerMoqawalatiConfigProxy();
		
		super.setUp();
	}
	
	[After]
	override public function tearDown():void
	{
		super.tearDown();
		
		removeMoqawalatiConfigProxy();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiSetCustomizedDataCommand;
	}
	
	private function get moqawalatiSetCustomizedDataCommand():MoqawalatiSetCustomizedDataCommand
	{
		return classInstanceUnderTest as MoqawalatiSetCustomizedDataCommand;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiSetCustomizedDataCommand);
	}
	
	[Test]
	public function test01_destination():void
	{
		assertEquals("customizerService", moqawalatiSetCustomizedDataCommand.destination);
	}
	
	[Test]
	public function test02_operationName():void
	{
		assertEquals("setCustomData", moqawalatiSetCustomizedDataCommand.operationName);
	}
	
	[Test]
	public function test03_proxyName():void
	{
		assertEquals("MoqawalatiCustomDataProxy", moqawalatiSetCustomizedDataCommand.proxyName);
	}
	
} // class
} // package