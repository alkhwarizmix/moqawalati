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

package dz.alkhwarizmix.moqawalati.java.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.user.model.vo.User;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.interfaces.IMoqawalatiDAOForXMLMarshalling;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٠ شعبان ١٤٣٥ (June 08, 2014)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class MoqawalatiDAOForXMLMarshallingTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static final String CUSTOM_DATA_ID = "dz.alkhwarizmix.moqawalati.java.dao.MoqawalatiDAOForXMLMarshallingTest";

	@Autowired
	private IMoqawalatiDAOForXMLMarshalling utMoqawalatiDAOForXMLMarshalling;

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private User newUser() {
		User user = new User();
		user.setUserId("userId");
		user.setName("userName");
		user.setCreatorId("creatorId");
		return user;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	// ----- -----

	@Test
	public void test01_add_get_then_update_get_CustomData()
			throws AlKhwarizmixException {

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
			String value) throws AlKhwarizmixException {

		CustomData customDataToAdd = new CustomData();
		customDataToAdd.setCustomizer(customizer);
		customDataToAdd.setCustomDataId(CUSTOM_DATA_ID);
		customDataToAdd.setCustomDataValue(value);
		utMoqawalatiDAOForXMLMarshalling.saveOrUpdate(customDataToAdd);
		return customDataToAdd;
	}

	private CustomData getCustomData(AlKhwarizmixDomainObject customizer)
			throws MoqawalatiException {

		CustomData customDataToGet = new CustomData();
		customDataToGet.setCustomizer(customizer);
		customDataToGet.setCustomDataId(CUSTOM_DATA_ID);
		customDataToGet = utMoqawalatiDAOForXMLMarshalling
				.getCustomData(customDataToGet);
		return customDataToGet;
	}

	private CustomData updateCustomData(CustomData customDataToUpdate,
			String value) throws AlKhwarizmixException {

		customDataToUpdate.setCustomDataValue(value);
		utMoqawalatiDAOForXMLMarshalling.saveOrUpdate(customDataToUpdate);
		return customDataToUpdate;
	}

	// ----- -----

	@Test
	public void test02_A_add_get_then_update_get_User()
			throws AlKhwarizmixException {

		Assert.assertNull(utMoqawalatiDAOForXMLMarshalling.getUser(newUser()));

		utMoqawalatiDAOForXMLMarshalling.saveOrUpdate(newUser());

		User savedUser = utMoqawalatiDAOForXMLMarshalling.getUser(newUser());
		Assert.assertNotNull(savedUser);
		Assert.assertEquals(newUser().getUserId(), savedUser.getUserId());
		Assert.assertEquals(newUser().getExtendedDataValue(),
				savedUser.getExtendedDataValue());
		Assert.assertNotNull(savedUser.getDomainObject());

		savedUser.setExtendedDataValue("updatedName");
		utMoqawalatiDAOForXMLMarshalling.saveOrUpdate(savedUser);

		savedUser = utMoqawalatiDAOForXMLMarshalling.getUser(newUser());
		Assert.assertEquals("updatedName", savedUser.getExtendedDataValue());
	}

	// ----- -----

	@Test
	public void test02_B_add_get_then_update_get_User_using_clear_and_flush()
			throws AlKhwarizmixException {

		Assert.assertNull(utMoqawalatiDAOForXMLMarshalling.getUser(newUser()));

		utMoqawalatiDAOForXMLMarshalling.saveOrUpdate(newUser());
		utMoqawalatiDAOForXMLMarshalling.flush();

		User savedUser = utMoqawalatiDAOForXMLMarshalling.getUser(newUser());
		utMoqawalatiDAOForXMLMarshalling.clear();
		Assert.assertNotNull(savedUser);
		Assert.assertEquals(newUser().getUserId(), savedUser.getUserId());
		Assert.assertEquals(newUser().getExtendedDataValue(),
				savedUser.getExtendedDataValue());
		Assert.assertNotNull(savedUser.getDomainObject());

		savedUser.setExtendedDataValue("updatedName");
		utMoqawalatiDAOForXMLMarshalling.saveOrUpdate(savedUser);
		utMoqawalatiDAOForXMLMarshalling.flush();

		savedUser = utMoqawalatiDAOForXMLMarshalling.getUser(newUser());
		utMoqawalatiDAOForXMLMarshalling.clear();
		Assert.assertEquals("updatedName", savedUser.getExtendedDataValue());
	}

} // Class