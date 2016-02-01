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

package dz.alkhwarizmix.moqawalati.flex.modules.productModule.view
{

import flash.events.Event;

import dz.alkhwarizmix.framework.flex.AlKhwarizmixConstants;
import dz.alkhwarizmix.framework.flex.dtos.record.model.vo.RecordListVO;
import dz.alkhwarizmix.framework.flex.dtos.record.model.vo.RecordVO;
import dz.alkhwarizmix.framework.flex.dtos.security.model.vo.EncryptionVO;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiMediator;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.ProductModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.model.ProductProxy;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.model.vo.ProductVO;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.view.components.ProductListCanvas;
import dz.alkhwarizmix.moqawalati.flex.view.MoqawalatiDataListMediator;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٦ صفر ١٤٣٦ (December 18, 2014)
 */
public class ProductListMediator extends MoqawalatiDataListMediator
	implements IMoqawalatiMediator
{
	//--------------------------------------------------------------------------
	//
	//  Constants
	//
	//--------------------------------------------------------------------------
	
	/**
	 * The mediator name
	 */
	public static const NAME:String = "ProductListMediator";
	
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function ProductListMediator(viewComponent:Object = null)
	{
		super(NAME, viewComponent);
		
		addEventListeners();
	}
	
	private function addEventListeners():void
	{
		productListCanvas.addEventListener("ProductListCanvas_REFRESH",
			productListCanvas_refreshHandler);
		productListCanvas.addEventListener("ProductListCanvas_COMMIT",
			productListCanvas_commitHandler);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
		getLogger(ProductListMediator);
	
	override protected function get logger():IAlKhwarizmixLogger { return LOG; }
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  mdiCanvas
	//----------------------------------
	
	public final function get productListCanvas():ProductListCanvas
	{
		return viewComponent as ProductListCanvas;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden properties
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function get proxyName():String
	{
		return ProductProxy.NAME;
	}
	
	/**
	 *  @inheritDoc
	 */
	override public function get proxyChangedNoteName():String
	{
		return ProductModuleConstants.PRODUCT_PROXY_CHANGED;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	private function productListCanvas_refreshHandler(event:Event):void
	{
		logger.debug("productListCanvas_refreshHandler");
		
		sendNotification(AlKhwarizmixConstants.GET_RECORD_LIST,
			{
				operationParams : ["Moqawalati", "Product", 0, 50]
			});
	}
	
	/**
	 * @private
	 */
	private function productListCanvas_commitHandler(event:Event):void
	{
		logger.debug("productListCanvas_commitHandler");
		
		sendNotification(AlKhwarizmixConstants.COMMIT_RECORD_LIST,
			{
				operationParams : [getTestingRecordList()]
			});
	}
	
	/**
	 * @private
	 */
	private function getTestingRecordList():RecordListVO
	{
		var result:RecordListVO = new RecordListVO();
		
		var newProductVO:ProductVO = new ProductVO();
		var now:Date = new Date();
		newProductVO.productId = "productId_for_Product_" + now;
		newProductVO.name = "name_for_Product_" + now;
		var recordVO:RecordVO = new RecordVO();
		recordVO.schemaName = "Moqawalati";
		recordVO.tableName = "Product";
		recordVO.action = RecordVO.INSERT_ACTION;
		recordVO.recordId = newProductVO.productId;
		recordVO.data = newProductVO.toXMLString();
		recordVO.encryption = new EncryptionVO("3");
		
		result.list.addItem(recordVO);
		return result;
	}
	
} // class
} // package
