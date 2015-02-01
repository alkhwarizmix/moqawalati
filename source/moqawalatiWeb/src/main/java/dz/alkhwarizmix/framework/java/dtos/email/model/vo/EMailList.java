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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.ObjectUtils;

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDomainObjectList;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ ربيع الثاني ١٤٣٦ (February 01, 2015)
 */
@XmlRootElement(name = "EMailList")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EMailList implements Cloneable, IAlKhwarizmixDomainObjectList {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public EMailList() {
		super();
	}

	public EMailList(List<EMail> list) {
		super();
		this.list = list;
	}

	@SuppressWarnings("unchecked")
	protected EMailList(EMailList other) {
		super();
		if (other != null) {
			this.list = (List<EMail>) ObjectUtils.clone(other.list);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	List<EMail> list = null;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new EMailList(this);
	}

	/**
	 */
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

	protected final int continueHashCode(int result, Object field) {
		return 31 * result + ObjectUtils.hashCode(field);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		boolean result = (getObjectAsThisClass(other) != null)
				&& ObjectUtils.equals(this.list,
						getObjectAsThisClass(other).list);
		return result;
	}

	private EMailList getObjectAsThisClass(Object other) {
		return (other instanceof EMailList)
				? (EMailList) other
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

	@XmlElement(name = "EMail", type = EMail.class)
	public List<AbstractAlKhwarizmixDomainObject> getList() {
		if (list == null)
			list = new ArrayList<EMail>();
		return (List<AbstractAlKhwarizmixDomainObject>) (List<?>) list;
	}

	public void setList(List<AbstractAlKhwarizmixDomainObject> value) {
		this.list = (List<EMail>) (List<?>) value;
	}

} // Class
