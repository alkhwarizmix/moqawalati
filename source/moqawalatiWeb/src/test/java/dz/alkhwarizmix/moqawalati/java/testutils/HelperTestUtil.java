////////////////////////////////////////////////////////////////////////////////
//بسم الله الرحمن الرحيم
//
//حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)  
//كافة الحقوق محفوظة (All Rights Reserved)
//
//NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.testutils;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ذو الحجة ١٤٣٤ (February 01, 2014) TODO HIDJRI
 */
public class HelperTestUtil {

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	public Jaxb2Marshaller getRealJaxb2Marshaller() {

		Jaxb2Marshaller result = new Jaxb2Marshaller();

		String[] jaxbPackagesToScan = { "dz.alkhwarizmix.framework.java.dtos",
				"dz.alkhwarizmix.moqawalati.java.dtos", };
		result.setPackagesToScan(jaxbPackagesToScan);

		Map<String, Object> jaxbProperties = new HashMap<String, Object>();
		jaxbProperties.put(Marshaller.JAXB_FRAGMENT, true);
		result.setMarshallerProperties(jaxbProperties);

		return result;
	}

} // Class