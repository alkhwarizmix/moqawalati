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

package dz.alkhwarizmix.moqawalati.flex.controller
{

import flash.net.registerClassAlias;
import flash.utils.Dictionary;

import mx.messaging.messages.RemotingMessage;

import spark.skins.spark.DataGridSkin;
import spark.skins.spark.ScrollerSkin;

import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.dtos.modules.clientModule.model.vo.ClientVO;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiApplication;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxy;
import dz.alkhwarizmix.moqawalati.flex.view.MDICanvasMediator;
import dz.alkhwarizmix.moqawalati.flex.view.MainControlBarMediator;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MainCanvas;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MoqawalatiStartupCommand extends MoqawalatiSimpleCommand
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
		var app:IMoqawalatiApplication = notif.getBody() as IMoqawalatiApplication;
		
		// REGISTER NEEDED CLASSES
		registerNeededClasses();
		
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
	private function registerMediators(app:IMoqawalatiApplication):void
	{
		var mainCanvas:MainCanvas = app.mainCanvas as MainCanvas;
		facade.registerMediator(new MainControlBarMediator(
			mainCanvas.mainControlBar));
		facade.registerMediator(new MDICanvasMediator(
			mainCanvas.mdiCanvas));
	}
	
	/**
	 * TODO: ASDOC Definition of registerNeededClasses
	 */
	private function registerNeededClasses():void
	{
		registerClassAlias("mx.messaging.messages.RemotingMessage",
			RemotingMessage);
		registerClassAlias("spark.skins.spark.DataGridSkin",
			DataGridSkin);
		registerClassAlias("spark.skins.spark.ScrollerSkin",
			ScrollerSkin);
		registerClassAlias("dz.alkhwarizmix.moqawalati.flex.dtos.modules.clientModule.model.vo.ClientVO",
			ClientVO);
	}
	
	/**
	 * TODO: ASDOC Definition of registerProxies
	 */
	private function registerProxies(app:IMoqawalatiApplication):void
	{
		facade.registerProxy(new MoqawalatiConfigProxy(
			getConfigDico(app.parameters)));
	}
	
	/**
	 * @private
	 */
	private function getConfigDico(appParameters:Object):Dictionary
	{
		var result:Dictionary = new Dictionary();
		result["appParameters"] = appParameters;
		return result;
	}
	
	/**
	 * TODO: ASDOC Definition of sendNotifications
	 */
	private function sendNotifications():void
	{
		sendNotification(MoqawalatiConstants.STARTUP_COMPLETE);
	}
	
} // class
} // package