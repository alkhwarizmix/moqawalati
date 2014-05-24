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

package dz.alkhwarizmix.framework.java.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ رجب ١٤٣٥ (May 13, 2014)
 */
public class CryptoUtilTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static String CLEAR_TEXT = "This is a text to encrypt 345 éè"
			+ "م خ ة";
	private static String CLEAR_TEXT_AS_HEX = "546869732069732061207465787420746f20656e63727970742033343520c3a9c3a8d98520d8ae20d8a9";
	private static String ENCRYPTED_BY_FLEX = "9800013c054878194737e873787291051c33432530ae1639d86cb0541f60450047ad0ac2b8b00fbd5635028039c4ee8f";

	private CryptoUtil utCryptoUtil;

	@Before
	public void setUp() {
		utCryptoUtil = newCryptoUtil();
	}

	private CryptoUtil newCryptoUtil() {
		String key = "1%KeyForT10t$#16%%K20ForTest2932";
		return new CryptoUtil(key, null, null, false, null);
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
	public void test00_constructor1() throws AlKhwarizmixException {
		Assert.assertNotNull(utCryptoUtil);
	}

	@Test
	public void test01_encrypted_should_be_different_than_original_text() {
		String textToEncrypt = CLEAR_TEXT;
		String encrypted = utCryptoUtil.encryptString(textToEncrypt);
		Assert.assertFalse("", textToEncrypt.equals(encrypted));
	}

	@Test
	public void test02_encrypted_text_then_decrypt_it_should_return_same() {
		String textToEncrypt = CLEAR_TEXT;
		String encrypted = utCryptoUtil.encryptString(textToEncrypt);
		String decrypted = utCryptoUtil.decryptString(encrypted);
		Assert.assertEquals(textToEncrypt, decrypted);
	}

	@Test
	public void test03_stringToHex() throws AlKhwarizmixException {
		String textToConvert = CLEAR_TEXT;
		String hexString = utCryptoUtil.stringToHex(textToConvert);
		Assert.assertEquals(CLEAR_TEXT_AS_HEX, hexString);
	}

	@Test
	public void test04_hexToString() throws AlKhwarizmixException {
		String hexToConvert = CLEAR_TEXT_AS_HEX;
		String resultString = utCryptoUtil.hexToString(hexToConvert);
		Assert.assertEquals(CLEAR_TEXT, resultString);
	}

	@Test
	public void test05_is_able_to_decrypt_flex_encrypted_text()
			throws AlKhwarizmixException {

		String textToEncrypt = CLEAR_TEXT;
		String encrypted = ENCRYPTED_BY_FLEX;
		String decrypted = utCryptoUtil.decryptString(encrypted);
		Assert.assertEquals(textToEncrypt, decrypted);
	}

	@Test
	public void test06_encrypt_2_different_texts_should_give_2_different_results() {
		String text1ToEncrypt = "Text1";
		String text2ToEncrypt = "Text2";
		String encrypted1 = utCryptoUtil.encryptString(text1ToEncrypt);
		String encrypted2 = utCryptoUtil.encryptString(text2ToEncrypt);
		Assert.assertFalse("", encrypted1.equals(encrypted2));
	}

} // Class