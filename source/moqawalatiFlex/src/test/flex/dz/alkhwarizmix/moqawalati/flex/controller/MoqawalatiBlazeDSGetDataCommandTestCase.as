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

import org.flexunit.asserts.assertEquals;
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
	
	override protected function setUp():void
	{
		super.setUp();
		
		registerMoqawalatiConfigProxy();
	}
	
	override protected function tearDown():void
	{
		removeMoqawalatiConfigProxy();
		
		super.tearDown();
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
	
	[Test]
	public function test01_amfURI():void
	{
		moqawalatiConfigProxy.appParameters.appURL = "http://dz.moqawalati.com/moqawalati.swf";
		assertEquals("http://dz.moqawalati.com/messagebroker/amf",
			moqawalatiBlazeDSGetDataCommand.amfURI);
	}
	
} // class
} // package
