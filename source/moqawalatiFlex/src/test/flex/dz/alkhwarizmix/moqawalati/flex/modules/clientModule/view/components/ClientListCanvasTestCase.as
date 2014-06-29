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

package dz.alkhwarizmix.moqawalati.flex.modules.clientModule.view.components
{

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiUITestCase;

import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٣٠ شعبان ١٤٣٥ (June 28, 2014)
 */
public class ClientListCanvasTestCase extends MoqawalatiUITestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	override protected function doAfterAsyncUISetUp():void
	{
		// NOOP
	}
	
	override protected function doBeforeUITearDown():void
	{
		// NOOP
	}
	
	override protected function get classUnderTest():Class
	{
		return ClientListCanvas;
	}
	
	private function get utClientListCanvas():ClientListCanvas
	{
		return classInstanceUnderTest as ClientListCanvas;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utClientListCanvas);
	}
	
	[Ignore("TODO: TDD")]
	[Test]
	public function test01():void
	{
		assertTrue(false);
	}
	
} // class
} // package
