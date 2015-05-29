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

package dz.alkhwarizmix.framework.java.blazeds.messaging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

import dz.alkhwarizmix.framework.java.AlKhwarizmixApplicationContextHolder;
import dz.alkhwarizmix.framework.java.security.ISecurityManager;
import flex.messaging.MessageBrokerServlet;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٧ رجب ١٤٣٦ (April 26, 2015)
 */
@Configurable
public class AlKhwarizmixMessageBrokerServlet extends MessageBrokerServlet {

	// --------------------------------------------------------------------------
	//
	// Constants
	//
	// --------------------------------------------------------------------------

	private static final long serialVersionUID = -494420199909554091L;

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public AlKhwarizmixMessageBrokerServlet() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	private Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory
					.getLogger(AlKhwarizmixMessageBrokerServlet.class);
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

	// --------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	@Override
	public void service(final HttpServletRequest req,
			final HttpServletResponse res) {
		getLogger().trace("service: IP={}", req.getRemoteAddr());

		if (getSecurityManager().validateRemoteAddrRestrictionForRequest(req, res))
			super.service(req, res);
	}

} // Class
