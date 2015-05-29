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
			final long thePermittedDelayForSubscription,
			final int thePermittedWrongLoginCountByDelay,
			final long thePermittedDelayForWrongLogin) {
		permittedReqCountByDelay = thePermittedReqCountByDelay;
		permittedDelayForRequest = thePermittedDelayForRequest;
		permittedSubscriptionCountByDelay = thePermittedSubscriptionCountByDelay;
		permittedDelayForSubscription = thePermittedDelayForSubscription;
		permittedWrongLoginCountByDelay = thePermittedWrongLoginCountByDelay;
		permittedDelayForWrongLogin = thePermittedDelayForWrongLogin;
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

	private final int permittedWrongLoginCountByDelay;

	private final long permittedDelayForWrongLogin;

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
			getLogger().trace("Stop access to {} until {}",
					req.getRemoteAddr(), new Date(-waitingTime));
			didErrorHappen = true;
		} else if (waitingTime > 0) {
			getLogger().trace("Wait {}ms please", waitingTime);
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
		final String remoteAddress = getHttpUtil()
				.getCurrentRequestRemoteAddress();
		final long waitingTime = validateRemoteAddrRestrictionAccess(
				remoteAddress,
				RemoteAddrRestrictedAccessInfo.ACCESS_TYPE_SUBSCRIPTION);
		boolean didErrorHappen = false;
		if (waitingTime != 0) {
			logStopSubscriptions(waitingTime, remoteAddress);
			didErrorHappen = true;
		}
		return (!didErrorHappen);
	}

	private void logStopSubscriptions(final long waitingTime,
			final String remoteAddress) {
		final String message = "Stop subscriptions for {}";
		if (waitingTime > 0)
			getLogger().warn(message, remoteAddress);
		else
			getLogger().trace(message, remoteAddress);
	}

	/**
	 */
	@Override
	public boolean validateRemoteAddrRestrictionForWrongLogin() {
		final String remoteAddress = getHttpUtil()
				.getCurrentRequestRemoteAddress();
		final long waitingTime = validateRemoteAddrRestrictionAccess(
				remoteAddress,
				RemoteAddrRestrictedAccessInfo.ACCESS_TYPE_WRONG_LOGIN);
		boolean didErrorHappen = false;
		if (waitingTime != 0) {
			logTooManyWrongLogins(waitingTime, remoteAddress);
			didErrorHappen = true;
		}
		return (!didErrorHappen);
	}

	private void logTooManyWrongLogins(final long waitingTime,
			final String remoteAddress) {
		final String message = "Too many wrong logins for {}";
		if (waitingTime > 0)
			getLogger().warn(message, remoteAddress);
		else
			getLogger().trace(message, remoteAddress);
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
					permittedDelayForSubscription,
					permittedWrongLoginCountByDelay,
					permittedDelayForWrongLogin);
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
		case RemoteAddrRestrictedAccessInfo.ACCESS_TYPE_WRONG_LOGIN: {
			result = hostAccessInfo.validateAccessRestrictionForWrongLogin();
			if (result != 0)
				hostAccessInfo.blockRequestAccessUntilRaiForWrongLoginPermits();
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
	public static final int ACCESS_TYPE_WRONG_LOGIN = 3;

	public RemoteAddrRestrictedAccessInfo(
			final int thePermittedRequestCountByDelay,
			final long thePermittedDelayForRequest,
			final int thePermittedSubscriptionCountByDelay,
			final long thePermittedDelayForSubscription,
			final int thePermittedWrongLoginCountByDelay,
			final long thePermittedDelayForWrongLogin) {
		raiForRequest = new RestrictedAccessInfo(
				thePermittedRequestCountByDelay, thePermittedDelayForRequest);
		raiForSubscription = new RestrictedAccessInfo(
				thePermittedSubscriptionCountByDelay,
				thePermittedDelayForSubscription);
		raiForWrongLogin = new RestrictedAccessInfo(
				thePermittedWrongLoginCountByDelay,
				thePermittedDelayForWrongLogin);
	}

	private final RestrictedAccessInfo raiForRequest;
	private final RestrictedAccessInfo raiForSubscription;
	private final RestrictedAccessInfo raiForWrongLogin;

	public long validateAccessRestrictionForRequest() {
		return raiForRequest.validateAccessRestriction();
	}

	public long validateAccessRestrictionForSubscription() {
		return raiForSubscription.validateAccessRestriction();
	}

	public long validateAccessRestrictionForWrongLogin() {
		return raiForWrongLogin.validateAccessRestriction();
	}

	public void blockRequestAccessUntilRaiForWrongLoginPermits() {
		raiForRequest.setAccessParameters(-1,
				raiForWrongLogin.getLimitedAccessUntil());
	}

} // Class

// ---------- ---------- ---------- ---------- ---------- ---------- ----------

class RestrictedAccessInfo {
	public RestrictedAccessInfo(final int thePermittedAccessCountByDelay,
			final long thePermittedDelay) {
		permittedAccessCountByDelay = thePermittedAccessCountByDelay;
		permittedDelay = thePermittedDelay;
		setAccessParameters(0, new Date(0));
	}

	private final int permittedAccessCountByDelay;
	private final long permittedDelay;

	private Date limitedAccessUntil;
	private int limitedAccessCount;

	/**
	 */
	public long validateAccessRestriction() {
		final Date now = new Date();
		long result = 0L;
		if (now.after(limitedAccessUntil))
			setAccessParameters(permittedAccessCountByDelay,
					new Date(now.getTime() + permittedDelay));

		if (limitedAccessCount <= 0)
			result = (limitedAccessCount == 0
					? limitedAccessUntil.getTime() - now.getTime()
					: -limitedAccessUntil.getTime());
		limitedAccessCount--;
		return result;
	}

	/**
	 */
	public void setAccessParameters(final int thePermittedAccessCount,
			final Date theLimitedAccessUntil) {
		limitedAccessUntil = theLimitedAccessUntil;
		limitedAccessCount = thePermittedAccessCount;
	}

	/**
	 */
	public Date getLimitedAccessUntil() {
		return limitedAccessUntil;
	}

} // Class
