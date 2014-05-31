////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2104 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dtos.extend.model.vo;

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
 * @since ٠١ شعبان ١٤٣٥ (May 30, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
public class ExtendedDataTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private ExtendedData utExtendedData;

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
	public void test01_set_then_get_ExtendedDataValue_stringLenOf0() {
		String value = "";
		utExtendedData.setExtendedDataValue(value);
		assertEquals(value, utExtendedData.getExtendedDataValue());
		assertEquals(0, utExtendedData.getExtendedDataParts().size());
	}

	@Test
	public void test02_set_then_get_ExtendedDataValue_stringLenOf1() {
		String value = "A";
		utExtendedData.setExtendedDataValue(value);
		assertEquals(value, utExtendedData.getExtendedDataValue());
		assertEquals(1, utExtendedData.getExtendedDataParts().size());
	}

	@Test
	public void test03_set_then_get_ExtendedDataValue_stringLenOf126() {
		String value = getStringWithLen(126);
		utExtendedData.setExtendedDataValue(value);
		assertEquals(value, utExtendedData.getExtendedDataValue());
		assertEquals(1, utExtendedData.getExtendedDataParts().size());
	}

	@Test
	public void test04_set_then_get_ExtendedDataValue_stringLenOf127() {
		String value = getStringWithLen(127);
		utExtendedData.setExtendedDataValue(value);
		assertEquals(value, utExtendedData.getExtendedDataValue());
		assertEquals(1, utExtendedData.getExtendedDataParts().size());
	}

	@Test
	public void test05_set_then_get_ExtendedDataValue_stringLenOf128() {
		String value = getStringWithLen(128);
		utExtendedData.setExtendedDataValue(value);
		assertEquals(value, utExtendedData.getExtendedDataValue());
		assertEquals(2, utExtendedData.getExtendedDataParts().size());
	}

	@Test
	public void test06_set_then_get_ExtendedDataValue_stringLenOf128_then_126() {
		String value = getStringWithLen(128);
		utExtendedData.setExtendedDataValue(value);
		assertEquals(value, utExtendedData.getExtendedDataValue());
		assertEquals(2, utExtendedData.getExtendedDataParts().size());

		value = getStringWithLen(126);
		utExtendedData.setExtendedDataValue(value);
		assertEquals(value, utExtendedData.getExtendedDataValue());
		assertEquals(2, utExtendedData.getExtendedDataParts().size());
	}

} // Class