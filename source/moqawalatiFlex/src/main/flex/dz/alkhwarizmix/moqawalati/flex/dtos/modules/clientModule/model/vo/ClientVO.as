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

package dz.alkhwarizmix.moqawalati.flex.dtos.modules.clientModule.model.vo
{

import dz.alkhwarizmix.moqawalati.flex.model.vo.MoqawalatiVO;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٢ ذو القعدة ١٤٣٤ (September 08, 2013)
 */
[Bindable]
[RemoteClass(alias="dz.alkhwarizmix.moqawalati.java.dtos.modules.clientModule.model.vo.Client")]
public class ClientVO extends MoqawalatiVO
{
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  clientId
	//----------------------------------
	
	private var _clientId:String = null;
	public function get clientId():String { return _clientId; }
	
	public function set clientId(value:String):void
	{
		if (_clientId == value)
			return;
		_clientId = value;
	}
	
	//----------------------------------
	//  name
	//----------------------------------
	
	private var _name:String = null;
	public function get name():String { return _name; }
	
	public function set name(value:String):void
	{
		if (_name == value)
			return;
		_name = value;
	}
	
} // Class
} // Package