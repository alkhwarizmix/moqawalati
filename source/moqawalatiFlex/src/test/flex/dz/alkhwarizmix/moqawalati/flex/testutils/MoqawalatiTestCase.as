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

import mx.core.UIComponent;

import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixMissingImplError;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxy;

import org.flexunit.asserts.assertFalse;
import org.flexunit.asserts.assertTrue;
import org.puremvc.as3.multicore.interfaces.IFacade;
import org.puremvc.as3.multicore.interfaces.IProxy;
import org.puremvc.as3.multicore.patterns.facade.Facade;

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
	
	protected var classInstanceUnderTest:* = null;
	
	[Before]
	public function setUp():void
	{
		classInstanceUnderTest = new classUnderTest();
	}
	
	protected function get classUnderTest():Class
	{
		throw new AlKhwarizmixMissingImplError();
	}
	
	[After]
	public function tearDown():void
	{
		classInstanceUnderTest = null;
	}
	
	//--------------------------------------------------------------------------
	//
	//  HELPERS
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	protected final function get moqawalatiMainFacade():IFacade
	{
		return Facade.getInstance(MoqawalatiConstants.FACADE_NAME);
	}
	
	/**
	 * @private
	 */
	protected final function registerMoqawalatiConfigProxy():IProxy
	{
		return registerProxy(MoqawalatiConfigProxy);
	}
	
	/**
	 * @private
	 */
	protected final function removeMoqawalatiConfigProxy():void
	{
		moqawalatiMainFacade.removeProxy(MoqawalatiConfigProxy.NAME);
	}
	
	//--------------------------------------------------------------------------
	//
	//  METHODS
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	private function registerProxy(proxyClass:Class):IProxy
	{
		var result:IProxy = new proxyClass();
		moqawalatiMainFacade.registerProxy(result);
		return moqawalatiMainFacade.retrieveProxy(result.getProxyName());
	}
	
} // class
} // package