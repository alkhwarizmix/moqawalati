////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.modules.productModule
{

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiTestCase;

import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ صفر ١٤٣٦ (December 17, 2014)
 */
public class ProductModuleConstantsTestCase extends MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	override protected function setUp():void
	{
		// NOOP
	}
	
	override protected function tearDown():void
	{
		// NOOP
	}
	
	override protected function get shouldNotHaveLogger():Boolean
	{
		return true;
	}
	
	override protected function get classUnderTest():Class
	{
		return ProductModuleConstants;
	}
	
	private function get utProductModuleConstants():ProductModuleConstants
	{
		return classInstanceUnderTest as ProductModuleConstants;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utProductModuleConstants);
	}
	
	[Ignore("TODO: TDD")]
	[Test]
	public function test01():void
	{
		assertTrue(false);
	}
	
} // class
} // package
