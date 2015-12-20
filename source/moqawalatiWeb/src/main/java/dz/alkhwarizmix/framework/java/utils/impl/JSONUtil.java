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

import com.google.gson.Gson;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.IAlKhwarizmixDomainObjectList;
import dz.alkhwarizmix.framework.java.utils.IJSONUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٥ شعبان ١٤٣٥ (June 03, 2014)
 */
public class JSONUtil implements IJSONUtil {

	// --------------------------------------------------------------------------
	//
	// Variables
	//
	// --------------------------------------------------------------------------

	private Gson gson = null;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * Constructor.
	 */
	public JSONUtil(Gson pGson) {
		if (pGson == null)
			pGson = new Gson();
		gson = pGson;
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
	public final String objectListToJSON(
			final List<AbstractAlKhwarizmixDomainObject> objectList) {
		final StringWriter stringWriter = new StringWriter();
		/*
		 * final JSONResult jsonResult = new JSONResult(stringWriter); for
		 * (final AbstractAlKhwarizmixDomainObject object : objectList)
		 * jaxb2Marshaller.marshal(object, jsonResult);
		 */
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final String marshalObjectToJSON(
			final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		try {
			return internal_marshalObjectToJSON(object);
		} catch (final Exception e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, e);
		}
	}

	protected String internal_marshalObjectToJSON( // NOPMD
			final AbstractAlKhwarizmixDomainObject object) {
		final StringWriter stringWriter = new StringWriter();
		/*
		 * final StreamResult streamResult = new StreamResult(stringWriter);
		 * jaxb2Marshaller.marshal(object, streamResult);
		 */
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final AbstractAlKhwarizmixDomainObject unmarshalObjectFromJSON(
			final String jsonValue) throws AlKhwarizmixException {
		try {
			return internal_unmarshalObjectFromJSON(jsonValue);
		} catch (final Exception e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, e);
		}
	}

	protected AbstractAlKhwarizmixDomainObject internal_unmarshalObjectFromJSON( // NOPMD
			final String jsonValue) {
		final AbstractAlKhwarizmixDomainObjectJsonWrapper wrapper = gson
				.fromJson("{domainObject:" + jsonValue + "}",
						AbstractAlKhwarizmixDomainObjectJsonWrapper.class);
		return wrapper.domainObject;
	}

	/**
	 * TODO: Javadoc
	 */
	public final String marshalObjectListToJSON(
			final IAlKhwarizmixDomainObjectList objectList)
			throws AlKhwarizmixException {
		try {
			return internal_marshalObjectListToJSON(objectList);
		} catch (final Exception e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, e);
		}
	}

	protected String internal_marshalObjectListToJSON( // NOPMD
			final IAlKhwarizmixDomainObjectList objectList) {
		final StringWriter stringWriter = new StringWriter();
		/*
		 * final StreamResult streamResult = new StreamResult(stringWriter);
		 * jaxb2Marshaller.marshal(objectList, streamResult);
		 */
		return stringWriter.toString();
	}

	/**
	 * TODO: Javadoc
	 */
	public final IAlKhwarizmixDomainObjectList unmarshalObjectListFromJSON(
			final String jsonValue) throws AlKhwarizmixException {
		try {
			return internal_unmarshalObjectListFromJSON(jsonValue);
		} catch (final Exception e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_JSON_PARSING, e);
		}
	}

	protected IAlKhwarizmixDomainObjectList internal_unmarshalObjectListFromJSON( // NOPMD
			final String jsonValue) {
		return null; /*
					 * (IAlKhwarizmixDomainObjectList) jaxb2Marshaller
					 * .unmarshal(new
					 * StreamSource(IOUtils.toInputStream(jsonValue)));
					 */
	}

	public class AbstractAlKhwarizmixDomainObjectJsonWrapper {
		private AbstractAlKhwarizmixDomainObject domainObject;

		/**
		 * @return the domainObject
		 */
		public AbstractAlKhwarizmixDomainObject getDomainObject() {
			return domainObject;
		}
	}

} // Class
