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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
 * @since ١٦ شعبان ١٤٣٥ (June 14, 2014)
 */
@Entity
@Table(name = "TGroup")
@XmlRootElement(name = "Group")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Group extends AbstractAlKhwarizmixDomainObject implements
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

	/**
	 * constructor
	 */
	protected Group(Group other) {
		super(other);
		if (other != null) {
			this.groupId = other.groupId;
			this.domainObject = (AlKhwarizmixDomainObject) ObjectUtils
					.clone(other.domainObject);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Column(name = "fGroupId", unique = true, nullable = false, length = 63)
	private String groupId;

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
	public String toString() {
		return super.toStringBuilder(this).append("groupId", groupId)
				.toString();
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {

		final Group sourceGroup = (Group) sourceObject;
		if (sourceGroup != null
				&& this.getGroupId().equals(sourceGroup.getGroupId())) {
			// NOOP
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
		ignoreBlazeDSProperty(Group.class, "id");
	}

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
