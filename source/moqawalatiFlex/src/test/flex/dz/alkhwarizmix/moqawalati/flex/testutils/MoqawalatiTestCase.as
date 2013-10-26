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

import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixMissingImplError;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٨ ذو الحجة ١٤٣٤ (October 23, 2013)
 */
public class MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  Constants
	//
	//--------------------------------------------------------------------------
	
	protected static const THREE_SECONDS:Number = 3000;
	
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	protected var classInstance:* = null;
	
	[Before]
	public function setUp():void
	{
		classInstance = new classUnderTest();
	}
	
	protected function get classUnderTest():Class
	{
		throw new AlKhwarizmixMissingImplError();
	}
	
	[After]
	public function tearDown():void
	{
		classInstance = null;
	}
	
	//--------------------------------------------------------------------------
	//
	//  HELPERS
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	/* Example
	protected final function forceRendering(renderer:UIComponent):void
	{
		renderer.validateNow();
	}
	*/
	
} // class
} // package