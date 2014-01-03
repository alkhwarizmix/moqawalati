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

package dz.alkhwarizmix.moqawalati.flex.view.components
{

import flash.events.ContextMenuEvent;
import flash.ui.ContextMenu;
import flash.ui.ContextMenuItem;

import mx.controls.Alert;

import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.framework.flex.view.components.AlKhwarizmixDataListCanvas;

import flexlib.mdi.containers.MDIWindow;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MoqawalatiDataListCanvas extends AlKhwarizmixDataListCanvas
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MoqawalatiDataListCanvas()
	{
		super();
	}
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
		getLogger(MoqawalatiDataListCanvas);
	
	override protected function get logger():IAlKhwarizmixLogger { return LOG; }
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @inheritDoc
	 */
	override protected function createChildren():void
	{
		super.createChildren();
		
		addContextMenu();
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
	 * @private
	 */
	private function addContextMenu():void
	{
		var defaultContextMenu:ContextMenu = new ContextMenu();
		defaultContextMenu.hideBuiltInItems();
		
		var arrangeItem:ContextMenuItem = new ContextMenuItem("CONTEXT_MENU_LABEL_CAPTION1");
		arrangeItem.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, menuItemSelectHandler);
		defaultContextMenu.customItems.push(arrangeItem);
		
		var arrangeFillItem:ContextMenuItem = new ContextMenuItem("CONTEXT_MENU_LABEL_CAPTION2");
		arrangeFillItem.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, menuItemSelectHandler);
		defaultContextMenu.customItems.push(arrangeFillItem);
		
		this.contextMenu = defaultContextMenu;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Event handlers
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	private function menuItemSelectHandler(event:ContextMenuEvent):void
	{
		var win:MDIWindow = event.contextMenuOwner as MDIWindow;
		switch (event.target.caption)
		{
			case ("CONTEXT_MENU_LABEL_CAPTION1"):
			{
				Alert.show("CONTEXT_MENU_LABEL_CAPTION1");
				break;
			}
				
			case ("CONTEXT_MENU_LABEL_CAPTION2"):
			{
				Alert.show("CONTEXT_MENU_LABEL_CAPTION2");
				break;
			}
		}
	}
	
} // Class
} // Package