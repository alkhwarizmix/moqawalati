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
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Encryption;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ذو الحجة ١٤٣٥ (October 02, 2014)
 */
@Repository
public class RecordDAO extends AbstractAlKhwarizmixDAOForXMLMarshalling
		implements IRecordDAO {

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
	public void saveOrUpdate(final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		if (object instanceof Record) {
			final Record record = (Record) object;
			try {
				setupTableAndSchema(record);
			} catch (final AlKhwarizmixException e) {
				throw getDAOException(e);
			}
		}
		super.saveOrUpdate(object);
	}

	private void setupTableAndSchema(final Record record)
			throws AlKhwarizmixException {
		if (record.isSchemaRecord())
			getLogger().warn("setupTableAndSchema: record.isSchemaRecord()");
		else if (record.isTableRecord())
			// record is Table
			getLogger().warn("setupTableAndSchema: record.isTableRecord()");
		else // record is Table row
		if (record.getParent() == null)
			getRecord_internal(record);
		else if (record.getParent().getParent() == null)
			getRecord_internal(record);
	}

	/**
	 */
	@Override
	public Record getRecord(final Record recordToGet)
			throws AlKhwarizmixException {
		getLogger().trace("getRecord()");
		final Record result = getRecord_internal((Record) recordToGet.clone());
		return ((result == null) || (result.getId() == null)
				? null
				: result);
	}

	private Record getRecord_internal(final Record recordToGet)
			throws AlKhwarizmixException {
		Record result = null;
		Record tableRecord = null;
		final Record schemaRecord = getSchemaRecord(recordToGet);
		if (schemaRecord != null)
			if (recordToGet.isSchemaRecord())
				result = schemaRecord;
			else
				tableRecord = getTableRecord(recordToGet, schemaRecord);
		if (tableRecord != null)
			if (recordToGet.isTableRecord())
				result = tableRecord;
			else {
				recordToGet.setParent(tableRecord);
				result = getRecordFromHibernate(recordToGet);
			}
		return result;
	}

	private Record getSchemaRecord(final Record record)
			throws AlKhwarizmixException {
		final Record existingRecord = getRecordFromHibernate(record
				.getSchemaRecord());
		return (existingRecord != null
				? existingRecord
				: record.getSchemaRecord());
	}

	private Record getTableRecord(final Record record, final Record schemaRecord)
			throws AlKhwarizmixException {
		final Record recordToFind = record.getTableRecord();
		recordToFind.setParent(schemaRecord);
		final Record existingRecord = getRecordFromHibernate(recordToFind);
		return (existingRecord != null
				? existingRecord
				: recordToFind);
	}

	private Record getRecordFromHibernate(final Record recordToGet)
			throws AlKhwarizmixException {
		try {
			Record result = null;
			final Criteria criteria = getHibernateCurrentSession()
					.createCriteria(Record.class);
			criteria.add(Restrictions.and(
					getEqualsRecordIdCriterion(recordToGet),
					getEqualsParentCriterion(recordToGet)));
			result = (Record) criteria.uniqueResult();
			return result;
		} catch (final DataAccessException e) {
			final AlKhwarizmixException ex = new AlKhwarizmixException(
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

	/**
	 */
	@Override
	public Encryption getEncryption(final Encryption encryptionToGet)
			throws AlKhwarizmixException {
		try {
			Encryption result = null;
			final Criteria criteria = getHibernateCurrentSession()
					.createCriteria(Encryption.class);
			criteria.add(Restrictions.and(
					getEqualsEncryptionIdCriterion(encryptionToGet),
					getEqualsUserCriterion(encryptionToGet)));
			result = (Encryption) criteria.uniqueResult();
			return result;
		} catch (final DataAccessException e) {
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
		}
	}

	private Criterion getEqualsEncryptionIdCriterion(final Encryption encryption) {
		return Restrictions.eq(Encryption.ENCRYPTIONID,
				encryption.getEncryptionId());
	}

	private Criterion getEqualsUserCriterion(final Encryption encryption) {
		return Restrictions.eq(Encryption.USERID, encryption.getUser().getId());
	}

} // Class
