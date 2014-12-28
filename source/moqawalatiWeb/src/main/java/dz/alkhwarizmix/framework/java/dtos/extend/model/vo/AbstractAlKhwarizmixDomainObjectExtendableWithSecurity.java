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
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Encryption;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Group;

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
			this.owner1 = (AlKhwarizmixDomainObject) ObjectUtils
					.clone(other.owner1);
			this.group1 = (Group) ObjectUtils.clone(other.group1);
			this.encryption1 = (Encryption) ObjectUtils.clone(other.encryption1);
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
	private AlKhwarizmixDomainObject owner1;

	@ManyToOne(targetEntity = Group.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	// gruop is not a typo, the word "group" is not allowed as a Table field
	@JoinColumn(name = "gruop", nullable = true)
	private Group group1;

	@ManyToOne(targetEntity = Encryption.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "encryption", nullable = true)
	private Encryption encryption1;

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
		result = continueHashCode(result, group1);
		result = continueHashCode(result, owner1);
		result = continueHashCode(result, encryption1);
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
				&& ObjectUtils.equals(this.group1,
						getObjectAsThisClass(other).group1)
				&& ObjectUtils.equals(this.owner1,
						getObjectAsThisClass(other).owner1)
				&& ObjectUtils.equals(this.encryption1,
						getObjectAsThisClass(other).encryption1);
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

	public AlKhwarizmixDomainObject getOwner1() {
		return owner1;
	}

	public void setOwner1(AlKhwarizmixDomainObject value) {
		this.owner1 = value;
	}

	// ----------------------------------
	// group
	// ----------------------------------

	public Group getGroup1() {
		return group1;
	}

	public void setGroup1(Group value) {
		this.group1 = value;
	}

	// ----------------------------------
	// encryption
	// ----------------------------------

	public Encryption getEncryption1() {
		return encryption1;
	}

	public void setEncryption1(Encryption value) {
		this.encryption1 = value;
	}

} // Class