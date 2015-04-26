////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٥ هجري، فارس بلحواس (Copyright 2014 Fares Belhaouas)
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.IAlKhwarizmixDAOForXMLMarshalling;
import dz.alkhwarizmix.framework.java.domain.AbstractAlKhwarizmixDomainObject;
import dz.alkhwarizmix.framework.java.utils.XMLUtil;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 *
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ٠٧ شعبان ١٤٣٥ (June 05, 2014)
 */
public abstract class AlKhwarizmixDAOForXMLMarshalling extends AlKhwarizmixDAO
		implements IAlKhwarizmixDAOForXMLMarshalling {

	// --------------------------------------------------------------------------
	//
	// Properties
	//
	// --------------------------------------------------------------------------

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	// --------------------------------------------------------------------------
	//
	// Overridden Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public void saveOrUpdate(final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixDAOException {
		getLogger().trace("saveOrUpdate({})", object);
		try {
			for (final AbstractAlKhwarizmixDomainObject cursor : object
					.getDaoObjectList()) {
				getLogger().trace("saveOrUpdate(cursor={})", cursor);
				object.beforeDaoSaveOrUpdate(cursor);
				getHibernateTemplate().saveOrUpdate(cursor);
			}
		} catch (final ConcurrencyFailureException e) {
			throw getDAOExceptionForConcurrencyFailure(e);
		} catch (final DataAccessException e) {
			throw getDAOExceptionForDataAccess(e);
		} catch (final Exception e) {
			throw getDAOException(e);
		}
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final String marshalObjectToXML(
			final AbstractAlKhwarizmixDomainObject object)
			throws AlKhwarizmixException {
		getLogger().trace("marshalObjectToXML()");

		return new XMLUtil(getJaxb2Marshaller()).marshalObjectToXML(object);
	}

	/**
	 * TODO: Javadoc
	 */
	@Override
	public final AbstractAlKhwarizmixDomainObject unmarshalObjectFromXML(
			final String xmlValue) throws AlKhwarizmixException {
		getLogger().trace("unmarshalObjectFromXML()");

		return new XMLUtil(getJaxb2Marshaller())
				.unmarshalObjectFromXML(xmlValue);
	}

	// --------------------------------------------------------------------------
	//
	// Getters & Setters
	//
	// --------------------------------------------------------------------------

	// ----------------------------------
	// hibernateTemplate
	// ----------------------------------

	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Override
	protected void setHibernateTemplate(final HibernateTemplate value) {
		hibernateTemplate = value;
	}

	// ----------------------------------
	// jaxb2Marshaller
	// ----------------------------------

	protected Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	protected void setJaxb2Marshaller(final Jaxb2Marshaller value) {
		jaxb2Marshaller = value;
	}

} // Class