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

package dz.alkhwarizmix.moqawalati.flex.facade
{

import flash.utils.Dictionary;

import mx.messaging.messages.RemotingMessage;

import spark.skins.spark.DataGridSkin;
import spark.skins.spark.ScrollerSkin;

import dz.alkhwarizmix.framework.flex.dtos.DTOsToInclude;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiBlazeDSGetDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiGetCustomizedDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSetCustomizedDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiStartupCommand;
import dz.alkhwarizmix.moqawalati.flex.dtos.DTOsToInclude;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiApplication;

import org.puremvc.as3.multicore.interfaces.IFacade;
import org.puremvc.as3.multicore.patterns.facade.Facade;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MoqawalatiMainFacade extends MoqawalatiFacade
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MoqawalatiMainFacade(key:String)
	{
		logger.debug("MoqawalatiMainFacade");
		super(key);
		
		registerNeededClasses();
	}
	
	/**
	 * TODO: ASDOC Definition of registerNeededClasses
	 */
	private function registerNeededClasses():void
	{
		var remotingMessage:RemotingMessage = new RemotingMessage();
		var dataGridSkin:DataGridSkin = new DataGridSkin();
		var scrollerSkin:ScrollerSkin = new ScrollerSkin();
		
		new dz.alkhwarizmix.framework.flex.dtos.DTOsToInclude().registerNeededClasses();
		new dz.alkhwarizmix.moqawalati.flex.dtos.DTOsToInclude().registerNeededClasses();
	}
	
	//--------------------------------------------------------------------------
	//
	//  Class methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of getInstance
	 */
	public static function getInstance(key:String):MoqawalatiMainFacade 
	{
		if (instanceMap[key] == null)
			instanceMap[key] = new MoqawalatiMainFacade(key);
		
		return instanceMap[key] as MoqawalatiMainFacade;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  commandsToRegister
	//----------------------------------
	
	private var _commandsToRegister:Dictionary = null;
	private final function get commandsToRegister():Dictionary
	{
		if (!_commandsToRegister)
		{
			_commandsToRegister = new Dictionary(true);
			
			addCommandToRegister(MoqawalatiConstants.STARTUP,
				MoqawalatiStartupCommand);
			addCommandToRegister(MoqawalatiConstants.GET_CUSTOMDATA,
				MoqawalatiGetCustomizedDataCommand);
			addCommandToRegister(MoqawalatiConstants.SET_CUSTOMDATA,
				MoqawalatiSetCustomizedDataCommand);
			addCommandToRegister(MoqawalatiConstants.LOGIN,
				MoqawalatiBlazeDSGetDataCommand);
			addCommandToRegister(MoqawalatiConstants.LOGOUT,
				MoqawalatiBlazeDSGetDataCommand);
		}
		
		return _commandsToRegister;
	}
	
	protected final function addCommandToRegister(
		commandName:String, commandClass:Class):void
	{
		_commandsToRegister[commandName] = commandClass;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * Register Commands with the Controller 
	 */
	override protected function initializeController():void 
	{
		super.initializeController();
		
		unregisterCommands();
		registerCommands();
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * Application startup
	 * 
	 * @param app a reference to the application component 
	 */  
	public function startup(app:IMoqawalatiApplication):void
	{
		sendNotification(MoqawalatiConstants.STARTUP, app);
	}
	
	/**
	 * registerCommands Method TO_ASDoc_DO:
	 */
	private function registerCommands():void
	{
		logger.debug("registerCommands");
		
		for (var key:String in commandsToRegister)
		{
			registerCommand(key, commandsToRegister[key]);
		}
	}
	
	/**
	 * unregisterCommands Method TO_ASDoc_DO:
	 */
	private function unregisterCommands():void
	{
		for (var key:String in commandsToRegister)
		{
			removeCommand(key);
		}
	}
	
} // class
} // package