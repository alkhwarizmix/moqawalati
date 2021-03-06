////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2016 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.winrak.java.services.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.utils.IHTTPUtil;
import dz.alkhwarizmix.trouvauto.java.model.vo.ReservautoPosition;
import dz.alkhwarizmix.winrak.java.model.IWinrakPosition;
import dz.alkhwarizmix.winrak.java.services.IWinrakService;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٣٠ ربيع الاول ١٤٣٧ (January 10, 2016)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class TrouvautoServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private final String JSON_TO_PROCESS = "{\"ExtensionData\":{},\"UserPosition\":{\"ExtensionData\":{},\"Lat\":45.5417145,\"Lon\":-73.59598849999999},\"Vehicules\":[{\"ExtensionData\":{},\"Id\":\"JTDKDTB38F1582390\",\"Name\":\"2967\",\"ModelName\":\"PRIUS-C\",\"Immat\":\"FKK4611\",\"EnergyLevel\":50,\"Position\":{\"ExtensionData\":{},\"Lat\":45.5383915,\"Lon\":-73.591833666666673}},{\"ExtensionData\":{},\"Id\":\"JTDKDTB35E1564234\",\"Name\":\"2797\",\"ModelName\":\"PRIUS-C\",\"Immat\":\"FVE7336\",\"EnergyLevel\":57,\"Position\":{\"ExtensionData\":{},\"Lat\":45.546632,\"Lon\":-73.590737166666671}}]}";
	private final String RESERVAUTO_GetVehicleProposals_RESPONSE = "\"\"("
			+ JSON_TO_PROCESS + ");";

	@Spy
	private TrouvautoService spyTrouvautoService;

	@Mock
	private Logger mockLogger;

	@Mock
	private IHTTPUtil mockHttpUtil;

	@Mock
	private IWinrakService mockWinrakService;

	@Before
	public void setUp() throws AlKhwarizmixException {
		when(spyTrouvautoService.getLogger()).thenReturn(mockLogger);
		when(spyTrouvautoService.getHttpUtil()).thenReturn(mockHttpUtil);
		spyTrouvautoService.setWinrakService(mockWinrakService);
		when(mockHttpUtil.sendGetRequest(anyString(), anyString())).thenReturn(
				RESERVAUTO_GetVehicleProposals_RESPONSE);
	}

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private ReservautoPosition getCurrentPosition() {
		final ReservautoPosition position = new ReservautoPosition(45.5417145,
				-73.59598849999999);
		return position;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test00_constructor() {
		final TrouvautoService utTrouvautoService = new TrouvautoService();
		Assert.assertNotNull(utTrouvautoService);
		Assert.assertNotNull(utTrouvautoService.getLogger());
		Assert.assertEquals(
				"dz.alkhwarizmix.winrak.java.services.impl.TrouvautoService",
				utTrouvautoService.getLogger().getName());
	}

	@Test
	public void test01_trouvauto_should_return_2_vehicules_for_count_2()
			throws AlKhwarizmixException {
		Assert.assertEquals(
				"{\"vehicules\":[{\"lat\":45.53839,\"lng\":-73.59183,\"name\":\"2967\",\"direction\":\"SE\",\"distance\":491},"
						+ "{\"lat\":45.54663,\"lng\":-73.59074,\"name\":\"2797\",\"direction\":\"NE\",\"distance\":682}]}",
				spyTrouvautoService.trouvauto(getCurrentPosition(), 2));
	}

	@Test
	public void test02_trouvauto_should_return_1_vehicule_for_count_1()
			throws AlKhwarizmixException {
		Assert.assertEquals(
				"{\"vehicules\":[{\"lat\":45.53839,\"lng\":-73.59183,\"name\":\"2967\",\"direction\":\"SE\",\"distance\":491}]}",
				spyTrouvautoService.trouvauto(getCurrentPosition(), 1));
	}

	@Test
	public void test03_trouvauto_should_return_0_vehicules_for_count_0()
			throws AlKhwarizmixException {
		Assert.assertEquals("{}",
				spyTrouvautoService.trouvauto(getCurrentPosition(), 0));
	}

	@Test
	public void test04_trouvauto_should_fill_currentPosition_with_address()
			throws AlKhwarizmixException {
		final String addressShort = "1234 My Current Address";
		final String addressLong = addressShort + ", Sidi Bel Abbès, Algérie";
		when(
				mockWinrakService.convertPositionToAddress(45.5417145,
						-73.59598849999999, 3000)).thenReturn(addressLong);
		final IWinrakPosition myCurrentPosition = getCurrentPosition();
		final String result = spyTrouvautoService.trouvauto(myCurrentPosition,
				1);
		Assert.assertTrue(result.contains("\"address\":\"" + addressShort
				+ "\""));
		Assert.assertEquals(addressLong, myCurrentPosition.getAddress());
	}

	@Test
	public void test05_trouvauto_should_return_1_vehicule_with_address()
			throws AlKhwarizmixException {
		when(
				mockWinrakService.convertPositionToAddress(45.5383915,
						-73.591833666666673, 3000)).thenReturn(
				"2967 Address, Sidi Bel Abbès, Algérie");
		final String result = spyTrouvautoService.trouvauto(
				getCurrentPosition(), 1);
		Assert.assertTrue(result.contains("\"address\":\"2967 Address\""));
	}

} // Class
