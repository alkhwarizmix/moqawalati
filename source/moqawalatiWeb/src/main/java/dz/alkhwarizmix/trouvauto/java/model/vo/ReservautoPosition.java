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

package dz.alkhwarizmix.trouvauto.java.model.vo;

import java.io.Serializable;

import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;
import dz.alkhwarizmix.winrak.java.model.vo.WinrakPosition;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ ربيع الاول ١٤٣٧ (December 25, 2015)
 */
public class ReservautoPosition extends WinrakPosition implements
		IWinrakPosition, Serializable, Cloneable {

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
		super(lat, lon);
	}

	protected ReservautoPosition(final ReservautoPosition other) {
		super(other);
	}

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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		final boolean result = super.equals(other)
				&& (getObjectAsThisClass(other) != null);
		return result;
	}

	private ReservautoPosition getObjectAsThisClass(final Object other) {
		return (other instanceof ReservautoPosition)
				? (ReservautoPosition) other
				: null;
	}

} // Class
