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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٢ ربيع الأول ١٤٣٥ (January 03, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class AbstractAlKhwarizmixServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Mock
	AbstractAlKhwarizmixService mockAlKhwarizmixService;

	@Mock
	AbstractAlKhwarizmixDomainObject mockAlKhwarizmixDomainObjectAbstract;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Ignore
	@Test
	public void test02_getObjectAsXML_should_call_nullifyProtectedProperies()
			throws AlKhwarizmixException {
		when(
				mockAlKhwarizmixService
						.getObjectAsXML(any(AbstractAlKhwarizmixDomainObject.class)))
				.thenCallRealMethod();
		when(
				mockAlKhwarizmixService
						.getObject(mockAlKhwarizmixDomainObjectAbstract))
				.thenReturn(mockAlKhwarizmixDomainObjectAbstract);
		mockAlKhwarizmixService
				.getObjectAsXML(mockAlKhwarizmixDomainObjectAbstract); // TEST
		// verify(mockAlKhwarizmixService, times(1)).nullifyProtectedProperties(
		// mockAlKhwarizmixDomainObjectAbstract);
	}

	@Ignore
	@Test
	public void test04_addObject_should_call_nullifyProtectedProperies()
			throws AlKhwarizmixException {
		when(mockAlKhwarizmixService.addObject(any(String.class)))
				.thenCallRealMethod();
		// when(
		// mockAlKhwarizmixService
		// .internal_unmarshalObjectFromXML(any(String.class)))
		// .thenReturn(mockAlKhwarizmixDomainObjectAbstract);
		mockAlKhwarizmixService.addObject(""); // TEST
		// verify(mockAlKhwarizmixService, times(1)).nullifyProtectedProperties(
		// mockAlKhwarizmixDomainObjectAbstract);
	}
} // Class
