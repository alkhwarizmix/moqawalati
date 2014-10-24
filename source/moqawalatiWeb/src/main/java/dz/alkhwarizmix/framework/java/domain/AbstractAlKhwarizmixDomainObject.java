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
	// Properties
	//
	// --------------------------------------------------------------------------

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	@Column(nullable = false)
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
	// Constructors
	//
	// --------------------------------------------------------------------------

	public AbstractAlKhwarizmixDomainObject() {
		created = new Date();
	}

	protected AbstractAlKhwarizmixDomainObject(
			AbstractAlKhwarizmixDomainObject other) {
		if (other != null) {
			this.id = (Long) ObjectUtils.clone(other.id);
			this.version = (Integer) ObjectUtils.clone(other.version);
			this.created = (Date) ObjectUtils.clone(other.created);
			this.modified = (Date) ObjectUtils.clone(other.modified);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public String toString() {
		return toStringBuilder(this).toString();
	}

	/**
	 */
	protected ToStringBuilder toStringBuilder(Object obj) {
		return new ToStringBuilder(obj).append("id", id)
				.append("version", version).append("created", created)
				.append("last modified", modified);
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

	protected final int continueHashCode(int result, Object field) {
		return PRIME * result + ObjectUtils.hashCode(field);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		boolean result = true;
		if (this == other) {
			result = true;
		} else if (other == null) {
			result = false;
		} else if (getObjectAsThisClass(other) == null) {
			result = false;
		} else {
			result = ObjectUtils.equals(this.created,
					getObjectAsThisClass(other).created)
					&& ObjectUtils.equals(this.id,
							getObjectAsThisClass(other).id)
					&& ObjectUtils.equals(this.modified,
							getObjectAsThisClass(other).modified)
					&& ObjectUtils.equals(this.version,
							getObjectAsThisClass(other).version);
		}
		return result;
	}

	private AbstractAlKhwarizmixDomainObject getObjectAsThisClass(Object other) {
		return (other instanceof AbstractAlKhwarizmixDomainObject)
				? (AbstractAlKhwarizmixDomainObject) other
				: null;
	}

	/**
	 */
	public List<AbstractAlKhwarizmixDomainObject> getDaoObjectList() {
		List<AbstractAlKhwarizmixDomainObject> result = new ArrayList<AbstractAlKhwarizmixDomainObject>();
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

	public void setId(Long value) {
		id = value;
	}

	// ----------------------------------
	// version
	// ----------------------------------

	public Integer getVersion() {
		return version;
	}

	// ----------------------------------
	// created
	// ----------------------------------

	public Date getCreated() {
		return created;
	}

	// ----------------------------------
	// modified
	// ----------------------------------

	public Date getModified() {
		return modified;
	}

} // Class
