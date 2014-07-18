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

package dz.alkhwarizmix.moqawalati.flex.model
{

import flash.utils.Dictionary;

import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.framework.flex.model.vo.AlKhwarizmixVO;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiProxy;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ١٨ ذو الحجة ١٤٣٤ (October 23, 2013)
 */
public class MoqawalatiConfigProxy extends MoqawalatiProxy
	implements IMoqawalatiProxy
{
	//--------------------------------------------------------------------------
	//
	//  Constants
	//
	//--------------------------------------------------------------------------
	
	/**
	 * The proxy name
	 */
	public static const NAME:String = "MoqawalatiConfigProxy";
	
	//--------------------------------------------------------------------------
	//
	//  Constructor
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  Constructor.
	 *
	 * @param data TODO: ASDOC
	 */
	public function MoqawalatiConfigProxy(data:Object=null)
	{
		super(NAME, data);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
		getLogger(MoqawalatiConfigProxy);
	
	override protected function get logger():IAlKhwarizmixLogger { return LOG; }
	
	//--------------------------------------------------------------------------
	//
	//  Overriden properties
	//
	//--------------------------------------------------------------------------
	
	/**
	 *  @inheritDoc
	 */
	override public function get changedNoteName():String
	{
		return null;
	}
	
	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------
	
	//----------------------------------
	//  configDico
	//----------------------------------
	
	public function get configDico():Dictionary
	{
		if (!getData())
			setData(new Dictionary());
		return (getData() as Dictionary);
	}
	
	//----------------------------------
	//  appParameters
	//----------------------------------
	
	public function get appParameters():Object
	{
		if (!configDico["appParameters"])
			configDico["appParameters"] = new Object();
		return (configDico["appParameters"]);
	}
	
	//----------------------------------
	//  swfURL
	//----------------------------------
	
	public function get appURL():String
	{
		if (appParameters["appURL"] == null)
			appParameters["appURL"] = "";
		return (appParameters["appURL"]);
	}
	
	//----------------------------------
	//  swfURLPath
	//----------------------------------
	
	public function get appURLPath():String
	{
		var result:String = appURL.substring(0, appURL.lastIndexOf("/") + 1);
		return result;
	}
	
	//----------------------------------
	//  mavenBuild
	//----------------------------------
	
	public function get flashBuilderBuild():Boolean
	{
		if (appParameters["flashBuilderBuild"] == null)
			appParameters["flashBuilderBuild"] = "false";
		return (appParameters["flashBuilderBuild"] == "true");
	}
	
	//--------------------------------------------------------------------------
	//
	//  Overriden methods
	//
	//--------------------------------------------------------------------------
	
	/**
	 * @inheritDoc
	 */
	override public function getOneItem():AlKhwarizmixVO
	{
		return null;
	}
	
} // Class
} // Package
