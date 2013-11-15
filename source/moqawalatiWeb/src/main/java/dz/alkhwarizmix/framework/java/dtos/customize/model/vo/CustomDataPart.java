////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.customize.model.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٧ محرم ١٤٣٥ (November 11, 2013)
 */
@Entity
@Table(name = "TCustomDataPart")
@XmlRootElement(name = "CustomDataPart")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomDataPart extends AlKhwarizmixDomainObject implements
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 8120336811515639394L;

	public static final String CUSTOMDATAPARTVALUE = "customDataPartValue";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public CustomDataPart() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@ManyToOne
	@JoinColumn(name = "customData")
	private CustomData customData;

	@Column(name = "customDataPartValue", nullable = false, length = 127)
	private String customDataPartValue;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		CustomDataPart sourceCustomDataPart = (CustomDataPart) sourceObject;
		if ((sourceCustomDataPart != null)
				&& (this.getId().equals(sourceCustomDataPart.getId()))) {
			if (sourceCustomDataPart.customDataPartValue != null) {
				this.customDataPartValue = sourceCustomDataPart.customDataPartValue;
			}
		} else {
			throw new MoqawalatiException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// customData
	// ----------------------------------

	public void setCustomData(CustomData value) {
		this.customData = value;
	}

	// ----------------------------------
	// customDataPartValue
	// ----------------------------------

	@XmlElement(name = "CustomDataPartValue")
	public String getCustomDataPartValue() {
		return customDataPartValue;
	}

	public void setCustomDataPartValue(String value) {
		this.customDataPartValue = value;
	}

} // Class