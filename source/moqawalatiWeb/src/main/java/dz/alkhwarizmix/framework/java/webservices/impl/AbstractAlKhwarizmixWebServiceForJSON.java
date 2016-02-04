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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
 * @since ٠٨ صفر ١٤٣٥ (December 10, 2013)
 */
public abstract class AbstractAlKhwarizmixWebServiceForJSON {

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	protected abstract Logger getLogger();

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
		final String result = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest().getRemoteAddr();
		return result;
	}

	/**
	 * get the response headers
	 *
	 * @return {@link HttpHeaders}
	 */
	public HttpHeaders getHttpHeadersForJSON() {
		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
		responseHeaders.setPragma("no-cache");
		responseHeaders.setCacheControl("no-cache");
		responseHeaders.setExpires(0);
		final List<Charset> charsets = new ArrayList<Charset>();
		charsets.add(Charset.forName("UTF-8"));
		responseHeaders.setAcceptCharset(charsets);
		return responseHeaders;
	}

	/**
	 */
	protected final void buildResponseErrorAsJSON(final StringBuilder sBuilder) {
		buildResponseStatusAsJSON(sBuilder, ResponseStatus.ERROR);
	}

	/**
	 */
	protected final void buildResponseSuccessAsJSON(final StringBuilder sBuilder) {
		buildResponseStatusAsJSON(sBuilder, ResponseStatus.SUCCESSFUL);
	}

	/**
	 */
	protected final void buildResponseStatusAsJSON(final StringBuilder builder,
			final ResponseStatus response) {
		final StringBuilder responseHead = new StringBuilder("");
		responseHead.append("{\"response\":{\"status\":\"").append(response)
				.append("\",");
		builder.insert(0, responseHead);
		builder.append("}}");
	}

	/**
	 */
	protected final ResponseEntity<String> errorResponseForJSON(
			final AlKhwarizmixErrorCode errorCode) {
		final StringBuilder sBuilder = new StringBuilder(
				"\"error\":{\"code\":\"").append(errorCode.getId()).append(
				"\"}");

		buildResponseErrorAsJSON(sBuilder);

		final HttpHeaders responseHeaders = getHttpHeadersForJSON();

		return new ResponseEntity<String>(sBuilder.toString(), responseHeaders,
				errorCode.getHttpStatus());
	}

	/**
	 */
	protected final ResponseEntity<String> errorResponseForJSON(
			final AlKhwarizmixException exception) {
		getLogger().error("errorResponseForJSON: {0}", exception);
		return errorResponseForJSON(exception.getErrorCode());
	}

	/**
	 */
	protected final ResponseEntity<String> errorResponseForJSON(
			final Exception exception) {
		return errorResponseForJSON(getAlKhwarizmixException(exception));
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
					AlKhwarizmixErrorCode.SERVER_INTERNAL_ERROR, exception);
		return result;
	}

	/**
	 */
	protected final ResponseEntity<String> successResponseForJSON(
			final StringBuilder sBuilder) {
		sBuilder.insert(0, "\"result\":");

		buildResponseSuccessAsJSON(sBuilder);

		final HttpHeaders responseHeaders = getHttpHeadersForJSON();

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

} // Class
