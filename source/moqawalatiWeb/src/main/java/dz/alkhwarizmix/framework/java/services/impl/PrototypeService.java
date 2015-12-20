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
import dz.alkhwarizmix.framework.java.utils.impl.HTTPUtil;

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
	public String position(final Double latitude, final Double longitude)
			throws AlKhwarizmixException {
		getLogger().trace("position");
		final String endPoint = "https://www.reservauto.net/WCF/LSI/LSIBookingService.asmx/GetVehicleProposals?Callback=%22%22&CustomerID=%22%22&Latitude="
				+ latitude + "&Longitude=" + longitude;
		String result = getHttpUtil().sendGetRequest(endPoint, null);
		result = result.substring(3, result.length() - 1);
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
