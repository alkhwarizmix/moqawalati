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

import dz.alkhwarizmix.framework.flex.controller.AlKhwarizmixBlazeDSGetDataCommand;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiConfigProxy;

import org.puremvc.as3.multicore.interfaces.IFacade;
import org.puremvc.as3.multicore.patterns.facade.Facade;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MoqawalatiBlazeDSGetDataCommand extends AlKhwarizmixBlazeDSGetDataCommand
	implements IMoqawalatiCommand
{
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
		getLogger(MoqawalatiBlazeDSGetDataCommand);
	
	override protected function get logger():IAlKhwarizmixLogger { return LOG; }
	
	//--------------------------------------------------------------------------
	//
	//  Overriden properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  amfURI
	//----------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function get amfURI():String
	{
		return appConfigProxy.appURLPath + "messagebroker/amf";
	}
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  appFacade
	//----------------------------------
	
	public final function get appFacade():IFacade
	{
		return Facade.getInstance(MoqawalatiConstants.FACADE_NAME);
	}
	
	//----------------------------------
	//  appConfigProxy
	//----------------------------------
	
	public final function get appConfigProxy():MoqawalatiConfigProxy
	{
		return appFacade.retrieveProxy(MoqawalatiConfigProxy.NAME)
			as MoqawalatiConfigProxy;
	}
	
} // class
} // package
