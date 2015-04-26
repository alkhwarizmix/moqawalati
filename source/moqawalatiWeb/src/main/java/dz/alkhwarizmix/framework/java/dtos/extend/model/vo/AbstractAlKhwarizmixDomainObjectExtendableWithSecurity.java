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

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
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

	public static final String OWNER_ID = "owner.id";

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public AbstractAlKhwarizmixDomainObjectExtendableWithSecurity() {
		super();
	}

	protected AbstractAlKhwarizmixDomainObjectExtendableWithSecurity(
			final AbstractAlKhwarizmixDomainObjectExtendableWithSecurity other) {
		super(other);
		if (other != null) {
			owner = (AlKhwarizmixDomainObject) ObjectUtils.clone(other.owner);
			group = (Group) ObjectUtils.clone(other.group);
			encryption = (Encryption) ObjectUtils.clone(other.encryption);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@ManyToOne(targetEntity = AlKhwarizmixDomainObject.class, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fOwner", nullable = true)
	private AlKhwarizmixDomainObject owner;

	@ManyToOne(targetEntity = Group.class, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fGroup", nullable = true)
	private Group group;

	@ManyToOne(targetEntity = Encryption.class, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fEncryption", nullable = true)
	private Encryption encryption;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder().append("owner", owner);
	}

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
	public boolean equals(final Object other) {
		final boolean result = super.equals(other)
				&& (getObjectAsThisClass(other) != null)
				&& ObjectUtils.equals(group, getObjectAsThisClass(other).group)
				&& ObjectUtils.equals(owner, getObjectAsThisClass(other).owner)
				&& ObjectUtils.equals(encryption,
						getObjectAsThisClass(other).encryption);
		return result;
	}

	private AbstractAlKhwarizmixDomainObjectExtendableWithSecurity getObjectAsThisClass(
			final Object other) {
		return (other instanceof AbstractAlKhwarizmixDomainObjectExtendableWithSecurity)
				? (AbstractAlKhwarizmixDomainObjectExtendableWithSecurity) other
				: null;
	}

	/**
	 */
	@Override
	public List<AbstractAlKhwarizmixDomainObject> getDaoObjectList() {
		final List<AbstractAlKhwarizmixDomainObject> result = super
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

	public final AlKhwarizmixDomainObject getOwner() {
		return owner;
	}

	public final void setOwner(final AlKhwarizmixDomainObject value) {
		owner = value;
	}

	// ----------------------------------
	// group
	// ----------------------------------

	public final Group getGroup() {
		return group;
	}

	public final void setGroup(final Group value) {
		group = value;
	}

	// ----------------------------------
	// encryption
	// ----------------------------------

	public final Encryption getEncryption() {
		return encryption;
	}

	public final void setEncryption(final Encryption value) {
		encryption = value;
	}

} // Class
