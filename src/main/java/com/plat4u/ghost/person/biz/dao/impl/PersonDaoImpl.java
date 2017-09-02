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
import com.plat4u.ghost.person.biz.entity.Person;

/**
 * PersonDaoImpl
 *
 * @author plat4u.com
 * @version 1.0
 */
@Repository
public class PersonDaoImpl implements com.plat4u.ghost.person.biz.dao.PersonDao {
	
	@PersistenceContext
    EntityManager entityManager;
	
	/* (non Javadoc)
	 * @see com.plat4u.ghost.person.biz.dao.PersonDao#findOne(com.plat4u.ghost.person.biz.entity.Person)
	 */
	public Person findOne(Person person) {
		TypedQuery<Person> query = entityManager.createNamedQuery("Person.findOne", Person.class);
		query.setParameter("id", person.getId());
		Person personEntityRtn = (Person)query.getSingleResult();
		
		entityManager.clear();

		return personEntityRtn;
	}

	/* (non Javadoc)
	 * @see com.plat4u.ghost.person.biz.dao.PersonDao#insert(com.plat4u.ghost.person.biz.entity.Person)
	 */
	public Person insert(Person person) throws DuplicateException {
		
		Query existsCheckQuery = entityManager.createNamedQuery("Person.count");
		existsCheckQuery.setParameter("id", person.getId());
		Long countOfEntity = (Long)existsCheckQuery.getSingleResult();
		if (countOfEntity > 0) {
			throw new DuplicateException("Double registration. " + person.getId());
			
		} else {
			entityManager.persist(person);
		}
		TypedQuery<Person> query = entityManager.createNamedQuery("Person.findOne", Person.class);
		query.setParameter("id", person.getId());
		Person personEntityRtn = (Person)query.getSingleResult();
		
		entityManager.clear();
		
		return personEntityRtn;
	}

	/* (non Javadoc)
	 * @see com.plat4u.ghost.person.biz.dao.PersonDao#update(com.plat4u.ghost.person.biz.entity.Person)
	 */
	public Person update(Person person) {
		
		entityManager.merge(person);
		
		TypedQuery<Person> query = entityManager.createNamedQuery("Person.findOne", Person.class);
		query.setParameter("id", person.getId());
		Person personEntityRtn = (Person)query.getSingleResult();
		
		entityManager.clear();
		
		return personEntityRtn;
	}

}
