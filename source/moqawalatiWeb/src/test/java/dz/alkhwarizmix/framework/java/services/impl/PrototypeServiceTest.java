////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2015 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.services.impl;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.utils.IHTTPUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ ربيع الاول ١٤٣٧ (December 19, 2015)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class PrototypeServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private final String JSON_TO_PROCESS = "{\"ExtensionData\":{},\"UserPosition\":{\"ExtensionData\":{},\"Lat\":2.0,\"Lon\":3.0},\"Vehicules\":[]}";
	private final String RESERVAUTO_GetVehicleProposals_RESPONSE = "\"\"("
			+ JSON_TO_PROCESS + ")";

	@Spy
	private PrototypeService spyPrototypeService;

	@Mock
	private Logger mockLogger;

	@Mock
	private IHTTPUtil mockHttpUtil;

	@Before
	public void setUp() {
		when(spyPrototypeService.getLogger()).thenReturn(mockLogger);
		when(spyPrototypeService.getHttpUtil()).thenReturn(mockHttpUtil);
		when(
				mockHttpUtil.sendGetRequest(Mockito.anyString(),
						Mockito.anyString())).thenReturn(
				RESERVAUTO_GetVehicleProposals_RESPONSE);
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() {
		final PrototypeService utPrototypeService = new PrototypeService();
		Assert.assertNotNull(utPrototypeService);
		Assert.assertNotNull(utPrototypeService.getLogger());
		Assert.assertEquals(
				"dz.alkhwarizmix.framework.java.services.impl.PrototypeService",
				utPrototypeService.getLogger().getName());
	}

	@Test
	public void test01_position_should_parse_correctly_reservauto_GetVehicleProposals_Reponse()
			throws AlKhwarizmixException {
		final String result = spyPrototypeService.position(2.0, 3.0);
		Assert.assertEquals(result, JSON_TO_PROCESS);
	}

} // Class
