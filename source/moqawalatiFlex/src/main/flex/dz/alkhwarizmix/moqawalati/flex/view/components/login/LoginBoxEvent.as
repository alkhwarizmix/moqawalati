////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.view.components.login
{

import dz.alkhwarizmix.moqawalati.flex.event.MoqawalatiEvent;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١١ جمادى الأول ١٤٣٥ (March 11, 2014)
 */
public class LoginBoxEvent extends MoqawalatiEvent
{
	//--------------------------------------------------------------------------
	//
	//  Constants
	//
	//--------------------------------------------------------------------------
	
	// EVENTS
	public static const LOGIN:String = "LoginBoxEvent_login";
	public static const LOGOUT:String = "LoginBoxEvent_logout";
	
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 */
	public function LoginBoxEvent(type:String, bubbles:Boolean)
	{
		super(type, bubbles);
	}
	
} // Class
} // Package
