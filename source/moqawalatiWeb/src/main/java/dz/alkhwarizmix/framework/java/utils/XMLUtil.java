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

package dz.alkhwarizmix.framework.java.utils;

import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.dom4j.io.XMLResult;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٥ شعبان ١٤٣٥ (June 03, 2014)
 */
public class XMLUtil {

	// --------------------------------------------------------------------------
	//
	// Variables
	//
	// --------------------------------------------------------------------------

	Jaxb2Marshaller jaxb2Marshaller = null;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * Constructor.
	 */
	public XMLUtil(Jaxb2Marshaller pJaxb2Marshaller) {
		jaxb2Marshaller = pJaxb2Marshaller;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * TODO: Javadoc
	 */
	public String objectListToXML(
			List<AbstractAlKhwarizmixDomainObject> objectList) {
		// getLogger().trace("objectListToXML()");

		StringWriter stringWriter = new StringWriter();
		XMLResult xmlResult = new XMLResult(stringWriter);
		for (AbstractAlKhwarizmixDomainObject object : objectList) {
			jaxb2Marshaller.marshal(object, xmlResult);
		}
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	public final String marshalObjectToXML(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		try {
			return internal_marshalObjectToXML(object);
		} catch (XmlMappingException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
		}
	}

	protected String internal_marshalObjectToXML(
			AbstractAlKhwarizmixDomainObject object) {

		StringWriter stringWriter = new StringWriter();
		StreamResult streamResult = new StreamResult(stringWriter);
		jaxb2Marshaller.marshal(object, streamResult);
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	public final AbstractAlKhwarizmixDomainObject unmarshalObjectFromXML(
			String xmlValue) throws AlKhwarizmixException {

		try {
			return internal_unmarshalObjectFromXML(xmlValue);
		} catch (XmlMappingException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
		}
	}

	protected AbstractAlKhwarizmixDomainObject internal_unmarshalObjectFromXML(
			String xmlValue) {
		return (AbstractAlKhwarizmixDomainObject) jaxb2Marshaller
				.unmarshal(new StreamSource(IOUtils.toInputStream(xmlValue)));
	}
}