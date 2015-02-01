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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;

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
public class EMailServiceValidatorTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Mock
	EMailServiceValidator mockEMailServiceValidator;

	@Mock
	EMail mockEMail;

	@Mock
	AlKhwarizmixDomainObject mockObjectOwner;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_validateObjectToPublish_should_nullifyId()
			throws AlKhwarizmixException {
		Mockito.doCallRealMethod().when(mockEMailServiceValidator)
				.validateObjectToPublish(mockEMail, mockObjectOwner);
		mockEMailServiceValidator.validateObjectToPublish(mockEMail,
				mockObjectOwner); // TEST
		verify(mockEMail, times(1)).setId(null);
	}

	@Test
	public void test03_validateObjectToPublish_should_not_throw_exception_when_passed_null_parameter()
			throws AlKhwarizmixException {
		Mockito.doCallRealMethod().when(mockEMailServiceValidator)
				.validateObjectToPublish(null, mockObjectOwner);
		mockEMailServiceValidator
				.validateObjectToPublish(null, mockObjectOwner); // TEST
	}

} // Class
