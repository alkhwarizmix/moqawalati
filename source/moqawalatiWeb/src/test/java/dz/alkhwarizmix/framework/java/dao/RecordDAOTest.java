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

package dz.alkhwarizmix.framework.java.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.interfaces.IRecordDAO;

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

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private Record newRecord() {
		Record record = new Record("recordId");
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

		utRecordDAO.saveOrUpdate(newRecord());

		Record savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertNotNull(savedRecord);
		Assert.assertEquals(newRecord().getRecordId(),
				savedRecord.getRecordId());
		Assert.assertEquals(newRecord().getParent(), savedRecord.getParent());
		// Assert.assertNotNull(savedRecord.getDomainObject());

		savedRecord.setParent(new Record("updatedParent"));
		utRecordDAO.saveOrUpdate(savedRecord);

		savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertEquals("updatedParent", savedRecord.getParent()
				.getRecordId());
	}

	// ----- -----

	@Test
	public void test01_B_add_get_then_update_get_Record_using_clear_and_flush()
			throws AlKhwarizmixException {

		Assert.assertNull(utRecordDAO.getRecord(newRecord()));

		utRecordDAO.saveOrUpdate(newRecord());
		utRecordDAO.flush();
		utRecordDAO.clear();

		Record savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertNotNull(savedRecord);
		Assert.assertEquals(newRecord().getRecordId(),
				savedRecord.getRecordId());
		Assert.assertEquals(newRecord().getParent(), savedRecord.getParent());
		// Assert.assertNotNull(savedRecord.getDomainObject());

		savedRecord.setParent(new Record("updatedParent"));
		utRecordDAO.saveOrUpdate(savedRecord);
		utRecordDAO.flush();
		utRecordDAO.clear();

		savedRecord = utRecordDAO.getRecord(newRecord());
		Assert.assertEquals("updatedParent", savedRecord.getParent()
				.getRecordId());
	}

} // Class
