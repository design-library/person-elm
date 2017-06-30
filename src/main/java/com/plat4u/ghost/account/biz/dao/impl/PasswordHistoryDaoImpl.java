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
package com.plat4u.ghost.account.biz.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.plat4u.ghost.account.biz.dao.PasswordHistoryDao;
import com.plat4u.ghost.account.biz.entity.PasswordHistory;
import com.plat4u.ghost.common.exception.DuplicateException;

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
	
	/* (�� Javadoc)
	 * @see com.plat4u.ghost.account.biz.dao.PasswordHistoryDao#findByAccountIdAndPassword(com.plat4u.ghost.account.biz.entity.PasswordHistory)
	 */
	public PasswordHistory findByAccountIdAndPassword(PasswordHistory passwordHistory) {

		TypedQuery<PasswordHistory> query = 
				entityManager.createNamedQuery("PasswordHistory.findByAccountIdAndPassword", PasswordHistory.class);
		query.setParameter("accountId", passwordHistory.getAccountId());
		query.setParameter("password", passwordHistory.getPassword());
		PasswordHistory passwordHistoryResult = (PasswordHistory)query.getSingleResult();

		entityManager.clear();
		
		return passwordHistoryResult;
		
	}

	/* (�� Javadoc)
	 * @see com.plat4u.ghost.account.biz.dao.PasswordHistoryDao#insert(com.plat4u.ghost.account.biz.entity.PasswordHistory)
	 */
	public PasswordHistory insert(PasswordHistory passwordHistory) throws DuplicateException {
		Query existsCheckQuery = entityManager.createNamedQuery("PasswordHistory.count");
		existsCheckQuery.setParameter("accountId", passwordHistory.getAccountId());
		existsCheckQuery.setParameter("password", passwordHistory.getPassword());
		Long countOfEntity = (Long)existsCheckQuery.getSingleResult();
		if (countOfEntity > 0) {
			throw new DuplicateException("Double registration. " + passwordHistory.getAccountId());
			
		} else {
			entityManager.persist(passwordHistory);
		}		
		TypedQuery<PasswordHistory> query = entityManager.createNamedQuery("PasswordHistory.findByAccountIdAndPassword", PasswordHistory.class);
		query.setParameter("accountId", passwordHistory.getAccountId());
		query.setParameter("password", passwordHistory.getPassword());
		PasswordHistory passwordHistoryResult = (PasswordHistory)query.getSingleResult();
		
		entityManager.clear();
		
		return passwordHistoryResult;
		
	}

}
