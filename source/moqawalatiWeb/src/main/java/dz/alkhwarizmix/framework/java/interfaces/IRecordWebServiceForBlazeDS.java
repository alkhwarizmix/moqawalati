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

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.RecordList;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٠ ذو الحجة ١٤٣٥ (October 14, 2014)
 */
public interface IRecordWebServiceForBlazeDS {

	/**
	 */
	public void commitRecordList(RecordList recordList)
			throws AlKhwarizmixException;

	/**
	 */
	public Record getRecord(Record record) throws AlKhwarizmixException;

	/**
	 */
	public RecordList getRecordList(String schemaName, String tableName,
			int firstResult, int maxResult) throws AlKhwarizmixException;

} // Class
