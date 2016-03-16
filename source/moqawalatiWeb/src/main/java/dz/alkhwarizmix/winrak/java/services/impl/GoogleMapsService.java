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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٦ ربيع الاول ١٤٣٧ (January 05, 2016)
 */
@Service
public class GoogleMapsService {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	private Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(GoogleMapsService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// properties
	//
	// --------------------------------------------------------------------------

	private String googleAPIKey;

	private GoogleGeocodingApiWrapper geocodingApiWrapper;

	private GoogleDistanceMatrixApiWrapper distanceMatrixApiWrapper;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	@Cacheable("google_addresses")
	public String convertPositionToAddress(final Double latitude,
			final Double longitude, final long timeout_ms)
			throws AlKhwarizmixException {
		getLogger().debug("convertPositionToAddress: {}, {}, {}", latitude,
				longitude, timeout_ms);
		final GeoApiContext context = getGeoApiContext(googleAPIKey, timeout_ms);
		GeocodingResult[] results;
		results = getGoogleGeocodingApiWrapper().reverseGeocode(context,
				new LatLng(latitude, longitude));
		getLogger().debug("convertPositionToAddress: returns {}",
				results[0].formattedAddress);
		return (results[0].formattedAddress);
	}

	private GeoApiContext getGeoApiContext(final String apiKey,
			final long timeout_ms) {
		final GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
		context.setConnectTimeout(timeout_ms, TimeUnit.MILLISECONDS);
		return context;
	}

	public Map<IWinrakPosition, Long> getDistances(
			final IWinrakPosition origin,
			final List<IWinrakPosition> destinations, final long timeout_ms)
			throws AlKhwarizmixException {
		getLogger().debug("setDistances: {}, {}, {}", origin, destinations,
				timeout_ms);

		final GeoApiContext context = getGeoApiContext(googleAPIKey, timeout_ms);
		final DistanceMatrixApiRequest request = getDistanceMatrixApiWrapper()
				.newRequest(context);
		request.origins(getLatLng(origin));
		for (final IWinrakPosition destination : destinations)
			request.destinations(getLatLng(destination));

		final DistanceMatrix matrix = request.awaitIgnoreError();
		final Map<IWinrakPosition, Long> result = new HashMap<IWinrakPosition, Long>();
		if (matrix != null)
			for (final DistanceMatrixRow row : matrix.rows)
				for (final DistanceMatrixElement element : row.elements)
					for (final IWinrakPosition destination : destinations)
						result.put(destination,
								Long.valueOf(element.distance.inMeters));
		return result;
	}

	private LatLng getLatLng(final IWinrakPosition pos) {
		return new LatLng(pos.getLat(), pos.getLng());
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// distanceMatrixApiWrapper
	// ----------------------------------

	public void setDistanceMatrixApiWrapper(
			final GoogleDistanceMatrixApiWrapper value) {
		distanceMatrixApiWrapper = value;
	}

	public GoogleDistanceMatrixApiWrapper getDistanceMatrixApiWrapper() {
		if (distanceMatrixApiWrapper == null)
			distanceMatrixApiWrapper = new GoogleDistanceMatrixApiWrapper();
		return distanceMatrixApiWrapper;
	}

	// ----------------------------------
	// geocodingApiWrapper
	// ----------------------------------

	public void setGeocodingApiWrapper(final GoogleGeocodingApiWrapper value) {
		geocodingApiWrapper = value;
	}

	public GoogleGeocodingApiWrapper getGoogleGeocodingApiWrapper() {
		if (geocodingApiWrapper == null)
			geocodingApiWrapper = new GoogleGeocodingApiWrapper();
		return geocodingApiWrapper;
	}

	// ----------------------------------
	// googleAPIKey
	// ----------------------------------

	public void setGoogleAPIKey(final String value) {
		googleAPIKey = value;
	}

} // Class
