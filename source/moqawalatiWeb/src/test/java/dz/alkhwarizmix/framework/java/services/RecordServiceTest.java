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

package dz.alkhwarizmix.framework.java.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.xml.transform.Source;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.Record;
import dz.alkhwarizmix.framework.java.dtos.record.model.vo.RecordList;
import dz.alkhwarizmix.framework.java.dtos.user.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IRecordDAO;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
import dz.alkhwarizmix.moqawalati.java.testutils.HelperTestUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ ذو الحجة ١٤٣٥ (October 06, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class RecordServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private RecordService utRecordService;

	@Mock
	private IRecordDAO mockRecordDAO;

	@Mock
	private Jaxb2Marshaller mockJaxb2Marshaller;

	@Spy
	private AlKhwarizmixSessionData spySessionData;

	@Mock
	private AlKhwarizmixDomainObject mockUserDomainObject;

	@Mock
	private User mockUser;

	@Before
	public void setUp() throws AlKhwarizmixException {
		setupUtRecordService();
		setupMockJaxb2Marshaller();
		when(mockUser.getDomainObject()).thenReturn(mockUserDomainObject);
	}

	private void setupUtRecordService() {
		utRecordService.setRecordDAO(mockRecordDAO);
		utRecordService.setSessionData(spySessionData);
		utRecordService.setJaxb2Marshaller(mockJaxb2Marshaller);
	}

	private void setupMockJaxb2Marshaller() {
		when(mockJaxb2Marshaller.unmarshal(any(Source.class))).thenReturn(
				new Record());
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private Jaxb2Marshaller getRealJaxb2Marshaller() {
		return new HelperTestUtil().getRealJaxb2Marshaller();
	}

	private RecordList newRecordList(Record record) {
		RecordList result = new RecordList();
		result.getList().add(record);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_unmarshalObjectFromXML() throws AlKhwarizmixException {
		String recordAsXML = "<Record id=\"1\" table=\"Table1\"/>";
		utRecordService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		Record record = (Record) utRecordService
				.unmarshalObjectFromXML(recordAsXML); // TEST
		Assert.assertEquals("1", record.getRecordId());
		Assert.assertEquals("Table1", record.getTableName());
	}

	@Test
	public void test02_A_marshalObjectToXML_recordId()
			throws AlKhwarizmixException {
		utRecordService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		String recordAsXML = utRecordService.marshalObjectToXML(new Record(
				"12301")); // TEST
		Assert.assertEquals("<Record id=\"12301\"/>", recordAsXML);
	}

	@Ignore("TODO: USE XPATH TO VALIDATE")
	@Test
	public void test02_B_marshalObjectToXML_schemaName()
			throws AlKhwarizmixException {
		utRecordService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		String recordAsXML = utRecordService.marshalObjectToXML(new Record(
				"12302", "theSchema")); // TEST
		Assert.assertEquals("<Record schema=\"theSchema\" id=\"12302\"/>",
				recordAsXML);
	}

	@Ignore("TODO: USE XPATH TO VALIDATE")
	@Test
	public void test02_C_marshalObjectToXML_tableName()
			throws AlKhwarizmixException {
		utRecordService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		String recordAsXML = utRecordService.marshalObjectToXML(new Record(
				"12303", null, "theTable")); // TEST
		Assert.assertEquals("<Record table=\"theTable\" id=\"12303\"/>",
				recordAsXML);
	}

	@Test
	public void test02_D_marshalObjectToXML_data() throws AlKhwarizmixException {
		utRecordService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		Record newRecord = new Record("12303");
		newRecord.setData("<Record id=\"746\"/>");
		String recordAsXML = utRecordService.marshalObjectToXML(newRecord); // TEST
		Assert.assertEquals(
				"<Record id=\"12303\"><data>&lt;Record id=\"746\"/&gt;</data></Record>",
				recordAsXML);
	}

	@Test
	public void test03_A_commitRecordList_calls_dao_saveOrUpdate()
			throws AlKhwarizmixException {
		Record newRecord = new Record();
		newRecord.setAction(Record.INSERT_ACTION);
		utRecordService.commitRecordList(newRecordList(newRecord)); // TEST
		verify(mockRecordDAO, times(1)).saveOrUpdate(
				any(AbstractAlKhwarizmixDomainObject.class));
	}

	@Ignore("TODO")
	@Test
	public void test03_B_addRecord_should_set_owner_from_session()
			throws AlKhwarizmixException {
		Record newRecord = Mockito.mock(Record.class);
		utRecordService.commitRecordList(newRecordList(newRecord)); // TEST
		verify(newRecord, times(1)).setOwner(mockUserDomainObject);
	}

	@Test
	public void test04_getObject_should_call_dao_getRecord()
			throws AlKhwarizmixException {
		utRecordService.getObject(new Record()); // TEST
		verify(mockRecordDAO, times(1)).getRecord(any(Record.class));
	}

	@Test
	public void test05_getRecord_should_not_return_id()
			throws AlKhwarizmixException {
		Record expectedRecord = new Record();
		expectedRecord.setId(324L);
		when(mockRecordDAO.getRecord(any(Record.class))).thenReturn(
				expectedRecord);
		Record foundRecord = utRecordService.getRecord(new Record()); // TEST
		Assert.assertNull(foundRecord.getId());
	}

	@Test
	public void test06_getRecord_should_not_return_domainObject()
			throws AlKhwarizmixException {
		Record expectedRecord = new Record();
		expectedRecord.setOwner(mockUserDomainObject);
		when(mockRecordDAO.getRecord(any(Record.class))).thenReturn(
				expectedRecord);
		Record foundRecord = utRecordService.getRecord(new Record()); // TEST
		Assert.assertNull(foundRecord.getOwner());
	}

	@Test
	public void test07_recordListToXML() throws AlKhwarizmixException {
		utRecordService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		RecordList newRecordList = new RecordList();
		newRecordList.getList().add(new Record("12305"));
		newRecordList.getList().add(new Record("12306"));
		String recordAsXML = utRecordService.recordListToXML(newRecordList); // TEST
		Assert.assertEquals(
				"<RecordList><Record id=\"12305\"/><Record id=\"12306\"/></RecordList>",
				recordAsXML);
	}

	@Test
	public void test08_xmlToRecordList() throws AlKhwarizmixException {
		utRecordService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		String newRecordListXML = "<RecordList><Record id=\"12305\"/><Record id=\"12306\"/></RecordList>";
		RecordList recordList = utRecordService
				.xmlToRecordList(newRecordListXML); // TEST
		Assert.assertEquals(2, recordList.getList().size());
	}

} // Class
