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

import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiStartupCommand;

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
		log.debug("MoqawalatiMainFacade");
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
	public function startup(app:MoqawalatiFlex):void
	{
		sendNotification(MoqawalatiConstants.STARTUP, app);
	}
	
	/**
	 * registerCommands Method TO_ASDoc_DO:
	 */
	private function registerCommands():void
	{
		log.debug("registerCommands");
		
		registerCommand(MoqawalatiConstants.STARTUP,
			MoqawalatiStartupCommand);
	}
	
	/**
	 * unregisterCommands Method TO_ASDoc_DO:
	 */
	private function unregisterCommands():void
	{
		removeCommand(MoqawalatiConstants.STARTUP);
	}
	
} // class
} // package