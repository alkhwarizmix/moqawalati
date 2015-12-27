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
import dz.alkhwarizmix.framework.java.utils.IJSONUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الاول ١٤٣٧ (December 25, 2015)
 */
public class ReservautoResponse extends AbstractAlKhwarizmixDomainObject
		implements Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 4580561139303131493L;

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public ReservautoResponse() {
		super();
	}

	public ReservautoResponse(final String json, final IJSONUtil jsonUtil)
			throws AlKhwarizmixException {
		this((ReservautoResponse) jsonUtil.unmarshalObjectFromJSON(json,
				ReservautoResponse.class));
	}

	protected ReservautoResponse(final ReservautoResponse other) {
		super(other);
		if (other != null) {
			ExtensionData = (ReservautoExtensionData) ObjectUtils
					.clone(other.ExtensionData);
			UserPosition = (ReservautoPosition) ObjectUtils
					.clone(other.UserPosition);
			Vehicules = (ReservautoVehiculeList) ObjectUtils
					.clone(other.Vehicules);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private ReservautoExtensionData ExtensionData;
	private ReservautoPosition UserPosition;
	private ReservautoVehiculeList Vehicules;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new ReservautoResponse(this);
	}

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder().append("ExtensionData", ExtensionData)
				.append("UserPosition", UserPosition)
				.append("Vehicules", Vehicules);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, ExtensionData);
		result = continueHashCode(result, UserPosition);
		result = continueHashCode(result, Vehicules);
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
				&& ObjectUtils.equals(ExtensionData,
						getObjectAsThisClass(other).ExtensionData)
				&& ObjectUtils.equals(UserPosition,
						getObjectAsThisClass(other).UserPosition)
				&& ObjectUtils.equals(Vehicules,
						getObjectAsThisClass(other).Vehicules);
		return result;
	}

	private ReservautoResponse getObjectAsThisClass(final Object other) {
		return (other instanceof ReservautoResponse)
				? (ReservautoResponse) other
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

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// ExtensionData
	// ----------------------------------

	public final ReservautoExtensionData getExtensionData() {
		return ExtensionData;
	}

	public final void setExtensionData(final ReservautoExtensionData value) {
		ExtensionData = value;
	}

	// ----------------------------------
	// Vehicules
	// ----------------------------------

	/**
	 * @return the vehicules
	 */
	public ReservautoVehiculeList getVehicules() {
		return Vehicules;
	}

	/**
	 * @param vehicules
	 *            the vehicules to set
	 */
	public void setVehicules(final ReservautoVehiculeList value) {
		Vehicules = value;
	}

	// ----------------------------------
	// UserPosition
	// ----------------------------------

	/**
	 * @return the userPosition
	 */
	public ReservautoPosition getUserPosition() {
		return UserPosition;
	}

	/**
	 * @param value
	 *            the userPosition to set
	 */
	public void setUserPosition(final ReservautoPosition value) {
		UserPosition = value;
	}

} // Class
