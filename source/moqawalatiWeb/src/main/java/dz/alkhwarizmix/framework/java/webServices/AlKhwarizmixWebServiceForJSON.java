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

package dz.alkhwarizmix.framework.java.webServices;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.ResponseStatus;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ صفر ١٤٣٥ (December 10, 2013)
 */
public abstract class AlKhwarizmixWebServiceForJSON {

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
		String result = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest().getRemoteAddr();
		return result;
	}

	/**
	 * get the response headers
	 * 
	 * @return {@link HttpHeaders}
	 */
	public HttpHeaders getHttpHeadersForJSON() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/xml; charset=UTF-8");
		responseHeaders.setPragma("no-cache");
		responseHeaders.setCacheControl("no-cache");
		responseHeaders.setExpires(0);

		return responseHeaders;
	}

	/**
	 */
	public void buildResponseErrorAsJSON(StringBuilder sBuilder) {
		buildResponseStatusAsJSON(sBuilder, ResponseStatus.ERROR);
	}

	/**
	 */
	public void buildResponseSuccessAsJSON(StringBuilder sBuilder) {
		buildResponseStatusAsJSON(sBuilder, ResponseStatus.SUCCESSFUL);
	}

	/**
	 */
	public void buildResponseStatusAsJSON(StringBuilder builder,
			ResponseStatus response) {
		StringBuilder responseHead = new StringBuilder(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		responseHead.append("<response status=\"").append(response)
				.append("\">");
		builder.insert(0, responseHead);
		builder.append("</response>");
	}

	/**
	 */
	public ResponseEntity<String> errorResponseForJSON(AlKhwarizmixErrorCode errorCode) {
		StringBuilder sBuilder = new StringBuilder("<error code=\"").append(
				errorCode.getId()).append("\"/>");

		buildResponseErrorAsJSON(sBuilder);

		HttpHeaders responseHeaders = getHttpHeadersForJSON();

		return new ResponseEntity<String>(sBuilder.toString(), responseHeaders,
				HttpStatus.METHOD_FAILURE);
	}

	/**
	 */
	public ResponseEntity<String> errorResponseAsJSON(AlKhwarizmixException exception) {
		return errorResponseForJSON(exception.getErrorCode());
	}

	/**
	 */
	public ResponseEntity<String> successResponseForJSON(StringBuilder sBuilder) {
		buildResponseSuccessAsJSON(sBuilder);

		HttpHeaders responseHeaders = getHttpHeadersForJSON();

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