////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.domain.IAlKhwarizmixJsonObject;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.services.IPrototypeService;
import dz.alkhwarizmix.framework.java.utils.IHTTPUtil;
import dz.alkhwarizmix.framework.java.utils.IJSONUtil;
import dz.alkhwarizmix.framework.java.utils.impl.HTTPUtil;
import dz.alkhwarizmix.framework.java.utils.impl.JSONUtil;
import dz.alkhwarizmix.trouvauto.java.model.vo.ReservautoResponse;
import dz.alkhwarizmix.trouvauto.java.model.vo.ReservautoVehicule;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;
import dz.alkhwarizmix.winrak.java.services.IWinrakService;
import dz.alkhwarizmix.winrak.java.services.impl.WinrakPositionWorker;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ربيع الاول ١٤٣٧ (December 19, 2015)
 */
@Service
@Transactional(readOnly = true)
public class PrototypeService extends AbstractAlKhwarizmixService implements
		IPrototypeService {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public PrototypeService() {
		super();
	}

	protected PrototypeService(final Logger theLogger) {
		this();
		logger = theLogger;
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(PrototypeService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IWinrakService winrakService;

	// @Autowired
	// private IRecordServiceValidator recordValidator;

	// @Autowired
	// private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			final AbstractAlKhwarizmixDomainObject object,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("getObject");
		return null;
	}

	/**
	 */
	public IHTTPUtil getHttpUtil() {
		return new HTTPUtil();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String position(final IWinrakPosition pos, final int count)
			throws AlKhwarizmixException {
		getLogger().trace("position");
		return position_internal(pos, count);
	}

	private String position_internal(final IWinrakPosition pos, final int count)
			throws AlKhwarizmixException {
		final long timeout_ms = 3000;
		final WinrakPositionWorker winrakPositionWorker = new WinrakPositionWorker(
				winrakService);
		winrakPositionWorker.fillPositionAddress(pos, timeout_ms);
		final ReservautoResponse reservautoResponse = jsonToTrouvautoResponse(getReservautoVehicleProposals(pos));
		final List<ReservautoVehicule> vehicules = getNearest(
				reservautoResponse, pos, count);

		for (final ReservautoVehicule vehicule : vehicules)
			winrakPositionWorker.fillPositionAddress(vehicule.getPosition(),
					timeout_ms);
		winrakPositionWorker.waitForAllFillPositionAddress(timeout_ms);

		final TrouvautoResponse response = new TrouvautoResponse(
				getShortAddress(pos));
		for (final ReservautoVehicule vehicule : vehicules)
			response.addVehicule(new TrouvautoVehicule(vehicule, pos));
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String protoPost(final String param1) throws AlKhwarizmixException {
		getLogger().trace("protoPost");
		final String result = "{}";
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String protoGet(final String param1) throws AlKhwarizmixException {
		getLogger().trace("protoGet");
		final String result = "{}";
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// DAO
	// ----------------------------------

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return null;
	}

	// ----------------------------------
	// Validator
	// ----------------------------------

	@Override
	protected IAlKhwarizmixServiceValidator getServiceValidator() {
		return null;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	@Override
	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return null;
	}

	@Override
	protected void setJaxb2Marshaller(final Jaxb2Marshaller value) {
		// jaxb2Marshaller = value;
	}

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
		public TrouvautoVehicule(final ReservautoVehicule vehicule,
				final IWinrakPosition pos) {
			super();
			address = getShortAddress(vehicule.getPosition());
			lat = roundTo5Decimals(vehicule.getPosition().getLat());
			lng = roundTo5Decimals(vehicule.getPosition().getLng());
			name = vehicule.getName();
			direction = pos.directionTo(vehicule.getPosition());
			distance = pos.distanceTo(vehicule.getPosition());
		}

		protected final String address;
		protected final Double lat;
		protected final Double lng;
		protected final String name;
		protected final String direction;
		protected final int distance;

		private Double roundTo5Decimals(final Double value) {
			final Double factor = Math.pow(10, 5);
			return Math.round(value * factor) / factor;
		}
	}

} // Class
