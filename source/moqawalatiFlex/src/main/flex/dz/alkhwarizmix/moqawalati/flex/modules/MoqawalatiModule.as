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

package dz.alkhwarizmix.moqawalati.flex.modules
{

import flash.net.registerClassAlias;

import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixMissingImplError;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.framework.flex.modules.AlKhwarizmixModule;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiModule;
import dz.alkhwarizmix.moqawalati.flex.view.components.MoqawalatiDataListCanvas;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MoqawalatiModule extends AlKhwarizmixModule
	implements IMoqawalatiModule
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MoqawalatiModule()
	{
		super();
		
		// TODO: CHECK THIS
		registerClassAlias( "dz.alkhwarizmix.moqawalati.flex.modules." +
			"MoqawalatiModule", MoqawalatiModule);
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
			LOG = AlKhwarizmixLog.getLogger(MoqawalatiModule);
		return LOG;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  dataListCanvas
	//----------------------------------
	
	public function get dataListCanvas():MoqawalatiDataListCanvas
	{
		throw new AlKhwarizmixMissingImplError();
		return null;
	}
	
} // class
} // package
