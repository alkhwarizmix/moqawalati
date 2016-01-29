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

package dz.alkhwarizmix.framework.java.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.EntityInterceptor;
import dz.alkhwarizmix.framework.java.utils.DateUtil;
import dz.alkhwarizmix.framework.java.utils.IJSONUtil;

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
public abstract class AbstractAlKhwarizmixDomainObject implements Serializable,
		Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 6486393269968267555L;

	public static final int PRIME = 31;
	public static final String ID = "id";
	public static final String CREATED = "created";
	public static final String VERSION = "version";
	public static final String MODIFIED = "modified";

	// --------------------------------------------------------------------------
	//
	// Static Variables
	//
	// --------------------------------------------------------------------------

	static public DateUtil dateUtil = null;

	// --------------------------------------------------------------------------
	//
	// Static methods
	//
	// --------------------------------------------------------------------------

	protected static void ignoreBlazeDSProperties(final Class<?> clazz,
			final String[] properties) {
		for (final String property : properties)
			ignoreBlazeDSProperty(clazz, property);
	}

	protected static void ignoreBlazeDSProperty(final Class<?> clazz,
			final String property) {
		flex.messaging.io.BeanProxy.addIgnoreProperty(clazz, property);
	}

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public AbstractAlKhwarizmixDomainObject() {
		if (dateUtil == null)
			dateUtil = new DateUtil();
		created = dateUtil.newDate();
	}

	protected AbstractAlKhwarizmixDomainObject(final Long theId,
			final Integer theVersion, final Date theCreated,
			final Date theModified) {
		id = theId;
		version = theVersion;
		created = theCreated;
		modified = theModified;
	}

	protected AbstractAlKhwarizmixDomainObject(
			final AbstractAlKhwarizmixDomainObject other) {
		if (other != null) {
			id = other.id;
			version = other.version;
			created = (Date) ObjectUtils.clone(other.created);
			modified = (Date) ObjectUtils.clone(other.modified);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	@Column(name = "version", nullable = false)
	private Integer version;

	@Column(name = "created", nullable = false, updatable = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "modified", nullable = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	@Override
	abstract public Object clone();

	/**
	 */
	@Override
	public String toString() {
		return toStringBuilder().toString();
	}

	/**
	 */
	protected ToStringBuilder toStringBuilder() {
		return new ToStringBuilder(this).append("id", id)
				.append("version", version).append("created", created)
				.append("modified", modified);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 1;
		result = continueHashCode(result, created);
		result = continueHashCode(result, id);
		result = continueHashCode(result, modified);
		result = continueHashCode(result, version);
		return result;
	}

	protected final int continueHashCode(final int result, final Object field) {
		return (PRIME * result) + ObjectUtils.hashCode(field);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		boolean result = true;
		if (this == other)
			result = true;
		else if (other == null)
			result = false;
		else if (getObjectAsThisClass(other) == null)
			result = false;
		else
			result = ObjectUtils.equals(created,
					getObjectAsThisClass(other).created)
					&& ObjectUtils.equals(id, getObjectAsThisClass(other).id)
					&& ObjectUtils.equals(modified,
							getObjectAsThisClass(other).modified)
					&& ObjectUtils.equals(version,
							getObjectAsThisClass(other).version);
		return result;
	}

	private AbstractAlKhwarizmixDomainObject getObjectAsThisClass(
			final Object other) {
		return (other instanceof AbstractAlKhwarizmixDomainObject)
				? (AbstractAlKhwarizmixDomainObject) other
				: null;
	}

	/**
	 */
	public List<AbstractAlKhwarizmixDomainObject> getDaoObjectList() {
		final List<AbstractAlKhwarizmixDomainObject> result = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		result.add(this);
		return result;
	}

	/**
	 */
	public abstract void beforeDaoSaveOrUpdate(
			AbstractAlKhwarizmixDomainObject object);

	/**
	 */
	public abstract void updateFrom(Object sourceObject)
			throws AlKhwarizmixException;

	/**
	 * @throws AlKhwarizmixException
	 */
	public String toJson(final IJSONUtil jsonUtil) throws AlKhwarizmixException {
		return jsonUtil.marshalObjectToJSON((IAlKhwarizmixJsonObject) clone());
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// id
	// ----------------------------------

	@XmlTransient
	public Long getId() {
		return id;
	}

	public void setId(final Long value) {
		id = value;
	}

	// ----------------------------------
	// version
	// ----------------------------------

	public final Integer getVersion() {
		return version;
	}

	// ----------------------------------
	// created
	// ----------------------------------

	public final Date getCreated() {
		return created;
	}

	// ----------------------------------
	// modified
	// ----------------------------------

	public final Date getModified() {
		return modified;
	}

} // Class
