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

package dz.alkhwarizmix.moqawalati.model.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;

import dz.alkhwarizmix.framework.EntityInterceptor;
import dz.alkhwarizmix.framework.domain.AlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
@MappedSuperclass
@EntityListeners(EntityInterceptor.class)
public abstract class MoqawalatiDomainObject extends AlKhwarizmixDomainObject
		implements Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -5201499942396846288L;

	public static final String CREATORID = "creatorId";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public MoqawalatiDomainObject() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Column(name = "creatorId", nullable = false, updatable = false, length = 63)
	private String creatorId;

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// creatorId
	// ----------------------------------

	@XmlTransient
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String value) {
		creatorId = value;
	}

} // Class