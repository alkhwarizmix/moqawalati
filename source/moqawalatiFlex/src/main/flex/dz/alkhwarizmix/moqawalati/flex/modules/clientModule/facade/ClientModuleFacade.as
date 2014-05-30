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
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModule;
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
		logger.debug("New ClientModuleFacade");
		
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
	 * initCommandsToRegister Method TO_ASDoc_DO:
	 */
	override protected function initCommandsToRegister():void
	{
		addCommandToRegister(ClientModuleConstants.STARTUP,
			ClientModuleStartupCommand);
		addCommandToRegister(ClientModuleConstants.CLIENT_GET_LIST,
			ClientGetListCommand);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * shutdown Method TO_ASDoc_DO:
	 */
	public function shutdown(app:IMoqawalatiModule):void
	{
		sendNotification(ClientModuleConstants.STARTUP, app);
	}
	
	/**
	 * Application startup
	 * 
	 * @param app a reference to the application component 
	 */  
	public function startup(app:IMoqawalatiModule):void
	{
		logger.debug("startup");
		
		sendNotification(ClientModuleConstants.STARTUP, app);
	}
	
} // class
} // package