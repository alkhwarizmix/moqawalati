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

import mx.messaging.messages.RemotingMessage;

import spark.skins.spark.DataGridSkin;
import spark.skins.spark.ScrollerSkin;

import dz.alkhwarizmix.framework.flex.AlKhwarizmixConstants;
import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixTypeError;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiConnectCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiGetCustomizedDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiLoginCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiLogoutCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSetCustomizedDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiStartupCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSubscribeCommand;
import dz.alkhwarizmix.moqawalati.flex.dtos.MoqawalatiDTOsToInclude;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiApplication;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiFacade;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MoqawalatiMainFacade extends MoqawalatiFacade
	implements IMoqawalatiFacade
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
		logger.debug("New MoqawalatiMainFacade");
		
		super(key);
		registerNeededClasses();
	}
	
	/**
	 * TODO: ASDOC Definition of registerNeededClasses
	 */
	private function registerNeededClasses():void
	{
		moqawalatiDTOsToInclude = new MoqawalatiDTOsToInclude();
		
		// TODO: Use Class registerar 
		moqawalatiDTOsToInclude.registerClass(RemotingMessage);
		moqawalatiDTOsToInclude.registerClass(DataGridSkin);
		moqawalatiDTOsToInclude.registerClass(ScrollerSkin);
	}
	private var moqawalatiDTOsToInclude:MoqawalatiDTOsToInclude = null;
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static var LOG:IAlKhwarizmixLogger = null;
	
	override protected function get logger():IAlKhwarizmixLogger
	{
		if (!LOG)
			LOG = AlKhwarizmixLog.getLogger(MoqawalatiMainFacade);
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
	public static function getInstance(key:String):IMoqawalatiFacade 
	{
		if (instanceMap[key] == null)
			instanceMap[key] = new MoqawalatiMainFacade(key);
		
		return instanceMap[key] as IMoqawalatiFacade;
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
		addCommandToRegister(MoqawalatiConstants.STARTUP,
			MoqawalatiStartupCommand);
		addCommandToRegister(AlKhwarizmixConstants.GET_CUSTOMDATA,
			MoqawalatiGetCustomizedDataCommand);
		addCommandToRegister(AlKhwarizmixConstants.SET_CUSTOMDATA,
			MoqawalatiSetCustomizedDataCommand);
		addCommandToRegister(AlKhwarizmixConstants.CONNECT,
			MoqawalatiConnectCommand);
		addCommandToRegister(AlKhwarizmixConstants.SUBSCRIBE,
			MoqawalatiSubscribeCommand);
		addCommandToRegister(AlKhwarizmixConstants.LOGIN,
			MoqawalatiLoginCommand);
		addCommandToRegister(AlKhwarizmixConstants.LOGOUT,
			MoqawalatiLogoutCommand);
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
	override public function startup(app:*):void
	{
		if (!(app is IMoqawalatiApplication))
			throw new AlKhwarizmixTypeError("IMoqawalatiApplication");
		sendNotification(MoqawalatiConstants.STARTUP, app);
	}
	
} // class
} // package
