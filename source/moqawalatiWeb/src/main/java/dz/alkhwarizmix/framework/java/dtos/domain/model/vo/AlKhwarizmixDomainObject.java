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
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

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
public class AlKhwarizmixDomainObject extends AbstractAlKhwarizmixDomainObject
		implements Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 2895852789900643357L;

	public static final String CREATORID = "creatorId";

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public AlKhwarizmixDomainObject() {
		super();
	}

	protected AlKhwarizmixDomainObject(Long theId, Integer theVersion,
			Date theCreated, Date theModified) {
		super(theId, theVersion, theCreated, theModified);
	}

	protected AlKhwarizmixDomainObject(AlKhwarizmixDomainObject other) {
		super(other);
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
	@Override
	public Object clone() {
		return new AlKhwarizmixDomainObject(this);
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
		AlKhwarizmixDomainObject domObj = (AlKhwarizmixDomainObject) sourceObject;
		if (domObj != null && getId().equals(domObj.getId())) {
			// NOOP
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

	// EMPTY

} // Class
