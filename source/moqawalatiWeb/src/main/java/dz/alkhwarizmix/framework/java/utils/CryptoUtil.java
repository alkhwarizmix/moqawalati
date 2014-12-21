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

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٤ رجب ١٤٣٥ (May 13, 2014)
 */
public class CryptoUtil {

	private Cipher cipher = null;
	private SecretKeySpec secretKeySpec = null;
	private IvParameterSpec ivParameterSpec = null;

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * Constructor.
	 */
	public CryptoUtil(String pKey, String pEncType, String pModeType,
			boolean pSimple, String pPaddingType) {

		if (pEncType == null)
			pEncType = "aes";
		pEncType = pEncType.toUpperCase();

		if (pModeType == null)
			pModeType = "cbc"; // ecb, cbc, ofb
		pModeType = pModeType.toUpperCase();

		if (pPaddingType == null)
			pPaddingType = "pkcs5";

		String pHexKey = stringToHex(pKey);
		pHexKey = pHexKey.substring(0, 64);
		String pHexIV = pHexKey.substring(0, 32);

		byte[] kdata = hex2Byte(pHexKey);
		String pad = ((pPaddingType == "pkcs5")
				? "PKCS5"
				: "No") + "Padding";
		String algo = pEncType + "/" + pModeType + "/" + pad;
		secretKeySpec = new SecretKeySpec(kdata, pEncType);
		ivParameterSpec = new IvParameterSpec(hex2Byte(pHexIV));
		try {
			cipher = Cipher.getInstance(algo);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * encryptString
	 */
	public final String encryptString(String stringToEncrypt) {

		String result = null;
		byte[] dataToEncrypt = null;
		byte[] encrypted = null;

		dataToEncrypt = hex2Byte(stringToHex(stringToEncrypt));

		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			encrypted = cipher.doFinal(dataToEncrypt);
			result = byte2hex(encrypted);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * decryptString
	 */
	public final String decryptString(String hexStringToDecrypt) {

		String result = null;
		byte[] dataToDecrypt = null;
		byte[] decrypted = null;

		dataToDecrypt = hex2Byte(hexStringToDecrypt);

		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			decrypted = cipher.doFinal(dataToDecrypt);
			result = hexToString(byte2hex(decrypted));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * getEncryptedVersion
	 */
	public final Object getEncryptedVersion(Object objectToEncrypt) {
		Object result = objectToEncrypt;
		return result;
	}

	/**
	 * getDecryptedVersion
	 */
	public final Object getDecryptedVersion(Object encryptedObject) {
		Object result = encryptedObject;
		return result;
	}

	/**
	 * reverseString
	 */
	public final String reverseString(String text) {
		String result = null;
		if (text != null) {
			result = "";
			for (int i = text.length() - 1; i >= 0; i--)
				result += text.charAt(i);
		}
		return result;
	}

	/**
	 * stringToHex
	 */
	protected String stringToHex(String arg) {

		String result = null;

		try {
			result = String.format("%040x",
					new BigInteger(1, arg.getBytes("UTF8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * hexToString
	 */
	protected String hexToString(String arg) {

		String result = null;
		byte[] bytes = null;

		try {
			bytes = Hex.decodeHex(arg.toCharArray());
			result = new String(bytes, "UTF8");
		} catch (DecoderException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	private byte[] hex2Byte(String str) {
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i + 2),
					16);
		}
		return bytes;
	}

	// Convert Byte Arrary to Hex String
	private String byte2hex(byte[] b) {

		// String Buffer can be used instead

		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = java.lang.Integer.toHexString(b[n] & 0XFF);

			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}

			if (n < b.length - 1) {
				hs = hs + "";
			}
		}

		return hs;
	}
}