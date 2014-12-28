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

package dz.alkhwarizmix.moqawalati.flex.modules.productModule.model
{

import mx.collections.ArrayCollection;

import dz.alkhwarizmix.framework.flex.dtos.record.model.vo.RecordListVO;
import dz.alkhwarizmix.framework.flex.dtos.record.model.vo.RecordVO;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.framework.flex.model.vo.AlKhwarizmixVO;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiProxy;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiProxy;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.ProductModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.model.vo.ProductVO;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٦ صفر ١٤٣٦ (December 18, 2014)
 */
public class ProductProxy extends MoqawalatiProxy
	implements IMoqawalatiProxy
{
	//--------------------------------------------------------------------------
	//
	//  Constants
	//
	//--------------------------------------------------------------------------
	
	/**
	 * The proxy name
	 */
	public static const NAME:String = "ProductProxy";
	
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 *
	 * @param data TODO: ASDOC
	 */
	public function ProductProxy(data:Object=null)
	{
		super(NAME, data);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static var LOG:IAlKhwarizmixLogger = null;
	
	override protected function get logger():IAlKhwarizmixLogger
	{
		if (!LOG)
			LOG = AlKhwarizmixLog.getLogger(ProductProxy);
		return LOG;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden properties
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function get changedNoteName():String
	{
		return ProductModuleConstants.PRODUCT_PROXY_CHANGED;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @inheritDoc
	 */
	override public function getOneItem():AlKhwarizmixVO
	{
		return new ProductVO();
	}
	
	/**
	 * @inheritDoc
	 */
	override public function setData(data:Object):void
	{
		if (data is RecordListVO)
		{
			var newData:ArrayCollection = new ArrayCollection();
			for each (var recordVO:RecordVO in RecordListVO(data).list)
			{
				newData.addItem(new ProductVO().fromXMLString(recordVO.data));
			}
			data = newData;
		}
		
		super.setData(data);
	}
	
} // Class
} // Package
