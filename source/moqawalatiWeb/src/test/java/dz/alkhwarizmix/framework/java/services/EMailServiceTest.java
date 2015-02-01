////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMailList;
import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;
import dz.alkhwarizmix.framework.java.interfaces.IEMailDAO;
import dz.alkhwarizmix.moqawalati.java.testutils.HelperTestUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ ربيع الثاني ١٤٣٦ (February 01, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class EMailServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private EMailService utEMailService;

	@Mock
	private IEMailDAO mockEMailDAO;

	@InjectMocks
	private EMailServiceValidator recordValidator;

	@Mock
	private Jaxb2Marshaller mockJaxb2Marshaller;

	@Mock
	private User mockUser;

	@Before
	public void setUp() throws AlKhwarizmixException {
		setupUtEMailService();
		setupMockJaxb2Marshaller();
	}

	private void setupUtEMailService() {
		utEMailService.setEMailDAO(mockEMailDAO);
		utEMailService.setEMailValidator(recordValidator);
		utEMailService.setJaxb2Marshaller(mockJaxb2Marshaller);
	}

	private void setupMockJaxb2Marshaller() {
		when(mockJaxb2Marshaller.unmarshal(any(Source.class))).thenReturn(
				new EMail());
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private Jaxb2Marshaller getRealJaxb2Marshaller() {
		return new HelperTestUtil().getRealJaxb2Marshaller();
	}

	private EMailList newEMailList(EMail record) {
		EMailList result = new EMailList();
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
		/*
		 * String recordAsXML = "<EMail id=\"1\" table=\"Table1\"/>";
		 * utEMailService.setJaxb2Marshaller(getRealJaxb2Marshaller()); EMail
		 * record = (EMail) utEMailService .unmarshalObjectFromXML(recordAsXML);
		 * // TEST Assert.assertEquals("1", record.getEMailId());
		 * Assert.assertEquals("Table1", record.getTableName());
		 */
	}

	@Test
	public void test02_A_marshalObjectToXML_recordId()
			throws AlKhwarizmixException {
		/*
		 * utEMailService.setJaxb2Marshaller(getRealJaxb2Marshaller()); String
		 * recordAsXML = utEMailService.marshalObjectToXML(new EMail( "12301"));
		 * // TEST Assert.assertEquals("<EMail id=\"12301\"/>", recordAsXML);
		 */
	}

	@Test
	public void test02_D_marshalObjectToXML_data() throws AlKhwarizmixException {
		/*
		 * utEMailService.setJaxb2Marshaller(getRealJaxb2Marshaller()); EMail
		 * newEMail = new EMail("12303");
		 * newEMail.setData("<EMail id=\"746\"/>"); String recordAsXML =
		 * utEMailService.marshalObjectToXML(newEMail); // TEST
		 * Assert.assertEquals(
		 * "<EMail id=\"12303\"><data>&lt;EMail id=\"746\"/&gt;</data></EMail>",
		 * recordAsXML);
		 */
	}

	@Test
	public void test03_A_commitEMailList_calls_dao_saveOrUpdate()
			throws AlKhwarizmixException {
		/*
		 * EMail newEMail = new EMail();
		 * newEMail.setAction(EMail.INSERT_ACTION);
		 * utEMailService.commitEMailList(newEMailList(newEMail)); // TEST
		 * verify(mockEMailDAO, times(1)).saveOrUpdate(
		 * any(AbstractAlKhwarizmixDomainObject.class));
		 */
	}

	@Test
	public void test04_getObject_should_call_dao_getEMail()
			throws AlKhwarizmixException {
		utEMailService.getObject(new EMail()); // TEST
		verify(mockEMailDAO, times(1)).getEMail(any(EMail.class));
	}

	@Test
	public void test07_recordListToXML() throws AlKhwarizmixException {
		/*
		 * utEMailService.setJaxb2Marshaller(getRealJaxb2Marshaller());
		 * EMailList newEMailList = new EMailList();
		 * newEMailList.getList().add(new EMail("12305"));
		 * newEMailList.getList().add(new EMail("12306")); String recordAsXML =
		 * utEMailService.recordListToXML(newEMailList); // TEST
		 * Assert.assertEquals(
		 * "<EMailList><EMail id=\"12305\"/><EMail id=\"12306\"/></EMailList>",
		 * recordAsXML);
		 */
	}

	@Test
	public void test08_xmlToEMailList() throws AlKhwarizmixException {
		/*
		 * utEMailService.setJaxb2Marshaller(getRealJaxb2Marshaller()); String
		 * newEMailListXML =
		 * "<EMailList><EMail id=\"12305\"/><EMail id=\"12306\"/></EMailList>";
		 * EMailList recordList = utEMailService
		 * .xmlToEMailList(newEMailListXML); // TEST Assert.assertEquals(2,
		 * recordList.getList().size());
		 */
	}

} // Class
