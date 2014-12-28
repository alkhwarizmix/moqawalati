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

package dz.alkhwarizmix.moqawalati.flex.facade
{

import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixMissingImplError;
import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixTypeError;
import dz.alkhwarizmix.framework.flex.facade.AlKhwarizmixFacade;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiFacade;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MoqawalatiFacade extends AlKhwarizmixFacade
	implements IMoqawalatiFacade
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MoqawalatiFacade(key:String)
	{
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
			LOG = AlKhwarizmixLog.getLogger(MoqawalatiFacade);
		return LOG;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @inheritDoc
	 */
	override public function registerCommand(
		notificationName:String, commandClassRef:Class):void
	{
		if (new commandClassRef() is IMoqawalatiCommand)
			super.registerCommand(notificationName, commandClassRef);
		else
			throw new AlKhwarizmixTypeError();
	}
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * Application or Module startup
	 * 
	 * @param app a reference to the application or module component 
	 */  
	public function startup(app:*):void
	{
		throw new AlKhwarizmixMissingImplError();
	}
	
} // class
} // package
