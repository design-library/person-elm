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
package com.plat4u.ghost.person.biz.service.impl;

import java.sql.Timestamp;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plat4u.ghost.common.exception.DuplicateException;
import com.plat4u.ghost.common.exception.NotFoundException;
import com.plat4u.ghost.common.util.RandomStringUtils;
import com.plat4u.ghost.person.biz.dao.PersonDao;
import com.plat4u.ghost.person.biz.dao.PersonHistoryDao;
import com.plat4u.ghost.person.biz.entity.Person;
import com.plat4u.ghost.person.biz.entity.PersonHistory;
import com.plat4u.ghost.person.biz.service.PersonService;
import com.plat4u.ghost.person.helper.PersonHelper;

/**
 * PersonServiceImpl
 *
 * @author plat4u.com
 * @version 1.0
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private PersonHistoryDao personHistoryDao;
	
	/* (non Javadoc)
	 * @see com.plat4u.ghost.person.biz.service.PersonService#findOne(com.plat4u.ghost.person.biz.entity.Person)
	 */
	public Person findOne(Person person) throws NotFoundException {

		Person personResult = null;
		try {
			personResult = personDao.findOne(person);
			
		} catch (NoResultException e) {
			throw new NotFoundException("Not Fond.", e);
			
		}
		return personResult;
		
	}

	/* (non Javadoc)
	 * @see com.plat4u.ghost.person.biz.service.PersonService#create(com.plat4u.ghost.person.biz.entity.Person)
	 */
	public Person create(Person person) throws DuplicateException {
		
		String personId = RandomStringUtils.alphanumeric(12);
		person.setId(personId);
		Person personResult = personDao.insert(person);
		
		insertPersonHistory(personResult);
		
		return personResult;
		
	}

	/* (non Javadoc)
	 * @see com.plat4u.ghost.person.biz.service.PersonService#update(com.plat4u.ghost.person.biz.entity.Person)
	 */
	public Person update(Person person) throws DuplicateException {
		
		Person personResult = personDao.update(person);
		
		insertPersonHistory(personResult);
		
		return personResult;
	}
	
	private void insertPersonHistory(Person person) throws DuplicateException {
		
		PersonHistory personHistory = PersonHelper.toPersonHistory(person);
		String personHistoryId = RandomStringUtils.alphanumeric(12);
		personHistory.setId(personHistoryId);
		personHistory.setRegisteredId(person.getId());
		Timestamp registrationDate = new Timestamp(System.currentTimeMillis());
		personHistory.setRegistrationDate(registrationDate);
		personHistoryDao.insert(personHistory);
		
	}
	
}
