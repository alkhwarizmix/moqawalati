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

package dz.alkhwarizmix.framework.java.dtos.user.model.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AlKhwarizmixDomainObjectExtendable;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٦ شعبان ١٤٣٥ (June 14, 2014)
 */
@Entity
@Table(name = "TGroup")
@XmlRootElement(name = "Group")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Group extends AlKhwarizmixDomainObjectExtendable implements
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -1990125451172697281L;

	public static final String GROUPID = "groupId";
	public static final String NAME = "name";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public Group() {
		super();
	}

	/**
	 * constructor
	 */
	public Group(String theGroupId) {
		super();
		setGroupId(theGroupId);
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Column(name = "groupId", unique = true, nullable = false, length = 63)
	private String groupId;

	@Transient
	private String name;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public String toString() {
		return super.toStringBuilder(this).append("groupId", groupId)
				.toString();
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		Group sourceGroup = (Group) sourceObject;
		if ((sourceGroup != null)
				&& (this.getGroupId().equals(sourceGroup.getGroupId()))) {
			if (sourceGroup.name != null) {
				this.name = sourceGroup.name;
			}
		} else {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(AbstractAlKhwarizmixDomainObject object) {
		// NOOP
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// groupId
	// ----------------------------------

	@XmlAttribute(name = "id")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String value) {
		this.groupId = value;
	}

	// ----------------------------------
	// name
	// ----------------------------------

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

} // Class