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
		return classInstance as DisplayObject;
	}
	
	[Before(async, ui)]
	override public function setUp():void
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
	}
	
	[After(ui)]
	override public function tearDown():void
	{
		UIImpersonator.removeChild(displayObjectUnderTest);
		
		super.tearDown();
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
	
} // class
} // package