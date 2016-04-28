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

import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixTypeError;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiFacade;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModule;
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
		super(key);
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
			LOG = AlKhwarizmixLog.getLogger(UserModuleFacade);
		return LOG;
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
	//  Overridden methods
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
	public function shutdown(app:IMoqawalatiModule):void
	{
		sendNotification(UserModuleConstants.STARTUP, app);
	}
	
	/**
	 * Application startup
	 * 
	 * @param app a reference to the application component 
	 */  
	override public function startup(app:*):void
	{
		logger.debug("startup");
		if (!app is IMoqawalatiModule)
			throw new AlKhwarizmixTypeError("IMoqawalatiModule");
		sendNotification(UserModuleConstants.STARTUP, app);
	}
	
} // class
} // package
