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

package dz.alkhwarizmix.framework.java.security.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dz.alkhwarizmix.framework.java.security.ISecurityManager;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٨ رجب ١٤٣٦ (April 27, 2015)
 */
public class SecurityManager implements ISecurityManager {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public SecurityManager(final int thePermittedReqCount,
			final int thePermittedMilliseconds) {
		permittedReqCount = thePermittedReqCount;
		permittedMilliseconds = thePermittedMilliseconds;
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	private Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(SecurityManager.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	private Map<String, HostAccessInfo> ipAddrDico;

	private final int permittedReqCount;

	private final int permittedMilliseconds;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public boolean validateAccess(final HttpServletRequest req,
			final HttpServletResponse res) {
		final int waitingTime = validateAccess_internal(req);
		boolean didErrorHappen = false;
		if (waitingTime < 0) {
			getLogger().warn("service: Stop access");
			didErrorHappen = true;
		} else if (waitingTime > 0) {
			getLogger().trace("service: Wait {}ms please", waitingTime);
			try {
				Thread.sleep(waitingTime);
			} catch (final InterruptedException ignore) {
				getLogger().warn("service: ignore {}", ignore);
				didErrorHappen = true;
			}
		}

		if (didErrorHappen)
			respondByMethodNotAllowed(res);

		return (!didErrorHappen);
	}

	private void respondByMethodNotAllowed(final HttpServletResponse res) {
		try {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		} catch (final IOException ignore) {
			getLogger().warn("service: ignore {}", ignore);
		}
	}

	/**
	 */
	synchronized private int validateAccess_internal(
			final HttpServletRequest req) {
		final String ipAddr = req.getRemoteAddr();
		HostAccessInfo hostAccessInfo = null;

		if (ipAddrDico == null) {
			getLogger().info("validateAccess: new ipAddrDico");
			ipAddrDico = new HashMap<String, HostAccessInfo>();
		} else
			hostAccessInfo = ipAddrDico.get(ipAddr);

		if (hostAccessInfo == null) {
			hostAccessInfo = new HostAccessInfo(permittedReqCount,
					permittedMilliseconds);
			ipAddrDico.put(ipAddr, hostAccessInfo);
		}

		return hostAccessInfo.validateAccess();
	}

} // Class

class HostAccessInfo {
	public HostAccessInfo(final int thePermittedReqCount,
			final int thePermittedMilliseconds) {
		initialPermittedReqCount = thePermittedReqCount;
		permittedMilliseconds = thePermittedMilliseconds;
		limitedAccessUntil = new Date(0);
	}

	public int validateAccess() {
		final Date now = new Date();
		if (now.after(limitedAccessUntil)) {
			limitedAccessUntil = new Date(now.getTime() + permittedMilliseconds);
			permittedReqCount = initialPermittedReqCount;
		}
		permittedReqCount--;
		if (permittedReqCount <= 0)
			return (permittedReqCount == 0
					? (int) (limitedAccessUntil.getTime() - now.getTime())
					: -1);
		return 0;
	}

	private final int initialPermittedReqCount;
	private final int permittedMilliseconds;
	public Date limitedAccessUntil;
	public int permittedReqCount;

} // Class
