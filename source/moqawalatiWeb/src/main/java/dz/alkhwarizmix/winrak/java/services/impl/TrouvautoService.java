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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.IAlKhwarizmixJsonObject;
import dz.alkhwarizmix.framework.java.utils.IHTTPUtil;
import dz.alkhwarizmix.framework.java.utils.IJSONUtil;
import dz.alkhwarizmix.framework.java.utils.impl.HTTPUtil;
import dz.alkhwarizmix.framework.java.utils.impl.JSONUtil;
import dz.alkhwarizmix.trouvauto.java.model.vo.ReservautoResponse;
import dz.alkhwarizmix.trouvauto.java.model.vo.ReservautoVehicule;
import dz.alkhwarizmix.winrak.java.model.IWinrakItinerary;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;
import dz.alkhwarizmix.winrak.java.services.ITrouvautoService;
import dz.alkhwarizmix.winrak.java.services.IWinrakService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٣٠ ربيع الاول ١٤٣٧ (January 10, 2016)
 */
public class TrouvautoService implements ITrouvautoService {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public TrouvautoService() {
		super();
	}

	public TrouvautoService(final IWinrakService winrakService) {
		super();
		this.winrakService = winrakService;
	}

	protected TrouvautoService(final Logger theLogger) {
		this();
		logger = theLogger;
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(TrouvautoService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private IWinrakService winrakService;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public IHTTPUtil getHttpUtil() {
		return new HTTPUtil();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String trouvauto(final IWinrakPosition pos, final int count)
			throws AlKhwarizmixException {
		getLogger().trace("position");
		return trouvauto_internal(pos, count);
	}

	private String trouvauto_internal(final IWinrakPosition pos, final int count)
			throws AlKhwarizmixException {
		final long timeout_ms = 3000;
		final ReservautoResponse reservautoResponse = jsonToTrouvautoResponse(getReservautoVehicleProposals(pos));
		final List<ReservautoVehicule> vehicules = getNearest(
				reservautoResponse, pos, count);

		final List<IWinrakPosition> destinations = new ArrayList<IWinrakPosition>();
		for (final ReservautoVehicule vehicule : vehicules)
			destinations.add(vehicule.getPosition());
		final Map<IWinrakPosition, IWinrakItinerary> itineraryData = winrakService
				.getItineraryData(pos, destinations, timeout_ms);

		final TrouvautoResponse response = new TrouvautoResponse(
				getShortAddress(pos));
		for (final ReservautoVehicule vehicule : vehicules)
			response.addVehicule(new TrouvautoVehicule(pos, vehicule,
					itineraryData.get(vehicule.getPosition())));
		final String result = getJsonUtil().marshalObjectToJSON(response);
		return result;
	}

	private String getShortAddress(final IWinrakPosition pos) {
		return (pos.getAddress() != null)
				? pos.getAddress().replaceFirst(",(.*)", "")
				: null;
	}

	private IJSONUtil getJsonUtil() {
		return new JSONUtil();
	}

	private List<ReservautoVehicule> getNearest(
			final ReservautoResponse reservautoResponse,
			final IWinrakPosition pos, int count) throws AlKhwarizmixException {
		final List<ReservautoVehicule> result = new ArrayList<ReservautoVehicule>();
		for (final ReservautoVehicule vehicule : reservautoResponse
				.getVehicules()) {
			if ((count--) == 0)
				break; // stop for
			result.add(vehicule);
		}
		return result;
	}

	private ReservautoResponse jsonToTrouvautoResponse(final String json)
			throws AlKhwarizmixException {
		return (ReservautoResponse) getJsonUtil().unmarshalObjectFromJSON(json,
				ReservautoResponse.class);
	}

	private String getReservautoVehicleProposals(final IWinrakPosition pos) {
		final String endPoint = "https://www.reservauto.net/WCF/LSI/LSIBookingService.asmx/GetVehicleProposals?Callback=%22%22&CustomerID=%22%22&Latitude="
				+ pos.getLat() + "&Longitude=" + pos.getLng();
		String result = getHttpUtil().sendGetRequest(endPoint, null);
		result = result.substring(3, result.length() - 2);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// winrakService
	// ----------------------------------

	public void setWinrakService(final IWinrakService value) {
		winrakService = value;
	}

	// --------------------------------------------------------------------------
	//
	// Internal Classes
	//
	// --------------------------------------------------------------------------

	final class TrouvautoResponse implements IAlKhwarizmixJsonObject {
		public TrouvautoResponse(final String address) {
			super();
			this.address = address;
		}

		protected final String address;
		private List<TrouvautoVehicule> vehicules;

		public void addVehicule(final TrouvautoVehicule vehicule) {
			if (vehicules == null)
				vehicules = new ArrayList<TrouvautoVehicule>();
			vehicules.add(vehicule);
		}
	}

	final class TrouvautoVehicule implements IAlKhwarizmixJsonObject {
		public TrouvautoVehicule(final IWinrakPosition pos,
				final ReservautoVehicule vehicule,
				final IWinrakItinerary itinerary) {
			super();
			address = getShortAddress(vehicule.getPosition());
			direction = pos.directionTo(vehicule.getPosition());
			distance = pos.distanceTo(vehicule.getPosition());
			if (itinerary != null) {
				itinDist = itinerary.getDistance();
				// Itinerary duration is in seconds.
				// It is converted to minutes for JSON
				long durationInSec = itinerary.getDuration();
				itinDura = 1L;
				while (durationInSec > 60) {
					itinDura++;
					durationInSec -= 60;
				}
			}
			lat = roundTo5Decimals(vehicule.getPosition().getLat());
			lng = roundTo5Decimals(vehicule.getPosition().getLng());
			name = vehicule.getName();
		}

		protected final String address;
		protected final String direction;
		protected final Integer distance;
		protected Long itinDist;
		protected Long itinDura;
		protected final Double lat;
		protected final Double lng;
		protected final String name;

		private Double roundTo5Decimals(final Double value) {
			final Double factor = Math.pow(10, 5);
			return Math.round(value * factor) / factor;
		}
	}

} // Class
