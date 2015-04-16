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
	public RecordList commitRecordList(final RecordList recordList,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("commitRecordList");

		final RecordList result = new RecordList();
		for (final AbstractAlKhwarizmixDomainObject record : recordList
				.getList()) {
			final Record commitedRecord = commitRecord((Record) record,
					validateObjectToPublish);
			result.getList().add(commitedRecord);
		}
		return result;
	}

	/**
	 */
	private Record commitRecord(final Record record,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		if (record.getAction() == null)
			throw new AlKhwarizmixException("Wrong action");
		switch (record.getAction()) {
		case Record.INSERT_ACTION:
			return insertRecord(record, validateObjectToPublish);
		case Record.UPDATE_ACTION:
			return updateRecord(record, validateObjectToPublish);
		case Record.DELETE_ACTION:
			return deleteRecord(record, validateObjectToPublish);
		default:
			throw new AlKhwarizmixException("Wrong action");
		}
	}

	/**
	 */
	private Record insertRecord(final Record record,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		record.setOwner(getSessionOwner());
		final Record result = (Record) addObject(record,
				validateObjectToPublish);
		return result;
	}

	/**
	 */
	private Record updateRecord(final Record record,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		final Record result = (Record) updateObject(record, getSessionOwner(),
				validateObjectToPublish);
		return result;
	}

	/**
	 */
	private Record deleteRecord(final Record record,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		// deleteObject(record);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public String commitRecordListFromXML(final String recordListXml)
			throws AlKhwarizmixException {
		getLogger().trace("commitRecordListFromXML");
		final RecordList newRecordList = xmlToRecordList(recordListXml);
		final RecordList commitedRecordList = commitRecordList(newRecordList,
				true);
		final String result = recordListToXML(commitedRecordList);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			final AbstractAlKhwarizmixDomainObject object,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("getObject");
		Record result = getRecordDAO().getRecord((Record) object);
		if (validateObjectToPublish && (result != null)) {
			result = (Record) result.clone();
			getServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}
		updateObjectFromExtendedDataXML(result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record getRecord(final Record record,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("getRecord");
		Record result = (Record) getObject(record, validateObjectToPublish);
		if (validateObjectToPublish && (result != null)) {
			result = (Record) result.clone();
			getServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRecordAsXML(final Record record)
			throws AlKhwarizmixException {
		getLogger().trace("getRecordAsXML 1");
		final String result = getObjectAsXML(record);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRecordAsXML(final String recordXml)
			throws AlKhwarizmixException {
		getLogger().trace("getRecordAsXML 2");
		final String result = getObjectAsXML(recordXml);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecordList getRecordList(final String schema, final String table,
			DetachedCriteria criteriaToUse, final int firstResult,
			final int maxResult, final boolean validateObjectToPublish)
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
					criteriaToUse, firstResult, maxResult,
					validateObjectToPublish);
		}
		return new RecordList(listOfRecords);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRecordListAsXML(final String schema, final String table,
			final DetachedCriteria criteria, final int firstResult,
			final int maxResult) throws AlKhwarizmixException {
		getLogger().trace("getRecordListAsXML");
		final String result = recordListToXML(getRecordList(schema, table,
				criteria, firstResult, maxResult, true));
		return result;
	}

	/**
	 * TODO: JAVADOC
	 */
	protected String recordListToXML(final RecordList recordList) {
		String result = null;
		try {
			result = new XMLUtil(getJaxb2Marshaller())
					.marshalObjectListToXML(recordList);
		} catch (final AlKhwarizmixException exception) {
			getLogger().error("recordListToXML(): exception {}", exception);
			throw new RuntimeException();
		}
		getLogger().trace("recordListToXML(): returns {}", result);
		return result;
	}

	/**
	 * TODO: JAVADOC
	 */
	protected RecordList xmlToRecordList(final String recordListXml) {
		RecordList result = null;
		try {
			result = (RecordList) new XMLUtil(getJaxb2Marshaller())
					.unmarshalObjectListFromXML(recordListXml);
		} catch (final AlKhwarizmixException exception) {
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

	protected final void setRecordDAO(final IRecordDAO value) {
		recordDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return recordDAO;
	}

	// ----------------------------------
	// recordValidator
	// ----------------------------------

	protected final void setRecordValidator(final IRecordServiceValidator value) {
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
	protected void setJaxb2Marshaller(final Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class
