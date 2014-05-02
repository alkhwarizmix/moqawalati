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

import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiCanvas;
import dz.alkhwarizmix.moqawalati.flex.view.containers.MoqawalatiHBox;

/**
 *  <p>
 *  TODO: ASDOC
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٢ ذو الحجة ١٤٣٤ (October 27, 2013)
 */
public interface IMoqawalatiApplication
{
	function get parameters():Object;
	function get mainCanvas():MoqawalatiCanvas;
	function get mainControlBar():MoqawalatiHBox;
	
} // Interface
} // Package