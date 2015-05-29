////////////////////////////////////////////////////////////////////////////////
//بسم الله الرحمن الرحيم
//
//حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)
//كافة الحقوق محفوظة (All Rights Reserved)
//
//NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.webservices.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.RecordList;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixService;
import dz.alkhwarizmix.framework.java.services.IRecordService;
import dz.alkhwarizmix.framework.java.webservices.IRecordWebServiceForBlazeDS;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٠ ذو الحجة ١٤٣٥ (October 14, 2014)
 */
public class RecordWebServiceForBlazeDS implements IRecordWebServiceForBlazeDS {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public RecordWebServiceForBlazeDS() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(RecordWebServiceForBlazeDS.class);
		return logger;
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
	 */
	@Override
	public void commitRecordList(final RecordList recordList)
			throws AlKhwarizmixException {
		getLogger().debug("commitRecordList({})", recordList);
		getRecordService().commitRecordList(recordList, true);
	}

	/**
	 */
	@Override
	public Record getRecord(final Record record) throws AlKhwarizmixException {
		getLogger().debug("getRecord({})", record);
		return getRecordService().getRecord(record, true);
	}

	/**
	 */
	@Override
	public RecordList getRecordList(final String schemaName,
			final String tableName, final int firstResult, final int maxResult)
			throws AlKhwarizmixException {
		getLogger().debug("getRecordList({}, {}, {}, {})", schemaName,
				tableName, firstResult, maxResult);
		return getRecordService().getRecordList(schemaName, tableName, null,
				firstResult, maxResult, true);
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

	protected void setRecordService(final IRecordService value) {
		recordService = value;
	}

	// ----------------------------------
	// service
	// ----------------------------------

	protected IAlKhwarizmixService getService() {
		return recordService;
	}

} // Class
