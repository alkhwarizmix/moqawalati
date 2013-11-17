////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
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
public class CustomData extends AlKhwarizmixDomainObjectAbstract implements
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 3943183332329388549L;

	public static final String CUSTOMDATAID = "customDataId";

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

	@Column(name = "customDataId", unique = false, nullable = false, length = 63)
	private String customDataId;

	@ManyToOne
	@JoinColumn(name = "customizer", nullable = false)
	private AlKhwarizmixDomainObject customizer;

	@OneToMany(mappedBy = "customData", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CustomDataPart> customDataParts = null;

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
		if (getCustomizer() == null) {
			setupFakeCustomizer();
			result.add(getCustomizer());
		}
		result.add(this);
		result.addAll(getCustomDataParts());
		return result;
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(AlKhwarizmixDomainObjectAbstract object) {
		// NOOP
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		CustomData sourceCustomData = (CustomData) sourceObject;
		if ((sourceCustomData != null)
				&& (this.getCustomDataId().equals(sourceCustomData
						.getCustomDataId()))) {
			if (sourceCustomData.customDataParts != null) {
				this.setCustomDataValue(sourceCustomData.getCustomDataValue());
			}
			this.setCustomizer(sourceCustomData.getCustomizer());
		} else {
			throw new MoqawalatiException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	/**
	 */
	public void setupFakeCustomizer() {
		setCustomizer(new AlKhwarizmixDomainObject());
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// customDataId
	// ----------------------------------

	@XmlAttribute(name = "id")
	public String getCustomDataId() {
		return customDataId;
	}

	public void setCustomDataId(String value) {
		this.customDataId = value;
	}

	// ----------------------------------
	// customDataValue
	// ----------------------------------

	@XmlElement(name = "Value")
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
				addCustomDataPart(customDataPart);
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

	private CustomDataPart newCustomDataPart() {
		CustomDataPart result = new CustomDataPart();
		return result;
	}

	private void addCustomDataPart(CustomDataPart customDataPart) {
		customDataPart.setCustomData(this);
		getCustomDataParts().add(customDataPart);
	}

	// ----------------------------------
	// customDataParts
	// ----------------------------------

	@XmlTransient
	public List<CustomDataPart> getCustomDataParts() {
		if (customDataParts == null)
			customDataParts = new ArrayList<CustomDataPart>();
		return customDataParts;
	}

	public void setCustomDataParts(List<CustomDataPart> value) {
		customDataParts = value;
	}

	// ----------------------------------
	// customizer
	// ----------------------------------

	@XmlTransient
	public AlKhwarizmixDomainObject getCustomizer() {
		return customizer;
	}

	public void setCustomizer(AlKhwarizmixDomainObject value) {
		this.customizer = value;
	}

} // Class