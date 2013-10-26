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

package dz.alkhwarizmix.moqawalati.flex.modules.clientModule.facade
{

import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiFacade;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.ClientModule;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.ClientModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.controller.ClientGetListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.controller.ClientModuleStartupCommand;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class ClientModuleFacade extends MoqawalatiFacade
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function ClientModuleFacade(key:String)
	{
		log.debug("ClientModuleFacade");
		super(key);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Class methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of getInstance
	 */
	public static function getInstance(key:String):ClientModuleFacade 
	{
		if (instanceMap[key] == null)
			instanceMap[key] = new ClientModuleFacade(key);
		
		return instanceMap[key] as ClientModuleFacade;
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
	 * TODO: ASDOC Definition of propertyWithGetAndSet1
	 */
	public function shutdown(app:ClientModule):void
	{
		sendNotification(ClientModuleConstants.STARTUP, app);
	}
	
	/**
	 * Application startup
	 * 
	 * @param app a reference to the application component 
	 */  
	public function startup(app:ClientModule):void
	{
		log.debug("startup");
		
		sendNotification(ClientModuleConstants.STARTUP, app);
	}
	
	/**
	 * registerCommands Method TO_ASDoc_DO:
	 */
	private function registerCommands():void
	{
		log.debug("registerCommands");
		
		registerCommand(ClientModuleConstants.STARTUP,
			ClientModuleStartupCommand);
		registerCommand(ClientModuleConstants.CLIENT_GET_LIST,
			ClientGetListCommand);
	}
	
	/**
	 * unregisterCommands Method TO_ASDoc_DO:
	 */
	private function unregisterCommands():void
	{
		removeCommand(ClientModuleConstants.STARTUP);
		removeCommand(ClientModuleConstants.CLIENT_GET_LIST);
	}
	
} // class
} // package