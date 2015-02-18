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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
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
@Table(name = "TRecord", uniqueConstraints = @UniqueConstraint(columnNames = {
		"fRecordId", "fParent" }))
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

	public static final int INSERT_ACTION = 1;
	public static final int UPDATE_ACTION = 2;
	public static final int DELETE_ACTION = 3;

	public static final String RECORDID = "recordId";
	public static final String PARENT_ID = "parent.id";

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public Record() {
		super();
	}

	public Record(String theRecordId) {
		this();
		setRecordId(theRecordId);
	}

	public Record(String theRecordId, String theSchemaName) {
		this(theRecordId);
		setSchemaName(theSchemaName);
	}

	public Record(String theRecordId, String theSchemaName, String theTableName) {
		this(theRecordId, theSchemaName);
		setTableName(theTableName);
	}

	protected Record(Record other) {
		super(other);
		if (other != null) {
			this.recordId = other.recordId;
			this.parent = (Record) ObjectUtils.clone(other.parent);
			this.schemaName = other.schemaName;
			this.tableName = other.tableName;
		}
	}

	/**
	 */
	@Override
	public List<AbstractAlKhwarizmixDomainObject> getDaoObjectList() {
		List<AbstractAlKhwarizmixDomainObject> result = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		if (getParent() != null)
			result.addAll(getParent().getDaoObjectList());
		result.addAll(super.getDaoObjectList());
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Column(name = "fRecordId", unique = false, nullable = false, length = 63)
	private String recordId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "fParent", nullable = true)
	private Record parent;

	@Transient
	private String schemaName;

	@Transient
	private String tableName;

	@Transient
	private Integer action;

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
			if (sourceRecord.recordId != null) {
				recordId = sourceRecord.recordId;
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

	/**
	 */
	public Record getSchemaRecord() {
		Record result = new Record(getSchemaRecordId(), getSchemaName());
		return result;
	}

	/**
	 */
	public Record getTableRecord() {
		Record result = new Record(getTableRecordId(), getSchemaName(),
				getTableName());
		result.setParent(getSchemaRecord());
		return result;
	}

	/**
	 */
	public boolean isSchemaRecord() {
		return ObjectUtils.equals(getRecordId(), getSchemaRecordId());
	}

	/**
	 */
	public boolean isTableRecord() {
		return ObjectUtils.equals(getRecordId(), getTableRecordId());
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	static {
		ignoreBlazeDSProperty(Record.class, "id");
		ignoreBlazeDSProperty(Record.class, "extendedData");
		ignoreBlazeDSProperty(Record.class, "extendedDataValue");
		ignoreBlazeDSProperty(Record.class, "owner");
		ignoreBlazeDSProperty(Record.class, "group");
	}

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
		return this.recordId;
	}

	public void setRecordId(String value) {
		this.recordId = value;
	}

	// ----------------------------------
	// parent
	// ----------------------------------

	static {
		ignoreBlazeDSProperty(Record.class, "parent");
	}

	@XmlTransient
	public Record getParent() {
		return this.parent;
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

	public String getSchemaRecordId() {
		return "_S_" + schemaName;
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

	public String getTableRecordId() {
		return "_T_" + tableName;
	}

	// ----------------------------------
	// action
	// ----------------------------------

	@XmlAttribute(name = "action")
	public Integer getAction() {
		return action;
	}

	public void setAction(Integer value) {
		this.action = value;
	}

} // Class
