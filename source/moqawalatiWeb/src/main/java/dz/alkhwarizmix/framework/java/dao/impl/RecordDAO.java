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

package dz.alkhwarizmix.framework.java.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IRecordDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ذو الحجة ١٤٣٥ (October 02, 2014)
 */
@Repository
public class RecordDAO extends AlKhwarizmixDAOForXMLMarshalling implements
		IRecordDAO {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public RecordDAO() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory.getLogger(RecordDAO.class);

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public void saveOrUpdate(AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		if (object instanceof Record) {
			Record record = (Record) object;
			try {
				setupTableAndSchema(record);
			} catch (AlKhwarizmixException e) {
				throw getDAOException(e);
			}
		}
		super.saveOrUpdate(object);
	}

	private void setupTableAndSchema(final Record record)
			throws AlKhwarizmixException {
		if (record.isSchemaRecord()) {
			getLogger().warn("setupTableAndSchema: record.isSchemaRecord()");
		} else if (record.isTableRecord()) {
			// record is Table
			getLogger().warn("setupTableAndSchema: record.isTableRecord()");
		} else {
			// record is Table row
			if (record.getParent() == null) {
				getRecord_internal(record);
			} else if (record.getParent().getParent() == null) {
				getRecord_internal(record);
			}
		}
	}

	/**
	 */
	@Override
	public Record getRecord(final Record recordToGet)
			throws AlKhwarizmixException {
		getLogger().trace("getRecord()");
		Record result = getRecord_internal((Record) recordToGet.clone());
		return ((result == null) || (result.getId() == null)
				? null
				: result);
	}

	private Record getRecord_internal(final Record recordToGet)
			throws AlKhwarizmixException {
		Record result = null;
		Record tableRecord = null;
		Record schemaRecord = getSchemaRecord(recordToGet);
		if (schemaRecord != null) {
			if (recordToGet.isSchemaRecord()) {
				result = schemaRecord;
			} else {
				tableRecord = getTableRecord(recordToGet, schemaRecord);
			}
		}
		if (tableRecord != null) {
			if (recordToGet.isTableRecord()) {
				result = tableRecord;
			} else {
				recordToGet.setParent(tableRecord);
				result = getRecordFromHibernate(recordToGet);
			}
		}
		return result;
	}

	private Record getSchemaRecord(final Record record)
			throws AlKhwarizmixException {
		Record existingRecord = getRecordFromHibernate(record.getSchemaRecord());
		return (existingRecord != null
				? existingRecord
				: record.getSchemaRecord());
	}

	private Record getTableRecord(final Record record, final Record schemaRecord)
			throws AlKhwarizmixException {
		Record recordToFind = record.getTableRecord();
		recordToFind.setParent(schemaRecord);
		Record existingRecord = getRecordFromHibernate(recordToFind);
		return (existingRecord != null
				? existingRecord
				: recordToFind);
	}

	private Record getRecordFromHibernate(final Record recordToGet)
			throws AlKhwarizmixException {
		try {
			Record result = null;
			Criteria criteria = getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(Record.class);
			// List<Record> allRecords = criteria.list();
			criteria.add(Restrictions.and(
					getEqualsRecordIdCriterion(recordToGet),
					getEqualsParentCriterion(recordToGet)));
			result = (Record) criteria.uniqueResult();
			if (result != null) {
				result.setParent(recordToGet.getParent());
				result.setExtendedData(getExtendedData(result.getExtendedData()));
			}
			return result;
		} catch (DataAccessException e) {
			AlKhwarizmixException ex = new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

	private Criterion getEqualsRecordIdCriterion(final Record record) {
		return Restrictions.eq(Record.RECORDID, record.getRecordId());
	}

	private Criterion getEqualsParentCriterion(final Record record) {
		return (record.getParent() != null)
				? Restrictions.eq(Record.PARENT_ID, record.getParent().getId())
				: Restrictions.isNull(Record.PARENT_ID);
	}

} // Class
