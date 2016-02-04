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

package dz.alkhwarizmix.framework.java.webservices.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import javax.management.RuntimeErrorException;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.services.IRecordService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٠ ذو الحجة ١٤٣٥ (October 14, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class RecordWebServiceForXMLTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private RecordWebServiceForXML utRecordWebServiceForXML;

	@Mock
	private IRecordService mockRecordService;

	@Before
	public void setUp() throws Exception {
		utRecordWebServiceForXML.setRecordService(mockRecordService);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	// EMPTY

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utRecordWebServiceForXML);
	}

	@Test
	public void test01_commitRecordList_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null))
				.when(mockRecordService).commitRecordListFromXML(anyString());
		final ResponseEntity<String> result = utRecordWebServiceForXML
				.commitRecordList("");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Test
	public void test02_getRecord_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null))
				.when(mockRecordService).getRecordAsXML(any(Record.class));
		final ResponseEntity<String> result = utRecordWebServiceForXML
				.getRecord("", "", "");
		Assert.assertEquals(500, result.getStatusCode().value());
	}

	@Test
	public void test03_getRecordList_should_return_Internal_Error_500_if_throws_not_AlKhwarizmixException()
			throws Exception {
		Mockito.doThrow(new RuntimeErrorException(null))
				.when(mockRecordService)
				.getRecordListAsXML(anyString(), anyString(),
						any(DetachedCriteria.class), anyInt(), anyInt());
		final ResponseEntity<String> result = utRecordWebServiceForXML
				.getRecordList("", "", 0, 0);
		Assert.assertEquals(500, result.getStatusCode().value());
	}

} // Class
