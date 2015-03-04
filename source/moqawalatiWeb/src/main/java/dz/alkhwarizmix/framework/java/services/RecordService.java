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

package dz.alkhwarizmix.framework.java.services;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.RecordList;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.interfaces.IRecordDAO;
import dz.alkhwarizmix.framework.java.interfaces.IRecordService;
import dz.alkhwarizmix.framework.java.interfaces.IRecordServiceValidator;
import dz.alkhwarizmix.framework.java.utils.XMLUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ ذو الحجة ١٤٣٥ (October 06, 2014)
 */
@Service
@Transactional(readOnly = true)
public class RecordService extends AbstractAlKhwarizmixService implements
		IRecordService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public RecordService() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(RecordService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IRecordDAO recordDAO;

	@Autowired
	private IRecordServiceValidator recordValidator;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public void commitRecordList(RecordList recordList)
			throws AlKhwarizmixException {
		getLogger().trace("commitRecordList");

		for (AbstractAlKhwarizmixDomainObject record : recordList.getList())
			commitRecord((Record) record);
	}

	/**
	 */
	private Record commitRecord(Record record) throws AlKhwarizmixException {
		if (record.getAction() == null)
			throw new AlKhwarizmixException("Wrong action");
		switch (record.getAction()) {
		case Record.INSERT_ACTION:
			return insertRecord(record);
		case Record.UPDATE_ACTION:
			return updateRecord(record);
		case Record.DELETE_ACTION:
			return deleteRecord(record);
		default:
			throw new AlKhwarizmixException("Wrong action");
		}
	}

	/**
	 */
	private Record insertRecord(Record record) throws AlKhwarizmixException {
		record.setOwner(getSessionOwner());
		addObject(record);
		return record;
	}

	/**
	 */
	private Record updateRecord(Record record) throws AlKhwarizmixException {
		updateObject(record);
		return record;
	}

	/**
	 */
	private Record deleteRecord(Record record) throws AlKhwarizmixException {
		// deleteObject(record);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String commitRecordListFromXML(String recordListXml)
			throws AlKhwarizmixException {
		getLogger().trace("commitRecordListFromXML");
		RecordList newRecordList = xmlToRecordList(recordListXml);
		commitRecordList(newRecordList);
		String result = recordListToXML(newRecordList);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");
		Record result = getRecordDAO().getRecord((Record) object);
		updateObjectFromExtendedDataXML(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record getRecord(Record record) throws AlKhwarizmixException {
		getLogger().trace("getRecord");
		Record result = internal_getRecord(record);
		getServiceValidator()
				.validateObjectToPublish(result, getSessionOwner());
		return result;
	}

	/**
	 * TODO: JAVADOC
	 */
	protected Record internal_getRecord(Record record)
			throws AlKhwarizmixException {
		getLogger().trace("internal_getRecord");
		Record result = (Record) getObject(record);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRecordAsXML(Record record) throws AlKhwarizmixException {
		getLogger().trace("getRecordAsXML 1");
		String result = getObjectAsXML(record);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRecordAsXML(String recordXml) throws AlKhwarizmixException {
		getLogger().trace("getRecordAsXML 2");
		String result = getObjectAsXML(recordXml);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecordList getRecordList(String schema, String table,
			DetachedCriteria criteriaToUse, int firstResult, int maxResult)
			throws AlKhwarizmixException {
		getLogger().trace("getRecordList");

		List<Record> listOfRecords = null;
		Record tableRecord = new Record(null, schema, table).getTableRecord();
		tableRecord = getRecordDAO().getRecord(tableRecord);
		if (tableRecord != null) {
			if (criteriaToUse == null) {
				criteriaToUse = DetachedCriteria.forClass(Record.class);
				criteriaToUse.addOrder(Order.asc(Record.RECORDID));
			}
			criteriaToUse.add(Restrictions.eq(Record.PARENT_ID,
					tableRecord.getId()));
			listOfRecords = (List<Record>) (List<?>) getObjectList(
					criteriaToUse, firstResult, maxResult);
		}
		return new RecordList(listOfRecords);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRecordListAsXML(String schema, String table,
			DetachedCriteria criteria, int firstResult, int maxResult)
			throws AlKhwarizmixException {
		getLogger().trace("getRecordListAsXML");
		String result = recordListToXML(getRecordList(schema, table, criteria,
				firstResult, maxResult));
		return result;
	}

	/**
	 * TODO: JAVADOC
	 */
	protected String recordListToXML(RecordList recordList) {
		String result = null;
		try {
			result = new XMLUtil(getJaxb2Marshaller())
					.marshalObjectListToXML(recordList);
		} catch (AlKhwarizmixException exception) {
			getLogger().error("recordListToXML(): exception {}", exception);
			throw new RuntimeException();
		}
		getLogger().trace("recordListToXML(): returns {}", result);
		return result;
	}

	/**
	 * TODO: JAVADOC
	 */
	protected RecordList xmlToRecordList(String recordListXml) {
		RecordList result = null;
		try {
			result = (RecordList) new XMLUtil(getJaxb2Marshaller())
					.unmarshalObjectListFromXML(recordListXml);
		} catch (AlKhwarizmixException exception) {
			getLogger().error("recordListToXML(): exception {}", exception);
			throw new RuntimeException();
		}
		getLogger().trace("recordListToXML(): returns {}", result);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// recordDAO
	// ----------------------------------

	private final IRecordDAO getRecordDAO() {
		return recordDAO;
	}

	protected final void setRecordDAO(IRecordDAO value) {
		recordDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return recordDAO;
	}

	// ----------------------------------
	// recordValidator
	// ----------------------------------

	protected final void setRecordValidator(IRecordServiceValidator value) {
		recordValidator = value;
	}

	@Override
	protected IAlKhwarizmixServiceValidator getServiceValidator() {
		return recordValidator;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	@Override
	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	@Override
	protected void setJaxb2Marshaller(Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class
