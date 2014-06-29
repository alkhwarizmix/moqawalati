////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.view.containers
{

import flash.display.DisplayObject;

import mx.events.MenuEvent;
import mx.events.PropertyChangeEvent;

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiUITestCase;
import dz.alkhwarizmix.moqawalati.flex.view.components.login.LoginBox;

import org.flexunit.asserts.assertNotNull;
import org.flexunit.asserts.assertTrue;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٩ محرم ١٤٣٥ (December 03, 2013)
 */
public class MainControlBarTestCase extends MoqawalatiUITestCase
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
		return MainControlBar;
	}
	
	private function get utMainControlBar():MainControlBar
	{
		return classInstanceUnderTest as MainControlBar;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test01_constructor():void
	{
		assertNotNull(utMainControlBar);
	}
	
	[Test]
	public function test02_should_contain_LoginBox():void
	{
		for (var i:int = 0; i < utMainControlBar.numChildren; i++)
		{
			var child:DisplayObject = utMainControlBar.getChildAt(i);
			if (child is LoginBox)
				return;
		}
		assertTrue(false);
	}
	
	[Test]
	public function test03_menuBar_itemClick_should_send_OPEN_WINDOW():void
	{
		assert_should_dispatchedEvent(utMainControlBar,
			MainControlBarEvent.OPEN_WINDOW,
			PropertyChangeEvent,
			function ():void
			{
				var item:XML = <menuitem label="testLabel" value="testValue"/>;
				utMainControlBar.menuBar.dispatchEvent(
					new MenuEvent(MenuEvent.ITEM_CLICK, false, true, null, null, item));
			});
	}
	
} // class
} // package