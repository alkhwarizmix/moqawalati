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

import flash.utils.IDataInput;
import flash.utils.IDataOutput;
import flash.utils.IExternalizable;

import mx.core.IFactory;

import dz.alkhwarizmix.moqawalati.flex.model.vo.MoqawalatiVO;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٦ صفر ١٤٣٦ (December 18, 2014)
 */
[XmlClass(alias="Product")]
public class ProductVO extends MoqawalatiVO
	implements IExternalizable, IFactory
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function ProductVO(xmlString:String = null)
	{
		super();
		// if (xmlString != null)
		// copyFrom(fromXMLString(xmlString));
	}
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  productId
	//----------------------------------
	
	private var _productId:String = null;
	[XmlAttribute]
	public function get productId():String { return _productId; }
	
	public function set productId(value:String):void
	{
		if (_productId == value)
			return;
		_productId = value;
	}
	
	//----------------------------------
	//  name
	//----------------------------------
	
	private var _name:String = null;
	[XmlAttribute]
	public function get name():String { return _name; }
	
	public function set name(value:String):void
	{
		if (_name == value)
			return;
		_name = value;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	public function newInstance():*
	{
		return new ProductVO();
	}
	
	public function readExternal(input:IDataInput):void
	{
		productId = input.readUTF();
		name = input.readUTF();
	}
	
	public function writeExternal(output:IDataOutput):void
	{
		output.writeUTF(productId);
		output.writeUTF(name);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden Methods
	//
	//--------------------------------------------------------------------------
	
	override protected function getClassForXMLDeserializer():Class
	{
		return ProductVO;
	}
	
} // Class
} // Package
