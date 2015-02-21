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

package dz.alkhwarizmix.framework.java.model;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.dtos.security.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ جمادى الأول ١٤٣٥ (March 25, 2014)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class AlKhwarizmixSessionDataTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@Mock
	AlKhwarizmixSessionData mockAlKhwarizmixSessionData;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_resetSessionOwner_should_nullify_sessionOwner() {
		Mockito.doCallRealMethod().when(mockAlKhwarizmixSessionData)
				.resetSessionOwner();
		mockAlKhwarizmixSessionData.resetSessionOwner();
		verify(mockAlKhwarizmixSessionData, times(1)).setSessionOwner(null);
	}

	@Test
	public void test02_set_then_get_connectedUser() {
		Mockito.doCallRealMethod().when(mockAlKhwarizmixSessionData)
				.setConnectedUser(any(User.class));
		Mockito.doCallRealMethod().when(mockAlKhwarizmixSessionData)
				.getConnectedUser();
		User valueToSet = new User("userId145");
		mockAlKhwarizmixSessionData.setConnectedUser(valueToSet);
		Assert.assertEquals(valueToSet,
				mockAlKhwarizmixSessionData.getConnectedUser());
	}

} // Class
