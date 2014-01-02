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

package dz.alkhwarizmix.framework.java.dtos.domain.model.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ محرم ١٤٣٥ (November 16, 2013)
 */
@Entity
@Table(name = "TAlKhwarizmixDomainObject")
@XmlRootElement(name = "AlKhwarizmixDomainObject")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AlKhwarizmixDomainObject extends AlKhwarizmixDomainObjectAbstract
		implements Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 2895852789900643357L;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public AlKhwarizmixDomainObject() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		AlKhwarizmixDomainObject domainObject = (AlKhwarizmixDomainObject) sourceObject;
		if ((domainObject != null)
				&& (this.getId().equals(domainObject.getId()))) {
			// NOOP
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

	// EMPTY

} // Class