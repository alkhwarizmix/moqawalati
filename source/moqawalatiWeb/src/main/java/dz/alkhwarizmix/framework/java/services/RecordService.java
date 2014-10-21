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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.RecordList;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.IRecordDAO;
import dz.alkhwarizmix.framework.java.interfaces.IRecordService;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
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
public class RecordService extends AlKhwarizmixService implements
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

	private static final Logger LOG = LoggerFactory
			.getLogger(RecordService.class);

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
	private IRecordDAO recordDAO;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@Autowired
	private AlKhwarizmixSessionData sessionData;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public void addRecord(Record record) throws AlKhwarizmixException {
		getLogger().debug("addRecord");
		addObject(record);
	}

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public String addRecordFromXML(String recordXml)
			throws AlKhwarizmixException {
		getLogger().trace("addRecordFromXML");
		Record newRecord = (Record) unmarshalObjectFromXML(recordXml);
		addRecord(newRecord);
		String result = marshalObjectToXML(newRecord);
		return result;
	}

	/**
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");
		Record result = getMoqawalatiDAO().getRecord((Record) object);
		updateObjectFromExtendedDataXML(result);
		return result;
	}

	/**
	 */
	@Override
	public Record getRecord(Record record) throws AlKhwarizmixException {
		getLogger().debug("getRecord");
		Record result = internal_getRecord(record);
		nullifyProtectedProperties(result);
		return result;
	}

	/**
	 */
	Record internal_getRecord(Record record) throws AlKhwarizmixException {
		getLogger().trace("internal_getRecord");
		Record result = (Record) getObject(record);
		return result;
	}

	/**
	 */
	@Override
	public String getRecordAsXML(Record record) throws AlKhwarizmixException {
		getLogger().trace("getRecordAsXML 1");
		String result = getObjectAsXML(record);
		return result;
	}

	/**
	 */
	@Override
	public String getRecordAsXML(String recordXml) throws AlKhwarizmixException {
		getLogger().trace("getRecordAsXML 2");
		String result = getObjectAsXML(recordXml);
		return result;
	}

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public Record updateRecord(Record record) throws AlKhwarizmixException {
		getLogger().debug("updateRecord");
		setupObjectExtendedDataXMLValue(record);
		Record result = (Record) updateObject(record);
		return result;
	}

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public String updateRecordFromXML(String recordXml)
			throws AlKhwarizmixException {
		getLogger().trace("updateRecordFromXML");
		Record newRecord = (Record) unmarshalObjectFromXML(recordXml);
		// newRecord.setCreatorId(updaterId);
		Record updatedRecord = updateRecord(newRecord);
		String result = marshalObjectToXML(updatedRecord);
		return result;
	}

	/**
	 */
	@Override
	public RecordList getRecordList(String schema, String table,
			DetachedCriteria criteriaToUse, int firstResult, int maxResult)
			throws AlKhwarizmixException {
		getLogger().debug("getRecordList");
		if (criteriaToUse == null) {
			criteriaToUse = DetachedCriteria.forClass(Record.class);
			criteriaToUse.addOrder(Order.asc(Record.RECORDID));
		}
		RecordList result = new RecordList(
				(List<Record>) (List<?>) getObjectList(criteriaToUse,
						firstResult, maxResult));
		return result;
	}

	/**
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
	 */
	@Override
	public String recordListToXML(RecordList recordList) {
		String result = null;
		try {
			result = new XMLUtil(getJaxb2Marshaller())
					.marshalObjectListToXML(recordList);
		} catch (AlKhwarizmixException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		getLogger().trace("recordListToXML(): returns {}", result);
		return result;
	}

	@Override
	protected void nullifyProtectedProperties(
			AbstractAlKhwarizmixDomainObject object) {
		super.nullifyProtectedProperties(object);
		Record record = (Record) object;
		if (record != null) {
			record.setOwner(null);
		}
	}

	/**
	 */
	private AlKhwarizmixDomainObject getSessionCustomizer() {
		return getSessionData().getCustomizer();
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// recordDAO
	// ----------------------------------

	final IRecordDAO getMoqawalatiDAO() {
		return recordDAO;
	}

	final void setRecordDAO(IRecordDAO value) {
		recordDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return recordDAO;
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

	// ----------------------------------
	// sessionData
	// ----------------------------------

	final AlKhwarizmixSessionData getSessionData() {
		return sessionData;
	}

	final void setSessionData(AlKhwarizmixSessionData value) {
		sessionData = value;
	}

} // Class
