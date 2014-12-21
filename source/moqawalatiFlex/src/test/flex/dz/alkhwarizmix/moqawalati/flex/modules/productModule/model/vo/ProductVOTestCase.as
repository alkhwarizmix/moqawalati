////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.modules.productModule.model.vo
{

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
 *  @since  ٢٦ صفر ١٤٣٦ (December 18, 2014)
 */
public class ProductVOTestCase extends MoqawalatiTestCase
{
	//--------------------------------------------------------------------------
	//
	//  SETUP & TEARDOWN
	//
	//--------------------------------------------------------------------------
	
	override protected function setUp():void
	{
		// NOOP
	}
	
	override protected function tearDown():void
	{
		// NOOP
	}
	
	override protected function get classUnderTest():Class
	{
		return ProductVO;
	}
	
	private function get utProductVO():ProductVO
	{
		return classInstanceUnderTest as ProductVO;
	}
	
	//--------------------------------------------------------------------------
	//
	//  TESTS
	//
	//--------------------------------------------------------------------------
	
	[Test]
	public function test00_constructor():void
	{
		assertNotNull(utProductVO);
	}
	
	[Test]
	public function test01_toXML():void
	{
		utProductVO.productId = "productId1";
		utProductVO.name = "name1";
		assertEquals(0, utProductVO.toXMLString().indexOf("<Product"));
		assertTrue(utProductVO.toXMLString().indexOf("name=\"name1\"") > 0);
		assertTrue(utProductVO.toXMLString().indexOf("productId=\"productId1\"") > 0);
	}
	
	[Test]
	public function test01_fromXML():void
	{
		var xml:String = "<Product productId=\"productId1\"/>";
		var result:ProductVO = (new ProductVO().fromXMLString(xml) as ProductVO);
		assertEquals("productId1", result.productId);
	}
	
	[Ignore("TODO: TDD")]
	[Test]
	public function test01():void
	{
		assertTrue(false);
	}
	
} // class
} // package
