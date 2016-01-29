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

package dz.alkhwarizmix.framework.java.dtos.record.model.vo;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.Encryption;
import dz.alkhwarizmix.framework.java.utils.DateUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ذو الحجة ١٤٣٥ (October 02, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class RecordTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private Record utRecord;

	@BeforeClass
	static public void static_setUp() {
		final DateUtil mockDateUtil = Mockito.mock(DateUtil.class);
		Mockito.when(mockDateUtil.newDate()).thenReturn(new Date(1234));
		AbstractAlKhwarizmixDomainObject.dateUtil = mockDateUtil;
	}

	@AfterClass
	static public void static_tearDown() {
		AbstractAlKhwarizmixDomainObject.dateUtil = null;
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private void setDataForRecordWithId(final Record record, final int id) {
		record.setId(new Long(id));
		record.setRecordId("recordId" + id);
		record.setSchemaName("SchemaName" + id);
		record.setTableName("TableName" + id);
		record.setParent(new Record());
		record.getParent().setId(new Long(id + 1));
		record.setAction(Record.INSERT_ACTION);
	}

	private void assertEqualRecords(final Record expectedRecord,
			final Record cloneRecord, final boolean testDeep) {
		Assert.assertEquals("recordId", expectedRecord.getRecordId(),
				cloneRecord.getRecordId());
		Assert.assertEquals("schemaName", expectedRecord.getSchemaName(),
				cloneRecord.getSchemaName());
		Assert.assertEquals("tableName", expectedRecord.getTableName(),
				cloneRecord.getTableName());
		Assert.assertEquals("action", expectedRecord.getAction(),
				cloneRecord.getAction());
		Assert.assertEquals("parent", expectedRecord.getParent(),
				cloneRecord.getParent());
		if (testDeep)
			Assert.assertEquals("parent id",
					expectedRecord.getParent().getId(), cloneRecord.getParent()
							.getId());
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() {
		Assert.assertNotNull(utRecord);
	}

	@Test
	public void test00_B_Cloneable() {
		Assert.assertTrue(utRecord instanceof Cloneable);
	}

	@Test
	public void test01_recordId_setAndGet() {
		final String valueToSet = "recordId1";
		utRecord.setRecordId(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getRecordId());
	}

	@Test
	public void test02_schemaName_setAndGet() {
		final String valueToSet = "schemaName";
		utRecord.setSchemaName(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getSchemaName());
	}

	@Test
	public void test03_tableName_setAndGet() {
		final String valueToSet = "tableName";
		utRecord.setTableName(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getTableName());
	}

	@Test
	public void test04_action_setAndGet() {
		final Integer valueToSet = Record.INSERT_ACTION;
		utRecord.setAction(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getAction());
	}

	@Test
	public void test05_encryption_setAndGet() {
		final Encryption valueToSet = new Encryption();
		utRecord.setEncryption(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getEncryption());
	}

	@Test
	public void test06_A_clone_null_properties() {
		// SetUp
		final Record expectedRecord = new Record();
		utRecord = new Record();
		// Test
		final Record cloneRecord = (Record) utRecord.clone();
		// Others
		setDataForRecordWithId(utRecord, 1567);
		// Asserts
		assertEqualRecords(expectedRecord, cloneRecord, false);
	}

	@Test
	public void test06_B_clone() {
		// SetUp
		final Record expectedRecord = new Record();
		setDataForRecordWithId(expectedRecord, 7651);
		setDataForRecordWithId(utRecord, 7651);
		// Test
		final Record cloneRecord = (Record) utRecord.clone();
		// Others
		setDataForRecordWithId(utRecord, 1569);
		// Asserts
		assertEqualRecords(expectedRecord, cloneRecord, true);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test07_toString_TDD() {
		Assert.assertTrue(false);
	}

	@Test
	public void test08_hashCode() {
		Assert.assertEquals(-1787616399, utRecord.hashCode());
		setDataForRecordWithId(utRecord, 7953);
		Assert.assertEquals(1331481537, utRecord.hashCode());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test09_equals_TDD() {
		Assert.assertTrue(false);
	}

} // Class
