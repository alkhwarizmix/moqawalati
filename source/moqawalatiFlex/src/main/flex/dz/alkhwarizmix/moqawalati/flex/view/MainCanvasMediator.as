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

package dz.alkhwarizmix.moqawalati.flex.view
{

import mx.controls.Alert;

import dz.alkhwarizmix.framework.flex.AlKhwarizmixConstants;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiMediator;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MainCanvas;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٣ ربيع الأول ١٤٣٦ (December 24, 2014)
 */
public class MainCanvasMediator extends MoqawalatiMediator
	implements IMoqawalatiMediator
{
	//--------------------------------------------------------------------------
	//
	//  Constants
	//
	//--------------------------------------------------------------------------
	
	/**
	 * The mediator name
	 */
	public static const NAME:String = "MainCanvasMediator";
	
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MainCanvasMediator(viewComponent:Object = null)
	{
		super(NAME, viewComponent);
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
			LOG = AlKhwarizmixLog.getLogger(MainCanvasMediator);
		return LOG;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of mainControlBar
	 */
	public final function get mainControlBar():MainCanvas
	{
		return viewComponent as MainCanvas;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function listNotificationInterests():Array
	{
		return [
			AlKhwarizmixConstants.REMOTE_SERVER_ERROR
		];
	}
	
	/**
	 *  @inheritDoc
	 */
	override protected function handleNotification_try(notif:INotification):void
	{
		super.handleNotification_try(notif);
		
		switch (notif.getName())
		{
			case AlKhwarizmixConstants.REMOTE_SERVER_ERROR:
			{
				handleRemoteServerError(notif.getBody());
				break;
			}
				
		} // switch
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * TODO: ASDOC Definition of handleRemoteServerError
	 */
	public function handleRemoteServerError(notifBody:Object):void
	{
		logger.debug("handleRemoteServerError");
		var error:Object = notifBody.error.rootCause;
		Alert.show(error.localizedMessage, "Remote Server Error: " + error.errorCode);
	}
	
} // class
} // package
