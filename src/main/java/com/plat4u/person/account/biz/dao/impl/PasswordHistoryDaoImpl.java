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
package com.plat4u.person.account.biz.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.plat4u.person.account.biz.dao.PasswordHistoryDao;
import com.plat4u.person.account.biz.entity.PasswordHistoryEntity;
import com.plat4u.person.exception.DuplicateException;

/**
 * PasswordHistoryDaoImpl
 *
 * @author plat4u.com
 * @version 1.0
 */
@Repository
public class PasswordHistoryDaoImpl implements PasswordHistoryDao {
	
	@PersistenceContext
    EntityManager entityManager;
	
	/* (”ñ Javadoc)
	 * @see com.plat4u.person.account.biz.dao.PasswordHistoryDao#findByAccountIdAndPassword(com.plat4u.person.account.biz.entity.PasswordHistoryEntity)
	 */
	public PasswordHistoryEntity findByAccountIdAndPassword(PasswordHistoryEntity entity) {

		TypedQuery<PasswordHistoryEntity> query = entityManager.createNamedQuery("PasswordHistoryEntity.findByAccountIdAndPassword", PasswordHistoryEntity.class);
		query.setParameter("accountId", entity.getAccountId());
		query.setParameter("password", entity.getPassword());
		PasswordHistoryEntity passwordHistoryEntityRtn = (PasswordHistoryEntity)query.getSingleResult();
		
		return passwordHistoryEntityRtn;
	}

	/* (”ñ Javadoc)
	 * @see com.plat4u.person.account.biz.dao.PasswordHistoryDao#insert(com.plat4u.person.account.biz.entity.PasswordHistoryEntity)
	 */
	public PasswordHistoryEntity insert(PasswordHistoryEntity entity) throws DuplicateException {
		Query existsCheckQuery = entityManager.createNamedQuery("PasswordHistoryEntity.count");
		existsCheckQuery.setParameter("accountId", entity.getAccountId());
		existsCheckQuery.setParameter("password", entity.getPassword());
		Long countOfEntity = (Long)existsCheckQuery.getSingleResult();
		if (countOfEntity > 0) {
			throw new DuplicateException("Double registration. " + entity.getAccountId());
			
		} else {
			entityManager.persist(entity);
			entityManager.flush();
		}		
		TypedQuery<PasswordHistoryEntity> query = entityManager.createNamedQuery("PasswordHistoryEntity.findByAccountIdAndPassword", PasswordHistoryEntity.class);
		query.setParameter("accountId", entity.getAccountId());
		query.setParameter("password", entity.getPassword());
		PasswordHistoryEntity passwordHistoryEntityRtn = (PasswordHistoryEntity)query.getSingleResult();
		
		return passwordHistoryEntityRtn;
	}

}
