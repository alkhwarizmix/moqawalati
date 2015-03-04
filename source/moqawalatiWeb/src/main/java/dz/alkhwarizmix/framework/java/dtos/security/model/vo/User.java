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

package dz.alkhwarizmix.framework.java.dtos.security.model.vo;

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

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;

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
public class User extends AbstractAlKhwarizmixDomainObject implements
		Serializable, Cloneable {

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
		this();
		setUserId(theUserId);
	}

	/**
	 * constructor
	 */
	public User(String theUserId, String theName) {
		this(theUserId);
		setName(theName);
	}

	/**
	 * constructor
	 */
	protected User(User other) {
		super(other);
		if (other != null) {
			this.userId = other.userId;
			this.name = other.name;
			this.group = (Group) ObjectUtils.clone(other.group);
			this.domainObject = (AlKhwarizmixDomainObject) ObjectUtils
					.clone(other.domainObject);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Email
	@Column(name = "fUserId", unique = true, nullable = false, length = 63)
	private String userId;

	@Column(name = "fName", nullable = false, length = 127)
	private String name;

	@ManyToOne(targetEntity = Group.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fGroup", nullable = true)
	private Group group;

	@ManyToOne(targetEntity = AlKhwarizmixDomainObject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fDomainObject", nullable = false)
	private AlKhwarizmixDomainObject domainObject;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new User(this);
	}

	/**
	 */
	public String toString() {
		return super.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, userId);
		result = continueHashCode(result, name);
		result = continueHashCode(result, group);
		result = continueHashCode(result, domainObject);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		boolean result = super.equals(other)
				&& (getObjectAsThisClass(other) != null)
				&& ObjectUtils.equals(this.userId,
						getObjectAsThisClass(other).userId)
				&& ObjectUtils.equals(this.name,
						getObjectAsThisClass(other).name)
				&& ObjectUtils.equals(this.group,
						getObjectAsThisClass(other).group)
				&& ObjectUtils.equals(this.domainObject,
						getObjectAsThisClass(other).domainObject);
		return result;
	}

	/**
	 */
	private User getObjectAsThisClass(Object other) {
		return (other instanceof User)
				? (User) other
				: null;
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

	static {
		ignoreBlazeDSProperty(User.class, "id");
	}

	// ----------------------------------
	// userId
	// ----------------------------------
	//
	@XmlAttribute(name = "id")
	public String getUserId() {
		if (userId == null)
			userId = "";
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
	// group
	// ----------------------------------

	@XmlElement(name = "Group")
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group value) {
		this.group = value;
	}

	// ----------------------------------
	// domainObject
	// ----------------------------------

	static {
		ignoreBlazeDSProperty(User.class, "domainObject");
	}

	@XmlTransient
	public AlKhwarizmixDomainObject getDomainObject() {
		return domainObject;
	}

	public void setDomainObject(AlKhwarizmixDomainObject value) {
		this.domainObject = value;
	}

} // Class
