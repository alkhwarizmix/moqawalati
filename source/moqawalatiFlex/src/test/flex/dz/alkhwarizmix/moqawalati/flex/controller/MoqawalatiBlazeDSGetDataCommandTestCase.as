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

import flexunit.framework.TestCase;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٣ ذو القعدة ١٤٣٤ (September 28, 2013)
 */
public class MoqawalatiBlazeDSGetDataCommandTestCase extends TestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	private var classUnderTest:MoqawalatiBlazeDSGetDataCommand = null;
	
	override public function setUp():void
	{
		super.setUp();
	}
	
	override public function tearDown():void
	{
		classUnderTest = null;
		
		super.tearDown();
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test01_constructor():void
	{
		// classUnderTest = new MoqawalatiBlazeDSGetDataCommand();
		// assertNotNull(classUnderTest);
		assertTrue(true);
	}
	
} // class
} // package