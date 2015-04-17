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

package dz.alkhwarizmix.framework.java.dtos.email.model.vo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.IAlKhwarizmixDomainObjectList;

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
public class EMailListTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	@InjectMocks
	private EMailList utEMailList;

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
	public void test00_A_constructor() {
		Assert.assertNotNull(utEMailList);
	}

	@Test
	public void test00_B_implements_Cloneable() {
		Assert.assertNotNull(utEMailList instanceof Cloneable);
	}

	@Test
	public void test00_C_implements_IAlKhwarizmixDomainObjectList() {
		Assert.assertNotNull(utEMailList instanceof IAlKhwarizmixDomainObjectList);
	}

	@Test
	public void test01_list_setAndGet() {
		List<AbstractAlKhwarizmixDomainObject> valueToSet = new ArrayList<AbstractAlKhwarizmixDomainObject>();
		valueToSet.add(new EMail());
		utEMailList.setList(valueToSet);
		Assert.assertEquals(valueToSet, utEMailList.getList());
	}

	@Ignore("TODO: TDD")
	@Test
	public void test02_clone() {
		Assert.assertTrue(false);
	}

} // Class
