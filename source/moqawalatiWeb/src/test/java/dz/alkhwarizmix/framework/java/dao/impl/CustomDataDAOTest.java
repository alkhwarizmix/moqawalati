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

import org.hibernate.Transaction;
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
import dz.alkhwarizmix.framework.java.dao.ICustomDataDAO;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ شعبان ١٤٣٥ (June 10, 2014)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
@SuppressWarnings("PMD.MethodNamingConventions")
public class CustomDataDAOTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static final String CUSTOM_DATA_ID = "dz.alkhwarizmix.moqawalati.MoqawalatiDAOForXMLMarshallingTest";

	@Autowired
	private ICustomDataDAO utCustomDataDAO;

	private Transaction transaction;

	@Before
	public void setUp() throws AlKhwarizmixDAOException {
		transaction = utCustomDataDAO.beginTransaction();
	}

	@After
	public void tearDown() throws AlKhwarizmixDAOException {
		utCustomDataDAO.rollbackTransaction(transaction);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private String getStringOfSize(final int size) {
		String result = "123456789A_ADD";
		while (result.length() < size)
			result += result;
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	// ----- -----

	@Test
	public void test01_A_add_get_then_update_get_CustomData()
			throws AlKhwarizmixException {
		final AlKhwarizmixDomainObject customizer = new AlKhwarizmixDomainObject();

		addCustomData(customizer, "123456789A_ADD");
		CustomData customDataToGet = getCustomData(customizer);
		Assert.assertEquals("123456789A_ADD",
				customDataToGet.getCustomDataValue());

		updateCustomData(customDataToGet, "123456789A_UPDATE");
		customDataToGet = getCustomData(customizer);
		Assert.assertEquals("123456789A_UPDATE",
				customDataToGet.getCustomDataValue());
	}

	private CustomData addCustomData(final AlKhwarizmixDomainObject customizer,
			final String value) throws AlKhwarizmixException {
		final CustomData customDataToAdd = new CustomData();
		customDataToAdd.setCustomizer(customizer);
		customDataToAdd.setCustomDataId(CUSTOM_DATA_ID);
		customDataToAdd.setCustomDataValue(value);
		utCustomDataDAO.saveOrUpdate(customDataToAdd);
		final String s = customDataToAdd.getCustomDataValue();
		customDataToAdd.setCustomDataValue(s);
		return customDataToAdd;
	}

	private CustomData getCustomData(final AlKhwarizmixDomainObject customizer)
			throws AlKhwarizmixException {
		CustomData customDataToGet = new CustomData();
		customDataToGet.setCustomizer(customizer);
		customDataToGet.setCustomDataId(CUSTOM_DATA_ID);
		customDataToGet = utCustomDataDAO.getCustomData(customDataToGet);
		final String s = customDataToGet.getCustomDataValue();
		customDataToGet.setCustomDataValue(s);
		return customDataToGet;
	}

	private CustomData updateCustomData(final CustomData customDataToUpdate,
			final String value) throws AlKhwarizmixException {
		customDataToUpdate.setCustomDataValue(value);
		utCustomDataDAO.saveOrUpdate(customDataToUpdate);
		return customDataToUpdate;
	}

	// ----- -----

	@Test
	public void test01_B_add_get_then_update_get_CustomData_for_huge_data()
			throws AlKhwarizmixException {
		final AlKhwarizmixDomainObject customizer = new AlKhwarizmixDomainObject();

		String expected = getStringOfSize(128 * 3);

		addCustomData(customizer, expected);
		CustomData customDataToGet = getCustomData(customizer);
		String result = customDataToGet.getCustomDataValue();
		Assert.assertEquals(expected, result);

		expected = "123456789A_UPDATE";
		updateCustomData(customDataToGet, expected);
		customDataToGet = getCustomData(customizer);
		result = customDataToGet.getCustomDataValue();
		Assert.assertEquals(expected, result);
	}

	// ----- -----

	@Test
	public void test01_C_add_get_then_update_get_CustomData_with_final_value_is_empty_string()
			throws AlKhwarizmixException {
		final AlKhwarizmixDomainObject customizer = new AlKhwarizmixDomainObject();

		String expected = getStringOfSize(128 * 5);

		addCustomData(customizer, expected);
		CustomData customDataToGet = getCustomData(customizer);
		String result = customDataToGet.getCustomDataValue();
		Assert.assertEquals(expected, result);

		expected = "";
		updateCustomData(customDataToGet, expected);
		customDataToGet = getCustomData(customizer);
		result = customDataToGet.getCustomDataValue();
		Assert.assertEquals(expected, result);
	}

} // Class
