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

import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiGetCustomizedDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiLoginCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiLogoutCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSetCustomizedDataCommand;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiStartupCommand;
import dz.alkhwarizmix.moqawalati.flex.dtos.MoqawalatiDTOsToInclude;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiApplication;

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
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * initCommandsToRegister Method TO_ASDoc_DO:
	 */
	override protected function initCommandsToRegister():void
	{
		addCommandToRegister(MoqawalatiConstants.STARTUP,
			MoqawalatiStartupCommand);
		addCommandToRegister(MoqawalatiConstants.GET_CUSTOMDATA,
			MoqawalatiGetCustomizedDataCommand);
		addCommandToRegister(MoqawalatiConstants.SET_CUSTOMDATA,
			MoqawalatiSetCustomizedDataCommand);
		addCommandToRegister(MoqawalatiConstants.LOGIN,
			MoqawalatiLoginCommand);
		addCommandToRegister(MoqawalatiConstants.LOGOUT,
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
	public function startup(app:IMoqawalatiApplication):void
	{
		sendNotification(MoqawalatiConstants.STARTUP, app);
	}
	
} // class
} // package
