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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

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
	private AbstractAlKhwarizmixDomainObject mockDomainObject,
			mockDomainObjectClone;

	@Mock
	private IAlKhwarizmixServiceValidator mockServiceValidator;

	@Mock
	private AlKhwarizmixDomainObject mockSessionOwner;

	@Mock
	private Logger mockLogger;

	@Mock
	private IAlKhwarizmixDAO mockServiceDAO;

	@Before
	public void setUp() {
		when(mockAlKhwarizmixService.getServiceValidator()).thenReturn(
				mockServiceValidator);
		when(mockAlKhwarizmixService.getSessionOwner()).thenReturn(
				mockSessionOwner);
		when(mockAlKhwarizmixService.getServiceDAO())
				.thenReturn(mockServiceDAO);
		when(mockDomainObject.clone()).thenReturn(mockDomainObjectClone);
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
		when(mockAlKhwarizmixService.getLogger()).thenReturn(mockLogger);
	}

	@Test
	public void test01_A_addObject_should_validateObjectToAdd()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest01_addObject();
		// Test
		mockAlKhwarizmixService.addObject(mockDomainObject, true);
		// Asserts
		verify(mockServiceValidator, times(1)).validateObjectToAdd(
				mockDomainObject, mockSessionOwner);
	}

	@Test
	public void test01_B_addObject_should_saveOrUpdate()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest01_addObject();
		// Test
		mockAlKhwarizmixService.addObject(mockDomainObject, true);
		// Asserts
		verify(mockServiceDAO, times(1)).saveOrUpdate(mockDomainObject);
	}

	@Test
	public void test01_C_addObject_should_validateObjectToPublish()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest01_addObject();
		// Test
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.addObject(mockDomainObject, true);
		// Asserts
		Assert.assertEquals(mockDomainObjectClone, result);
		verify(mockDomainObject, times(1)).clone();
		verify(mockServiceValidator, times(1)).validateObjectToPublish(
				mockDomainObjectClone, mockSessionOwner);
	}

	@Test
	public void test01_D_addObject_should_NOT_validateObjectToPublish()
			throws AlKhwarizmixException {
		// Setup
		setUpForTest01_addObject();
		// Test
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.addObject(mockDomainObject, false);
		// Asserts
		Assert.assertEquals(mockDomainObject, result);
		verify(mockDomainObject, times(0)).clone();
		verify(mockServiceValidator, times(0)).validateObjectToPublish(
				any(AbstractAlKhwarizmixDomainObject.class),
				any(AlKhwarizmixDomainObject.class));
	}

	@Ignore
	@Test
	public void test02_getObjectAsXML_should_call_nullifyProtectedProperies()
			throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService
						.getObjectAsXML(any(AbstractAlKhwarizmixDomainObject.class)))
				.thenCallRealMethod();
		when(mockAlKhwarizmixService.getObject(mockDomainObject, true))
				.thenReturn(mockDomainObject);
		mockAlKhwarizmixService.getObjectAsXML(mockDomainObject); // TEST
		// verify(mockAlKhwarizmixService, times(1)).nullifyProtectedProperties(
		// mockAlKhwarizmixDomainObjectAbstract);
	}

	@Ignore
	@Test
	public void test04_addObject_should_call_nullifyProtectedProperies()
			throws AlKhwarizmixException {
		when(mockAlKhwarizmixService.addObject(any(String.class)))
				.thenCallRealMethod();
		// when(
		// mockAlKhwarizmixService
		// .internal_unmarshalObjectFromXML(any(String.class)))
		// .thenReturn(mockAlKhwarizmixDomainObjectAbstract);
		mockAlKhwarizmixService.addObject(""); // TEST
		// verify(mockAlKhwarizmixService, times(1)).nullifyProtectedProperties(
		// mockAlKhwarizmixDomainObjectAbstract);
	}
} // Class
