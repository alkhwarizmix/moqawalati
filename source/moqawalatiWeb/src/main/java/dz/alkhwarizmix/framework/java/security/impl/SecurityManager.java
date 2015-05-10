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
import dz.alkhwarizmix.framework.java.utils.HTTPUtil;

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
	public SecurityManager(final int thePermittedReqCountByDelay,
			final long thePermittedDelayForRequest,
			final int thePermittedSubscriptionCountByDelay,
			final long thePermittedDelayForSubscription) {
		permittedReqCountByDelay = thePermittedReqCountByDelay;
		permittedDelayForRequest = thePermittedDelayForRequest;
		permittedSubscriptionCountByDelay = thePermittedSubscriptionCountByDelay;
		permittedDelayForSubscription = thePermittedDelayForSubscription;
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

	private final int permittedReqCountByDelay;

	private final long permittedDelayForRequest;

	private final int permittedSubscriptionCountByDelay;

	private final long permittedDelayForSubscription;

	private Map<String, RemoteAddrRestrictedAccessInfo> remoteAddrDico;

	private HTTPUtil httpUtil;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public boolean validateRemoteAddrRestrictionForRequest(
			final HttpServletRequest req, final HttpServletResponse res) {
		final long waitingTime = validateRemoteAddrRestrictionAccess(
				req.getRemoteAddr(),
				RemoteAddrRestrictedAccessInfo.ACCESS_TYPE_REQUEST);
		boolean didErrorHappen = false;
		if (waitingTime < 0) {
			getLogger().warn(
					"validateRemoteAddrRestrictionForRequest: Stop access");
			didErrorHappen = true;
		} else if (waitingTime > 0) {
			getLogger()
					.trace("validateRemoteAddrRestrictionForRequest: Wait {}ms please",
							waitingTime);
			try {
				Thread.sleep(waitingTime);
			} catch (final InterruptedException ignore) {
				getLogger().warn(
						"validateRemoteAddrRestrictionForRequest: ignore {}",
						ignore);
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
			getLogger().warn("respondByMethodNotAllowed: ignore {}", ignore);
		}
	}

	/**
	 */
	@Override
	public boolean validateRemoteAddrRestrictionForSubscription() {
		final long waitingTime = validateRemoteAddrRestrictionAccess(
				getHttpUtil().getCurrentRequestRemoteAddress(),
				RemoteAddrRestrictedAccessInfo.ACCESS_TYPE_SUBSCRIPTION);
		boolean didErrorHappen = false;
		if (waitingTime != 0) {
			getLogger()
					.warn("validateRemoteAddrRestrictionForSubscription: Stop access");
			didErrorHappen = true;
		}
		return (!didErrorHappen);
	}

	/**
	 */
	synchronized private long validateRemoteAddrRestrictionAccess(
			final String remoteAddr, final int accessType) {
		RemoteAddrRestrictedAccessInfo hostAccessInfo = getRemoteAddrDico()
				.get(remoteAddr);
		long result = -1L;
		if (hostAccessInfo == null) {
			getLogger().info("new host IP={}", remoteAddr);
			hostAccessInfo = new RemoteAddrRestrictedAccessInfo(
					permittedReqCountByDelay, permittedDelayForRequest,
					permittedSubscriptionCountByDelay,
					permittedDelayForSubscription);
			getRemoteAddrDico().put(remoteAddr, hostAccessInfo);
		}

		switch (accessType) {
		case RemoteAddrRestrictedAccessInfo.ACCESS_TYPE_REQUEST: {
			result = hostAccessInfo.validateAccessRestrictionForRequest();
			break;
		}
		case RemoteAddrRestrictedAccessInfo.ACCESS_TYPE_SUBSCRIPTION: {
			result = hostAccessInfo.validateAccessRestrictionForSubscription();
			break;
		}
		}

		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// httpUtil
	// ----------------------------------

	public HTTPUtil getHttpUtil() {
		if (httpUtil == null)
			httpUtil = new HTTPUtil();
		return httpUtil;
	}

	public void setHttpUtil(final HTTPUtil value) {
		httpUtil = value;
	}

	// ----------------------------------
	// remoteAddrDico
	// ----------------------------------

	private Map<String, RemoteAddrRestrictedAccessInfo> getRemoteAddrDico() {
		if (remoteAddrDico == null) {
			getLogger().info("getRemoteAddrDico: new remoteAddrDico");
			remoteAddrDico = new HashMap<String, RemoteAddrRestrictedAccessInfo>();
		}
		return remoteAddrDico;
	}

} // Class

// ---------- ---------- ---------- ---------- ---------- ---------- ----------

class RemoteAddrRestrictedAccessInfo {
	public static final int ACCESS_TYPE_REQUEST = 1;
	public static final int ACCESS_TYPE_SUBSCRIPTION = 2;

	public RemoteAddrRestrictedAccessInfo(
			final int thePermittedRequestCountByDelay,
			final long thePermittedDelayForRequest,
			final int thePermittedSubscriptionCountByDelay,
			final long thePermittedDelayForSubscription) {
		raiForRequest = new RestrictedAccessInfo(
				thePermittedRequestCountByDelay, thePermittedDelayForRequest);
		raiForSubscription = new RestrictedAccessInfo(
				thePermittedSubscriptionCountByDelay,
				thePermittedDelayForSubscription);
	}

	private final RestrictedAccessInfo raiForRequest;
	private final RestrictedAccessInfo raiForSubscription;

	public long validateAccessRestrictionForRequest() {
		return raiForRequest.validateAccessRestriction();
	}

	public long validateAccessRestrictionForSubscription() {
		return raiForSubscription.validateAccessRestriction();
	}

} // Class

// ---------- ---------- ---------- ---------- ---------- ---------- ----------

class RestrictedAccessInfo {
	public RestrictedAccessInfo(final int thePermittedAccessCountByDelay,
			final long thePermittedDelay) {
		initialPermittedAccessCountByDelay = thePermittedAccessCountByDelay;
		permittedDelay = thePermittedDelay;
		limitedAccessUntil = new Date(0);
	}

	private final int initialPermittedAccessCountByDelay;
	private final long permittedDelay;
	public Date limitedAccessUntil;
	public int permittedAccessCountByDelay;

	public long validateAccessRestriction() {
		final Date now = new Date();
		long result = 0L;
		if (now.after(limitedAccessUntil)) {
			limitedAccessUntil = new Date(now.getTime() + permittedDelay);
			permittedAccessCountByDelay = initialPermittedAccessCountByDelay;
		}
		if (permittedAccessCountByDelay <= 0)
			result = (permittedAccessCountByDelay == 0
					? limitedAccessUntil.getTime() - now.getTime()
					: -1L);
		permittedAccessCountByDelay--;
		return result;
	}

} // Class
