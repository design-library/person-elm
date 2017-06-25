/**
 * Copyright 2017 plat4u.com
 * 
 * This file is part of ghost.
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
package com.plat4u.ghost.person.helper;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.plat4u.ghost.common.util.DateConverter;
import com.plat4u.ghost.common.util.DateStringConverter;
import com.plat4u.ghost.person.biz.entity.Person;
import com.plat4u.ghost.person.biz.entity.PersonHistory;
import com.plat4u.ghost.person.web.msg.PersonMsg;

/**
 * PersonHelper
 *
 * @author plat4u.com
 * @version 1.0
 */
public class PersonHelper {
	
	public static Person toPerson(PersonMsg messageCarrier) 
			throws IllegalAccessException, InvocationTargetException 
	{
		Person person = new Person();
		ConvertUtils.register(new DateConverter(), java.util.Date.class);
		BeanUtils.copyProperties(person, messageCarrier);
		return person;
		
	}
	
	public static PersonMsg toPersonMsg(Person person) 
			throws IllegalAccessException, InvocationTargetException 
	{
		PersonMsg messageCarrier = new PersonMsg();
		ConvertUtils.register(new DateStringConverter(), java.lang.String.class);
		BeanUtils.copyProperties(messageCarrier, person);
		return messageCarrier;
	}
	
	public static PersonHistory toPersonHistory(Person person) {
		PersonHistory personHistory = new PersonHistory();
		personHistory.setPersonId(person.getId());
		personHistory.setFirstName(person.getFirstName());
		personHistory.setMiddleName(person.getMiddleName());
		personHistory.setLastName(person.getLastName());
		personHistory.setPostalCode(person.getPostalCode());
		personHistory.setStateProvince(person.getStateProvince());
		personHistory.setCity(person.getCity());
		personHistory.setBuilding(person.getBuilding());
		personHistory.setAddress1(person.getAddress1());
		personHistory.setAddress2(person.getAddress2());
		personHistory.setAddress3(person.getAddress3());
		return personHistory;
	}

}
