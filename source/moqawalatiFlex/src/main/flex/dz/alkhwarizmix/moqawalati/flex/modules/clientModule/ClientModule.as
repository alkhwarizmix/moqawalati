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

package dz.alkhwarizmix.moqawalati.flex.modules.clientModule
{

import dz.alkhwarizmix.moqawalati.flex.modules.MoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.facade.ClientModuleFacade;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.view.components.ClientListCanvas;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class ClientModule extends MoqawalatiModule
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function ClientModule()
	{
		super();
		
		percentWidth = 100;
		percentHeight = 100;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Variables
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of dataListCanvas
	 */
	public var dataListCanvas:ClientListCanvas = null;
	
	//--------------------------------------------------------------------------
	//
	//  Overriden properties
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function set initialized(value:Boolean):void
	{
		super.initialized = value;
		
		if (initialized)
			facadeStartup();
	}
	
	/**
	 *  @inheritDoc
	 */
	override public function get facadeName():String
	{
		return ClientModuleConstants.FACADE_NAME;
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
		ClientModuleFacade.getInstance(facadeName).startup(this);
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
		if (dataListCanvas == null)
		{
			dataListCanvas = new ClientListCanvas();
			dataListCanvas.percentWidth = 100;
			dataListCanvas.percentHeight = 100;
			addElement(dataListCanvas);
		}
	}
	
} // class
} // package