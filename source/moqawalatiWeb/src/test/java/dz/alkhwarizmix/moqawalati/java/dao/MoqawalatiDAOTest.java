////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.dao.AlKhwarizmixDAOException;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢١ محرم ١٤٣٥ (November 25, 2013)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "MoqawalatiDAOTest-context.xml")
@Transactional
public class MoqawalatiDAOTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static final String CUSTOM_DATA_ID = "dz.alkhwarizmix.moqawalati.java.dao.MoqawalatiDAOTest";

	@Autowired
	private MoqawalatiDAO utMoqawalatiDAO;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_saveOrUpdate_CustomData_then_get()
			throws AlKhwarizmixDAOException, MoqawalatiException {

		AlKhwarizmixDomainObject customizer = new AlKhwarizmixDomainObject();
		addCustomData(customizer);
		CustomData customDataToGet = getCustomData(customizer);
		Assert.assertEquals("123456789A_ADD",
				customDataToGet.getCustomDataValue());

		updateCustomData(customizer);
		customDataToGet = getCustomData(customizer);
		Assert.assertEquals("123456789A_UPDATE",
				customDataToGet.getCustomDataValue());
	}

	private CustomData addCustomData(AlKhwarizmixDomainObject customizer)
			throws AlKhwarizmixDAOException {

		CustomData customDataToAdd = new CustomData();
		customDataToAdd.setCustomizer(customizer);
		customDataToAdd.setCustomDataId(CUSTOM_DATA_ID);
		customDataToAdd.setCustomDataValue("123456789A_ADD");
		utMoqawalatiDAO.saveOrUpdate(customDataToAdd);
		return customDataToAdd;
	}

	private CustomData getCustomData(AlKhwarizmixDomainObject customizer)
			throws MoqawalatiException {

		CustomData customDataToGet = new CustomData();
		customDataToGet.setCustomizer(customizer);
		customDataToGet.setCustomDataId(CUSTOM_DATA_ID);
		customDataToGet = utMoqawalatiDAO.getCustomData(customDataToGet);
		return customDataToGet;
	}

	private CustomData updateCustomData(AlKhwarizmixDomainObject customizer)
			throws AlKhwarizmixDAOException {

		CustomData customDataToUpdate = new CustomData();
		customDataToUpdate.setCustomizer(customizer);
		customDataToUpdate.setCustomDataId(CUSTOM_DATA_ID);
		customDataToUpdate.setCustomDataValue("123456789A_UPDATE");
		utMoqawalatiDAO.saveOrUpdate(customDataToUpdate);
		return customDataToUpdate;
	}

} // Class