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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
import dz.alkhwarizmix.moqawalati.java.MoqawalatiException;
import dz.alkhwarizmix.moqawalati.java.interfaces.IMoqawalatiDAO;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٢ ربيع الأول ١٤٣٥ (January 03, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomizerServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private CustomizerService utCustomizerService;

	@Mock
	AlKhwarizmixSessionData mockSessionData;

	@Mock
	AlKhwarizmixDomainObject mockCustomizer;

	@Mock
	IMoqawalatiDAO mockMoqawalatiDAO;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_constructor() {
		Assert.assertNotNull(utCustomizerService);
	}

	// -----

	@Test
	public void test02_getCustomData_should_return_default_if_not_found()
			throws MoqawalatiException {

		setUpMocks();
		Mockito.when(
				mockMoqawalatiDAO.getCustomData(Mockito.any(CustomData.class)))
				.thenReturn(null);

		CustomData customData = new CustomData();
		customData.setCustomDataId("id12345");
		CustomData result = utCustomizerService.getCustomData(customData);
		Assert.assertEquals("Default", result.getCustomDataValue());
	}

	private void setUpMocks() throws MoqawalatiException {
		Mockito.when(mockSessionData.getCustomizer())
				.thenReturn(mockCustomizer);
		utCustomizerService.setSessionData(mockSessionData);
		utCustomizerService.setMoqawalatiDAO(mockMoqawalatiDAO);
	}

	// -----

} // Class