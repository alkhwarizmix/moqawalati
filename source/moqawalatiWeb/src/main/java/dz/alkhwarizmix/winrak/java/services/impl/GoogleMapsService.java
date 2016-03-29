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
import com.google.maps.model.TravelMode;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.winrak.java.model.IWinrakItinerary;
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

	@Cacheable("google_itineraries")
	public Map<IWinrakPosition, IWinrakItinerary> getItineraryData(
			final IWinrakPosition origin,
			final List<IWinrakPosition> destinations, final long timeout_ms)
			throws AlKhwarizmixException {
		getLogger().debug("getItineraryData: {}, {}, {}", origin, destinations,
				timeout_ms);

		final GeoApiContext context = getGeoApiContext(googleAPIKey, timeout_ms);
		final DistanceMatrixApiRequest request = getDistanceMatrixApiWrapper()
				.newRequest(context);
		request.mode(TravelMode.WALKING);
		request.origins(getLatLng(origin));
		final int len = destinations.size();
		final LatLng[] destinationsAsPoints = new LatLng[len];
		for (int i = 0; i < len; i++) {
			final IWinrakPosition destination = destinations.get(i);
			destinationsAsPoints[i] = getLatLng(destination);
		}
		request.destinations(destinationsAsPoints);

		DistanceMatrix matrix = null;
		try {
			matrix = request.await();
		} catch (final Exception e) {
			logger.warn("getDistances: {}", e.getMessage());
		}
		final Map<IWinrakPosition, IWinrakItinerary> result = new HashMap<IWinrakPosition, IWinrakItinerary>();
		if (matrix != null) {
			origin.setAddress(matrix.originAddresses[0]);
			for (final DistanceMatrixRow row : matrix.rows)
				for (int i = 0; i < row.elements.length; i++) {
					final DistanceMatrixElement element = row.elements[i];
					final IWinrakPosition destination = destinations.get(i);
					destination.setAddress(matrix.destinationAddresses[i]);
					result.put(destination, new GoogleMapsWinrakItinerary(
							element.distance.inMeters,
							element.duration.inSeconds));
				}
		}
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

	// --------------------------------------------------------------------------
	//
	// Internal Classes
	//
	// --------------------------------------------------------------------------

	final class GoogleMapsWinrakItinerary implements IWinrakItinerary {
		public GoogleMapsWinrakItinerary(final long distance,
				final long duration) {
			this.distance = distance;
			this.duration = duration;
		}

		private final long distance;
		private final long duration;

		@Override
		public long getDistance() {
			return distance;
		}

		@Override
		public long getDuration() {
			return duration;
		}
	}

} // Class
