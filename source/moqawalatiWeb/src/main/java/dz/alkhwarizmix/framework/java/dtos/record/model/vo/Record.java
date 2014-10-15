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

package dz.alkhwarizmix.framework.java.dtos.record.model.vo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AbstractAlKhwarizmixDomainObjectExtendableWithSecurity;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ذو الحجة ١٤٣٥ (October 02, 2014)
 */
@Entity
@Table(name = "TRecord")
@XmlRootElement(name = "Record")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Record extends
		AbstractAlKhwarizmixDomainObjectExtendableWithSecurity implements
		Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = 3395819474101382304L;

	public static final String RECORDID = "recordId";

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public Record() {
		super();
	}

	public Record(String theRecordId) {
		super();
		setRecordId(theRecordId);
	}

	public Record(String theRecordId, String theSchemaName) {
		super();
		setRecordId(theRecordId);
		setSchemaName(theSchemaName);
	}

	public Record(String theRecordId, String theSchemaName, String theTableName) {
		super();
		setRecordId(theRecordId);
		setSchemaName(theSchemaName);
		setTableName(theTableName);
	}

	protected Record(Record other) {
		super(other);
		if (other != null) {
			this.recordId = (String) ObjectUtils.clone(other.recordId);
			this.parent = (Record) ObjectUtils.clone(other.parent);
			this.schemaName = (String) ObjectUtils.clone(other.schemaName);
			this.tableName = (String) ObjectUtils.clone(other.tableName);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Column(name = "recordId", unique = true, nullable = false, length = 63)
	private String recordId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "parent", nullable = true)
	private Record parent;

	@Transient
	private String schemaName;

	@Transient
	private String tableName;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new Record(this);
	}

	/**
	 */
	public String toString() {
		return super.toStringBuilder(this).append("recordId", recordId)
				.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = continueHashCode(result, recordId);
		result = continueHashCode(result, parent);
		result = continueHashCode(result, schemaName);
		result = continueHashCode(result, tableName);
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
				&& ObjectUtils.equals(this.recordId,
						getObjectAsThisClass(other).recordId)
				&& ObjectUtils.equals(this.parent,
						getObjectAsThisClass(other).parent)
				&& ObjectUtils.equals(this.parent,
						getObjectAsThisClass(other).parent)
				&& ObjectUtils.equals(this.schemaName,
						getObjectAsThisClass(other).schemaName)
				&& ObjectUtils.equals(this.tableName,
						getObjectAsThisClass(other).tableName);
		return result;
	}

	private Record getObjectAsThisClass(Object other) {
		return (other instanceof Record)
				? (Record) other
				: null;
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {

		final Record sourceRecord = (Record) sourceObject;
		if (sourceRecord != null
				&& getRecordId().equals(sourceRecord.getRecordId())) {
			if (sourceRecord.parent != null) {
				parent = sourceRecord.parent;
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

	public String getData() {
		String result = getExtendedDataValue();
		return (result == ""
				? null
				: result);
	}

	public void setData(String value) {
		setExtendedDataValue(value);
	}

	// ----------------------------------
	// recordId
	// ----------------------------------

	@XmlAttribute(name = "id")
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String value) {
		this.recordId = value;
	}

	// ----------------------------------
	// parent
	// ----------------------------------

	@XmlTransient
	public Record getParent() {
		return parent;
	}

	public void setParent(Record value) {
		this.parent = value;
	}

	// ----------------------------------
	// schemaName
	// ----------------------------------

	@XmlAttribute(name = "schema")
	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String value) {
		this.schemaName = value;
	}

	// ----------------------------------
	// tableName
	// ----------------------------------

	@XmlAttribute(name = "table")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String value) {
		this.tableName = value;
	}

} // Class
