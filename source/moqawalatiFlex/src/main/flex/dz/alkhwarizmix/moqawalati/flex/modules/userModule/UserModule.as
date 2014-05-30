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

package dz.alkhwarizmix.moqawalati.flex.modules.userModule
{

import dz.alkhwarizmix.moqawalati.flex.modules.MoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.facade.UserModuleFacade;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.view.components.UserListCanvas;
import dz.alkhwarizmix.moqawalati.flex.view.components.MoqawalatiDataListCanvas;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
public class UserModule extends MoqawalatiModule
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function UserModule()
	{
		super();
		
		percentWidth = 100;
		percentHeight = 100;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  dataListCanvas
	//----------------------------------
	
	private var _dataListCanvas:UserListCanvas = null;
	
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
		return UserModuleConstants.FACADE_NAME;
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
		UserModuleFacade.getInstance(facadeName).startup(this);
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
			_dataListCanvas = new UserListCanvas();
			_dataListCanvas.percentWidth = 100;
			_dataListCanvas.percentHeight = 100;
			addElement(_dataListCanvas);
		}
	}
	
} // class
} // package