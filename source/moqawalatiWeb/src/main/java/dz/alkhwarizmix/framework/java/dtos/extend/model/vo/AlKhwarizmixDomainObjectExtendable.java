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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠١ شعبان ١٤٣٥ (May 30, 2014)
 */
@MappedSuperclass
public abstract class AlKhwarizmixDomainObjectExtendable extends
		AlKhwarizmixDomainObjectAbstract implements Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 3750062939850083719L;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public AlKhwarizmixDomainObjectExtendable() {
		super();
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

	/**
	 */
	@Override
	public List<AlKhwarizmixDomainObjectAbstract> getDaoObjectList() {

		List<AlKhwarizmixDomainObjectAbstract> result = new ArrayList<AlKhwarizmixDomainObjectAbstract>();
		result.add(this);
		if (extendedData != null)
			result.addAll(extendedData.getDaoObjectList());
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