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
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.extend.model.vo.AbstractAlKhwarizmixDomainObjectExtendable;
import dz.alkhwarizmix.framework.java.services.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.utils.IXMLUtil;
import dz.alkhwarizmix.framework.java.utils.impl.XMLUtil;

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
	private IXMLUtil mockXMLUtil;

	@Mock
	private DetachedCriteria mockCriteria;

	@Mock
	private AbstractAlKhwarizmixDomainObjectExtendable mockExtendableObject;

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

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private void setUpForTest01_addObject() throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService.addObject(
						any(AbstractAlKhwarizmixDomainObject.class),
						anyBoolean())).thenCallRealMethod();
	}

	@Test
	public void test01_A_addObject_should_validateObjectToAdd()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_addObject();
		// TEST
		mockAlKhwarizmixService.addObject(mockDomainObject1, true);
		// ASSERTS
		verify(mockServiceValidator, times(1)).validateObjectToAdd(
				mockDomainObject1, mockSessionOwner);
	}

	@Test
	public void test01_B_addObject_should_saveOrUpdate()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_addObject();
		// TEST
		mockAlKhwarizmixService.addObject(mockDomainObject1, true);
		// ASSERTS
		verify(mockServiceDAO, times(1)).saveOrUpdate(mockDomainObject1);
	}

	@Test
	public void test01_C_addObject_should_validateObjectToPublish()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_addObject();
		// TEST
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.addObject(mockDomainObject1, true);
		// ASSERTS
		Assert.assertEquals(mockDomainObject2, result);
		verify(mockDomainObject1, times(1)).clone();
		verify(mockServiceValidator, times(1)).validateObjectToPublish(
				mockDomainObject2, mockSessionOwner);
	}

	@Test
	public void test01_D_addObject_should_NOT_validateObjectToPublish()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest01_addObject();
		// TEST
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.addObject(mockDomainObject1, false);
		// ASSERTS
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
						anyBoolean())).thenReturn(mockDomainObject2);
	}

	@Test
	public void test02_A_addObject_should_unmarshalObjectFromXML()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest02_addObject();
		// TEST
		mockAlKhwarizmixService.addObject("XML1");
		// ASSERTS
		verify(mockXMLUtil, times(1)).unmarshalObjectFromXML("XML1");
	}

	@Test
	public void test02_B_addObject_should_addObject()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest02_addObject();
		// TEST
		mockAlKhwarizmixService.addObject("XML");
		// ASSERTS
		verify(mockAlKhwarizmixService, times(1)).addObject(mockDomainObject1,
				true);
	}

	@Test
	public void test02_C_addObject_should_marshalObjectToXML()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest02_addObject();
		when(mockXMLUtil.marshalObjectToXML(mockDomainObject2)).thenReturn(
				"XML2");
		// TEST
		final String result = mockAlKhwarizmixService.addObject("XML");
		// ASSERTS
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
		// SETUP
		setUpForTest03_getObjectAsXML();
		// TEST
		final String result = mockAlKhwarizmixService
				.getObjectAsXML(mockDomainObject1);
		// ASSERTS
		Assert.assertEquals("", result);
		verify(mockAlKhwarizmixService, times(1)).getObject(mockDomainObject1,
				true);
		verify(mockXMLUtil, times(0)).marshalObjectToXML(
				any(AbstractAlKhwarizmixDomainObject.class));
	}

	@Test
	public void test03_B_getObjectAsXML_should_marshalObjectToXML_if_foundObject()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest03_getObjectAsXML();
		when(mockAlKhwarizmixService.getObject(mockDomainObject1, true))
				.thenReturn(mockDomainObject2);
		when(mockXMLUtil.marshalObjectToXML(mockDomainObject2)).thenReturn(
				"XML2");
		// TEST
		final String result = mockAlKhwarizmixService
				.getObjectAsXML(mockDomainObject1);
		// ASSERTS
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
		// SETUP
		setUpForTest04_getObjectAsXML();
		// TEST
		final String result = mockAlKhwarizmixService.getObjectAsXML("XML1");
		// ASSERTS
		Assert.assertEquals("XML2", result);
		verify(mockXMLUtil, times(1)).unmarshalObjectFromXML("XML1");
	}

	@Test
	public void test04_B_getObjectAsXML_should_getObjectAsXML()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest04_getObjectAsXML();
		// TEST
		final String result = mockAlKhwarizmixService.getObjectAsXML("XML");
		// ASSERTS
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
		final List<AbstractAlKhwarizmixDomainObject> resultList = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		resultList.add(mockDomainObject1);
		resultList.add(mockDomainObject2);
		when(mockServiceDAO.getList(mockCriteria, 1, 50))
				.thenReturn(resultList);
		doThrow(new AlKhwarizmixException("mockDomainObject1 not valid "))
				.when(mockServiceValidator).validateObjectToPublish(
						mockDomainObject1, mockSessionOwner);
	}

	@Test
	public void test06_A_getObjectList_should_call_dao_getList()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest06_getObjectList();
		// TEST
		final List<AbstractAlKhwarizmixDomainObject> result = mockAlKhwarizmixService
				.getObjectList(mockCriteria, 1, 50, false);
		// ASSERTS
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(mockDomainObject1, result.get(0));
		verify(mockServiceDAO, times(1)).getList(mockCriteria, 1, 50);
		verify(mockServiceValidator, times(0)).validateObjectToPublish(
				mockDomainObject1, mockSessionOwner);
	}

	@Test
	public void test06_B_getObjectList_should_validateForPublishing()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest06_getObjectList();
		// TEST
		final List<AbstractAlKhwarizmixDomainObject> result = mockAlKhwarizmixService
				.getObjectList(mockCriteria, 1, 50, true);
		// ASSERTS
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(mockDomainObject2, result.get(0));
		verify(mockServiceDAO, times(1)).getList(mockCriteria, 1, 50);
		verify(mockServiceValidator, times(1)).validateObjectToPublish(
				mockDomainObject1, mockSessionOwner);
		verify(mockServiceValidator, times(1)).validateObjectToPublish(
				mockDomainObject2, mockSessionOwner);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest07_updateObject() throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService.updateObject(
						any(AbstractAlKhwarizmixDomainObject.class),
						any(AlKhwarizmixDomainObject.class), anyBoolean()))
				.thenCallRealMethod();
		when(mockAlKhwarizmixService.getObject(mockDomainObject1, false))
				.thenReturn(mockDomainObject2);
	}

	@Test
	public void test07_A_updateObject_should_call_getSessionOwner_if_objectOwner_is_null()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest07_updateObject();
		// TEST
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.updateObject(mockDomainObject1, null, false);
		// ASSERTS
		Assert.assertEquals(mockDomainObject2, result);
		verify(mockAlKhwarizmixService, times(1)).getSessionOwner();
		verify(mockServiceValidator, times(1)).validateObjectToUpdate(
				mockDomainObject1, mockSessionOwner);
	}

	@Test
	public void test07_B_updateObject_should_call_validateObjectToUpdate()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest07_updateObject();
		final AlKhwarizmixDomainObject mockSessionOwner2 = Mockito
				.mock(AlKhwarizmixDomainObject.class);
		// TEST
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.updateObject(mockDomainObject1, mockSessionOwner2, false);
		// ASSERTS
		Assert.assertEquals(mockDomainObject2, result);
		verify(mockServiceValidator, times(1)).validateObjectToUpdate(
				mockDomainObject1, mockSessionOwner2);
	}

	@Test
	public void test07_C_updateObject_should_use_object_if_version_is_set()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest07_updateObject();
		final AbstractAlKhwarizmixDomainObject mockDomainObjectWithVersion = getDomainObjectWithVersion();
		// TEST
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.updateObject(mockDomainObjectWithVersion, mockSessionOwner,
						false);
		// ASSERTS
		Assert.assertEquals(mockDomainObjectWithVersion, result);
		verify(mockAlKhwarizmixService, times(0)).getObject(
				mockDomainObjectWithVersion, false);
	}

	@SuppressWarnings("serial")
	private AbstractAlKhwarizmixDomainObject getDomainObjectWithVersion() {
		class MyAbstractAlKhwarizmixDomainObject extends
				AbstractAlKhwarizmixDomainObject {

			public MyAbstractAlKhwarizmixDomainObject() {
				super(Long.MIN_VALUE, Integer.MIN_VALUE, null, null);
			}

			@Override
			public Object clone() {
				return null;
			}

			@Override
			public void beforeDaoSaveOrUpdate(
					final AbstractAlKhwarizmixDomainObject object) {
			}

			@Override
			public void updateFrom(final Object sourceObject)
					throws AlKhwarizmixException {
			}
		}
		return new MyAbstractAlKhwarizmixDomainObject();
	}

	@Test
	public void test07_D_updateObject_foundObject_should_updateFrom_object()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest07_updateObject();
		// TEST
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.updateObject(mockDomainObject1, mockSessionOwner, false);
		// ASSERTS
		Assert.assertEquals(mockDomainObject2, result);
		verify(mockDomainObject2, times(1)).updateFrom(mockDomainObject1);
	}

	@Test
	public void test07_E_updateObject_should_saveOrUpdate_if_foundObject()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest07_updateObject();
		// TEST
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.updateObject(mockDomainObject1, mockSessionOwner, false);
		// ASSERTS
		Assert.assertEquals(mockDomainObject2, result);
		verify(mockServiceDAO, times(1)).saveOrUpdate(mockDomainObject2);
	}

	@Test
	public void test07_F_updateObject_should_throw_exception_if_not_foundObject()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest07_updateObject();
		when(mockAlKhwarizmixService.getObject(mockDomainObject1, false))
				.thenReturn(null);
		// EXPECTED EXCEPTION
		thrown.expect(AlKhwarizmixException.class);
		thrown.expectMessage("update1.");
		// TEST
		mockAlKhwarizmixService.updateObject(mockDomainObject1,
				mockSessionOwner, false);
	}

	@Test
	public void test07_G_updateObject_should_validateForPublishing_if_needed()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest07_updateObject();
		final AbstractAlKhwarizmixDomainObject mockDomainObject2Clone = Mockito
				.mock(AbstractAlKhwarizmixDomainObject.class);
		when(mockDomainObject2.clone()).thenReturn(mockDomainObject2Clone);
		// TEST
		final AbstractAlKhwarizmixDomainObject result = mockAlKhwarizmixService
				.updateObject(mockDomainObject1, mockSessionOwner, true);
		// ASSERTS
		Assert.assertEquals(mockDomainObject2Clone, result);
		verify(mockServiceValidator, times(1)).validateObjectToPublish(
				mockDomainObject2Clone, mockSessionOwner);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest08_updateObject() throws AlKhwarizmixException {
		when(mockAlKhwarizmixService.updateObject(any(String.class)))
				.thenCallRealMethod();
		when(mockAlKhwarizmixService.getXMLUtil()).thenReturn(mockXMLUtil);
		when(mockXMLUtil.unmarshalObjectFromXML(any(String.class))).thenReturn(
				mockDomainObject1);
		when(
				mockAlKhwarizmixService.updateObject(
						any(AbstractAlKhwarizmixDomainObject.class),
						any(AlKhwarizmixDomainObject.class), anyBoolean()))
				.thenReturn(mockDomainObject2);
	}

	@Test
	public void test08_A_updateObject_should_unmarshalObjectFromXML()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest08_updateObject();
		// TEST
		mockAlKhwarizmixService.updateObject("XML1");
		// ASSERTS
		verify(mockXMLUtil, times(1)).unmarshalObjectFromXML("XML1");
	}

	@Test
	public void test08_B_updateObject_should_updateObject()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest08_updateObject();
		// TEST
		mockAlKhwarizmixService.updateObject("XML");
		// ASSERTS
		verify(mockAlKhwarizmixService, times(1)).updateObject(
				mockDomainObject1, mockSessionOwner, true);
	}

	@Test
	public void test08_C_updateObject_should_marshalObjectToXML()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest08_updateObject();
		when(mockXMLUtil.marshalObjectToXML(mockDomainObject2)).thenReturn(
				"XML2");
		// TEST
		final String result = mockAlKhwarizmixService.updateObject("XML");
		// ASSERTS
		Assert.assertEquals("XML2", result);
		verify(mockXMLUtil, times(1)).marshalObjectToXML(mockDomainObject2);
	}

	// --------------------------------------------------------------------------

	@Ignore("TODO: TDD")
	@Test
	public void test09_objectListToJSON() throws AlKhwarizmixException {
		final String result = mockAlKhwarizmixService.objectListToJSON(null);
		Assert.assertEquals("JSON", result);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest10_objectListToXML() throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService
						.objectListToXML(anyListOf(AbstractAlKhwarizmixDomainObject.class)))
				.thenCallRealMethod();
		when(mockAlKhwarizmixService.getXMLUtil()).thenReturn(mockXMLUtil);
		when(
				mockXMLUtil
						.objectListToXML(anyListOf(AbstractAlKhwarizmixDomainObject.class)))
				.thenReturn("XMLLIST");
	}

	@Test
	public void test10_objectListToXML() throws AlKhwarizmixException {
		// SETUP
		setUpForTest10_objectListToXML();
		final List<AbstractAlKhwarizmixDomainObject> objectList = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		// TEST
		final String result = mockAlKhwarizmixService
				.objectListToXML(objectList);
		// ASSERTS
		Assert.assertEquals("XMLLIST", result);
	}

	// --------------------------------------------------------------------------

	@Test
	public void test11_getXMLUtil() throws AlKhwarizmixException {
		// SETUP
		when(mockAlKhwarizmixService.getXMLUtil()).thenCallRealMethod();
		// TEST
		final IXMLUtil result = mockAlKhwarizmixService.getXMLUtil();
		// ASSERTS
		Assert.assertTrue(result instanceof XMLUtil);
	}

	// --------------------------------------------------------------------------

	@Ignore("TODO: TDD")
	@Test
	public void test12_marshalObjectToJSON() throws AlKhwarizmixException {
		final String result = mockAlKhwarizmixService.marshalObjectToJSON(null);
		Assert.assertEquals("JSON", result);
	}

	// --------------------------------------------------------------------------

	private void setUpForTest13_updateObjectFromExtendedDataXML()
			throws AlKhwarizmixException {
		when(mockAlKhwarizmixService.getXMLUtil()).thenReturn(mockXMLUtil);
		when(mockXMLUtil.unmarshalObjectFromXML("StringValue")).thenReturn(
				mockDomainObject1);
	}

	@Test
	public void test13_updateObjectFromExtendedDataXML_should_updateFrom_extendedData()
			throws AlKhwarizmixException {
		// SETUP
		setUpForTest13_updateObjectFromExtendedDataXML();
		mockExtendableObject.setExtendedDataValue("StringValue");
		// TEST
		mockAlKhwarizmixService
				.updateObjectFromExtendedDataXML(mockExtendableObject);
		// ASSERTS
		verify(mockExtendableObject, times(1)).updateFrom(mockDomainObject1);
	}

	// --------------------------------------------------------------------------

} // Class
