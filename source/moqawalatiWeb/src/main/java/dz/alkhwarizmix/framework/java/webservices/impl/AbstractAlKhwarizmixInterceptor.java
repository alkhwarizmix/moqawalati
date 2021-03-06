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

package dz.alkhwarizmix.framework.java.webservices.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
public abstract class AbstractAlKhwarizmixInterceptor extends HandlerInterceptorAdapter {

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	protected abstract Logger getLogger();

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * This will help to log the accessing HOSTS
	 */
	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {
		getLogger().trace("Before handling the request");

		final String host = request.getRemoteHost();
		final String address = request.getRemoteAddr();
		final String pathInfo = request.getPathInfo();
		getLogger().debug("HOST {} IS CALLING {}",
				host + "(IP:" + address + ")", pathInfo);

		return super.preHandle(request, response, handler);
	}

	/**
	 */
	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		getLogger().trace("After handling the request");
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 */
	@Override
	public void afterCompletion(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final Exception ex) throws Exception {
		getLogger().trace("After rendering the view");
		super.afterCompletion(request, response, handler, ex);
	}

} // Class
