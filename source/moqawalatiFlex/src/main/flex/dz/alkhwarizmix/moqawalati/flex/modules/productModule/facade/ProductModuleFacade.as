////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.modules.productModule.facade
{

import dz.alkhwarizmix.framework.flex.AlKhwarizmixConstants;
import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixTypeError;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiFacade;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.ProductModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductCommitListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductGetListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductModuleStartupCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.RemoteServerErrorCommand;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٦ صفر ١٤٣٦ (December 18, 2014)
 */
public class ProductModuleFacade extends MoqawalatiFacade
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function ProductModuleFacade(key:String)
	{
		logger.debug("New ProductModuleFacade");
		
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
			LOG = AlKhwarizmixLog.getLogger(ProductModuleFacade);
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
	public static function getInstance(key:String):ProductModuleFacade 
	{
		if (instanceMap[key] == null)
			instanceMap[key] = new ProductModuleFacade(key);
		
		return instanceMap[key] as ProductModuleFacade;
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
		addCommandToRegister(ProductModuleConstants.STARTUP,
			ProductModuleStartupCommand);
		addCommandToRegister(AlKhwarizmixConstants.GET_RECORD_LIST,
			ProductGetListCommand);
		addCommandToRegister(AlKhwarizmixConstants.COMMIT_RECORD_LIST,
			ProductCommitListCommand);
		addCommandToRegister(AlKhwarizmixConstants.REMOTE_SERVER_ERROR,
			RemoteServerErrorCommand);
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
		logger.debug("shutdown");
		
		sendNotification(ProductModuleConstants.STARTUP, app);
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
		sendNotification(ProductModuleConstants.STARTUP, app);
	}
	
} // class
} // package
