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

package dz.alkhwarizmix.moqawalati.flex.testutils
{

import dz.alkhwarizmix.moqawalati.flex.controller.MoqawalatiSimpleCommand;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiCommand;

import org.puremvc.as3.multicore.interfaces.INotification;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢١ جمادى الأول ١٤٣٥ (March 21, 2014)
 */
public class MoqawalatiSimpleCommandMock extends MoqawalatiSimpleCommand
	implements IMoqawalatiCommand
{
	private static var _wasExecuteCalled:Boolean = false;
	private static var _notifBody:Object = null;
	
	public static function get wasExecuteCalled():Boolean
	{
		return _wasExecuteCalled;
	}
	
	public static function get notifBody():Object
	{
		return _notifBody;
	}
	
	public static function init():void
	{
		_wasExecuteCalled = false;
		_notifBody = null;
	}
	
	override protected function execute_try(notif:INotification):void	
	{
		_wasExecuteCalled = true;
		_notifBody = notif.getBody();
	}
	
} // class
} // package

