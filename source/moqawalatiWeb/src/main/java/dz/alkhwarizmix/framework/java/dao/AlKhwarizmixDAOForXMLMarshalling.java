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

package dz.alkhwarizmix.framework.java.dao;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAOForXMLMarshalling;
import dz.alkhwarizmix.framework.java.utils.XMLUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٧ شعبان ١٤٣٥ (June 05, 2014)
 */
public abstract class AlKhwarizmixDAOForXMLMarshalling extends AlKhwarizmixDAO
		implements IAlKhwarizmixDAOForXMLMarshalling {

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final String marshalObjectToXML(
			AlKhwarizmixDomainObjectAbstract object)
			throws AlKhwarizmixException {
		getLogger().trace("marshalObjectToXML()");

		return new XMLUtil(getJaxb2Marshaller()).marshalObjectToXML(object);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final AlKhwarizmixDomainObjectAbstract unmarshalObjectFromXML(
			String xmlValue) throws AlKhwarizmixException {
		getLogger().trace("unmarshalObjectFromXML()");

		return new XMLUtil(getJaxb2Marshaller())
				.unmarshalObjectFromXML(xmlValue);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	/**
	 * get the jaxb2Marshaller
	 */
	protected abstract Jaxb2Marshaller getJaxb2Marshaller();

	/**
	 * set the jaxb2Marshaller
	 */
	protected abstract void setJaxb2Marshaller(Jaxb2Marshaller value);

} // Class