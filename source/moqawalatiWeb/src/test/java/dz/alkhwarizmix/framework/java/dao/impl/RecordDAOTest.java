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

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IRecordDAO;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ذو الحجة ١٤٣٥ (October 02, 2014)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@SuppressWarnings("PMD.MethodNamingConventions")
public class RecordDAOTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IRecordDAO utRecordDAO;

	String schema1Name;
	String schema1RecordId;

	String schema2Name;
	String schema2RecordId;

	String table1Name;
	String table1RecordId;

	String table2Name;
	String table2RecordId;

	@Before
	public void setUp() {
		schema1Name = "schema1";
		schema1RecordId = "_S_" + schema1Name;
		schema2Name = "schema2";
		schema2RecordId = "_S_" + schema2Name;
		table1Name = "table1";
		table1RecordId = "_T_" + table1Name;
		table2Name = "table2";
		table2RecordId = "_T_" + table2Name;
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private Record newRecord() {
		Record record = new Record("recordId", schema1Name, table1Name);
		return record;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	// ----- -----

	@Test
	public void test01_A_add_get_then_update_get_Record()
			throws AlKhwarizmixException {
		Assert.assertNull(utRecordDAO.getRecord(newRecord()));
		// add
		utRecordDAO.saveOrUpdate(newRecord());
		// get
		Record savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertNotNull(savedRecord);
		Assert.assertEquals(newRecord().getRecordId(),
				savedRecord.getRecordId());
		Assert.assertNotNull(savedRecord.getParent());
		Assert.assertNotNull(savedRecord.getParent().getParent());
		// update
		savedRecord.setData("Updated Data 2546");
		utRecordDAO.saveOrUpdate(savedRecord);
		// get
		savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertEquals("Updated Data 2546", savedRecord.getData());
	}

	@Test
	public void test01_B_add_get_then_update_get_Record_using_clear_and_flush()
			throws AlKhwarizmixException {
		Assert.assertNull(utRecordDAO.getRecord(newRecord()));
		// add
		utRecordDAO.saveOrUpdate(newRecord());
		utRecordDAO.flush();
		utRecordDAO.clear();
		// get
		Record savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertNotNull(savedRecord);
		Assert.assertEquals(newRecord().getRecordId(),
				savedRecord.getRecordId());
		Assert.assertEquals(newRecord().getData(), savedRecord.getData());
		// update
		savedRecord.setData("Update Data 3445");
		utRecordDAO.saveOrUpdate(savedRecord);
		utRecordDAO.flush();
		utRecordDAO.clear();
		// get
		savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertEquals("Update Data 3445", savedRecord.getData());
	}

	// ----- -----

	@Test
	public void test02_A_add_record_should_add_schema_and_table()
			throws AlKhwarizmixException {
		Record record = new Record("recordId", schema1Name, table1Name);
		// Test
		utRecordDAO.saveOrUpdate(record);
		// Asserts
		Record tableRecord = record.getParent();
		Assert.assertNotNull(tableRecord);
		Assert.assertNotNull(tableRecord.getId());
		Assert.assertEquals(schema1Name, tableRecord.getSchemaName());
		Assert.assertEquals(table1Name, tableRecord.getTableName());
		Assert.assertEquals(table1RecordId, tableRecord.getRecordId());
		Record schemaRecord = tableRecord.getParent();
		Assert.assertNotNull(schemaRecord);
		Assert.assertNotNull(schemaRecord.getId());
		Assert.assertEquals(schema1Name, schemaRecord.getSchemaName());
		Assert.assertNull(schemaRecord.getTableName());
		Assert.assertEquals(schema1RecordId, schemaRecord.getRecordId());
	}

	@Test
	public void test02_B_add_record_should_add_schema_and_table()
			throws AlKhwarizmixException {
		Record schemaRecord1 = new Record(schema1RecordId, schema1Name);
		Record tableRecord1 = new Record(table1RecordId, schema1Name,
				table1Name);
		Assert.assertNull(utRecordDAO.getRecord(schemaRecord1));
		Assert.assertNull(utRecordDAO.getRecord(tableRecord1));
		Record record = new Record("recordId", schema1Name, table1Name);
		Assert.assertNull(utRecordDAO.getRecord(record));
		// Test
		utRecordDAO.saveOrUpdate(record);
		// Asserts
		Assert.assertNotNull(utRecordDAO.getRecord(schemaRecord1));
		Assert.assertNotNull(utRecordDAO.getRecord(tableRecord1));
		Assert.assertNotNull(utRecordDAO.getRecord(record));
	}

	// ----- -----

	@Test
	public void test03_add_record_when_schema_exists()
			throws AlKhwarizmixException {
		// Setup
		Record record1 = new Record("recordId1", schema1Name, table1Name);
		Record record2 = new Record("recordId2", schema1Name, table2Name);
		utRecordDAO.saveOrUpdate(record1);
		// Test
		utRecordDAO.saveOrUpdate(record2);
		// Asserts
		Assert.assertNotNull(utRecordDAO.getRecord(record2));
	}

	// ----- -----

	@Test
	public void test04_add_record_when_schema_and_table_exist()
			throws AlKhwarizmixException {
		// Setup
		Record record1 = new Record("recordId1", schema1Name, table1Name);
		Record record2 = new Record("recordId2", schema1Name, table1Name);
		utRecordDAO.saveOrUpdate(record1);
		// Test
		utRecordDAO.saveOrUpdate(record2);
		// Asserts
		Assert.assertNotNull(utRecordDAO.getRecord(record2));
	}

	// ----- -----

	@Test
	public void test05_should_be_able_to_add_same_recordId_for_different_tables()
			throws AlKhwarizmixException {
		// Setup
		Record record1 = new Record("recordId", schema1Name, table1Name);
		Record record2 = new Record("recordId", schema1Name, table2Name);
		utRecordDAO.saveOrUpdate(record1);
		// Test
		utRecordDAO.saveOrUpdate(record2);
		// Asserts
		Assert.assertNotNull(utRecordDAO.getRecord(record2));
	}

	// ----- -----

	@Test
	public void test06_should_be_able_to_add_same_recordId_for_different_schemas()
			throws AlKhwarizmixException {
		// Setup
		Record record1 = new Record("recordId", schema1Name, table1Name);
		Record record2 = new Record("recordId", schema2Name, table1Name);
		utRecordDAO.saveOrUpdate(record1);
		// Test
		utRecordDAO.saveOrUpdate(record2);
		// Asserts
		Assert.assertNotNull(utRecordDAO.getRecord(record2));
	}

} // Class
