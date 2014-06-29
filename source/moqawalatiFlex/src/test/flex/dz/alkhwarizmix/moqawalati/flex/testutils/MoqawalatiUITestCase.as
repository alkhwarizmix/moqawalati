////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.testutils
{

import flash.display.DisplayObject;

import mx.core.UIComponent;
import mx.events.FlexEvent;

import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixMissingImplError;

import org.flexunit.asserts.assertFalse;
import org.flexunit.asserts.assertTrue;
import org.flexunit.async.Async;
import org.fluint.uiImpersonation.UIImpersonator;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٨ ذو الحجة ١٤٣٤ (October 23, 2013)
 */
public class MoqawalatiUITestCase extends MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	private function get displayObjectUnderTest():DisplayObject
	{
		return classInstanceUnderTest as DisplayObject;
	}
	
	[Before(async, ui)]
	override public final function setUp():void
	{
		super.setUp();
		
		if (displayObjectUnderTest)
		{
			Async.proceedOnEvent(this, displayObjectUnderTest,
				FlexEvent.CREATION_COMPLETE, THREE_SECONDS);
			addDisplayObjectToUI(displayObjectUnderTest);
		}
		else
		{
			assertTrue("Could not instanciate displayObjectUnderTest", false);
		}
		
		doAfterAsyncUISetUp();
	}
	
	protected function doAfterAsyncUISetUp():void
	{
		throw new AlKhwarizmixMissingImplError();
	}
	
	[After(ui)]
	override public final function tearDown():void
	{
		doBeforeUITearDown();
		
		UIImpersonator.removeChild(displayObjectUnderTest);
		
		super.tearDown();
	}
	
	protected function doBeforeUITearDown():void
	{
		throw new AlKhwarizmixMissingImplError();
	}
	
	//--------------------------------------------------------------------------
	//
	//  HELPERS
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	protected final function addDisplayObjectToUI(displayObject:DisplayObject):void
	{
		UIImpersonator.addChild(displayObject);
	}
	
	/**
	 * @private
	 */
	protected final function forceRendering(renderer:UIComponent):void
	{
		renderer.validateNow();
	}
	
	/**
	 * @private
	 */
	protected final function assertVisible(component:UIComponent, componentName:String):void
	{
		assertTrue(componentName + " should be visible", component.visible);
		assertTrue(componentName + " should be include in layout", component.includeInLayout);
	}
	
	/**
	 * @private
	 */
	protected final function assertHidden(component:UIComponent, componentName:String):void
	{
		assertFalse(componentName + " should not be visible", component.visible);
		assertFalse(componentName + " should not be include in layout", component.includeInLayout);
	}
	
} // class
} // package