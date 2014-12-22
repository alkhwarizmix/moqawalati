////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٦ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٩ صفر ١٤٣٦ (December 21, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class AbstractAlKhwarizmixServiceValidatorTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Mock
	AbstractAlKhwarizmixServiceValidator mockAlKhwarizmixServiceValidator;

	@Mock
	AbstractAlKhwarizmixDomainObject mockAlKhwarizmixDomainObjectAbstract;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_validateObjectToPublish_should_nullifyId() {
		Mockito.doCallRealMethod()
				.when(mockAlKhwarizmixServiceValidator)
				.validateObjectToPublish(
						any(AbstractAlKhwarizmixDomainObject.class));
		mockAlKhwarizmixServiceValidator
				.validateObjectToPublish(mockAlKhwarizmixDomainObjectAbstract); // TEST
		verify(mockAlKhwarizmixDomainObjectAbstract, times(1)).setId(null);
	}

	@Test
	public void test03_validateObjectToPublish_should_not_throw_exception_when_passed_null_parameter() {
		Mockito.doCallRealMethod()
				.when(mockAlKhwarizmixServiceValidator)
				.validateObjectToPublish(
						any(AbstractAlKhwarizmixDomainObject.class));
		mockAlKhwarizmixServiceValidator.validateObjectToPublish(null); // TEST
	}

} // Class
