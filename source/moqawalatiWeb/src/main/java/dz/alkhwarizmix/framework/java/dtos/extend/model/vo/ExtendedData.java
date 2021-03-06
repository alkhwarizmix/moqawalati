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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠١ شعبان ١٤٣٥ (May 30, 2014)
 */
@Entity
@Table(name = "TExtendedData")
public class ExtendedData extends AbstractAlKhwarizmixDomainObject implements
		Serializable, Cloneable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -4633359691056719807L;

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public ExtendedData() {
		super();
	}

	private ExtendedData(final ExtendedData other) {
		super(other);
		if (other != null)
			setExtendedDataValue(other.getExtendedDataValue());
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@OneToMany(mappedBy = "extendedData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ExtendedDataPart> extendedDataParts = null;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public Object clone() {
		return new ExtendedData(this);
	}

	/**
	 */
	@Override
	public List<AbstractAlKhwarizmixDomainObject> getDaoObjectList() {

		final List<AbstractAlKhwarizmixDomainObject> result = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		result.add(this);
		result.addAll(getExtendedDataParts());
		return result;
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(
			final AbstractAlKhwarizmixDomainObject object) {
		// DO NOTHING
	}

	/**
	 */
	@Override
	public void updateFrom(final Object sourceObject)
			throws AlKhwarizmixException {
		final ExtendedData source = (ExtendedData) sourceObject;
		if ((source != null) && getId().equals(source.getId()))
			setExtendedDataValue(source.getExtendedDataValue());
		else
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// extendedDataValue
	// ----------------------------------

	public String getExtendedDataValue() {
		String extendedDataValue = "";
		for (final ExtendedDataPart extendedDataPart : getExtendedDataParts())
			extendedDataValue += extendedDataPart.getExtendedDataPartValue();
		return extendedDataValue;
	}

	public void setExtendedDataValue(final String value) {
		final int extendedDataPartValueMaxLen = 127;
		ExtendedDataPart extendedDataPart = null;
		int startIndex = 0;
		int i = 0;
		for (; startIndex < value.length(); i++) {
			if (getExtendedDataParts().size() > i)
				extendedDataPart = getExtendedDataParts().get(i);
			else {
				extendedDataPart = newExtendedDataPart();
				addExtendedDataPart(extendedDataPart);
			}
			extendedDataPart.setOrder(Long.valueOf(i));
			final int endIndex = Math.min(startIndex
					+ extendedDataPartValueMaxLen, value.length());
			extendedDataPart.setExtendedDataPartValue(value.substring(
					startIndex, endIndex));
			startIndex = endIndex;
		}

		for (; i < getExtendedDataParts().size(); i++) {
			extendedDataPart = getExtendedDataParts().get(i);
			extendedDataPart.setOrder(Long.valueOf(i));
			extendedDataPart.setExtendedDataPartValue("");
		}
	}

	private ExtendedDataPart newExtendedDataPart() {
		final ExtendedDataPart result = new ExtendedDataPart();
		return result;
	}

	private void addExtendedDataPart(final ExtendedDataPart extendedDataPart) {
		extendedDataPart.setExtendedData(this);
		getExtendedDataParts().add(extendedDataPart);
	}

	// ----------------------------------
	// extendedDataParts
	// ----------------------------------

	public List<ExtendedDataPart> getExtendedDataParts() {
		if (extendedDataParts == null)
			extendedDataParts = new ArrayList<ExtendedDataPart>();
		return extendedDataParts;
	}

	public void setExtendedDataParts(final List<ExtendedDataPart> value) {
		extendedDataParts = value;
	}

} // Class
