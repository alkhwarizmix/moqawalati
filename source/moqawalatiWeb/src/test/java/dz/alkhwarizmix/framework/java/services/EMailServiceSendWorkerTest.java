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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.interfaces.IEMailService;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
import dz.alkhwarizmix.framework.java.utils.DateUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٦ جمادى الأولى ١٤٣٦ (March 07, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class EMailServiceSendWorkerTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private EMailServiceSendWorker utEMailServiceSendWorker;

	@Mock
	private IEMailService mockEMailService;

	@Mock
	private EMail mockEMail;

	@Mock
	private AlKhwarizmixSessionData mockSessionData;

	@Mock
	private DateUtil mockDateUtil;

	@Mock
	private Date mockDate;

	@Before
	public void setUp() {
		setupUtEMailServiceSendWorker();
	}

	private void setupUtEMailServiceSendWorker() {
		utEMailServiceSendWorker.setEMailService(mockEMailService);
		utEMailServiceSendWorker.setSessionData(mockSessionData);
		when(mockDateUtil.newDate()).thenReturn(mockDate);
		utEMailServiceSendWorker.setDateUtil(mockDateUtil);
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
	public void test04_scheduledSendEMail_should_call_emailService_getEMail()
			throws AlKhwarizmixException {
		// TEST
		Mockito.when(mockEMailService.getPendingEMail(false)).thenReturn(
				mockEMail);
		// TEST
		utEMailServiceSendWorker.scheduledSendEMail();
		// ASSERT
		verify(mockEMailService, times(1)).sendEMail(mockEMail);
		Assert.assertEquals(mockDate, mockEMail.getSentAt());
		verify(mockEMailService, times(1)).updateEMail(mockEMail, false);
	}

} // Class
