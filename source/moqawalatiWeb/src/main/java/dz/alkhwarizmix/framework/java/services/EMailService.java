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
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.dtos.domain.model.vo.AlKhwarizmixDomainObject;
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
	public EMail addEMail(final EMail email,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().debug("addEmail");
		final EMail result = (EMail) addObject(email, validateObjectToPublish);
		getLogger().trace("addEMail: return {}", result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EMail getEMail(final EMail email,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().debug("getEMail");
		final EMail result = (EMail) getObject(email, validateObjectToPublish);
		getLogger().trace("getEMail: return {}", result);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractAlKhwarizmixDomainObject getObject(
			final AbstractAlKhwarizmixDomainObject object,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().trace("getObject");
		EMail result = getEMailDAO().getEMail((EMail) object);
		if (validateObjectToPublish && (result != null)) {
			result = (EMail) result.clone();
			getServiceValidator().validateObjectToPublish(result,
					getSessionOwner());
		}
		getLogger().trace("getObject: return {}", result);
		return result;
	}

	/**
	 * TODO: JAVADOC
	 */
	public List<EMail> getEMailList(DetachedCriteria criteriaToUse,
			final int firstResult, final int maxResult,
			final boolean validateForPublishing) throws AlKhwarizmixException {
		getLogger().debug("getEMailList");
		if (criteriaToUse == null)
			criteriaToUse = DetachedCriteria.forClass(EMail.class);
		// criteriaToUse.addOrder(Order.asc(EMail.USERID));
		final List<EMail> result = (List<EMail>) (List<?>) getObjectList(
				criteriaToUse, firstResult, maxResult, validateForPublishing);
		return result;
	}

	/**
	 */
	@Override
	public EMail getPendingEMail(final boolean validateObjectToPublish)
			throws AlKhwarizmixException {
		getLogger().trace("getPendingEMail");
		EMail result = null;
		final List<EMail> pendingEMails = getEMailList(
				getPendingEMailsCriteria(), 0, 1, validateObjectToPublish);
		if (pendingEMails.size() > 0)
			result = pendingEMails.get(0);
		getLogger().trace("getPendingEMail: return {}", result);
		return result;
	}

	private DetachedCriteria getPendingEMailsCriteria() {
		final DetachedCriteria result = DetachedCriteria.forClass(EMail.class);
		result.add(Restrictions.isNull("sentAt"));
		return result;
	}

	/**
	 *
	 */
	@Override
	public void sendEMail(final EMail email) throws AlKhwarizmixException {
		final SimpleMailMessage simpleMailMessage = getSimpleMailMessage(email);
		final MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					true);
			helper.setFrom(simpleMailMessage.getFrom(), email.getSender()
					.getName());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(simpleMailMessage.getText());
			mailSender.send(mimeMessage);
		} catch (final MessagingException ex) {
			getLogger().warn("sendEmail: {}", ex.getMessage());
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.SERVER_INTERNAL_ERROR, ex);
		} catch (final UnsupportedEncodingException ex) {
			getLogger().warn("sendEmail: {}", ex.getMessage());
			throw new AlKhwarizmixException(
					AlKhwarizmixErrorCode.SERVER_INTERNAL_ERROR, ex);
		}
	}

	/**
	 */
	@Transactional(readOnly = false)
	@Override
	public EMail updateEMail(final EMail email,
			final AlKhwarizmixDomainObject updater,
			final boolean validateObjectToPublish) throws AlKhwarizmixException {
		getLogger().debug("updateEMail");
		final EMail result = (EMail) updateObject(email, updater,
				validateObjectToPublish);
		getLogger().trace("updateEMail: return {}", result);
		return result;
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// mailSender
	// ----------------------------------

	/**
	 *
	 */
	public void setMailSender(final JavaMailSender value) {
		mailSender = value;
	}

	// ----------------------------------
	// simpleMailMessage
	// ----------------------------------

	/**
	 *
	 */
	public void setSimpleMailMessage(final SimpleMailMessage value) {
		simpleMailMessage = value;
	}

	/**
	 *
	 */
	public SimpleMailMessage getSimpleMailMessage(final EMail email) {
		final SimpleMailMessage result = new SimpleMailMessage(
				simpleMailMessage);
		result.setFrom(email.getSender().getUserId());
		result.setTo(email.getReceiver().getUserId());
		result.setText(email.getBody());
		return result;
	}

	// ----------------------------------
	// emailDAO
	// ----------------------------------

	private final IEMailDAO getEMailDAO() {
		return emailDAO;
	}

	protected final void setEMailDAO(final IEMailDAO value) {
		emailDAO = value;
	}

	@Override
	protected IAlKhwarizmixDAO getServiceDAO() {
		return emailDAO;
	}

	// ----------------------------------
	// emailValidator
	// ----------------------------------

	protected final void setEMailValidator(final IEMailServiceValidator value) {
		emailValidator = value;
	}

	@Override
	protected final IAlKhwarizmixServiceValidator getServiceValidator() {
		return emailValidator;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	@Override
	protected final Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	@Override
	protected final void setJaxb2Marshaller(final Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class
