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

package dz.alkhwarizmix.moqawalati.flex.testutils
{

import dz.alkhwarizmix.framework.flex.facade.AlKhwarizmixFacade;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxy;

import org.puremvc.as3.multicore.interfaces.IFacade;
import org.puremvc.as3.multicore.interfaces.IProxy;
import org.puremvc.as3.multicore.patterns.facade.Facade;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٤ جمادى الأول ١٤٣٥ (March 24, 2014)
 */
public class MoqawalatiPureMVCTestCase extends MoqawalatiTestCase
{
	private static const TEST_FACADE_NAME:String = "TEST_FACADE";
	
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	[Before]
	override public function setUp():void
	{
		super.setUp();
	}
	
	[After]
	override public function tearDown():void
	{
		if (Facade.hasCore(MoqawalatiConstants.FACADE_NAME))
			removeFacadeCore(MoqawalatiConstants.FACADE_NAME);
		if (Facade.hasCore(TEST_FACADE_NAME))
			removeFacadeCore(TEST_FACADE_NAME);
		
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
	protected final function get testFacade():IFacade
	{
		return Facade.getInstance(TEST_FACADE_NAME);
	}
	
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
	
	/**
	 * @private
	 */
	protected final function removeFacadeCore(key:String):void
	{
		Facade.removeCore(key);
	}
	
	/**
	 * @private
	 */
	protected final function newFacadeCommandClassWithKey(
		facade:AlKhwarizmixFacade, key:String):*
	{
		return new (facade.getCommandClassWithKey(key));
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