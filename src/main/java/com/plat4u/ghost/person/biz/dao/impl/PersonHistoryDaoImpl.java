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
package com.plat4u.ghost.person.biz.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.plat4u.ghost.common.exception.DuplicateException;
import com.plat4u.ghost.person.biz.dao.PersonHistoryDao;
import com.plat4u.ghost.person.biz.entity.PersonHistory;

/**
 * PersonHistoryDaoImpl
 *
 * @author plat4u.com
 * @version 1.0
 */
@Repository
public class PersonHistoryDaoImpl implements PersonHistoryDao {

	@PersistenceContext
    EntityManager entityManager;
	
	/* (”ñ Javadoc)
	 * @see com.plat4u.ghost.person.biz.dao.PersonHistoryDao#findOne(com.plat4u.ghost.person.biz.entity.PersonHistory)
	 */
	public PersonHistory findOne(PersonHistory personHistory) {
		TypedQuery<PersonHistory> query = entityManager.createNamedQuery("PersonHistory.findOne", PersonHistory.class);
		query.setParameter("id", personHistory.getId());
		PersonHistory personHistoryResutl = (PersonHistory)query.getSingleResult();
		
		entityManager.clear();
		
		return personHistoryResutl;
		
	}

	/* (”ñ Javadoc)
	 * @see com.plat4u.ghost.person.biz.dao.PersonHistoryDao#insert(com.plat4u.ghost.person.biz.entity.PersonHistory)
	 */
	public PersonHistory insert(PersonHistory personHistory) throws DuplicateException {
		
		Query existsCheckQuery = entityManager.createNamedQuery("PersonHistory.count");
		existsCheckQuery.setParameter("id", personHistory.getId());
		Long countOfEntity = (Long)existsCheckQuery.getSingleResult();
		if (countOfEntity > 0) {
			throw new DuplicateException("Double registration. " + personHistory.getId());
			
		} else {
			entityManager.persist(personHistory);
		}		
		TypedQuery<PersonHistory> query = entityManager.createNamedQuery("PersonHistory.findOne", PersonHistory.class);
		query.setParameter("id", personHistory.getId());
		PersonHistory personHistoryResult = (PersonHistory)query.getSingleResult();
		
		entityManager.clear();
		
		return personHistoryResult;
		
	}

}
