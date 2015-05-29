////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.services.IRecordServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٩ صفر ١٤٣٦ (December 21, 2014)
 */
@Component
public class RecordServiceValidator extends
		AbstractAlKhwarizmixServiceValidator implements IRecordServiceValidator {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public RecordServiceValidator() {
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
			logger = LoggerFactory.getLogger(RecordServiceValidator.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToAdd(
			final AbstractAlKhwarizmixDomainObject object,
			final AlKhwarizmixDomainObject objectOwner)
			throws AlKhwarizmixException {
		super.validateObjectToAdd(object, objectOwner);
		final Record record = (Record) object;
		if (record != null) {
			nullifyPropertiesForRecordToAdd(record);
			record.setOwner(objectOwner);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToUpdate(
			final AbstractAlKhwarizmixDomainObject object,
			final AlKhwarizmixDomainObject objectOwner)
			throws AlKhwarizmixException {
		super.validateObjectToUpdate(object, objectOwner);
		final Record record = (Record) object;
		if (record != null)
			nullifyPropertiesForRecordToUpdate(record);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateObjectToPublish(
			final AbstractAlKhwarizmixDomainObject object,
			final AlKhwarizmixDomainObject objectOwner)
			throws AlKhwarizmixException {
		super.validateObjectToPublish(object, objectOwner);

		final Record record = (Record) object;
		if (record != null) {
			validateRecordOwner(record, objectOwner);
			nullifyPropertiesForRecordToPublish(record);
		}
	}

	/**
	 */
	private void nullifyPropertiesForRecordToAdd(final Record record) {
		record.setOwner(null);
		record.setGroup(null);
	}

	/**
	 */
	private void nullifyPropertiesForRecordToUpdate(final Record record) {
		record.setOwner(null);
		record.setGroup(null);
	}

	/**
	 */
	private void nullifyPropertiesForRecordToPublish(final Record record) {
		record.setOwner(null);
		record.setGroup(null);
	}

	/**
	 * TODO: Javadoc
	 */
	private void validateRecordOwner(final Record record,
			final AlKhwarizmixDomainObject recordOwner)
			throws AlKhwarizmixException {
		if (!recordOwner.equals(record.getOwner()))
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_UNAUTHORIZED);
	}

} // Class
