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

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

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

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	// EMPTY

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
	public void test01_recordId_setAndGet() {
		String valueToSet = "recordId1";
		utRecord.setRecordId(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getRecordId());
	}

	@Test
	public void test02_schemaName_setAndGet() {
		String valueToSet = "schemaName";
		utRecord.setSchemaName(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getSchemaName());
	}

	@Test
	public void test03_tableName_setAndGet() {
		String valueToSet = "tableName";
		utRecord.setTableName(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getTableName());
	}

	@Test
	public void test04_action_setAndGet() {
		Integer valueToSet = Record.INSERT_ACTION;
		utRecord.setAction(valueToSet);
		Assert.assertEquals(valueToSet, utRecord.getAction());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test02_clone() {
		Assert.assertTrue(false);
	}

} // Class
