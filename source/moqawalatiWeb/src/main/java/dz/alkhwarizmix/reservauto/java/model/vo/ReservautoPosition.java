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

package dz.alkhwarizmix.reservauto.java.model.vo;

import java.io.Serializable;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الاول ١٤٣٧ (December 25, 2015)
 */
public class ReservautoPosition extends AbstractAlKhwarizmixDomainObject
		implements Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 1987133374172140520L;

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public ReservautoPosition() {
		super();
	}

	public ReservautoPosition(final Double lat, final Double lon) {
		this();
		Lat = lat;
		Lon = lon;
	}

	protected ReservautoPosition(final ReservautoPosition other) {
		super(other);
		if (other != null) {
			Lat = other.Lat;
			Lon = other.Lon;
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private Double Lat;
	private Double Lon;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new ReservautoPosition(this);
	}

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder().append("Lat", Lat).append("Lon", Lon);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, Lat);
		result = continueHashCode(result, Lon);
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		final boolean result = super.equals(other)
				&& (getObjectAsThisClass(other) != null)
				&& ObjectUtils.equals(Lat, getObjectAsThisClass(other).Lat)
				&& ObjectUtils.equals(Lon, getObjectAsThisClass(other).Lon);
		return result;
	}

	private ReservautoPosition getObjectAsThisClass(final Object other) {
		return (other instanceof ReservautoPosition)
				? (ReservautoPosition) other
				: null;
	}

	/**
	 */
	@Override
	public void updateFrom(final Object sourceObject)
			throws AlKhwarizmixException {
		// NOOP
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(
			final AbstractAlKhwarizmixDomainObject object) {
		// NOOP
	}

	/**
	 * http://www.movable-type.co.uk/scripts/latlong.html
	 */
	public int distanceTo(final ReservautoPosition pos2) {
		final ReservautoPosition pos1 = this;
		final Double R = 6371000.0; // metres
		final Double radLat1 = Math.toRadians(pos1.getLat());
		final Double radLat2 = Math.toRadians(pos2.getLat());
		final Double deltaLat = Math.toRadians(pos2.getLat() - pos1.getLat());
		final Double deltaLon = Math.toRadians(pos2.getLon() - pos1.getLon());
		final Double a = (Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2))
				+ (Math.cos(radLat1) * Math.cos(radLat2)
						* Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2));
		final Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		final Double d = R * c;
		return (d.intValue());
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// Lat
	// ----------------------------------

	/**
	 * @return the lat
	 */
	public Double getLat() {
		return Lat;
	}

	/**
	 * @param value
	 *            the lat to set
	 */
	public void setLat(final Double value) {
		Lat = value;
	}

	// ----------------------------------
	// Lon
	// ----------------------------------

	/**
	 * @return the lon
	 */
	public Double getLon() {
		return Lon;
	}

	/**
	 * @param value
	 *            the lon to set
	 */
	public void setLon(final Double value) {
		Lon = value;
	}

} // Class
