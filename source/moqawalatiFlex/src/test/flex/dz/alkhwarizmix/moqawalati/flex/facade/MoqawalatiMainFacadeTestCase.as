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

import dz.alkhwarizmix.framework.flex.AlKhwarizmixConstants;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiCommitRecordListCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiConnectCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiGetCustomizedDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiGetRecordListCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiLoginCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiLogoutCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSetCustomizedDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiStartupCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSubscribeCommand;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiPureMVCTestCase;

import org.flexunit.asserts.assertFalse;
import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٩ جمادى الأول ١٤٣٥ (March 19, 2014)
 */
public class MoqawalatiMainFacadeTestCase extends MoqawalatiPureMVCTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	override protected function setUp():void
	{
		super.setUp();
	}
	
	override protected function tearDown():void
	{
		removeFacadeCore(classUnderTestConstructorArg1);
		
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiMainFacade;
	}
	
	override protected function get classUnderTestConstructorArg1():*
	{
		return "MoqawalatiMainFacadeTestCase_FACADE";
	}
	
	private function get utMoqawalatiMainFacade():MoqawalatiMainFacade
	{
		return classInstanceUnderTest as MoqawalatiMainFacade;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utMoqawalatiMainFacade);
	}
	
	[Test]
	public function test01_A_registeredCommands_should_register():void
	{
		assertTrue(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			MoqawalatiConstants.STARTUP) is MoqawalatiStartupCommand);
		assertTrue(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			AlKhwarizmixConstants.GET_CUSTOMDATA) is MoqawalatiGetCustomizedDataCommand);
		assertTrue(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			AlKhwarizmixConstants.SET_CUSTOMDATA) is MoqawalatiSetCustomizedDataCommand);
		assertTrue(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			AlKhwarizmixConstants.LOGIN) is MoqawalatiLoginCommand);
		assertTrue(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			AlKhwarizmixConstants.LOGOUT) is MoqawalatiLogoutCommand);
		assertTrue(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			AlKhwarizmixConstants.CONNECT) is MoqawalatiConnectCommand);
		assertTrue(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			AlKhwarizmixConstants.SUBSCRIBE) is MoqawalatiSubscribeCommand);
	}
	
	[Ignore("TODO")]
	[Test]
	public function test01_B_registeredCommands_should_NOT_register():void
	{
		assertFalse(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			AlKhwarizmixConstants.GET_RECORD_LIST) is MoqawalatiGetRecordListCommand);
		assertFalse(newFacadeCommandClassWithKey(utMoqawalatiMainFacade,
			AlKhwarizmixConstants.COMMIT_RECORD_LIST) is MoqawalatiCommitRecordListCommand);
	}
	
} // class
} // package
