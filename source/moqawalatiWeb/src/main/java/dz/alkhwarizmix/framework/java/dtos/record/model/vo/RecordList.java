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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.ObjectUtils;

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.IAlKhwarizmixDomainObjectList;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٥ ذو الحجة ١٤٣٥ (October 11, 2014)
 */
@XmlRootElement(name = "RecordList")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RecordList implements Cloneable, IAlKhwarizmixDomainObjectList {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public RecordList() {
		super();
	}

	public RecordList(final List<Record> list) {
		super();
		this.list = list;
	}

	@SuppressWarnings("unchecked")
	protected RecordList(final RecordList other) {
		super();
		if (other != null)
			list = (List<Record>) ObjectUtils.clone(other.list);
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private List<Record> list = null;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new RecordList(this);
	}

	/**
	 */
	@Override
	public String toString() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 1;
		result = continueHashCode(result, list);
		return result;
	}

	private int continueHashCode(final int result, final Object field) {
		return (31 * result) + ObjectUtils.hashCode(field);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		final boolean result = (getObjectAsThisClass(other) != null)
				&& ObjectUtils.equals(list, getObjectAsThisClass(other).list);
		return result;
	}

	private RecordList getObjectAsThisClass(final Object other) {
		return (other instanceof RecordList)
				? (RecordList) other
				: null;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// list
	// ----------------------------------

	@Override
	@XmlElement(name = "Record", type = Record.class)
	public List<AbstractAlKhwarizmixDomainObject> getList() {
		if (list == null)
			list = new ArrayList<Record>();
		return (List<AbstractAlKhwarizmixDomainObject>) (List<?>) list;
	}

	@Override
	public void setList(final List<AbstractAlKhwarizmixDomainObject> value) {
		list = (List<Record>) (List<?>) value;
	}

} // Class
