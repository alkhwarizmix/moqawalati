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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;

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
public class ExtendedData extends AlKhwarizmixDomainObjectAbstract implements
		Serializable {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -4633359691056719807L;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public ExtendedData() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@OneToMany(mappedBy = "extendedData", cascade = CascadeType.ALL)
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
	public List<AlKhwarizmixDomainObjectAbstract> getDaoObjectList() {

		List<AlKhwarizmixDomainObjectAbstract> result = new ArrayList<AlKhwarizmixDomainObjectAbstract>();
		result.add(this);
		result.addAll(getExtendedDataParts());
		return result;
	}

	/**
	 */
	@Override
	public void beforeDaoSaveOrUpdate(AlKhwarizmixDomainObjectAbstract object) {
		// DO NOTHING
	}

	/**
	 */
	public void updateFrom(Object sourceObject) throws AlKhwarizmixException {

		ExtendedData sourceExtendedData = (ExtendedData) sourceObject;
		if ((sourceExtendedData != null)
				&& (this.getId().equals(sourceExtendedData.getId()))) {
			this.setExtendedDataValue(sourceExtendedData.getExtendedDataValue());
		} else {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.UPDATE_DATA_ERROR);
		}
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
		for (ExtendedDataPart extendedDataPart : getExtendedDataParts()) {
			extendedDataValue += extendedDataPart.getExtendedDataPartValue();
		}
		return extendedDataValue;
	}

	public void setExtendedDataValue(String value) {
		int extendedDataPartValueMaxLen = 127;
		ExtendedDataPart extendedDataPart = null;
		int startIndex = 0;
		int i = 0;
		for (; startIndex < value.length(); i++) {
			if (getExtendedDataParts().size() > i) {
				extendedDataPart = getExtendedDataParts().get(i);
			} else {
				extendedDataPart = newExtendedDataPart();
				addExtendedDataPart(extendedDataPart);
			}
			int endIndex = Math.min(startIndex + extendedDataPartValueMaxLen,
					value.length());
			extendedDataPart.setExtendedDataPartValue(value.substring(
					startIndex, endIndex));
			startIndex = endIndex;
		}

		for (; i < getExtendedDataParts().size(); i++) {
			extendedDataPart = getExtendedDataParts().get(i);
			extendedDataPart.setExtendedDataPartValue("");
		}
	}

	private ExtendedDataPart newExtendedDataPart() {
		ExtendedDataPart result = new ExtendedDataPart();
		return result;
	}

	private void addExtendedDataPart(ExtendedDataPart extendedDataPart) {
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

	public void setExtendedDataParts(List<ExtendedDataPart> value) {
		extendedDataParts = value;
	}

} // Class