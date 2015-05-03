////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.webservices.impl;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.ResponseStatus;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public abstract class AbstractAlKhwarizmixWebServiceForXML {

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	protected abstract Logger getLogger();

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private ServletRequestAttributes servletRequestAttributes;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * get the remote ip address, usefull to control add and update
	 *
	 * @return {@link String} the current request remote ip address
	 */
	public String getCurrentRequestRemoteAddress() {
		final String result = (getServletRequestAttributes() != null)
				? getServletRequestAttributes().getRequest().getRemoteAddr()
				: null;
		return result;
	}

	/**
	 * get the response headers
	 *
	 * @return {@link HttpHeaders}
	 */
	public HttpHeaders getHttpHeadersForXML() {
		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/xml; charset=UTF-8");
		responseHeaders.setPragma("no-cache");
		responseHeaders.setCacheControl("no-cache");
		responseHeaders.setExpires(0);

		return responseHeaders;
	}

	/**
	 */
	public void buildResponseErrorAsXML(final StringBuilder sBuilder) {
		buildResponseStatusAsXML(sBuilder, ResponseStatus.ERROR);
	}

	/**
	 */
	public void buildResponseSuccessAsXML(final StringBuilder sBuilder) {
		buildResponseStatusAsXML(sBuilder, ResponseStatus.SUCCESSFUL);
	}

	/**
	 */
	public void buildResponseStatusAsXML(final StringBuilder builder,
			final ResponseStatus response) {
		final StringBuilder responseHead = new StringBuilder(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		responseHead.append("<response status=\"").append(response)
				.append("\">");
		builder.insert(0, responseHead);
		builder.append("</response>");
	}

	/**
	 */
	public ResponseEntity<String> errorResponseForXML(
			final AlKhwarizmixErrorCode errorCode) {
		final StringBuilder sBuilder = new StringBuilder("<error code=\"")
				.append(errorCode.getId()).append("\"/>");

		buildResponseErrorAsXML(sBuilder);

		final HttpHeaders responseHeaders = getHttpHeadersForXML();

		return new ResponseEntity<String>(sBuilder.toString(), responseHeaders,
				errorCode.getHttpStatus());
	}

	/**
	 */
	protected final ResponseEntity<String> errorResponseForXML(
			final AlKhwarizmixException ex) {
		getLogger().error("{}: {}", ex.getLocalizedMessage(),
				ex.getStackTrace());
		return errorResponseForXML(ex.getErrorCode());
	}

	/**
	 */
	protected final ResponseEntity<String> errorResponseForXML(
			final Exception exception) {
		return errorResponseForXML(getAlKhwarizmixException(exception));
	}

	/**
	 */
	protected final AlKhwarizmixException getAlKhwarizmixException(
			final Exception exception) {
		AlKhwarizmixException result = null;
		if (exception instanceof AlKhwarizmixException)
			result = (AlKhwarizmixException) exception;
		else
			result = new AlKhwarizmixException(
					AlKhwarizmixErrorCode.SERVER_INTERNAL_ERROR);
		return result;
	}

	/**
	 */
	public ResponseEntity<String> successResponseForXML(
			final StringBuilder sBuilder) {
		buildResponseSuccessAsXML(sBuilder);

		final HttpHeaders responseHeaders = getHttpHeadersForXML();

		return new ResponseEntity<String>(sBuilder.toString(), responseHeaders,
				HttpStatus.OK);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	/**
	 * get the service
	 */
	protected abstract IAlKhwarizmixService getService();

	// ----------------------------------
	// servletRequestAttributes
	// ----------------------------------

	public ServletRequestAttributes getServletRequestAttributes() {
		return (servletRequestAttributes != null)
				? servletRequestAttributes
				: ((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes());
	}

	public void setServletRequestAttributes(final ServletRequestAttributes value) {
		servletRequestAttributes = value;
	}

} // Class
