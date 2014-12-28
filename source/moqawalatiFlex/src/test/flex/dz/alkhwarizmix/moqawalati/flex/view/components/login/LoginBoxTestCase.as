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

package dz.alkhwarizmix.moqawalati.flex.view.components.login
{

import flash.events.MouseEvent;

import dz.alkhwarizmix.framework.flex.dtos.security.model.vo.UserVO;
import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiUITestCase;

import org.flexunit.asserts.assertEquals;
import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  Test for MoqawalatiDataListCanvas
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١١ جمادى الأول ١٤٣٥ (March 11, 2014)
 */
public class LoginBoxTestCase extends MoqawalatiUITestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	override protected function setUpAsyncUI():void
	{
		// NOOP
	}
	
	override protected function tearDownUI():void
	{
		// NOOP
	}
	
	override protected function get classUnderTest():Class
	{
		return LoginBox;
	}
	
	private function get utLoginBox():LoginBox
	{
		return classInstanceUnderTest as LoginBox;
	}
	
	//--------------------------------------------------------------------------
	//
	//  HELPERS
	//
	//--------------------------------------------------------------------------
	
	private function get userFaresBelhaouas():UserVO
	{
		var result:UserVO = new UserVO();
		result.name = "Fares Belhaouas";
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
		assertNotNull(utLoginBox);
	}
	
	[Test]
	public function test02_default_show_inputText_and_hide_label():void
	{
		assertVisible(utLoginBox.textUserName, "text");
		assertHidden(utLoginBox.labelUserName, "label");
	}
	
	[Test]
	public function test03_click_btnLogInOrOut_should_dispatch_LOGIN_event_if_not_loggedUser():void
	{
		utLoginBox.loggedUser = null;
		assert_should_dispatchedEvent(utLoginBox,
			LoginBoxEvent.LOGIN,
			LoginBoxEvent,
			function ():void
			{
				utLoginBox.btnLogInOrOut.dispatchEvent(
					new MouseEvent(MouseEvent.CLICK));
			});
	}
	
	[Test]
	public function test04_click_btnLogInOrOut_should_dispatch_LOGOUT_event_if_loggedUser():void
	{
		utLoginBox.loggedUser = userFaresBelhaouas;
		assert_should_dispatchedEvent(utLoginBox,
			LoginBoxEvent.LOGOUT,
			LoginBoxEvent,
			function ():void
			{
				utLoginBox.btnLogInOrOut.dispatchEvent(
					new MouseEvent(MouseEvent.CLICK));
			});
	}
	
	[Test]
	public function test05_label_should_display_loggedUser_name_after_rendering():void
	{
		utLoginBox.loggedUser = userFaresBelhaouas;
		assertEquals("label should be empty", "", utLoginBox.labelUserName.text);
		forceRendering(utLoginBox);
		assertEquals("label should have loggedUser name", userFaresBelhaouas.name, utLoginBox.labelUserName.text);
	}
	
	[Test]
	public function test06_label_should_change_visibility_depending_on_loggedUser_after_rendering():void
	{
		utLoginBox.loggedUser = userFaresBelhaouas;
		assertHidden(utLoginBox.labelUserName, "label");
		forceRendering(utLoginBox);
		assertVisible(utLoginBox.labelUserName, "label");
		
		utLoginBox.loggedUser = null;
		assertVisible(utLoginBox.labelUserName, "label");
		forceRendering(utLoginBox);
		assertHidden(utLoginBox.labelUserName, "label");
	}
	
	[Ignore("REDO TDD")]
	[Test]
	public function test07_updateButtonLabel():void
	{
		assertTrue("REDO WITH TDD", false);
	}
	
	[Test]
	public function test08_textUserName_should_empty_when_loggedUser_after_rendering():void
	{
		utLoginBox.textUserName.text = "default";
		utLoginBox.loggedUser = userFaresBelhaouas;
		assertEquals("default", utLoginBox.textUserName.text);
		forceRendering(utLoginBox);
		assertEquals("", utLoginBox.textUserName.text);
	}
	
} // class
} // package
