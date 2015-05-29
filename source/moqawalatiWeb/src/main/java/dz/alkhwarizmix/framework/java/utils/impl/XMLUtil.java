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

package dz.alkhwarizmix.framework.java.utils.impl;

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
import dz.alkhwarizmix.framework.java.dtos.IAlKhwarizmixDomainObjectList;
import dz.alkhwarizmix.framework.java.utils.IXMLUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٥ شعبان ١٤٣٥ (June 03, 2014)
 */
public class XMLUtil implements IXMLUtil {

	// --------------------------------------------------------------------------
	//
	// Variables
	//
	// --------------------------------------------------------------------------

	private Jaxb2Marshaller jaxb2Marshaller = null;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * Constructor.
	 */
	public XMLUtil(final Jaxb2Marshaller pJaxb2Marshaller) {
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
	@Override
	public final String objectListToXML(
			final List<AbstractAlKhwarizmixDomainObject> objectList) {
		final StringWriter stringWriter = new StringWriter();
		final XMLResult xmlResult = new XMLResult(stringWriter);
		for (final AbstractAlKhwarizmixDomainObject object : objectList)
			jaxb2Marshaller.marshal(object, xmlResult);
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final String marshalObjectToXML(
			final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		try {
			return internal_marshalObjectToXML(object);
		} catch (final XmlMappingException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
		}
	}

	protected String internal_marshalObjectToXML( // NOPMD
			final AbstractAlKhwarizmixDomainObject object) {
		final StringWriter stringWriter = new StringWriter();
		final StreamResult streamResult = new StreamResult(stringWriter);
		jaxb2Marshaller.marshal(object, streamResult);
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final AbstractAlKhwarizmixDomainObject unmarshalObjectFromXML(
			final String xmlValue) throws AlKhwarizmixException {
		try {
			return internal_unmarshalObjectFromXML(xmlValue);
		} catch (final XmlMappingException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
		}
	}

	protected AbstractAlKhwarizmixDomainObject internal_unmarshalObjectFromXML( // NOPMD
			final String xmlValue) {
		return (AbstractAlKhwarizmixDomainObject) jaxb2Marshaller
				.unmarshal(new StreamSource(IOUtils.toInputStream(xmlValue)));
	}

	/**
	 * TODO: Javadoc
	 */
	public final String marshalObjectListToXML(
			final IAlKhwarizmixDomainObjectList objectList)
			throws AlKhwarizmixException {
		try {
			return internal_marshalObjectListToXML(objectList);
		} catch (final XmlMappingException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
		}
	}

	protected String internal_marshalObjectListToXML( // NOPMD
			final IAlKhwarizmixDomainObjectList objectList) {
		final StringWriter stringWriter = new StringWriter();
		final StreamResult streamResult = new StreamResult(stringWriter);
		jaxb2Marshaller.marshal(objectList, streamResult);
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	public final IAlKhwarizmixDomainObjectList unmarshalObjectListFromXML(
			final String xmlValue) throws AlKhwarizmixException {
		try {
			return internal_unmarshalObjectListFromXML(xmlValue);
		} catch (final XmlMappingException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_XML_PARSING, e);
		}
	}

	protected IAlKhwarizmixDomainObjectList internal_unmarshalObjectListFromXML( // NOPMD
			final String xmlValue) {
		return (IAlKhwarizmixDomainObjectList) jaxb2Marshaller
				.unmarshal(new StreamSource(IOUtils.toInputStream(xmlValue)));
	}

} // Class
