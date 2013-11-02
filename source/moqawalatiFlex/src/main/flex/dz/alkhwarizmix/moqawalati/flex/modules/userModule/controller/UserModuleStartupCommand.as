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

package dz.alkhwarizmix.moqawalati.flex.modules.userModule.controller
{

import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSimpleCommand;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.UserModule;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.UserModuleConstants;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.model.UserProxy;
import dz.alkhwarizmix.moqawalati.flex.modules.userModule.view.UserListMediator;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
public class UserModuleStartupCommand extends MoqawalatiSimpleCommand
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
		var app:UserModule = notif.getBody() as UserModule;
		
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
	private function registerMediators(app:UserModule):void
	{
		facade.registerMediator(new UserListMediator(app.dataListCanvas));
	}
	
	/**
	 * TODO: ASDOC Definition of registerProxies
	 */
	private function registerProxies(app:UserModule):void
	{
		// ApplicationSpringContextProxy
		// facade.registerProxy(new ApplicationSpringContextProxy(
		//		app.applicationSpringContext));
		
		facade.registerProxy(new UserProxy());
	}
	
	/**
	 * TODO: ASDOC Definition of sendNotifications
	 */
	private function sendNotifications():void
	{
		sendNotification(UserModuleConstants.STARTUP_COMPLETE);
		sendNotification(UserModuleConstants.USER_GET_LIST);
	}
	
} // class
} // package