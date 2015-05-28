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

package dz.alkhwarizmix.framework.java.services.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.ICustomDataDAO;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.model.impl.AlKhwarizmixSessionData;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٢ ربيع الأول ١٤٣٥ (January 03, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class CustomizerServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private CustomizerService utCustomizerService;

	@InjectMocks
	AlKhwarizmixSessionData sessionData;

	@Mock
	AlKhwarizmixDomainObject mockCustomizer;

	@Mock
	ICustomDataDAO mockCustomDataDAO;

	@Mock
	private Logger mockLogger;

	@Mock
	private CustomData mockCustomData;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() {
		Assert.assertNotNull(utCustomizerService);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest01_setCustomData() throws AlKhwarizmixException {
		/*
		 * when(mockAlKhwarizmixService.addObject(any(String.class)))
		 * .thenCallRealMethod();
		 * when(mockAlKhwarizmixService.getXMLUtil()).thenReturn(mockXMLUtil);
		 * when
		 * (mockXMLUtil.unmarshalObjectFromXML(any(String.class))).thenReturn(
		 * mockDomainObject1); when( mockAlKhwarizmixService.addObject(
		 * any(AbstractAlKhwarizmixDomainObject.class),
		 * anyBoolean())).thenReturn(mockDomainObject2);
		 */
	}

	@Ignore("TODO: TDD")
	@Test
	public void test01_A_setCustomData() throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_setCustomData();
		// TEST
		utCustomizerService.setCustomData(null, false);
		// ASSERTS
		Assert.assertEquals("", "");
	}

	// --------------------------------------------------------------------------

	private void setUpForTest02_setCustomDataFromXML()
			throws AlKhwarizmixException {
	}

	@Ignore("TODO: TDD")
	@Test
	public void test02_A_setCustomDataFromXML() throws AlKhwarizmixException {
		// SETUP
		setUpForTest02_setCustomDataFromXML();
		// TEST
		utCustomizerService.setCustomDataFromXML("");
		// ASSERTS
		Assert.assertEquals("", "");
	}

	// --------------------------------------------------------------------------

	private void setUpForTest03_getObject() throws AlKhwarizmixException {
	}

	@Ignore("TODO: TDD")
	@Test
	public void test03_A_getObject() throws AlKhwarizmixException {
		// SETUP
		setUpForTest03_getObject();
		// TEST
		utCustomizerService.getObject(null, false);
		// ASSERTS
		Assert.assertEquals("", "");
	}

	// --------------------------------------------------------------------------

	private void setUpForTest04_getCustomData() throws AlKhwarizmixException {
		sessionData.setSessionOwner(mockCustomizer);
		utCustomizerService.setSessionData(sessionData);
		utCustomizerService.setCustomDataDAO(mockCustomDataDAO);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test04_A_getCustomData() throws AlKhwarizmixException {
		// SETUP
		setUpForTest04_getCustomData();
		// TEST
		utCustomizerService.getCustomData(null, false);
		// ASSERTS
		Assert.assertEquals("", "");
	}

	@Ignore("TODO: TDD")
	@Test
	public void test04_B_getCustomData_should_return_default_if_not_found()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest04_getCustomData();
		Mockito.when(
				mockCustomDataDAO.getCustomData(Mockito.any(CustomData.class)))
				.thenReturn(null);
		final CustomData customData = new CustomData();
		customData.setCustomDataId("id12345");
		// TEST
		final CustomData result = utCustomizerService.getCustomData(customData,
				true);
		// ASSERTS
		Assert.assertEquals("Default", result.getCustomDataValue());
	}

	// --------------------------------------------------------------------------

	private void setUpForTest05_getCustomDataAsXML()
			throws AlKhwarizmixException {
	}

	@Ignore("TODO: TDD")
	@Test
	public void test05_A_getCustomDataAsXML() throws AlKhwarizmixException {
		// SETUP
		setUpForTest05_getCustomDataAsXML();
		// TEST
		utCustomizerService.getCustomDataAsXML(mockCustomData);
		// ASSERTS
		Assert.assertEquals("", "");
	}

	// --------------------------------------------------------------------------

	private void setUpForTest06_getCustomDataAsXML()
			throws AlKhwarizmixException {
	}

	@Ignore("TODO: TDD")
	@Test
	public void test06_A_getCustomDataAsXML() throws AlKhwarizmixException {
		// SETUP
		setUpForTest06_getCustomDataAsXML();
		// TEST
		utCustomizerService.getCustomDataAsXML("");
		// ASSERTS
		Assert.assertEquals("", "");
	}

	// --------------------------------------------------------------------------

	@Test
	public void test_getSessionOwner_should_return_getSessionData_getSessionOwner()
			throws AlKhwarizmixException {
		final AlKhwarizmixDomainObject mockSessionOwner = Mockito
				.mock(AlKhwarizmixDomainObject.class);
		sessionData.setSessionOwner(mockSessionOwner);
		utCustomizerService.setSessionData(sessionData);
		final AlKhwarizmixDomainObject result = utCustomizerService
				.getSessionOwner();
		Assert.assertEquals(mockSessionOwner, result);
	}

} // Class
