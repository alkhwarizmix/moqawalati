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

package dz.alkhwarizmix.moqawalati.flex.view
{

import flash.events.Event;
import flash.system.System;

import mx.events.ModuleEvent;
import mx.utils.StringUtil;

import spark.modules.ModuleLoader;

import dz.alkhwarizmix.framework.flex.dtos.customize.model.vo.CustomDataVO;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiMediator;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiMDICanvas;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiMDIWindow;
import dz.alkhwarizmix.moqawalati.flex.view.containers.TestAndDebugWindow;

import flexlib.mdi.events.MDIWindowEvent;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MDICanvasMediator extends MoqawalatiMediator
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
	public static const NAME:String = "MDICanvasMediator";
	
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MDICanvasMediator(viewComponent:Object = null)
	{
		super(NAME, viewComponent);
		
		mdiCanvas.addEventListener("TestAndDebugWindow_getCustomData",
			mdiCanvas_getCustomDataHandler);
		mdiCanvas.addEventListener("TestAndDebugWindow_setCustomData",
			mdiCanvas_setCustomDataHandler);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
		getLogger(MDICanvasMediator);
	
	override protected function get logger():IAlKhwarizmixLogger { return LOG; }
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  mdiCanvas
	//----------------------------------
	
	public final function get mdiCanvas():MoqawalatiMDICanvas
	{
		return viewComponent as MoqawalatiMDICanvas;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function listNotificationInterests():Array
	{
		return [
			 MoqawalatiConstants.OPEN_WINDOW
			,MoqawalatiConstants.CUSTOMDATA_PROXY_CHANGED
		];
	}
	
	/**
	 *  @inheritDoc
	 */
	override protected function handleNotification_try(notif:INotification):void
	{
		super.handleNotification_try(notif);
		
		switch (notif.getName())
		{
			case MoqawalatiConstants.CUSTOMDATA_PROXY_CHANGED:
			{
				handleCustomDataProxyChanged(notif.getBody());
				break;
			}
			
			case MoqawalatiConstants.OPEN_WINDOW:
			{
				handleOpenWindow(notif.getBody());
				break;
			}
			
		} // switch
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of handleCustomDataProxyChanged
	 */
	public function handleCustomDataProxyChanged(notifBody:Object):void
	{
		logger.debug("handleCustomDataProxyChanged");
		
		var customDataVO:CustomDataVO = appCustomDataProxy.getData() as CustomDataVO;
		testAndDebugWindow.textArea.text = customDataVO.customDataValue;
	}
	
	/**
	 * TODO: ASDOC Definition of handleOpenWindow
	 */
	public function handleOpenWindow(notifBody:Object):void
	{
		logger.debug("handleOpenWindow");
		
		var win:MoqawalatiMDIWindow = new MoqawalatiMDIWindow();
		win.width = 350;
		win.title = notifBody.moduleName;
		mdiCanvas.windowManager.add(win);
		win.addEventListener(MDIWindowEvent.CLOSE,
			win_closeHandler);
		var moduleLoader:ModuleLoader = new ModuleLoader();
		moduleLoader.percentWidth = 100;
		moduleLoader.percentHeight = 100;
		win.addChild(moduleLoader);
		moduleLoader.addEventListener(ModuleEvent.ERROR, moduleLoader_errorHandler);
		moduleLoader.loadModule(getModuleAbsoluteURL(notifBody));
	}
	
	/**
	 * @private
	 */
	private function getModuleAbsoluteURL(notifBody:Object):String
	{
		return appConfigProxy.appURLPath + getModuleRelativeURL(notifBody);
	}
	
	/**
	 * @private
	 */
	private function getModuleRelativeURL(notifBody:Object):String
	{
		var moduleName:String = notifBody.moduleName;
		var moduleNameLowerCase:String = moduleName.toLowerCase();
		var result:String = appConfigProxy.flashBuilderBuild
			? StringUtil.substitute("dz/alkhwarizmix/moqawalati/flex/modules/{0}Module/{1}Module", moduleNameLowerCase, moduleName)
			: StringUtil.substitute("moqawalatiFlex-1.0.0.1-{0}module", moduleNameLowerCase);
		result += ".swf";
		
		logger.debug("getModuleRelativePath: result={0}", result);
		return result;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Event handlers
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	private function moduleLoader_errorHandler(event:ModuleEvent):void
	{
		logger.error("moduleLoader_errorHandler: error={0}", event.errorText);
	}
	
	/**
	 * @private
	 */
	private function win_closeHandler(event:MDIWindowEvent):void
	{
		logger.debug("win_closeHandler");
		
		var win:MoqawalatiMDIWindow = event.window as MoqawalatiMDIWindow;
		var moduleLoader:ModuleLoader = win.getChildAt(0) as ModuleLoader;
		var module:IMoqawalatiModule = moduleLoader.child as IMoqawalatiModule;
		module.facadeShutdown();
		win.removeChild(moduleLoader);
		mdiCanvas.windowManager.remove(win);
		
		module = null;
		moduleLoader = null;
		win = null;
		
		System.gc();
	}
	
	/**
	 * @private
	 */
	private function mdiCanvas_getCustomDataHandler(event:Event):void
	{
		logger.debug("mdiCanvas_getCustomDataHandler");
		
		testAndDebugWindow = (event.target as TestAndDebugWindow);
		
		var newCustomDataVO:CustomDataVO = new CustomDataVO(
			"dz.alkhwarizmix.moqawalati.flex.view");
		
		sendNotification(MoqawalatiConstants.GET_CUSTOMDATA,
			{
				operationParams : [newCustomDataVO]
			});
	}
	private var testAndDebugWindow:TestAndDebugWindow = null;
	
	/**
	 * @private
	 */
	private function mdiCanvas_setCustomDataHandler(event:Event):void
	{
		logger.debug("mdiCanvas_setCustomDataHandler");
		
		testAndDebugWindow = (event.target as TestAndDebugWindow);
		
		var newCustomDataVO:CustomDataVO = new CustomDataVO(
			"dz.alkhwarizmix.moqawalati.flex.view",
			testAndDebugWindow.textArea.text);
		
		sendNotification(MoqawalatiConstants.SET_CUSTOMDATA,
			{
				operationParams : [newCustomDataVO]
			});
	}
	
} // class
} // package