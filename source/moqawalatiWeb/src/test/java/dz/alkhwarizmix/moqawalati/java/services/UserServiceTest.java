////////////////////////////////////////////////////////////////////////////////
//بسم الله الرحمن الرحيم
//
//حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//كافة الحقوق محفوظة (All Rights Reserved)
//
//NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.domain.AlKhwarizmixDomainObjectAbstract;
import dz.alkhwarizmix.moqawalati.java.dao.MoqawalatiDAO;
import dz.alkhwarizmix.moqawalati.java.dtos.modules.userModule.model.vo.User;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ذو الحجة ١٤٣٤ (November 01, 2013)
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private UserService utUserService;

	@Mock
	private MoqawalatiDAO mockMoqawalatiDAO;

	// --------------------------------------------------------------------------
	//
	// Tests
	//
	// --------------------------------------------------------------------------

	@Test
	public void test01_addUser_calls_dao_saveOrUpdate() throws Exception {
		utUserService.setMoqawalatiDAO(mockMoqawalatiDAO);
		utUserService.addUser(new User());
		verify(mockMoqawalatiDAO, times(1)).saveOrUpdate(
				any(AlKhwarizmixDomainObjectAbstract.class));
	}

} // Class