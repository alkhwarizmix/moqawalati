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

package dz.alkhwarizmix.moqawalati.flex.interfaces
{
import dz.alkhwarizmix.framework.flex.interfaces.IAlKhwarizmixCommand;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٦ محرم ١٤٣٦ (October 27, 2014)
 */
public interface IMoqawalatiCommand extends IAlKhwarizmixCommand
{
	/**
	 */
	function get appFacade():IMoqawalatiFacade;
	
	/**
	 */
	function get appConfigProxy():IMoqawalatiConfigProxy;
	
} // Interface
} // Package
