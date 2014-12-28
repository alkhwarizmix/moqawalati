////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.flex.interfaces
{

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٠٣ ربيع الأول ١٤٣٦ (December 24, 2014)
 */
public interface IMoqawalatiConfigProxy
{
	/**
	 */
	function get appURLPath():String;
	
	/**
	 */
	function get appParameters():Object;
	
	/**
	 */
	function get flashBuilderBuild():Boolean;
	
} // Interface
} // Package
