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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import dz.alkhwarizmix.framework.java.utils.IXMLUtil;

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

	@Mock
	private CustomizerService mockCustomizerService;

	@Mock
	private Logger mockLogger;

	@Mock
	private IXMLUtil mockXMLUtil;

	@InjectMocks
	AlKhwarizmixSessionData sessionData;

	@Mock
	AlKhwarizmixDomainObject mockCustomizer;

	@Mock
	ICustomDataDAO mockCustomDataDAO;

	@Mock
	private CustomData mockCustomData1, mockCustomData2, mockCustomData3;

	@Before
	public void setUp() {
		when(mockCustomizerService.getLogger()).thenReturn(mockLogger);
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() {
		final CustomizerService utCustomizerService = new CustomizerService();
		Assert.assertNotNull(utCustomizerService);
		Assert.assertNotNull(utCustomizerService.getLogger());
		Assert.assertEquals(
				"dz.alkhwarizmix.framework.java.services.impl.CustomizerService",
				utCustomizerService.getLogger().getName());
	}

	// --------------------------------------------------------------------------

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private void setUpForTest01_setCustomData() throws AlKhwarizmixException {
		when(
				mockCustomizerService.setCustomData(any(CustomData.class),
						anyBoolean())).thenCallRealMethod();
	}

	@Test
	public void test01_A_setCustomData_should_getCustomData()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_setCustomData();
		// TEST
		mockCustomizerService.setCustomData(mockCustomData1, true);
		// ASSERTS
		verify(mockCustomizerService, times(1)).getCustomData(mockCustomData1,
				false);
	}

	@Test
	public void test01_B_setCustomData_should_updateFrom()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_setCustomData();
		when(mockCustomizerService.getCustomData(mockCustomData1, false))
				.thenReturn(mockCustomData2);
		when(mockCustomizerService.addObject(mockCustomData2, true))
				.thenReturn(mockCustomData3);
		// TEST
		final CustomData result = mockCustomizerService.setCustomData(
				mockCustomData1, true);
		// ASSERTS
		Assert.assertEquals(mockCustomData3, result);
		verify(mockCustomData2, times(1)).updateFrom(mockCustomData1);
		verify(mockCustomizerService, times(1))
				.addObject(mockCustomData2, true);
	}

	@Test
	public void test01_C_setCustomData_should_validateObjectToPublish()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_setCustomData();
		when(mockCustomizerService.addObject(mockCustomData1, true))
				.thenReturn(mockCustomData2);
		// TEST
		final CustomData result = mockCustomizerService.setCustomData(
				mockCustomData1, true);
		// ASSERTS
		Assert.assertEquals(mockCustomData2, result);
		verify(mockCustomData2, times(0)).updateFrom(mockCustomData1);
		verify(mockCustomizerService, times(1))
				.addObject(mockCustomData1, true);
	}

	@Test
	public void test01_D_setCustomData_should_not_validateObjectToPublish()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_setCustomData();
		when(mockCustomizerService.addObject(mockCustomData1, false))
				.thenReturn(mockCustomData2);
		mockCustomizerService.setCustomData(mockCustomData1, false);
		// ASSERTS
		verify(mockCustomizerService, times(1)).addObject(mockCustomData1,
				false);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest02_setCustomDataFromXML()
			throws AlKhwarizmixException {
		when(mockCustomizerService.setCustomDataFromXML(any(String.class)))
				.thenCallRealMethod();
		when(mockCustomizerService.getXMLUtil()).thenReturn(mockXMLUtil);
		when(mockXMLUtil.unmarshalObjectFromXML(any(String.class))).thenReturn(
				mockCustomData1);
		when(
				mockCustomizerService.setCustomData(any(CustomData.class),
						anyBoolean())).thenReturn(mockCustomData2);
	}

	@Test
	public void test02_A_setCustomDataFromXML_should_unmarshalObjectFromXML()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest02_setCustomDataFromXML();
		// TEST
		mockCustomizerService.setCustomDataFromXML("XML1");
		// ASSERTS
		verify(mockXMLUtil, times(1)).unmarshalObjectFromXML("XML1");
	}

	@Test
	public void test02_B_setCustomDataFromXML_should_addObject()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest02_setCustomDataFromXML();
		// TEST
		mockCustomizerService.setCustomDataFromXML("XML");
		// ASSERTS
		verify(mockCustomizerService, times(1)).setCustomData(mockCustomData1,
				true);
	}

	@Test
	public void test02_C_setCustomDataFromXML_should_marshalObjectToXML()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest02_setCustomDataFromXML();
		when(mockXMLUtil.marshalObjectToXML(mockCustomData2))
				.thenReturn("XML2");
		// TEST
		final String result = mockCustomizerService.setCustomDataFromXML("XML");
		// ASSERTS
		Assert.assertEquals("XML2", result);
		verify(mockXMLUtil, times(1)).marshalObjectToXML(mockCustomData2);
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
		mockCustomizerService.getObject(null, false);
		// ASSERTS
		Assert.assertEquals("", "");
	}

	// --------------------------------------------------------------------------

	private void setUpForTest04_getCustomData() throws AlKhwarizmixException {
		sessionData.setSessionOwner(mockCustomizer);
		mockCustomizerService.setSessionData(sessionData);
		mockCustomizerService.setCustomDataDAO(mockCustomDataDAO);
	}

	@Ignore("TODO: TDD")
	@Test
	public void test04_A_getCustomData() throws AlKhwarizmixException {
		// SETUP
		setUpForTest04_getCustomData();
		// TEST
		mockCustomizerService.getCustomData(null, false);
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
		final CustomData result = mockCustomizerService.getCustomData(
				customData, true);
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
		mockCustomizerService.getCustomDataAsXML(mockCustomData1);
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
		mockCustomizerService.getCustomDataAsXML("");
		// ASSERTS
		Assert.assertEquals("", "");
	}

	// --------------------------------------------------------------------------

	@Ignore("TODO: TDD")
	@Test
	public void test_getSessionOwner_should_return_getSessionData_getSessionOwner()
			throws AlKhwarizmixException {
		final AlKhwarizmixDomainObject mockSessionOwner = Mockito
				.mock(AlKhwarizmixDomainObject.class);
		sessionData.setSessionOwner(mockSessionOwner);
		mockCustomizerService.setSessionData(sessionData);
		final AlKhwarizmixDomainObject result = mockCustomizerService
				.getSessionOwner();
		Assert.assertEquals(mockSessionOwner, result);
	}

} // Class
