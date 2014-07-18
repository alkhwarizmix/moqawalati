////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2104 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.extend.model.vo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠١ شعبان ١٤٣٥ (May 30, 2014)
 */
@Entity
@Table(name = "TExtendedDataPart")
public class ExtendedDataPart extends AbstractAlKhwarizmixDomainObject
		implements Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 5790230324866753986L;

	public static final String EXTENDEDDATAPARTVALUE = "extendedDataPartValue";
	public static final String EXTENDEDDATA = "extendedData";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ExtendedDataPart() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "extendedData", nullable = false)
	private ExtendedData extendedData;

	@Column(name = "extendedDataPartValue", nullable = false, length = 127)
	private String extendedDataPartValue;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public void beforeDaoSaveOrUpdate(AbstractAlKhwarizmixDomainObject object) {
		// NOOP
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		ExtendedDataPart sourceEDP = (ExtendedDataPart) sourceObject;
		if (sourceEDP != null && getId().equals(sourceEDP.getId())) {
			if (sourceEDP.extendedDataPartValue != null) {
				extendedDataPartValue = sourceEDP.extendedDataPartValue;
			}
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
	// extendedData
	// ----------------------------------

	public void setExtendedData(ExtendedData value) {
		this.extendedData = value;
	}

	// ----------------------------------
	// extendedDataPartValue
	// ----------------------------------

	@XmlElement(name = "ExtendedDataPartValue")
	public String getExtendedDataPartValue() {
		return extendedDataPartValue;
	}

	public void setExtendedDataPartValue(String value) {
		this.extendedDataPartValue = value;
	}

} // Class