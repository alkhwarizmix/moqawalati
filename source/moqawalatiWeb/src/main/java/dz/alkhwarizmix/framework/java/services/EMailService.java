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

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.email.model.vo.EMail;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixDAO;
import dz.alkhwarizmix.framework.java.interfaces.IAlKhwarizmixServiceValidator;
import dz.alkhwarizmix.framework.java.interfaces.IEMailDAO;
import dz.alkhwarizmix.framework.java.interfaces.IEMailService;
import dz.alkhwarizmix.framework.java.interfaces.IEMailServiceValidator;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٢٨ ربيع الأول ١٤٣٦ (January 18, 2015)
 */
@Service
@Transactional(readOnly = true)
public class EMailService extends AbstractAlKhwarizmixService implements
		IEMailService {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public EMailService() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static Logger logger = null;

	@Override
	protected Logger getLogger() {
		if (logger == null)
			logger = LoggerFactory.getLogger(EMailService.class);
		return logger;
	}

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private IEMailDAO emailDAO;

	@Autowired
	private IEMailServiceValidator emailValidator;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SimpleMailMessage simpleMailMessage;

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	@Override
	public void addEMail(EMail email) throws AlKhwarizmixException {
		getLogger().debug("addEmail");
		addObject(email);
	}

	/**
	 */
	@Override
	public EMail getEMail(EMail email) throws AlKhwarizmixException {
		getLogger().debug("getEmail");
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("getObject");
		EMail result = getEMailDAO().getEMail((EMail) object);
		updateObjectFromExtendedDataXML(result);
		return result;
	}

	/**
	 * 
	 */
	public void setMailSender(JavaMailSender value) {
		this.mailSender = value;
	}

	/**
	 * 
	 */
	public void setSimpleMailMessage(SimpleMailMessage value) {
		this.simpleMailMessage = value;
	}

	/**
	 * 
	 */
	public SimpleMailMessage getSimpleMailMessage(EMail email) {
		SimpleMailMessage result = new SimpleMailMessage(this.simpleMailMessage);
		result.setFrom(email.getSender().getUserId());
		result.setTo(email.getReceiver().getUserId());
		result.setText(email.getBody());
		return result;
	}

	/**
	 * 
	 */
	public void sendEMail(EMail email) {
		SimpleMailMessage simpleMailMessage = getSimpleMailMessage(email);
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(simpleMailMessage.getFrom(), email.getSender().getName());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(simpleMailMessage.getText());
			mailSender.send(mimeMessage);
		} catch (MessagingException ex) {
			getLogger().warn("sendEmail: {}", ex.getMessage());
		} catch (UnsupportedEncodingException ex) {
			getLogger().warn("sendEmail: {}", ex.getMessage());
		}
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// emailDAO
	// ----------------------------------

	private final IEMailDAO getEMailDAO() {
		return emailDAO;
	}

	protected final void setEMailDAO(IEMailDAO value) {
		emailDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return emailDAO;
	}

	// ----------------------------------
	// emailValidator
	// ----------------------------------

	protected final void setEMailValidator(IEMailServiceValidator value) {
		emailValidator = value;
	}

	@Override
	protected IAlKhwarizmixServiceValidator getServiceValidator() {
		return emailValidator;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	@Override
	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	@Override
	protected void setJaxb2Marshaller(Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class
