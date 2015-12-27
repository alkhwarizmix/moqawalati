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
public class ReservautoVehicule extends AbstractAlKhwarizmixDomainObject
		implements Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 7967220610098420973L;

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public ReservautoVehicule() {
		super();
	}

	protected ReservautoVehicule(final ReservautoVehicule other) {
		super(other);
		if (other != null) {
			Name = other.Name;
			Position = (ReservautoPosition) ObjectUtils.clone(other.Position);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private String Name;
	private ReservautoPosition Position;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new ReservautoVehicule(this);
	}

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder().append("Name", Name)
				.append("Position", Position);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, Name);
		result = continueHashCode(result, Position);
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
				&& ObjectUtils.equals(Name, getObjectAsThisClass(other).Name)
				&& ObjectUtils.equals(Position,
						getObjectAsThisClass(other).Position);
		return result;
	}

	private ReservautoVehicule getObjectAsThisClass(final Object other) {
		return (other instanceof ReservautoVehicule)
				? (ReservautoVehicule) other
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
	// Name
	// ----------------------------------

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param value
	 *            the name to set
	 */
	public void setName(final String value) {
		Name = value;
	}

	// ----------------------------------
	// Position
	// ----------------------------------

	/**
	 * @return the position
	 */
	public ReservautoPosition getPosition() {
		return Position;
	}

	/**
	 * @param value
	 *            the position to set
	 */
	public void setPosition(final ReservautoPosition value) {
		Position = value;
	}

} // Class
