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

package dz.alkhwarizmix.moqawalati.flex.model
{

import flash.utils.Dictionary;

import dz.alkhwarizmix.framework.flex.dtos.customize.model.vo.CustomDataVO;
import dz.alkhwarizmix.framework.flex.logging.AlKhwarizmixLog;
import dz.alkhwarizmix.framework.flex.logging.IAlKhwarizmixLogger;
import dz.alkhwarizmix.framework.flex.model.vo.AlKhwarizmixVO;
import dz.alkhwarizmix.moqawalati.flex.MoqawalatiConstants;
import dz.alkhwarizmix.moqawalati.flex.interfaces.IMoqawalatiProxy;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٠ محرم ١٤٣٥ (November 24, 2013)
 */
public class MoqawalatiCustomDataProxy extends MoqawalatiProxy
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
	public static const NAME:String = "MoqawalatiCustomDataProxy";
	
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
	public function MoqawalatiCustomDataProxy(data:Object=null)
	{
		super(NAME, data);
	}
	
	//--------------------------------------------------------------------------
	//
	//  Logger
	//
	//--------------------------------------------------------------------------
	
	private static const LOG:IAlKhwarizmixLogger = AlKhwarizmixLog.
		getLogger(MoqawalatiCustomDataProxy);
	
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
		return MoqawalatiConstants.CUSTOMDATA_PROXY_CHANGED;;
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
		return new CustomDataVO();
	}
	
} // Class
} // Package