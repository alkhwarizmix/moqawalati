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

package dz.alkhwarizmix.framework.java.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.interfaces.IEMailService;
import dz.alkhwarizmix.framework.java.model.AlKhwarizmixSessionData;
import dz.alkhwarizmix.framework.java.utils.DateUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٥ جمادى الأولى ١٤٣٦ (March 06, 2015)
 */
public class EMailServiceSendWorker {

	// --------------------------------------------------------------------------
	//
	// Constructors
	//
	// --------------------------------------------------------------------------

	public EMailServiceSendWorker() {
		// NOOP
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	private Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(EMailServiceSendWorker.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IEMailService emailService;

	@Autowired
	private AlKhwarizmixSessionData sessionData;

	private DateUtil dateUtil = null;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 *
	 */
	public void scheduledSendEMail() {
		getLogger().trace("scheduledSendEMail");
		try {
			setupSessionOwner();
			final EMail emailToSend = getEMailService().getPendingEMail(false);
			if (emailToSend != null) {
				getLogger().trace("scheduledSendEMail: emailToSend={}",
						emailToSend);
				getEMailService().sendEMail(emailToSend);
				updateEMailSentAt(emailToSend);
			}
		} catch (final AlKhwarizmixException e) {
			getLogger().warn("scheduledSendEMail 1: {}", e);
		} catch (final Exception e) {
			getLogger().warn("scheduledSendEMail 2: {}", e);
		} finally {
			getSessionData().resetSessionOwner();
		}
	}

	private void setupSessionOwner() {
		getSessionData().setSessionOwner(new AlKhwarizmixDomainObject());
		getSessionData().getSessionOwner().setId(-1L);
	}

	private void updateEMailSentAt(final EMail emailToSend)
			throws AlKhwarizmixException {
		emailToSend.setSentAt(getDateUtil().newDate());
		getEMailService().updateEMail(emailToSend, false);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// dateUtil
	// ----------------------------------

	protected final DateUtil getDateUtil() {
		if (dateUtil == null)
			dateUtil = new DateUtil();
		return dateUtil;
	}

	protected final void setDateUtil(final DateUtil value) {
		dateUtil = value;
	}

	// ----------------------------------
	// emailService
	// ----------------------------------

	protected final void setEMailService(final IEMailService value) {
		emailService = value;
	}

	protected final IEMailService getEMailService() {
		return emailService;
	}

	// ----------------------------------
	// sessionData
	// ----------------------------------

	protected final AlKhwarizmixSessionData getSessionData() {
		return sessionData;
	}

	protected final void setSessionData(final AlKhwarizmixSessionData value) {
		sessionData = value;
	}

} // Class
