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

import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModuleMediator;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٣ ربيع الأول ١٤٣٦ (December 24, 2014)
 */
public class MoqawalatiModuleMediator extends MoqawalatiMediator
	implements IMoqawalatiModuleMediator
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MoqawalatiModuleMediator(
		mediatorName:String = null, viewComponent:Object = null)
	{
		super(mediatorName, viewComponent);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
		getLogger(MoqawalatiModuleMediator);
	
	override protected function get logger():IAlKhwarizmixLogger { return LOG; }
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override protected function handleNotification_catch_error(
		notif:INotification, error:Error):void
	{
		super.handleNotification_catch_error(notif, error);
	}
	
	/**
	 *  @inheritDoc
	 */
	override protected function handleNotification_finally(
		notif:INotification):void
	{
		super.handleNotification_finally(notif);
	}
	
	/**
	 *  @inheritDoc
	 */
	override protected function handleNotification_try(
		notif:INotification):void
	{
		super.handleNotification_try(notif);
	}
	
} // class
} // package
