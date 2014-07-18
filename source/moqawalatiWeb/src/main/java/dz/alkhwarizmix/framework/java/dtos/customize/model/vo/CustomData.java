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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AlKhwarizmixDomainObjectExtendableWithSecurity;

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
public class CustomData extends AlKhwarizmixDomainObjectExtendableWithSecurity
		implements Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 3943183332329388549L;

	public static final String CUSTOMDATAID = "customDataId";
	public static final String CUSTOMIZER = "customizer.id";

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

	@ManyToOne(targetEntity = AlKhwarizmixDomainObject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "customizer", nullable = false)
	private AlKhwarizmixDomainObject customizer;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public List<AbstractAlKhwarizmixDomainObject> getDaoObjectList() {
		List<AbstractAlKhwarizmixDomainObject> result = super
				.getDaoObjectList();
		if (getCustomizer().getId() == null)
			result.add(0, getCustomizer());
		return result;
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(AbstractAlKhwarizmixDomainObject object) {
		// NOOP
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {

		final CustomData sourceCD = (CustomData) sourceObject;
		if (sourceCD != null
				&& getCustomDataId().equals(sourceCD.getCustomDataId())) {
			setCustomDataValue(sourceCD.getCustomDataValue());
		} else {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
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
		return getExtendedDataValue();
	}

	public void setCustomDataValue(String value) {
		setExtendedDataValue(value);
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