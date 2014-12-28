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

package dz.alkhwarizmix.moqawalati.java.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import dz.alkhwarizmix.framework.java.AlKhwarizmixErrorCode;
import dz.alkhwarizmix.framework.java.AlKhwarizmixException;
import dz.alkhwarizmix.framework.java.dao.AlKhwarizmixDAOForXMLMarshalling;
import dz.alkhwarizmix.framework.java.dtos.customize.model.vo.CustomData;
import dz.alkhwarizmix.framework.java.interfaces.ICustomDataDAO;

/**
 * <p>
 * TODO: Javadoc
 * </p>
 * 
 * @author فارس بلحواس (Fares Belhaouas)
 * @since ١٢ شعبان ١٤٣٥ (June 10, 2014)
 */
@Repository
public class CustomDataDAO extends AlKhwarizmixDAOForXMLMarshalling implements
		ICustomDataDAO {

	// --------------------------------------------------------------------------
	//
	// Constructor
	//
	// --------------------------------------------------------------------------

	/**
	 * constructor
	 */
	public CustomDataDAO() {
		super();
	}

	// --------------------------------------------------------------------------
	//
	// Logger
	//
	// --------------------------------------------------------------------------

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomDataDAO.class);

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	// --------------------------------------------------------------------------
	//
	// Methods
	//
	// --------------------------------------------------------------------------

	/**
	 */
	@Override
	public CustomData getCustomData(CustomData customDataToGet)
			throws AlKhwarizmixException {
		getLogger().trace("getCustomData()");
		try {
			Criteria criteria = getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createCriteria(CustomData.class);
			Criterion criter1 = Restrictions.eq(CustomData.CUSTOMDATAID,
					customDataToGet.getCustomDataId());
			Criterion criter2 = Restrictions.eq(CustomData.CUSTOMIZER,
					customDataToGet.getCustomizer().getId());
			criteria.add(Restrictions.and(criter1, criter2));
			customDataToGet = (CustomData) criteria.uniqueResult();
			if (customDataToGet != null) {
				customDataToGet.setExtendedData(getExtendedData(customDataToGet
						.getExtendedData()));
			}
			return customDataToGet;
		} catch (DataAccessException e) {
			AlKhwarizmixException ex = new AlKhwarizmixException(
					AlKhwarizmixErrorCode.ERROR_DATABASE, e);
			throw ex;
		}
	}

} // Class
