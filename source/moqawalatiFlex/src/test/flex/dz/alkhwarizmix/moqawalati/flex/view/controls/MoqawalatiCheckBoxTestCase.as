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

package dz.alkhwarizmix.moqawalati.flex.view.controls
{

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiUITestCase;

import org.flexunit.asserts.assertNotNull;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ جمادى الثانية ١٤٣٥ (April 25, 2014)
 */
public class MoqawalatiCheckBoxTestCase extends MoqawalatiUITestCase
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
		return MoqawalatiCheckBox;
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
	
} // class
} // package
