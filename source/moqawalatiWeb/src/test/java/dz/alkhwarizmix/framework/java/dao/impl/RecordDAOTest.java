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

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IRecordDAO;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Encryption;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

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

	private Transaction transaction;

	@Before
	public void setUp() throws AlKhwarizmixDAOException {
		schema1Name = "schema1";
		schema1RecordId = "_S_" + schema1Name;
		schema2Name = "schema2";
		schema2RecordId = "_S_" + schema2Name;
		table1Name = "table1";
		table1RecordId = "_T_" + table1Name;
		table2Name = "table2";
		table2RecordId = "_T_" + table2Name;
		transaction = utRecordDAO.beginTransaction();
	}

	@After
	public void tearDown() throws AlKhwarizmixDAOException {
		utRecordDAO.rollbackTransaction(transaction);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private Record newRecord(final int id, final AlKhwarizmixDomainObject owner) {
		final Record record = new Record("recordId" + id, schema1Name,
				table1Name);
		record.setOwner(owner);
		return record;
	}

	private Record newRecord() {
		return newRecord(0, null);
	}

	private AlKhwarizmixDomainObject insertNewOwner()
			throws AlKhwarizmixException {
		final AlKhwarizmixDomainObject newOwner = new AlKhwarizmixDomainObject();
		utRecordDAO.saveOrUpdate(newOwner);
		return newOwner;
	}

	private Record insertNewRecord(final int id,
			final AlKhwarizmixDomainObject owner, final String data)
			throws AlKhwarizmixException {
		final Record newRecord = newRecord(id, owner);
		newRecord.setData(data);
		utRecordDAO.saveOrUpdate(newRecord);
		return newRecord;
	}

	private Record newRecordS1T1() {
		return new Record("recordId", schema1Name, table1Name);
	}

	private Record newRecordS1T2() {
		return new Record("recordId", schema1Name, table2Name);
	}

	private String getStringOfSize(final int size) {
		String result = "123456789A_ADD";
		while (result.length() < size)
			result += result;
		return result;
	}

	private Encryption newEncryption() {
		final Encryption result = new Encryption();
		final User user = new User("fbelhaouas@alkhwarizmix.com", "Fares");
		result.setUser(user);
		result.setEncryptionId("CryptoUtilV2");
		return result;
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
		final Record record = newRecordS1T1();
		// Test
		utRecordDAO.saveOrUpdate(record);
		// Asserts
		final Record tableRecord = record.getParent();
		Assert.assertNotNull(tableRecord);
		Assert.assertNotNull(tableRecord.getId());
		Assert.assertEquals(schema1Name, tableRecord.getSchemaName());
		Assert.assertEquals(table1Name, tableRecord.getTableName());
		Assert.assertEquals(table1RecordId, tableRecord.getRecordId());
		final Record schemaRecord = tableRecord.getParent();
		Assert.assertNotNull(schemaRecord);
		Assert.assertNotNull(schemaRecord.getId());
		Assert.assertEquals(schema1Name, schemaRecord.getSchemaName());
		Assert.assertNull(schemaRecord.getTableName());
		Assert.assertEquals(schema1RecordId, schemaRecord.getRecordId());
	}

	@Test
	public void test02_B_add_record_should_add_schema_and_table()
			throws AlKhwarizmixException {
		final Record schemaRecord1 = new Record(schema1RecordId, schema1Name);
		final Record tableRecord1 = new Record(table1RecordId, schema1Name,
				table1Name);
		Assert.assertNull(utRecordDAO.getRecord(schemaRecord1));
		Assert.assertNull(utRecordDAO.getRecord(tableRecord1));
		final Record record = newRecordS1T1();
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
		final Record record1 = new Record("recordId1", schema1Name, table1Name);
		final Record record2 = new Record("recordId2", schema1Name, table2Name);
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
		final Record record1 = new Record("recordId1", schema1Name, table1Name);
		final Record record2 = new Record("recordId2", schema1Name, table1Name);
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
		final Record record1 = newRecordS1T1();
		final Record record2 = newRecordS1T2();
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
		final Record record1 = newRecordS1T1();
		final Record record2 = new Record("recordId", schema2Name, table1Name);
		utRecordDAO.saveOrUpdate(record1);
		// Test
		utRecordDAO.saveOrUpdate(record2);
		// Asserts
		Assert.assertNotNull(utRecordDAO.getRecord(record2));
	}

	// ----- -----

	@Test
	public void test07_getList() throws AlKhwarizmixException {
		// Setup
		final String data1 = getStringOfSize(128 * 3);
		final AlKhwarizmixDomainObject owner1 = insertNewOwner();
		final Record record1 = insertNewRecord(1, owner1, data1);
		insertNewRecord(2, insertNewOwner(), getStringOfSize(128 * 5));
		// Setup Criteria
		final Record tableRecord = utRecordDAO.getRecord(record1
				.getTableRecord());
		final DetachedCriteria criteria = DetachedCriteria
				.forClass(Record.class);
		criteria.addOrder(Order.asc(Record.RECORDID));
		criteria.add(Restrictions.eq(Record.PARENT_ID, tableRecord.getId()));
		criteria.add(Restrictions.eq(Record.OWNER_ID, owner1.getId()));
		// Test
		@SuppressWarnings("unchecked")
		final List<Record> listOfRecords = utRecordDAO.getList(criteria, 0,
				1000);
		// Asserts
		Assert.assertNotNull(listOfRecords);
		Assert.assertEquals(1, listOfRecords.size());
		Assert.assertEquals(record1.getRecordId(), listOfRecords.get(0)
				.getRecordId());
		Assert.assertEquals(data1, listOfRecords.get(0).getData());
	}

	// ----- -----

	@Test
	public void test08_add_record_with_encryption()
			throws AlKhwarizmixException {
		Assert.assertNull(utRecordDAO.getRecord(newRecord()));
		// Setup
		final Encryption encryption = newEncryption();
		final Record record = newRecord();
		record.setEncryption(encryption);
		// Test
		utRecordDAO.saveOrUpdate(encryption.getUser());
		utRecordDAO.saveOrUpdate(encryption);
		utRecordDAO.saveOrUpdate(record);
		// Asserts
		final Record savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertNotNull(savedRecord);
		Assert.assertNotNull(savedRecord.getEncryption());
		Assert.assertEquals("CryptoUtilV2", savedRecord.getEncryption()
				.getEncryptionId());
	}

	// ----- -----

	@Test
	public void test09_add_then_get_Encryption() throws AlKhwarizmixException {
		Assert.assertNull(utRecordDAO.getEncryption(newEncryption()));
		// add
		final Encryption encryptionToAdd = newEncryption();
		utRecordDAO.saveOrUpdate(encryptionToAdd.getUser());
		utRecordDAO.saveOrUpdate(encryptionToAdd);
		// get
		final Encryption encryptionToFind = newEncryption();
		encryptionToFind.setUser(encryptionToAdd.getUser());
		final Encryption savedEncryption = utRecordDAO
				.getEncryption(encryptionToFind);
		// Asserts
		Assert.assertNotNull(savedEncryption);
		Assert.assertEquals(encryptionToAdd.getEncryptionId(),
				savedEncryption.getEncryptionId());
		Assert.assertNotNull(savedEncryption.getUser());
		Assert.assertEquals(encryptionToAdd.getUser().getUserId(),
				savedEncryption.getUser().getUserId());
		Assert.assertEquals(encryptionToAdd.getUser().getId(), savedEncryption
				.getUser().getId());
	}

} // Class
