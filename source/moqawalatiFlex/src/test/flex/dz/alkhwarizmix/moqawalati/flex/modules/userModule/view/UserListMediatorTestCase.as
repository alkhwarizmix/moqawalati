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

package dz.alkhwarizmix.moqawalati.flex.modules.userModule.view
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
public class UserListMediatorTestCase extends MoqawalatiTestCase
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
		return UserListMediator;
	}
	
	private function get utUserListMediator():UserListMediator
	{
		return classInstanceUnderTest as UserListMediator;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utUserListMediator);
	}
	
	[Ignore("TODO: TDD")]
	[Test]
	public function test01():void
	{
		assertTrue(false);
	}
	
} // class
} // package
