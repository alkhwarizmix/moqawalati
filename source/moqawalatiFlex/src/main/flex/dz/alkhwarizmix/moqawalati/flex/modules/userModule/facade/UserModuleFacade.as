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

package dz.alkhwarizmix.moqawalati.flex.modules.userModule.facade
{

import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiFacade;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.UserModule;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.UserModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.controller.UserGetListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.controller.UserModuleStartupCommand;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
public class UserModuleFacade extends MoqawalatiFacade
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function UserModuleFacade(key:String)
	{
		logger.debug("New UserModuleFacade");
		
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
	public static function getInstance(key:String):UserModuleFacade 
	{
		if (instanceMap[key] == null)
			instanceMap[key] = new UserModuleFacade(key);
		
		return instanceMap[key] as UserModuleFacade;
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
		addCommandToRegister(UserModuleConstants.STARTUP,
			UserModuleStartupCommand);
		addCommandToRegister(UserModuleConstants.USER_GET_LIST,
			UserGetListCommand);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * shutdown Method TO_ASDoc_DO:
	 */
	public function shutdown(app:UserModule):void
	{
		sendNotification(UserModuleConstants.STARTUP, app);
	}
	
	/**
	 * Application startup
	 * 
	 * @param app a reference to the application component 
	 */  
	public function startup(app:UserModule):void
	{
		logger.debug("startup");
		
		sendNotification(UserModuleConstants.STARTUP, app);
	}
	
} // class
} // package