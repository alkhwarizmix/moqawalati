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
import org.flexunit.asserts.assertTrue;

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
	
	override protected function setUp():void
	{
		var configDico:Dictionary = new Dictionary();
		configDico["appParameters"] = getAppParameters();
		moqawalatiConfigProxy.setData(configDico);
	}
	
	override protected function tearDown():void
	{
		moqawalatiConfigProxy.setData(null);
	}
	
	override protected function get classUnderTest():Class
	{
		return MoqawalatiConfigProxy;
	}
	
	private function get moqawalatiConfigProxy():MoqawalatiConfigProxy
	{
		return classInstanceUnderTest as MoqawalatiConfigProxy;
	}
	
	//--------------------------------------------------------------------------
	//
	//  HELPERS
	//
	//--------------------------------------------------------------------------
	
	private function getAppParameters():Object
	{
		return {
			appURL : "http://localhost:8080/moqawalati/MoqawalatiFlex.swf",
			flashBuilderBuild : "true"
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
		assertEquals(51, moqawalatiConfigProxy.appURL.length);
	}
	
	[Test]
	public function test02_swfURLPath():void
	{
		assertNotNull(moqawalatiConfigProxy.appURLPath);
		assertEquals(51 - 18, moqawalatiConfigProxy.appURLPath.length);
	}
	
	[Test]
	public function test03_flashBuilderBuild():void
	{
		assertNotNull(moqawalatiConfigProxy.flashBuilderBuild);
		assertTrue(moqawalatiConfigProxy.flashBuilderBuild);
	}
	
} // class
} // package
