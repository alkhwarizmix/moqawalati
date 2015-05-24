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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.utils.XMLUtil;

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
public class AbstractAlKhwarizmixServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Mock
	private AbstractAlKhwarizmixService mockAlKhwarizmixService;

	@Mock
	private AbstractAlKhwarizmixDomainObject mockDomainObject1,
			mockDomainObject2;

	@Mock
	private IAlKhwarizmixServiceValidator mockServiceValidator;

	@Mock
	private AlKhwarizmixDomainObject mockSessionOwner;

	@Mock
	private Logger mockLogger;

	@Mock
	private IAlKhwarizmixDAO mockServiceDAO;

	@Mock
	private XMLUtil mockXMLUtil;

	@Mock
	private DetachedCriteria mockCriteria;

	@Before
	public void setUp() {
		when(mockAlKhwarizmixService.getServiceValidator()).thenReturn(
				mockServiceValidator);
		when(mockAlKhwarizmixService.getSessionOwner()).thenReturn(
				mockSessionOwner);
		when(mockAlKhwarizmixService.getServiceDAO())
				.thenReturn(mockServiceDAO);
		when(mockDomainObject1.clone()).thenReturn(mockDomainObject2);
		when(mockAlKhwarizmixService.getLogger()).thenReturn(mockLogger);
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	private void setUpForTest01_addObject() throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService.addObject(
						any(AbstractAlKhwarizmixDomainObject.class),
						any(boolean.class))).thenCallRealMethod();
	}

	@Test
	public void test01_A_addObject_should_validateObjectToAdd()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest01_addObject();
		// Test
		mockAlKhwarizmixService.addObject(mockDomainObject1, true);
		// Asserts
		verify(mockServiceValidator, times(1)).validateObjectToAdd(
				mockDomainObject1, mockSessionOwner);
	}

	@Test
	public void test01_B_addObject_should_saveOrUpdate()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest01_addObject();
		// Test
		mockAlKhwarizmixService.addObject(mockDomainObject1, true);
		// Asserts
		verify(mockServiceDAO, times(1)).saveOrUpdate(mockDomainObject1);
	}

	@Test
	public void test01_C_addObject_should_validateObjectToPublish()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest01_addObject();
		// Test
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.addObject(mockDomainObject1, true);
		// Asserts
		Assert.assertEquals(mockDomainObject2, result);
		verify(mockDomainObject1, times(1)).clone();
		verify(mockServiceValidator, times(1)).validateObjectToPublish(
				mockDomainObject2, mockSessionOwner);
	}

	@Test
	public void test01_D_addObject_should_NOT_validateObjectToPublish()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest01_addObject();
		// Test
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.addObject(mockDomainObject1, false);
		// Asserts
		Assert.assertEquals(mockDomainObject1, result);
		verify(mockDomainObject1, times(0)).clone();
		verify(mockServiceValidator, times(0)).validateObjectToPublish(
				any(AbstractAlKhwarizmixDomainObject.class),
				any(AlKhwarizmixDomainObject.class));
	}

	// --------------------------------------------------------------------------

	private void setUpForTest02_addObject() throws AlKhwarizmixException {
		when(mockAlKhwarizmixService.addObject(any(String.class)))
				.thenCallRealMethod();
		when(mockAlKhwarizmixService.getXMLUtil()).thenReturn(mockXMLUtil);
		when(mockXMLUtil.unmarshalObjectFromXML(any(String.class))).thenReturn(
				mockDomainObject1);
		when(
				mockAlKhwarizmixService.addObject(
						any(AbstractAlKhwarizmixDomainObject.class),
						any(boolean.class))).thenReturn(mockDomainObject2);
	}

	@Test
	public void test02_A_addObject_should_unmarshalObjectFromXML()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest02_addObject();
		// Test
		mockAlKhwarizmixService.addObject("XML1");
		// Asserts
		verify(mockXMLUtil, times(1)).unmarshalObjectFromXML("XML1");
	}

	@Test
	public void test02_B_addObject_should_addObject()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest02_addObject();
		// Test
		mockAlKhwarizmixService.addObject("XML");
		// Asserts
		verify(mockAlKhwarizmixService, times(1)).addObject(mockDomainObject1,
				true);
	}

	@Test
	public void test02_C_addObject_should_marshalObjectToXML()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest02_addObject();
		when(mockXMLUtil.marshalObjectToXML(mockDomainObject2)).thenReturn(
				"XML2");
		// Test
		final String result = mockAlKhwarizmixService.addObject("XML");
		// Asserts
		Assert.assertEquals("XML2", result);
		verify(mockXMLUtil, times(1)).marshalObjectToXML(mockDomainObject2);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest03_getObjectAsXML() throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService
						.getObjectAsXML(any(AbstractAlKhwarizmixDomainObject.class)))
				.thenCallRealMethod();
		when(mockAlKhwarizmixService.getXMLUtil()).thenReturn(mockXMLUtil);
	}

	@Test
	public void test03_A_getObjectAsXML_should_getObject()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest03_getObjectAsXML();
		// Test
		final String result = mockAlKhwarizmixService
				.getObjectAsXML(mockDomainObject1);
		// Asserts
		Assert.assertEquals("", result);
		verify(mockAlKhwarizmixService, times(1)).getObject(mockDomainObject1,
				true);
		verify(mockXMLUtil, times(0)).marshalObjectToXML(
				any(AbstractAlKhwarizmixDomainObject.class));
	}

	@Test
	public void test03_B_getObjectAsXML_should_marshalObjectToXML_if_foundObject()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest03_getObjectAsXML();
		when(mockAlKhwarizmixService.getObject(mockDomainObject1, true))
				.thenReturn(mockDomainObject2);
		when(mockXMLUtil.marshalObjectToXML(mockDomainObject2)).thenReturn(
				"XML2");
		// Test
		final String result = mockAlKhwarizmixService
				.getObjectAsXML(mockDomainObject1);
		// Asserts
		Assert.assertEquals("XML2", result);
		verify(mockXMLUtil, times(1)).marshalObjectToXML(mockDomainObject2);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest04_getObjectAsXML() throws AlKhwarizmixException {
		when(mockAlKhwarizmixService.getObjectAsXML(any(String.class)))
				.thenCallRealMethod();
		when(mockAlKhwarizmixService.getXMLUtil()).thenReturn(mockXMLUtil);
		when(mockXMLUtil.unmarshalObjectFromXML(any(String.class))).thenReturn(
				mockDomainObject1);
		when(
				mockAlKhwarizmixService
						.getObjectAsXML(any(AbstractAlKhwarizmixDomainObject.class)))
				.thenReturn("XML2");
	}

	@Test
	public void test04_A_getObjectAsXML_should_unmarshalObjectFromXML()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest04_getObjectAsXML();
		// Test
		final String result = mockAlKhwarizmixService.getObjectAsXML("XML1");
		// Asserts
		Assert.assertEquals("XML2", result);
		verify(mockXMLUtil, times(1)).unmarshalObjectFromXML("XML1");
	}

	@Test
	public void test04_B_getObjectAsXML_should_getObjectAsXML()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest04_getObjectAsXML();
		// Test
		final String result = mockAlKhwarizmixService.getObjectAsXML("XML");
		// Asserts
		Assert.assertEquals("XML2", result);
		verify(mockAlKhwarizmixService, times(1)).getObjectAsXML(
				mockDomainObject1);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest05_getObjectAsJSON() throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService
						.getObjectAsJSON(any(AbstractAlKhwarizmixDomainObject.class)))
				.thenCallRealMethod();
	}

	@Ignore("TODO: TDD")
	@Test
	public void test05_getObjectAsJSON() throws AlKhwarizmixException {
		setUpForTest05_getObjectAsJSON();
		final String result = mockAlKhwarizmixService
				.getObjectAsJSON(mockDomainObject1);
		Assert.assertEquals("JSON", result);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest06_getObjectList() throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService.getObjectList(
						any(DetachedCriteria.class), anyInt(), anyInt(),
						anyBoolean())).thenCallRealMethod();
		final List resultList;
		when(mockServiceDAO.getList(mockCriteria, 1, 50))
				.thenReturn(resultList);
	}

	@Test
	public void test06_getObjectList_should_call_dao_getList()
			throws AlKhwarizmixException {
		setUpForTest06_getObjectList();
		final List<AbstractAlKhwarizmixDomainObject> result = mockAlKhwarizmixService
				.getObjectList(mockCriteria, 1, 50, false);
		Assert.assertEquals("JSON", result);
		verify(mockServiceDAO, times(1)).getList(mockCriteria, 1, 50);
	}

	// --------------------------------------------------------------------------

} // Class
