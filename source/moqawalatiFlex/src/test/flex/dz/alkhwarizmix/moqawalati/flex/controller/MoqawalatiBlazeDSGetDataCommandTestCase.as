////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.controller
{

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiPureMVCTestCase;

import org.flexunit.asserts.assertNotNull;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٣ ذو القعدة ١٤٣٤ (September 28, 2013)
 */
public class MoqawalatiBlazeDSGetDataCommandTestCase extends MoqawalatiPureMVCTestCase
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
		return MoqawalatiBlazeDSGetDataCommand;
	}
	
	private function get moqawalatiBlazeDSGetDataCommand():MoqawalatiBlazeDSGetDataCommand
	{
		return classInstanceUnderTest as MoqawalatiBlazeDSGetDataCommand;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiBlazeDSGetDataCommand);
	}
	
} // class
} // package