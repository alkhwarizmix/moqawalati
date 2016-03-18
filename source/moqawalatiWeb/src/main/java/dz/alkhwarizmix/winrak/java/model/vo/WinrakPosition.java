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

package dz.alkhwarizmix.winrak.java.model.vo;

import java.io.Serializable;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.domain.IAlKhwarizmixJsonObject;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٣ ربيع الاول ١٤٣٧ (January 02, 2016)
 */
public class WinrakPosition extends AbstractAlKhwarizmixDomainObject implements
		IWinrakPosition, IAlKhwarizmixJsonObject, Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -6265964503450667114L;

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public WinrakPosition() {
		super();
	}

	public WinrakPosition(final Double lat, final Double lng) {
		this();
		Lat = lat;
		Lon = lng;
	}

	protected WinrakPosition(final WinrakPosition other) {
		super(other);
		if (other != null) {
			Lat = other.Lat;
			Lon = other.Lon;
			address = other.address;
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private String address;
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
		return new WinrakPosition(this);
	}

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder().append("Lat", Lat).append("Lng", Lon)
				.append("address", address);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, address);
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
				&& ObjectUtils.equals(Lon, getObjectAsThisClass(other).Lon)
				&& ObjectUtils.equals(address,
						getObjectAsThisClass(other).address);
		return result;
	}

	private WinrakPosition getObjectAsThisClass(final Object other) {
		return (other instanceof WinrakPosition)
				? (WinrakPosition) other
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
	 *
	 * @param pos2
	 * @return the direction to take to go to pos2
	 */
	@Override
	public String directionTo(final IWinrakPosition pos2) {
		return (getLat() < pos2.getLat()
				? "N"
				: (getLat() > pos2.getLat()
						? "S"
						: "")) + (getLng() < pos2.getLng()
				? "E"
				: (getLng() > pos2.getLng()
						? "W"
						: ""));
	}

	/**
	 * http://www.movable-type.co.uk/scripts/latlong.html
	 */
	@Override
	public int distanceTo(final IWinrakPosition pos2) {
		final WinrakPosition pos1 = this;
		final Double R = 6371000.0; // meters
		final Double radLat1 = Math.toRadians(pos1.getLat());
		final Double radLat2 = Math.toRadians(pos2.getLat());
		final Double deltaLat = Math.toRadians(pos2.getLat() - pos1.getLat());
		final Double deltaLng = Math.toRadians(pos2.getLng() - pos1.getLng());
		final Double a = (Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2))
				+ (Math.cos(radLat1) * Math.cos(radLat2)
						* Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2));
		final Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		final Double d = R * c;
		return (d.intValue());
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAddress() {
		return address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAddress(final String value) {
		address = value;
	}

	// ----------------------------------
	// Lat
	// ----------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getLat() {
		return Lat;
	}

	protected void setLat(final Double value) {
		Lat = value;
	}

	// ----------------------------------
	// Lng
	// ----------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getLng() {
		return Lon;
	}

	protected void setLng(final Double value) {
		Lon = value;
	}

} // Class
