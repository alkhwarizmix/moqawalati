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

package dz.alkhwarizmix.moqawalati.flex.facade
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
 *  @since  ٣٠ شعبان ١٤٣٥ (June 28, 2014)
 */
public class MoqawalatiFacadeTestCase extends MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	[Before]
	override public function setUp():void
	{
		super.setUp();
	}
	
	[After]
	override public function tearDown():void
	{
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiFacadeEnhancedForTest;
	}
	
	override protected function get classUnderTestConstructorArg1():*
	{
		return "key"; // key
	}
	
	private function get utMoqawalatiFacade():MoqawalatiFacade
	{
		return classInstanceUnderTest as MoqawalatiFacade;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utMoqawalatiFacade);
	}
	
	[Ignore("TODO: TDD")]
	[Test]
	public function test01():void
	{
		assertTrue(false);
	}
	
} // class
} // package

//--------------------------------------------------------------------------

import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiFacade;

internal class MoqawalatiFacadeEnhancedForTest extends MoqawalatiFacade
{
	
	public function MoqawalatiFacadeEnhancedForTest(key:String)
	{
		super(key);
	}
	
	override protected function initCommandsToRegister():void
	{
		// NO throw new AlKhwarizmixMissingImplError();
	}
	
}

//--------------------------------------------------------------------------
