/**
  * Copyright 2017 plat4u.com
 * 
 * This file is part of person elements.
 *
 *  plat4u.com is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  plat4u.com is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with plat4u.com.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.plat4u.ghost.common.util;

import java.sql.Date;

import org.apache.commons.beanutils.Converter;

/**
 * DateConverter
 *
 * @author plat4u.com
 * @version 1.0
 */
public class DateConverter implements Converter {

	/* (non Javadoc)
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
	 */
	public <T> T convert(Class<T> type, Object value){
		if (value == null) {
			// (java.sql.Date)null
			return (T)(java.sql.Date)null;
			
		} else {
            if (value instanceof java.lang.String) {
            	return (T) Date.valueOf((String) value);
            	
            }
			return (T)(java.sql.Date)null;
            
		}
	}

}
