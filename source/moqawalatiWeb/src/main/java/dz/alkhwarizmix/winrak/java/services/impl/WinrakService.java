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

package dz.alkhwarizmix.winrak.java.services.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.services.impl.AbstractAlKhwarizmixService;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;
import dz.alkhwarizmix.winrak.java.services.IWinrakService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٧ ربيع الاول ١٤٣٧ (December 28, 2015)
 */
@Service
@Transactional(readOnly = true)
public class WinrakService extends AbstractAlKhwarizmixService implements
		IWinrakService {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public WinrakService() {
		super();
	}

	protected WinrakService(final Logger theLogger) {
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
			logger = LoggerFactory.getLogger(WinrakService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private GoogleMapsService googleGeocodingService;

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
	 * {@inheritDoc}
	 */
	@Override
	public String convertPositionToAddress(final Double latitude,
			final Double longitude, final long timeout_ms)
			throws AlKhwarizmixException {
		return googleGeocodingService.convertPositionToAddress(
				roundTo5Decimals(latitude), roundTo5Decimals(longitude),
				timeout_ms);
	}

	private Double roundTo5Decimals(final Double value) {
		final Double factor = Math.pow(10, 5);
		return Math.round(value * factor) / factor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<IWinrakPosition, Long> getDistances(
			final IWinrakPosition origin,
			final List<IWinrakPosition> destinations, final long timeout_ms)
			throws AlKhwarizmixException {
		return googleGeocodingService.getDistances(origin, destinations,
				timeout_ms);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String trouvauto(final IWinrakPosition pos, final int count)
			throws AlKhwarizmixException {
		getLogger().trace("position");
		return new TrouvautoService(this).trouvauto(pos, count);
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

	/**
	 * @param value
	 *            the googleGeocodingService to set
	 */
	public void setGoogleGeocodingService(final GoogleMapsService value) {
		googleGeocodingService = value;
	}

} // Class
