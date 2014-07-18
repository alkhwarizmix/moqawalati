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
public abstract class AbstractAlKhwarizmixDomainObject implements Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 6486393269968267555L;

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
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public AbstractAlKhwarizmixDomainObject() {
		created = new Date();
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

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

	// @XmlTransient
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