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

package dz.alkhwarizmix.framework.java.interfaces;

import org.hibernate.criterion.DetachedCriteria;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.RecordList;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ ذو الحجة ١٤٣٥ (October 06, 2014)
 */
public interface IRecordService extends IAlKhwarizmixService {

	/**
	 */
	public RecordList commitRecordList(RecordList recordList,
			boolean validateObjectToPublish) throws AlKhwarizmixException;

	/**
	 */
	public String commitRecordListFromXML(String recordListXml)
			throws AlKhwarizmixException;

	/**
	 */
	public Record getRecord(Record record, boolean validateObjectToPublish)
			throws AlKhwarizmixException;

	/**
	 */
	public String getRecordAsXML(Record record) throws AlKhwarizmixException;

	/**
	 */
	public String getRecordAsXML(String partialXml)
			throws AlKhwarizmixException;

	/**
	 */
	public RecordList getRecordList(String schema, String table,
			DetachedCriteria criteriaToUse, int firstResult, int maxResult,
			boolean validateObjectToPublish) throws AlKhwarizmixException;

	/**
	 */
	public String getRecordListAsXML(String schema, String table,
			DetachedCriteria criteria, int firstResult, int maxResult)
			throws AlKhwarizmixException;

} // Interface
