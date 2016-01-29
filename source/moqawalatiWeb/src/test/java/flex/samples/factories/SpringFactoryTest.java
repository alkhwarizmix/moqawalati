////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٧ هجري، فارس بلحواس (Copyright 2016 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package flex.samples.factories;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import flex.messaging.FactoryInstance;
import flex.messaging.config.ConfigMap;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٧ ربيع الثاني ١٤٣٧ (January 23, 2016)
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("PMD.MethodNamingConventions")
public class SpringFactoryTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static final String SOURCE = "source";

	private static final String BEAN_NAME = "BEAN_SpringFactoryPowerMockTest";

	private SpringFactory utSpringFactory;

	private final String mockId = "mockId";

	@Mock
	private ConfigMap mockConfigMap;

	@Before
	public void setUp() throws AlKhwarizmixException {
		utSpringFactory = new SpringFactory();

		when(mockConfigMap.getPropertyAsString(SOURCE, mockId)).thenReturn(
				BEAN_NAME);
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
	public void test00_constructor() throws AlKhwarizmixException {
		Assert.assertNotNull(utSpringFactory);
	}

	@Test
	public void test01_initialize_shoulf_not_do_nothing()
			throws AlKhwarizmixException {
		utSpringFactory.initialize(null, null);
	}

	@Test
	public void test02_createFactoryInstance() throws AlKhwarizmixException {
		final FactoryInstance result = utSpringFactory.createFactoryInstance(
				mockId, mockConfigMap);
		Assert.assertNotNull(result);
	}

	@Test
	public void test03_SpringFactoryInstance_toString()
			throws AlKhwarizmixException {
		final FactoryInstance result = utSpringFactory.createFactoryInstance(
				mockId, mockConfigMap);
		final String expected = "SpringFactory instance for id=" + mockId
				+ " source=" + SOURCE + " scope=" + "";
		Assert.assertNotNull(expected, result.toString());
	}

} // Class
