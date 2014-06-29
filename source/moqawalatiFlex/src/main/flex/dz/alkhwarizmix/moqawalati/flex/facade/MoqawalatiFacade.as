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

import dz.alkhwarizmix.framework.flex.errors.AlKhwarizmixTypeError;
import dz.alkhwarizmix.framework.flex.facade.AlKhwarizmixFacade;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
public class MoqawalatiFacade extends AlKhwarizmixFacade
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
			throw new AlKhwarizmixTypeError;
	}
	
} // class
} // package
