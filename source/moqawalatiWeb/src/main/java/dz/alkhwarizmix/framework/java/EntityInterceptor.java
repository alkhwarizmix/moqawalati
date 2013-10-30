////////////////////////////////////////////////////////////////////////////////
//  بسم الله الرحمن الرحيم
//
//  حقوق التأليف والنشر ١٤٣٤ هجري، فارس بلحواس (Copyright 2013 Fares Belhaouas)  
//  كافة الحقوق محفوظة (All Rights Reserved)
//
//  NOTICE: Fares Belhaouas permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package dz.alkhwarizmix.framework.java;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.DateType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

/**
 *  <p>
 *  TODO: Javadoc
 *  </p>
 * 
 *  @author فارس بلحواس (Fares Belhaouas)
 *  @since  ٢٥ ذو القعدة ١٤٣٤ (October 01, 2013)
 */
@Component
public class EntityInterceptor extends EmptyInterceptor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4574510715964636804L;

	// TODO: Add tests on this class!!
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
	{
		return prePersist(state, propertyNames, types);
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
	{
		return prePersist(currentState, propertyNames, types);
	}
	
	/**
	 * update the modified date of entities
	 * @param currentState {@link Object} the entity properties values
	 * @param propertyNames {@link String} the entity properties names
	 * @param types {@link Type} the entity property types
	 * @return {@link Boolean}
	 */
	private boolean prePersist(Object[] currentState, String[] propertyNames, Type[] types)
	{
		for (int i = 0; i < propertyNames.length; i++)
		{
			if ("modified".equals(propertyNames[i]) && (types[i] instanceof TimestampType || types[i] instanceof DateType))
			{
				currentState[i] = new Date();
				return true;
			}
		}
		return false;
	}
}
