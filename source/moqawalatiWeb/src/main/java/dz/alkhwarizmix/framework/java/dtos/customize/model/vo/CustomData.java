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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
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
@Table(name = "TCustomData")
@XmlRootElement(name = "CustomData")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomData extends AlKhwarizmixDomainObject implements
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 3943183332329388549L;

	public static final String CUSTOMDATAID = ID;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public CustomData() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private List<CustomDataPart> customDataParts = null;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		CustomData sourceCustomData = (CustomData) sourceObject;
		if ((sourceCustomData != null)
				&& (this.getId().equals(sourceCustomData.getId()))) {
			if (sourceCustomData.customDataParts != null) {
				this.setCustomDataValue(sourceCustomData.getCustomDataValue());
			}
		} else {
			throw new MoqawalatiException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	/**
	 */
	// public void setupAddress() {
	// createFakeAddress();
	// }

	/**
	 */
	// private void createFakeAddress() {
	// CustomDataPart newAddress = new CustomDataPart();
	// newAddress.setCreatorId(getCreatorId());
	// newAddress.setAddressId(String.valueOf(Math.random()));
	// newAddress.setStreet(new Date().toString());
	// setAddress(newAddress);
	// }

	private CustomDataPart newCustomDataPart() {
		CustomDataPart result = new CustomDataPart();
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// customDataId
	// ----------------------------------

	public Long getCustomDataId() {
		return getId();
	}

	public void setCustomDataId(Long value) {
		setId(value);
	}

	// ----------------------------------
	// customDataValue
	// ----------------------------------

	@XmlElement(name = "CustomDataValue")
	public String getCustomDataValue() {
		String customDataValue = "";
		for (CustomDataPart customDataPart : getCustomDataParts()) {
			customDataValue += customDataPart.getCustomDataPartValue();
		}
		return customDataValue;
	}

	public void setCustomDataValue(String value) {
		int customDataPartValueMaxLen = 127;
		CustomDataPart customDataPart = null;
		int startIndex = 0;
		int i = 0;
		for (; startIndex < value.length(); i++) {
			if (getCustomDataParts().size() > i) {
				customDataPart = getCustomDataParts().get(i);
			} else {
				customDataPart = newCustomDataPart();
				getCustomDataParts().add(customDataPart);
			}
			int endIndex = Math.min(startIndex + customDataPartValueMaxLen,
					value.length());
			customDataPart.setCustomDataPartValue(value.substring(startIndex,
					endIndex));
			startIndex = endIndex;
		}

		for (; i < getCustomDataParts().size(); i++) {
			customDataPart = getCustomDataParts().get(i);
			customDataPart.setCustomDataPartValue("");
		}
	}

	// ----------------------------------
	// customDataParts
	// ----------------------------------

	public List<CustomDataPart> getCustomDataParts() {
		if (customDataParts == null)
			customDataParts = new ArrayList<CustomDataPart>();
		return customDataParts;
	}

} // Class