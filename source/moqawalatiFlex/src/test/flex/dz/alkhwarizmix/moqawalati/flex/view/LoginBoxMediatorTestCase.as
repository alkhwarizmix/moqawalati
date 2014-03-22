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

package dz.alkhwarizmix.moqawalati.flex.view
{

import dz.alkhwarizmix.framework.flex.utils.EventUtil;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.dtos.modules.userModule.model.vo.UserVO;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiSimpleCommandMock;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiTestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBox;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBoxEvent;

import org.flexunit.asserts.assertEquals;
import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;
import org.puremvc.as3.multicore.interfaces.IFacade;
import org.puremvc.as3.multicore.patterns.facade.Facade;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٣ جمادى الأول ١٤٣٥ (March 13, 2014)
 */
public class LoginBoxMediatorTestCase extends MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	private var facade:IFacade;
	private var loginBox:LoginBox;
	
	[Before]
	override public function setUp():void
	{
		loginBox = new LoginBox();
		
		super.setUp();
		
		facade = Facade.getInstance("TEST_FACADE");
		facade.registerMediator(utLoginBoxMediator);
		
		MoqawalatiSimpleCommandMock.init();
	}
	
	[After]
	override public function tearDown():void
	{
		utLoginBoxMediator.setViewComponent(null);
		facade.removeMediator(utLoginBoxMediator.getMediatorName());
		Facade.removeCore("TEST_FACADE");
		
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return LoginBoxMediator;
	}
	
	override protected function get classUnderTestConstructorArg1():*
	{
		return loginBox;
	}
	
	private function get utLoginBoxMediator():LoginBoxMediator
	{
		return classInstanceUnderTest as LoginBoxMediator;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test01_constructor():void
	{
		assertNotNull(utLoginBoxMediator);
	}
	
	[Test]
	public function test02_dispatchEvent_LOGIN_should_sendNotification_LOGIN():void
	{
		facade.registerCommand(MoqawalatiConstants.LOGIN,
			MoqawalatiSimpleCommandMock);
		new EventUtil().sendEvent(loginBox, LoginBoxEvent.LOGIN, LoginBoxEvent);
		assertTrue(MoqawalatiSimpleCommandMock.wasExecuteCalled);
		assertNotNull(MoqawalatiSimpleCommandMock.notifBody);
		assertNotNull(MoqawalatiSimpleCommandMock.notifBody.operationParams);
		assertEquals(1, MoqawalatiSimpleCommandMock.notifBody.operationParams.length);
		assertTrue(MoqawalatiSimpleCommandMock.notifBody.operationParams[0] is UserVO);
	}
	
	[Test]
	public function test03_dispatchEvent_LOGOUT_should_sendNotification_LOGOUT():void
	{
		facade.registerCommand(MoqawalatiConstants.LOGOUT,
			MoqawalatiSimpleCommandMock);
		new EventUtil().sendEvent(loginBox, LoginBoxEvent.LOGOUT, LoginBoxEvent);
		assertTrue(MoqawalatiSimpleCommandMock.wasExecuteCalled);
		assertNotNull(MoqawalatiSimpleCommandMock.notifBody);
		assertNotNull(MoqawalatiSimpleCommandMock.notifBody.operationParams);
	}
	
} // class
} // package