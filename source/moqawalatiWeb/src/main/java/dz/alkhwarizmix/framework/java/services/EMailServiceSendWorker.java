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

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.interfaces.IEMailService;

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
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
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
			EMail emailToSend = getEMailService().getPendingEMail();
			if (emailToSend != null) {
				getLogger().trace("scheduledSendEMail: emailToSend={}",
						emailToSend);
				getEMailService().sendEMail(emailToSend);
				emailToSend.setSentAt(new Date());
				getEMailService().updateEMail(emailToSend);
			}
		} catch (AlKhwarizmixException e) {
			getLogger().warn("scheduledSendEMail 1: {}", e);
		} catch (Exception e) {
			getLogger().warn("scheduledSendEMail 2: {}", e);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// emailService
	// ----------------------------------

	protected final void setEMailService(IEMailService value) {
		emailService = value;
	}

	protected final IEMailService getEMailService() {
		return emailService;
	}

} // Class
