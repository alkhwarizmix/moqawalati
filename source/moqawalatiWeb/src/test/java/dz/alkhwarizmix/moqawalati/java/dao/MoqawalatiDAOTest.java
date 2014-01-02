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
import dz.alkhwarizmix.moqawalati.java.interfaces.IMoqawalatiDAO;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢١ محرم ١٤٣٥ (November 25, 2013)
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from
// "classpath:/dz/alkhwarizmix/moqawalati/java/dao/MoqawalatiDAOTest-context.xml"
@ContextConfiguration
// (value = "MoqawalatiDAOTest-context.xml")
@Transactional
public class MoqawalatiDAOTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static final String CUSTOM_DATA_ID = "dz.alkhwarizmix.moqawalati.java.dao.MoqawalatiDAOTest";

	@Autowired
	private IMoqawalatiDAO utMoqawalatiDAO;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_add_get_then_update_get_CustomData()
			throws AlKhwarizmixDAOException, MoqawalatiException {

		AlKhwarizmixDomainObject customizer = new AlKhwarizmixDomainObject();

		addCustomData(customizer, "123456789A_ADD");
		CustomData customDataToGet = getCustomData(customizer);
		Assert.assertEquals("123456789A_ADD",
				customDataToGet.getCustomDataValue());

		updateCustomData(customDataToGet, "123456789A_UPDATE");
		customDataToGet = getCustomData(customizer);
		Assert.assertEquals("123456789A_UPDATE",
				customDataToGet.getCustomDataValue());
	}

	private CustomData addCustomData(AlKhwarizmixDomainObject customizer,
			String value) throws AlKhwarizmixDAOException {

		CustomData customDataToAdd = new CustomData();
		customDataToAdd.setCustomizer(customizer);
		customDataToAdd.setCustomDataId(CUSTOM_DATA_ID);
		customDataToAdd.setCustomDataValue(value);
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

	private CustomData updateCustomData(CustomData customDataToUpdate,
			String value) throws AlKhwarizmixDAOException {

		customDataToUpdate.setCustomDataValue(value);
		utMoqawalatiDAO.saveOrUpdate(customDataToUpdate);
		return customDataToUpdate;
	}

} // Class