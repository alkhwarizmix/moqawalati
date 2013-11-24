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

import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiBlazeDSGetDataCommand;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٨ محرم ١٤٣٥ (November 22, 2013)
 */
public class MoqawalatiSetCustomizedDataCommand extends MoqawalatiBlazeDSGetDataCommand
{
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function MoqawalatiSetCustomizedDataCommand()
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
		return "setCustomData";		
	}
	
	/**
	 *  @inheritDoc
	 */
	override public function get proxyName():String
	{
		return null;
	}
	
} // class
} // package