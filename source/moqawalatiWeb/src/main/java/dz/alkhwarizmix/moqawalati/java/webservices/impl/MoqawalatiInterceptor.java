////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.moqawalati.java.webservices.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import dz.alkhwarizmix.framework.java.AlKhwarizmixApplicationContextHolder;
import dz.alkhwarizmix.framework.java.security.ISecurityManager;
import dz.alkhwarizmix.framework.java.webservices.impl.AbstractAlKhwarizmixInterceptor;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public class MoqawalatiInterceptor extends AbstractAlKhwarizmixInterceptor {

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(MoqawalatiInterceptor.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private ISecurityManager securityManager;

	public ISecurityManager getSecurityManager() {
		if (securityManager == null)
			securityManager = (ISecurityManager) AlKhwarizmixApplicationContextHolder
					.getContext().getBean("securityManager");
		return securityManager;
	}

	// --------------------------------------------------------------------------
	//
	// Others
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public boolean preHandle(final HttpServletRequest req,
			final HttpServletResponse res, final Object handler)
			throws Exception {
		getLogger().trace("preHandle: IP={}", req.getRemoteAddr());
		return (getSecurityManager().validateRemoteAddrRestrictionForRequest(req, res))
				? super.preHandle(req, res, handler)
				: false;
	}

	/**
	 */
	@Override
	public void postHandle(final HttpServletRequest req,
			final HttpServletResponse res, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		super.postHandle(req, res, handler, modelAndView);
	}

	/**
	 */
	@Override
	public void afterCompletion(final HttpServletRequest req,
			final HttpServletResponse res, final Object handler,
			final Exception ex) throws Exception {
		super.afterCompletion(req, res, handler, ex);
	}

} // Class
