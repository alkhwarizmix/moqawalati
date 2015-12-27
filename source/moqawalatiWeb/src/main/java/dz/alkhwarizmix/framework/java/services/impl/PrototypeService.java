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
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.services.IPrototypeService;
import dz.alkhwarizmix.framework.java.utils.IHTTPUtil;
import dz.alkhwarizmix.framework.java.utils.IJSONUtil;
import dz.alkhwarizmix.framework.java.utils.impl.HTTPUtil;
import dz.alkhwarizmix.framework.java.utils.impl.JSONUtil;
import dz.alkhwarizmix.reservauto.java.model.vo.ReservautoPosition;
import dz.alkhwarizmix.reservauto.java.model.vo.ReservautoResponse;
import dz.alkhwarizmix.reservauto.java.model.vo.ReservautoVehicule;

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

	// @Autowired
	// private IRecordDAO recordDAO;

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
	public String position(final ReservautoPosition position, final int count)
			throws AlKhwarizmixException {
		getLogger().trace("position");
		return position_internal(position, count);
	}

	private String position_internal(final ReservautoPosition position,
			final int count) throws AlKhwarizmixException {
		String result = getReservautoVehicleProposals(position);
		final ReservautoResponse reservautoResponse = jsonToReservautoResponse(result);
		final List<ReservautoVehicule> vehicules = getNearest(
				reservautoResponse, position, count);
		result = "{\"vehicules\":[";
		String coma = "";
		for (final ReservautoVehicule vehicule : vehicules) {
			result += coma + "{\"name\":\"" + vehicule.getName()
					+ "\",\"distance\":"
					+ position.distanceTo(vehicule.getPosition()) + "}";
			coma = ",";
		}
		result += "]}";
		return result;
	}

	private IJSONUtil getJsonUtil() {
		return new JSONUtil();
	}

	private List<ReservautoVehicule> getNearest(
			final ReservautoResponse reservautoResponse,
			final ReservautoPosition position, int count)
			throws AlKhwarizmixException {
		final List<ReservautoVehicule> result = new ArrayList<ReservautoVehicule>();
		for (final ReservautoVehicule vehicule : reservautoResponse
				.getVehicules()) {
			if ((count--) == 0)
				break; // stop for
			result.add(vehicule);
		}
		return result;
	}

	private ReservautoResponse jsonToReservautoResponse(final String json)
			throws AlKhwarizmixException {
		return (ReservautoResponse) getJsonUtil().unmarshalObjectFromJSON(json,
				ReservautoResponse.class);
	}

	private String getReservautoVehicleProposals(
			final ReservautoPosition position) {
		final String endPoint = "https://www.reservauto.net/WCF/LSI/LSIBookingService.asmx/GetVehicleProposals?Callback=%22%22&CustomerID=%22%22&Latitude="
				+ position.getLat() + "&Longitude=" + position.getLon();
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

} // Class
