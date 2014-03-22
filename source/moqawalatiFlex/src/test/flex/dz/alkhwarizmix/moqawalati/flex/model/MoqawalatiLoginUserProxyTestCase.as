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

package dz.alkhwarizmix.moqawalati.flex.model
{

import dz.alkhwarizmix.moqawalati.flex.dtos.modules.userModule.model.vo.UserVO;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiTestCase;

import org.flexunit.asserts.assertEquals;
import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  Test for MoqawalatiLoginUserProxy
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢١ جمادى الأول ١٤٣٥ (March 21, 2014)
 */
public class MoqawalatiLoginUserProxyTestCase extends MoqawalatiTestCase
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
		
		moqawalatiMainFacade.registerProxy(moqawalatiLoginUserProxy);
	}
	
	[After]
	override public function tearDown():void
	{
		moqawalatiMainFacade.removeProxy(
			moqawalatiLoginUserProxy.getProxyName());
		
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiLoginUserProxy;
	}
	
	private function get moqawalatiLoginUserProxy():MoqawalatiLoginUserProxy
	{
		return classInstanceUnderTest as MoqawalatiLoginUserProxy;
	}
	
	//--------------------------------------------------------------------------
	//
	//  HELPERS
	//
	//--------------------------------------------------------------------------
	
	// EMPTY
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiLoginUserProxy);
	}
	
	[Test]
	public function test01_changedNoteName():void
	{
		assertEquals("Moqawalati_loginuser_proxy_changed",
			moqawalatiLoginUserProxy.changedNoteName);
	}
	
	[Test]
	public function test02_user_cannot_be_null():void
	{
		moqawalatiLoginUserProxy.setData(null);
		assertTrue(moqawalatiLoginUserProxy.user is UserVO);
	}
	
	[Test]
	public function test03_getOneItem_should_return_UserVO():void
	{
		assertTrue(moqawalatiLoginUserProxy.getOneItem() is UserVO);
	}
	
} // class
} // package