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
import dz.alkhwarizmix.framework.flex.facade.AlKhwarizmixFacade;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxy;

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
		if (classUnderTestConstructorArg2)
		{
			classInstanceUnderTest = new classUnderTest(
				classUnderTestConstructorArg1,
				classUnderTestConstructorArg2);
		}
		else if (classUnderTestConstructorArg1)
		{
			classInstanceUnderTest = new classUnderTest(
				classUnderTestConstructorArg1);
		}
		else
		{
			classInstanceUnderTest = new classUnderTest();
		}
	}
	
	protected function get classUnderTest():Class
	{
		throw new AlKhwarizmixMissingImplError();
	}
	
	protected function get classUnderTestConstructorArg1():*
	{
		return null;
	}
	
	protected function get classUnderTestConstructorArg2():*
	{
		return null;
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
	
	/**
	 * @private
	 * 
	 * Example of use:
	 *  classUnderTest = new MoqawalatiClass();
	 *  assert_should_throwMissingImplError(
	 *    function ():void
	 *    {
	 *      classUnderTest.function_should_throw_error();
	 *    });
	 */
	protected  final function assert_should_throwMissingImplError(
		functionThrowingException:Function):void
	{
		try
		{
			functionThrowingException();
			assertTrue("Should throw exception before to be here", false);
		}
		catch (error:Error)
		{
			assertTrue(error is AlKhwarizmixMissingImplError);
		}
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