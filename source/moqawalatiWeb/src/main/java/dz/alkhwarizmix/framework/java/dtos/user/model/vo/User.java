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

package dz.alkhwarizmix.framework.java.dtos.user.model.vo;

import java.io.Serializable;

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
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AbstractAlKhwarizmixDomainObjectExtendable;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
@Entity
@Table(name = "TUser")
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class User extends AbstractAlKhwarizmixDomainObjectExtendable implements
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 3626775497823357809L;

	public static final String USERID = "userId";
	public static final String NAME = "name";

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public User() {
		super();
	}

	/**
	 * constructor
	 */
	public User(String theUserId) {
		super();
		setUserId(theUserId);
	}

	/**
	 * constructor
	 */
	public User(String theUserId, String theName) {
		super();
		setUserId(theUserId);
		setName(theName);
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Column(name = "userId", unique = true, nullable = false, length = 63)
	private String userId;

	@Column(name = "name", nullable = false, length = 127)
	private String name;

	@ManyToOne(targetEntity = Group.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "theGroup", nullable = true)
	private Group group;

	@ManyToOne(targetEntity = AlKhwarizmixDomainObject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "domainObject", nullable = false)
	private AlKhwarizmixDomainObject domainObject;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	public String toString() {
		return super.toStringBuilder(this).append("userId", userId).toString();
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {
		User sourceUser = (User) sourceObject;
		if (sourceUser != null && getUserId().equals(sourceUser.getUserId())) {
			if (sourceUser.name != null) {
				this.name = sourceUser.name;
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
		if (domainObject == null)
			domainObject = new AlKhwarizmixDomainObject();
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// domainObject
	// ----------------------------------

	@XmlTransient
	public AlKhwarizmixDomainObject getDomainObject() {
		return domainObject;
	}

	public void setDomainObject(AlKhwarizmixDomainObject value) {
		this.domainObject = value;
	}

	// ----------------------------------
	// userId
	// ----------------------------------
	//
	@XmlAttribute(name = "id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String value) {
		this.userId = value;
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

	// ----------------------------------
	// extendedData
	// ----------------------------------

	@XmlElement(name = "Group")
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group value) {
		this.group = value;
	}

} // Class
