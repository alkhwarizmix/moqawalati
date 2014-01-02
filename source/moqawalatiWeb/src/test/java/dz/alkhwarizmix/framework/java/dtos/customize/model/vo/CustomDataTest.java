////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.customize.model.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ محرم ١٤٣٥ (November 12, 2013)
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomDataTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private CustomData utCustomData;

	// --------------------------------------------------------------------------
	//
	// Helpers
	//
	// --------------------------------------------------------------------------

	private String getStringWithLen(int len) {
		String result = "";
		int i = 32;
		while (result.length() < len) {
			if (i > 122)
				i = 32;
			result += Character.toString((char) i);
			i++;
		}
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_set_then_get_CustomDataValue_stringLenOf0() {
		String value = "";
		utCustomData.setCustomDataValue(value);
		assertEquals(value, utCustomData.getCustomDataValue());
		assertEquals(0, utCustomData.getCustomDataParts().size());
	}

	@Test
	public void test02_set_then_get_CustomDataValue_stringLenOf1() {
		String value = "A";
		utCustomData.setCustomDataValue(value);
		assertEquals(value, utCustomData.getCustomDataValue());
		assertEquals(1, utCustomData.getCustomDataParts().size());
	}

	@Test
	public void test03_set_then_get_CustomDataValue_stringLenOf126() {
		String value = getStringWithLen(126);
		utCustomData.setCustomDataValue(value);
		assertEquals(value, utCustomData.getCustomDataValue());
		assertEquals(1, utCustomData.getCustomDataParts().size());
	}

	@Test
	public void test04_set_then_get_CustomDataValue_stringLenOf127() {
		String value = getStringWithLen(127);
		utCustomData.setCustomDataValue(value);
		assertEquals(value, utCustomData.getCustomDataValue());
		assertEquals(1, utCustomData.getCustomDataParts().size());
	}

	@Test
	public void test05_set_then_get_CustomDataValue_stringLenOf128() {
		String value = getStringWithLen(128);
		utCustomData.setCustomDataValue(value);
		assertEquals(value, utCustomData.getCustomDataValue());
		assertEquals(2, utCustomData.getCustomDataParts().size());
	}

	@Test
	public void test06_set_then_get_CustomDataValue_stringLenOf128_then_126() {
		String value = getStringWithLen(128);
		utCustomData.setCustomDataValue(value);
		assertEquals(value, utCustomData.getCustomDataValue());
		assertEquals(2, utCustomData.getCustomDataParts().size());

		value = getStringWithLen(126);
		utCustomData.setCustomDataValue(value);
		assertEquals(value, utCustomData.getCustomDataValue());
		assertEquals(2, utCustomData.getCustomDataParts().size());
	}

} // Class