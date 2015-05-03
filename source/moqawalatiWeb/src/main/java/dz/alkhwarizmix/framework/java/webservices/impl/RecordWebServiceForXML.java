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

package dz.alkhwarizmix.framework.java.webservices.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.services.IRecordService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٠ ذو الحجة ١٤٣٥ (October 14, 2014)
 */
@Controller
@RequestMapping("alkhwarizmix/xml/record")
public class RecordWebServiceForXML extends AbstractAlKhwarizmixWebServiceForXML {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public RecordWebServiceForXML() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(RecordWebServiceForXML.class);

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IRecordService recordService;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * commit the records to database
	 * 
	 * @param xmlValue
	 *            {@link String} the recordList as xml
	 * @return {@link ResponseEntity}
	 * @throws AlKhwarizmixException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> commitRecordList(
			@RequestParam("recordList") String xmlValue)
			throws AlKhwarizmixException {
		getLogger().debug("commitRecordList({})", xmlValue);
		try {
			String result = recordService.commitRecordListFromXML(xmlValue);
			StringBuilder sBuilder = new StringBuilder(result);
			return successResponseForXML(sBuilder);
		} catch (AlKhwarizmixException e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 * get the record from database
	 * 
	 * @param recordId
	 *            {@link Long} recordId
	 * @return {@link ResponseEntity}
	 * @throws AlKhwarizmixException
	 */
	@RequestMapping(value = "/{schemaName}/{tableName}/{recordId}", method = RequestMethod.GET)
	public ResponseEntity<String> getRecord(
			@PathVariable("schemaName") String schemaName,
			@PathVariable("tableName") String tableName,
			@PathVariable("recordId") String recordId)
			throws AlKhwarizmixException {
		getLogger().debug("getRecordById({}, {}, {})", schemaName, tableName,
				recordId);
		try {
			Record recordToGet = new Record();
			recordToGet.setSchemaName(schemaName);
			recordToGet.setTableName(tableName);
			recordToGet.setRecordId(recordId);
			StringBuilder sBuilder = new StringBuilder(
					recordService.getRecordAsXML(recordToGet));
			return successResponseForXML(sBuilder);
		} catch (AlKhwarizmixException e) {
			return errorResponseForXML(e);
		}
	}

	/**
	 */
	@RequestMapping(value = "/{schemaName}/{tableName}", method = RequestMethod.GET)
	public ResponseEntity<String> getRecordList(
			@PathVariable("schemaName") String schemaName,
			@PathVariable("tableName") String tableName,
			@RequestParam("firstResult") int firstResult,
			@RequestParam("maxResult") int maxResult) {
		getLogger().debug("getRecordList({}, {}, {}, {})", schemaName,
				tableName, firstResult, maxResult);
		StringBuilder result = new StringBuilder();
		try {
			result.append(recordService.getRecordListAsXML(schemaName,
					tableName, null, firstResult, maxResult));
			return successResponseForXML(result);
		} catch (AlKhwarizmixException e) {
			return errorResponseForXML(e);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// recordService
	// ----------------------------------

	protected IRecordService getRecordService() {
		return recordService;
	}

	protected void setRecordService(IRecordService value) {
		recordService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	@Override
	protected IAlKhwarizmixService getService() {
		return recordService;
	}

} // Class
