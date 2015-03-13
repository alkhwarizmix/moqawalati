////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.email.model.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AbstractAlKhwarizmixDomainObjectExtendable;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٧ ربيع الثاني ١٤٣٦ (January 27, 2015)
 */
@Entity
@Table(name = "TEMail")
public class EMail extends AbstractAlKhwarizmixDomainObjectExtendable implements
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -5567357610371705150L;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public EMail() {
		super();
	}

	/**
	 * constructor
	 */
	protected EMail(final EMail other) {
		super(other);
		if (other != null) {
			sender = (User) ObjectUtils.clone(other.sender);
			receiver = (User) ObjectUtils.clone(other.receiver);
			sentAt = (Date) ObjectUtils.clone(other.sentAt);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fSender", nullable = false, updatable = false)
	private User sender;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fReceiver", nullable = false, updatable = false)
	private User receiver;

	@Column(name = "fSendAt", nullable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentAt;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new EMail(this);
	}

	/**
	 */
	@Override
	protected ToStringBuilder toStringBuilder() {
		return super.toStringBuilder().append("sentAt", sentAt)
				.append("sender", sender).append("receiver", receiver);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, sender);
		result = continueHashCode(result, receiver);
		result = continueHashCode(result, sentAt);
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
				&& ObjectUtils.equals(sender,
						getObjectAsThisClass(other).sender)
						&& ObjectUtils.equals(receiver,
								getObjectAsThisClass(other).receiver)
								&& ObjectUtils.equals(sentAt,
										getObjectAsThisClass(other).sentAt);
		return result;
	}

	private EMail getObjectAsThisClass(final Object other) {
		return (other instanceof EMail)
				? (EMail) other
						: null;
	}

	/**
	 */
	@Override
	public void updateFrom(final Object sourceObject) throws AlKhwarizmixException {
		final EMail sourceEMail = (EMail) sourceObject;
		if (sourceEMail != null) {
			// NOOP
		} else {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(final AbstractAlKhwarizmixDomainObject object) {
		// NOOP
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// data
	// ----------------------------------

	public final String getBody() {
		final String result = getExtendedDataValue();
		return (result == ""
				? null
						: result);
	}

	public final void setBody(final String value) {
		setExtendedDataValue(value);
	}

	// ----------------------------------
	// sender
	// ----------------------------------

	public final User getSender() {
		return sender;
	}

	public final void setSender(final User value) {
		sender = value;
	}

	// ----------------------------------
	// receiver
	// ----------------------------------

	public final User getReceiver() {
		return receiver;
	}

	public final void setReceiver(final User value) {
		receiver = value;
	}

	// ----------------------------------
	// sentAt
	// ----------------------------------

	public final Date getSentAt() {
		return sentAt;
	}

	public final void setSentAt(final Date value) {
		sentAt = value;
	}

} // Class
