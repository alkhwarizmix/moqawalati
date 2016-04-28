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

package dz.alkhwarizmix.moqawalati.flex.modules.productModule.controller
{

import dz.alkhwarizmix.framework.flex.AlKhwarizmixConstants;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSimpleCommand;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.ProductModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.model.ProductProxy;
import dz.alkhwarizmix.moqawalati.flex.modules.productModule.view.ProductListMediator;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٦ صفر ١٤٣٦ (December 18, 2014)
 */
public class ProductModuleStartupCommand extends MoqawalatiSimpleCommand
	implements IMoqawalatiCommand
{
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static var LOG:IAlKhwarizmixLogger = null;
	
	override protected function get logger():IAlKhwarizmixLogger
	{
		if (!LOG)
			LOG = AlKhwarizmixLog.getLogger(ProductModuleStartupCommand);
		return LOG;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overridden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * Register the Proxies and Mediators.
	 * 
	 * Get the View Components for the Mediators from the app,
	 * which passed a reference to itself on the notification.
	 */
	override protected function execute_try(notif:INotification):void	
	{
		var app:IMoqawalatiModule = notif.getBody() as IMoqawalatiModule;
		
		// REGISTER PROXIES FIRST
		registerProxies(app);
		
		// THEN MEDIATORS
		registerMediators(app);
		
		// SEND NOTIFICATIONS
		sendNotifications();
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of registerMediators
	 */
	private function registerMediators(app:IMoqawalatiModule):void
	{
		facade.registerMediator(new ProductListMediator(app.dataListCanvas));
	}
	
	/**
	 * TODO: ASDOC Definition of registerProxies
	 */
	private function registerProxies(app:IMoqawalatiModule):void
	{
		// ApplicationSpringContextProxy
		// facade.registerProxy(new ApplicationSpringContextProxy(
		//		app.applicationSpringContext));
		
		facade.registerProxy(new ProductProxy());
	}
	
	/**
	 * TODO: ASDOC Definition of sendNotifications
	 */
	private function sendNotifications():void
	{
		sendNotification(ProductModuleConstants.STARTUP_COMPLETE);
		sendNotification(AlKhwarizmixConstants.GET_RECORD_LIST,
			{
				operationParams : ["Moqawalati", "Product", 0, 50]
			});
	}
	
} // class
} // package
