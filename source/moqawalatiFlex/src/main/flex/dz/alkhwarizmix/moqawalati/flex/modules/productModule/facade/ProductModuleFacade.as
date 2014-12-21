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

import dz.alkhwarizmix.moqawalati.flex.facade.MoqawalatiFacade;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.ProductModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductCommitListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductGetListCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller.ProductModuleStartupCommand;

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
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * initCommandsToRegister Method TO_ASDoc_DO:
	 */
	override protected function initCommandsToRegister():void
	{
		addCommandToRegister(ProductModuleConstants.STARTUP,
			ProductModuleStartupCommand);
		addCommandToRegister(ProductModuleConstants.RECORD_GET_LIST,
			ProductGetListCommand);
		addCommandToRegister(ProductModuleConstants.RECORD_COMMIT_LIST,
			ProductCommitListCommand);
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
	public function startup(app:IMoqawalatiModule):void
	{
		logger.debug("startup");
		
		sendNotification(ProductModuleConstants.STARTUP, app);
	}
	
} // class
} // package
