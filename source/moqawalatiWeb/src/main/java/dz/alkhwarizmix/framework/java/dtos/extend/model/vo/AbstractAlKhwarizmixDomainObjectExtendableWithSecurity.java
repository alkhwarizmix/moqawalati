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

package dz.alkhwarizmix.framework.java.dtos.extend.model.vo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
@MappedSuperclass
public abstract class AbstractAlKhwarizmixDomainObjectExtendableWithSecurity
		extends AbstractAlKhwarizmixDomainObjectExtendable implements
		Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 5808134939227056239L;

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public AbstractAlKhwarizmixDomainObjectExtendableWithSecurity() {
		super();
	}

	protected AbstractAlKhwarizmixDomainObjectExtendableWithSecurity(
			AbstractAlKhwarizmixDomainObjectExtendableWithSecurity other) {
		super(other);
		if (other != null) {
			this.owner = (AlKhwarizmixDomainObject) ObjectUtils
					.clone(other.owner);
			this.group = (AlKhwarizmixDomainObject) ObjectUtils
					.clone(other.group);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@ManyToOne(targetEntity = AlKhwarizmixDomainObject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "owner", nullable = true)
	private AlKhwarizmixDomainObject owner;

	@ManyToOne(targetEntity = AlKhwarizmixDomainObject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	// gruop is not a typo, the word "group" is not allowed as a Table field
	@JoinColumn(name = "gruop", nullable = true)
	private AlKhwarizmixDomainObject group;

	@ManyToOne(targetEntity = AlKhwarizmixDomainObject.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "encryption", nullable = true)
	private AlKhwarizmixDomainObject encryption;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, group);
		result = continueHashCode(result, owner);
		result = continueHashCode(result, encryption);
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
				&& ObjectUtils.equals(this.group,
						getObjectAsThisClass(other).group)
				&& ObjectUtils.equals(this.owner,
						getObjectAsThisClass(other).owner)
				&& ObjectUtils.equals(this.encryption,
						getObjectAsThisClass(other).encryption);
		return result;
	}

	private AbstractAlKhwarizmixDomainObjectExtendableWithSecurity getObjectAsThisClass(
			Object other) {
		return (other instanceof AbstractAlKhwarizmixDomainObjectExtendableWithSecurity)
				? (AbstractAlKhwarizmixDomainObjectExtendableWithSecurity) other
				: null;
	}

	/**
	 */
	@Override
	public List<AbstractAlKhwarizmixDomainObject> getDaoObjectList() {

		List<AbstractAlKhwarizmixDomainObject> result = super
				.getDaoObjectList();
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// owner
	// ----------------------------------

	public AlKhwarizmixDomainObject getOwner() {
		return owner;
	}

	public void setOwner(AlKhwarizmixDomainObject value) {
		this.owner = value;
	}

	// ----------------------------------
	// group
	// ----------------------------------

	public AlKhwarizmixDomainObject getGroup() {
		return group;
	}

	public void setGroup(AlKhwarizmixDomainObject value) {
		this.group = value;
	}

	// ----------------------------------
	// encryption
	// ----------------------------------

	public AlKhwarizmixDomainObject getEncryption() {
		return encryption;
	}

	public void setEncryption(AlKhwarizmixDomainObject value) {
		this.encryption = value;
	}

} // Class
