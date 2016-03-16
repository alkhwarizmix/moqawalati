////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2016 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.winrak.java.services.impl;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٧ ربيع الثاني ١٤٣٧ (February 06, 2016)
 */
public class GoogleDistanceMatrixApiWrapper {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// properties
	//
	// --------------------------------------------------------------------------

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	public DistanceMatrixApiRequest newRequest(final GeoApiContext context) {
		return DistanceMatrixApi.newRequest(context);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// EMPTY

} // Class
