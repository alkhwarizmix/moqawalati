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

package dz.alkhwarizmix.moqawalati.flex.model
{

import flash.utils.Dictionary;

import dz.alkhwarizmix.moqawalati.flex.testutils.MoqawalatiTestCase;

import org.flexunit.asserts.assertEquals;
import org.flexunit.asserts.assertNotNull;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٨ ذو الحجة ١٤٣٤ (October 23, 2013)
 */
public class MoqawalatiConfigProxyTestCase extends MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	override public function setUp():void
	{
		super.setUp();
		
		var configDico:Dictionary = new Dictionary();
		configDico["appParameters"] = getAppParameters();
		moqawalatiConfigProxy.setData(configDico);
	}
	
	override public function tearDown():void
	{
		super.tearDown();
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiConfigProxy;
	}
	
	private function get moqawalatiConfigProxy():MoqawalatiConfigProxy
	{
		return classInstance as MoqawalatiConfigProxy;
	}
	
	//--------------------------------------------------------------------------
	//
	//  HELPERS
	//
	//--------------------------------------------------------------------------
	
	private function getAppParameters():Object
	{
		return {
			appURL : "http://localhost:8080/bin-debug/MoqawalatiFlex.swf"
		}
	}

	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(moqawalatiConfigProxy);
	}
	
	[Test]
	public function test01_swfURL():void
	{
		assertNotNull(moqawalatiConfigProxy.appURL);
		assertEquals(50, moqawalatiConfigProxy.appURL.length);
	}
	
	[Test]
	public function test02_swfURLPath():void
	{
		assertNotNull(moqawalatiConfigProxy.appURLPath);
		assertEquals(50 - 18, moqawalatiConfigProxy.appURLPath.length);
	}
	
} // class
} // package