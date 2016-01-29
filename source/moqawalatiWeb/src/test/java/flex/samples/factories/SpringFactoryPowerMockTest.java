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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import flex.messaging.FlexContext;
import flex.messaging.config.ConfigMap;
import flex.messaging.services.ServiceException;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٨ ربيع الثاني ١٤٣٧ (January 24, 2016)
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ WebApplicationContextUtils.class, FlexContext.class })
@SuppressWarnings("PMD.MethodNamingConventions")
public class SpringFactoryPowerMockTest {

	// --------------------------------------------------------------------------
	//
	// Setup & Teardown
	//
	// --------------------------------------------------------------------------

	private static final String SOURCE = "source";

	private static final String BEAN_NAME = "BEAN_SpringFactoryPowerMockTest";

	private SpringFactory utSpringFactory;

	@Mock
	private ConfigMap mockConfigMap;

	private final String mockId = "mockId";

	@Mock
	private ServletConfig mockServletConfig;

	@Mock
	private WebApplicationContext mockWebApplicationContext;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() throws AlKhwarizmixException {
		utSpringFactory = new SpringFactory();

		when(mockConfigMap.getPropertyAsString(SOURCE, mockId)).thenReturn(
				BEAN_NAME);

		PowerMockito.mockStatic(WebApplicationContextUtils.class);
		when(
				WebApplicationContextUtils
						.getWebApplicationContext(any(ServletContext.class)))
				.thenReturn(mockWebApplicationContext);

		PowerMockito.mockStatic(FlexContext.class);
		when(FlexContext.getServletConfig()).thenReturn(mockServletConfig);

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
	public void test01_lookup_should_call_SpringFactoryInstance_lookup()
			throws AlKhwarizmixException {
		final Object expected = mock(Object.class);
		when(mockWebApplicationContext.getBean(anyString())).thenReturn(
				expected);
		final Object result = utSpringFactory.lookup(utSpringFactory
				.createFactoryInstance(mockId, mockConfigMap));
		assertEquals(expected, result);
	}

	@Test
	public void test02_lookup_should_throw_ServiceException_when_NoSuchBeanDefinitionException_happen()
			throws AlKhwarizmixException {
		when(mockWebApplicationContext.getBean(anyString())).thenThrow(
				mock(NoSuchBeanDefinitionException.class));
		expectedException.expect(ServiceException.class);
		expectedException.expectMessage("Spring service named '" + BEAN_NAME
				+ "' does not exist.");
		utSpringFactory.lookup(utSpringFactory.createFactoryInstance(mockId,
				mockConfigMap));
	}

	@Test
	public void test03_lookup_should_throw_ServiceException_when_BeansException_happen()
			throws AlKhwarizmixException {
		when(mockWebApplicationContext.getBean(anyString())).thenThrow(
				mock(BeansException.class));
		expectedException.expect(ServiceException.class);
		expectedException
				.expectMessage("Unable to create Spring service named '"
						+ BEAN_NAME + "' ");
		utSpringFactory.lookup(utSpringFactory.createFactoryInstance(mockId,
				mockConfigMap));
	}

} // Class
