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

package dz.alkhwarizmix.framework.java;

import org.apache.log4j.spi.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public enum AlKhwarizmixErrorCode {

	ERROR_DATABASE(10500, "Database error", HttpStatus.INTERNAL_SERVER_ERROR),

	INVALID_DATA(20500, "Invalid Data", HttpStatus.INTERNAL_SERVER_ERROR),

	UPDATE_DATA_ERROR(30500, "Cannot update data",
			HttpStatus.INTERNAL_SERVER_ERROR),

	ERROR_XML_PARSING(10400, "Xml parsing error", HttpStatus.BAD_REQUEST), ERROR_JSON_PARSING(
			20400, "Json parsing error", HttpStatus.BAD_REQUEST),

	ERROR_LOGIN(10406, "Login error", HttpStatus.NOT_ACCEPTABLE);

	private int id;
	private String description;
	private HttpStatus httpStatus;

	/**
	 * constructor
	 * 
	 * @param id
	 *            {@link Integer} the error code
	 * @param description
	 *            {@link String} the error default description
	 */
	private AlKhwarizmixErrorCode(final int theId, final String description,
			final HttpStatus httpStatus) {
		this.id = theId;
		this.description = description;
		this.httpStatus = httpStatus;
	}

	/**
	 * get an errorCode using it's id
	 * 
	 * @param id
	 *            {@link Integer} the error code id
	 * @return {@link ErrorCode}
	 */
	public static AlKhwarizmixErrorCode getErrorCode(int id) {
		AlKhwarizmixErrorCode[] errorCodes = values();

		for (int i = 0; i < errorCodes.length; i++) {
			if (errorCodes[i].id == id) {
				return errorCodes[i];
			}
		}
		return null;
	}

	/**
	 * get the id
	 * 
	 * @return id {@link int} the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * get the error description
	 * 
	 * @return description {@link String} the error description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * get the httpStatus
	 * 
	 * @return httpStatus {@link HttpStatus} the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

} // Class