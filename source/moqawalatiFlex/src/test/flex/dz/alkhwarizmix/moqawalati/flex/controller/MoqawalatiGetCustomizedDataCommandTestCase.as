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
public class MoqawalatiGetCustomizedDataCommandTestCase extends MoqawalatiTestCase
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
		return MoqawalatiGetCustomizedDataCommand;
	}
	
	private function get moqawalatiGetCustomizedDataCommand():MoqawalatiGetCustomizedDataCommand
	{
		return classInstanceUnderTest as MoqawalatiGetCustomizedDataCommand;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiGetCustomizedDataCommand);
	}
	
	[Test]
	public function test01_destination():void
	{
		assertEquals("customizerService", moqawalatiGetCustomizedDataCommand.destination);
	}
	
	[Test]
	public function test02_operationName():void
	{
		assertEquals("getCustomData", moqawalatiGetCustomizedDataCommand.operationName);
	}
	
	[Ignore("Test in progress")]
	[Test]
	public function test03_proxyName():void
	{
		assertEquals("MoqawalatiCustomDataProxy", moqawalatiGetCustomizedDataCommand.proxyName);
	}
	
} // class
} // package