////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.controller
{

import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.framework.flex.rpc.remoting.AlKhwarizmixRemoteObject;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;
import dz.alkhwarizmix.moqawalati.flex.model.MoqawalatiCustomDataProxy;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٨ محرم ١٤٣٥ (November 22, 2013)
 */
public class MoqawalatiGetCustomizedDataCommand extends MoqawalatiBlazeDSGetDataCommand
	implements IMoqawalatiCommand
{
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
		getLogger(MoqawalatiGetCustomizedDataCommand);
	
	override protected function get logger():IAlKhwarizmixLogger { return LOG; }
	
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MoqawalatiGetCustomizedDataCommand()
	{
		super();
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden properties
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function get destination():String
	{
		return "customizerService";
	}
	
	/**
	 *  @inheritDoc
	 */
	override public function get operationName():String
	{
		return "getCustomData";		
	}
	
	/**
	 *  @inheritDoc
	 */
	override public function get proxyName():String
	{
		return MoqawalatiCustomDataProxy.NAME;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @private
	 */
	override protected function newRemoteObject():AlKhwarizmixRemoteObject
	{
		var result:AlKhwarizmixRemoteObject = super.newRemoteObject();
		result.showBusyCursor = false;
		return result;
	}
	
} // class
} // package
