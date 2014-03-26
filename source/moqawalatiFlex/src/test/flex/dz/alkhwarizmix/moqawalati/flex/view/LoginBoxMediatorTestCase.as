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
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiLoginUserProxy;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiPureMVCTestCase;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiSimpleCommandMock;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBox;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBoxEvent;

import org.flexunit.asserts.assertEquals;
import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٣ جمادى الأول ١٤٣٥ (March 13, 2014)
 */
public class LoginBoxMediatorTestCase extends MoqawalatiPureMVCTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	private var loginBox:LoginBox;
	
	[Before]
	override public function setUp():void
	{
		loginBox = new LoginBox();
		
		super.setUp();
		
		moqawalatiMainFacade.registerProxy(new MoqawalatiLoginUserProxy());
		
		testFacade.registerMediator(utLoginBoxMediator);
	}
	
	[After]
	override public function tearDown():void
	{
		utLoginBoxMediator.setViewComponent(null);
		testFacade.removeMediator(utLoginBoxMediator.getMediatorName());
		
		moqawalatiMainFacade.removeProxy(MoqawalatiLoginUserProxy.NAME);
		
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
	
	private function get appLoginUserProxy():MoqawalatiLoginUserProxy
	{
		return moqawalatiMainFacade.retrieveProxy(MoqawalatiLoginUserProxy.NAME)
			as MoqawalatiLoginUserProxy;
	}
	
	private function newUserVO(userId:String):UserVO
	{
		var result:UserVO = new UserVO();
		result.userId = userId;
		return result;
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
		testFacade.registerCommand(MoqawalatiConstants.LOGIN,
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
		testFacade.registerCommand(MoqawalatiConstants.LOGOUT,
			MoqawalatiSimpleCommandMock);
		var user:UserVO = newUserVO("id2");
		loginBox.loggedUser = user;
		new EventUtil().sendEvent(loginBox, LoginBoxEvent.LOGOUT, LoginBoxEvent);
		assertTrue(MoqawalatiSimpleCommandMock.wasExecuteCalled);
		assertNotNull(MoqawalatiSimpleCommandMock.notifBody);
		assertNotNull(MoqawalatiSimpleCommandMock.notifBody.operationParams);
		assertEquals(user, MoqawalatiSimpleCommandMock.notifBody.operationParams[0]);
	}
	
	[Test]
	public function test04_should_listen_to_LOGINUSER_PROXY_CHANGED():void
	{
		assertTrue(utLoginBoxMediator.listNotificationInterests().indexOf(
			MoqawalatiConstants.LOGINUSER_PROXY_CHANGED) > -1);
	}
	
	[Ignore("REDO TDD")]
	[Test]
	public function test05_handleLoginUserProxyChanged():void
	{
		assertTrue("REDO WITH TDD", false);
	}
	
	[Test]
	public function test06_dispatchEvent_LOGOUT_should_not_set_loginUserProxy_data_to_null():void
	{
		var user:UserVO = newUserVO("id1");
		appLoginUserProxy.setData(user);
		
		new EventUtil().sendEvent(loginBox, LoginBoxEvent.LOGOUT, LoginBoxEvent);
		
		assertEquals(user, appLoginUserProxy.user);
	}
	
	[Test]
	public function test07_set_loginUserProxy_data_to_null_should_set_loginBox_loggedUser():void
	{
		appLoginUserProxy.setData(newUserVO("id2"));
		appLoginUserProxy.setData(null);
		assertNull(loginBox.loggedUser);
	}
	
} // class
} // package