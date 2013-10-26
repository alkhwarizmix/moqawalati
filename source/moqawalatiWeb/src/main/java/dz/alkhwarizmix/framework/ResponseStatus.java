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

package dz.alkhwarizmix.framework;

/**
 *  <p>
 *  TODO: Javadoc
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public enum ResponseStatus
{
	SUCCESSFUL, // If the request was processed successfully
	DUPLICATE, // If the request is a duplicate
	ERROR, // If there was an error while processing the request.
	WARNING; // If there was an mix success and error while processing the request.
	
	/**
	 * get the response status using its name
	 * @param name {@link String} the status name
	 * @return {@link String}
	 */
	public static ResponseStatus getResponseStatus(String name)
	{
		if (name == null) return null;
		
		try
		{
			return ResponseStatus.valueOf(name);
		}
		catch (IllegalArgumentException e)
		{
			return null;
		}
	}
}
