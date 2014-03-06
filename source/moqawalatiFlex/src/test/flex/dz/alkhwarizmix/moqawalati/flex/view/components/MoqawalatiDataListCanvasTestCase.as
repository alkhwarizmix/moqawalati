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

package dz.alkhwarizmix.moqawalati.flex.view.components
{

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiUITestCase;

import org.flexunit.asserts.assertNotNull;

/**
 *  <p>
 *  Test for MoqawalatiDataListCanvas
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ربيع الأول ١٤٣٥ (January 03, 2014)
 */
public class MoqawalatiDataListCanvasTestCase extends MoqawalatiUITestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	[Before(async, ui)]
	override public function setUp():void
	{
		super.setUp();
	}
	
	[After(ui)]
	override public function tearDown():void
	{
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiDataListCanvas;
	}
	
	private function get utMoqawalatiDataListCanvas():MoqawalatiDataListCanvas
	{
		return classInstanceUnderTest as MoqawalatiDataListCanvas;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test01_constructor():void
	{
		assertNotNull(classInstanceUnderTest);
	}
	
	[Test]
	public function test02_has_contextMenu():void
	{
		assertNotNull(utMoqawalatiDataListCanvas.contextMenu);
	}
	
} // class
} // package