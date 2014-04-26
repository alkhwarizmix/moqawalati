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

import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiApplication;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiApplicationMock;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiPureMVCTestCase;

import org.flexunit.asserts.assertNotNull;

/**
 *  <p>
 *  Test for MoqawalatiWebGetDataCommand
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٦ جمادى الثانية ١٤٣٥ (April 26, 2014)
 */
public class MoqawalatiWebGetDataCommandTestCase extends MoqawalatiPureMVCTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	private var app:IMoqawalatiApplication = null;
	
	[Before]
	override public function setUp():void
	{
		super.setUp();
		
		app = new MoqawalatiApplicationMock();
		testFacade.registerCommand("NOTE", MoqawalatiWebGetDataCommand);
	}
	
	[After]
	override public function tearDown():void
	{
		testFacade.removeCommand("NOTE");
		app = null;
		
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiWebGetDataCommand;
	}
	
	private function get moqawalatiWebGetDataCommand():MoqawalatiWebGetDataCommand
	{
		return classInstanceUnderTest as MoqawalatiWebGetDataCommand;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiWebGetDataCommand);
	}
	
} // class
} // package