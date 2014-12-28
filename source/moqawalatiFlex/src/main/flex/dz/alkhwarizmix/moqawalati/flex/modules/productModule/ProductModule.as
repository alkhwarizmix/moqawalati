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

package dz.alkhwarizmix.moqawalati.flex.modules.productModule
{

import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.modules.MoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.facade.ProductModuleFacade;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.view.components.ProductListCanvas;
import dz.alkhwarizmix.moqawalati.flex.view.components.MoqawalatiDataListCanvas;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٦ صفر ١٤٣٦ (December 18, 2014)
 */
public class ProductModule extends MoqawalatiModule
	implements IMoqawalatiModule
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function ProductModule()
	{
		super();
		
		percentWidth = 100;
		percentHeight = 100;
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
			LOG = AlKhwarizmixLog.getLogger(ProductModule);
		return LOG;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  dataListCanvas
	//----------------------------------
	
	private var _dataListCanvas:ProductListCanvas = null;
	
	/**
	 *  @inheritDoc
	 */
	override public function get dataListCanvas():MoqawalatiDataListCanvas
	{
		return _dataListCanvas;
	}
	
	//----------------------------------
	//  initialized
	//----------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function set initialized(value:Boolean):void
	{
		super.initialized = value;
		
		if (initialized)
			facadeStartup();
	}
	
	//----------------------------------
	//  facadeName
	//----------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function get facadeName():String
	{
		return ProductModuleConstants.FACADE_NAME;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @inheritDoc
	 */
	override protected function facadeStartup():void
	{
		ProductModuleFacade.getInstance(facadeName).startup(this);
	}
	
	/**
	 * @inheritDoc
	 */
	override protected function createChildren():void
	{
		super.createChildren();
		
		createListCanvas();
	}
	
	/**
	 * @inheritDoc
	 */
	override protected function updateDisplayList(
		unscaledWidth:Number, unscaledHeight:Number):void
	{
		super.updateDisplayList(unscaledWidth, unscaledHeight);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of createDataGrid
	 */
	private function createListCanvas():void
	{
		if (_dataListCanvas == null)
		{
			_dataListCanvas = new ProductListCanvas();
			_dataListCanvas.percentWidth = 100;
			_dataListCanvas.percentHeight = 100;
			addElement(_dataListCanvas);
		}
	}
	
} // class
} // package
