////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.extend.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠١ شعبان ١٤٣٥ (May 30, 2014)
 */
@MappedSuperclass
public abstract class AbstractAlKhwarizmixDomainObjectExtendable extends
		AbstractAlKhwarizmixDomainObject implements Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 3750062939850083719L;

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public AbstractAlKhwarizmixDomainObjectExtendable() {
		super();
	}

	protected AbstractAlKhwarizmixDomainObjectExtendable(
			AbstractAlKhwarizmixDomainObjectExtendable other) {
		super(other);
		if (other != null) {
			this.extendedData = (ExtendedData) ObjectUtils
					.clone(other.extendedData);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@OneToOne(targetEntity = ExtendedData.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "extendedData", nullable = true)
	private ExtendedData extendedData;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, extendedData);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		boolean result = super.equals(other)
				&& (getObjectAsThisClass(other) != null)
				&& ObjectUtils.equals(this.extendedData,
						getObjectAsThisClass(other).extendedData);
		return result;
	}

	private AbstractAlKhwarizmixDomainObjectExtendable getObjectAsThisClass(
			Object other) {
		return (other instanceof AbstractAlKhwarizmixDomainObjectExtendable)
				? (AbstractAlKhwarizmixDomainObjectExtendable) other
				: null;
	}

	/**
	 */
	@Override
	public List<AbstractAlKhwarizmixDomainObject> getDaoObjectList() {

		List<AbstractAlKhwarizmixDomainObject> result = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		if (extendedData != null)
			result.addAll(extendedData.getDaoObjectList());
		result.add(this);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// extendedDataValue
	// ----------------------------------

	@XmlTransient
	public String getExtendedDataValue() {
		String result = "";
		if (extendedData != null)
			result = extendedData.getExtendedDataValue();
		return result;
	}

	public void setExtendedDataValue(String value) {
		if (extendedData == null)
			extendedData = new ExtendedData();
		extendedData.setExtendedDataValue(value);
	}

	// ----------------------------------
	// extendedData
	// ----------------------------------

	@XmlTransient
	public ExtendedData getExtendedData() {
		return extendedData;
	}

	public void setExtendedData(ExtendedData value) {
		this.extendedData = value;
	}

} // Class
