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

package dz.alkhwarizmix.moqawalati.flex.modules.clientModule.controller
{

import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSimpleCommand;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.ClientModule;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.ClientModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.model.ClientProxy;
import dz.alkhwarizmix.moqawalati.flex.modules.clientModule.view.ClientListMediator;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class ClientModuleStartupCommand extends MoqawalatiSimpleCommand
	implements IMoqawalatiCommand
{
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
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
		var app:ClientModule = notif.getBody() as ClientModule;
		
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
	private function registerMediators(app:ClientModule):void
	{
		facade.registerMediator(new ClientListMediator(app.dataListCanvas));
	}
	
	/**
	 * TODO: ASDOC Definition of registerProxies
	 */
	private function registerProxies(app:ClientModule):void
	{
		// ApplicationSpringContextProxy
		// facade.registerProxy(new ApplicationSpringContextProxy(
		//		app.applicationSpringContext));
		
		facade.registerProxy(new ClientProxy());
	}
	
	/**
	 * TODO: ASDOC Definition of sendNotifications
	 */
	private function sendNotifications():void
	{
		sendNotification(ClientModuleConstants.STARTUP_COMPLETE);
		sendNotification(ClientModuleConstants.CLIENT_GET_LIST,
			{
				operationParams : [0, 50]
			});
	}
	
} // class
} // package