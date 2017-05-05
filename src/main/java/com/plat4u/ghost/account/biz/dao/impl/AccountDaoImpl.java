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

import com.plat4u.ghost.account.biz.dao.AccountDao;
import com.plat4u.ghost.account.biz.entity.Account;
import com.plat4u.ghost.common.exception.DuplicateException;

/**
 * AccountDaoImpl
 *
 * @author plat4u.com
 * @version 1.0
 */
@Repository
public class AccountDaoImpl implements AccountDao {
	
	@PersistenceContext
    EntityManager entityManager;
	
	/* (”ñ Javadoc)
	 * @see com.plat4u.ghost.account.biz.dao.AccountDao#findOne(com.plat4u.ghost.account.biz.entity.Account)
	 */
	public Account findOne(Account entity) {
		
		TypedQuery<Account> query = entityManager.createNamedQuery("Account.findOne", Account.class);
		query.setParameter("id", entity.getId());
		query.setParameter("password", entity.getPassword());
		Account accountEntityRtn = (Account)query.getSingleResult();
		
		entityManager.clear();

		return accountEntityRtn;
	}

	/* (”ñ Javadoc)
	 * @see com.plat4u.ghost.account.biz.dao.AccountDao#insert(com.plat4u.ghost.account.biz.entity.Account)
	 */
	public Account insert(Account entity) throws DuplicateException {
		
		Query existsCheckQuery = entityManager.createNamedQuery("Account.count");
		existsCheckQuery.setParameter("id", entity.getId());
		existsCheckQuery.setParameter("password", entity.getPassword());
		Long countOfEntity = (Long)existsCheckQuery.getSingleResult();
		if (countOfEntity > 0) {
			throw new DuplicateException("Double registration. " + entity.getId());
			
		} else {
			entityManager.persist(entity);
		}		
		TypedQuery<Account> query = entityManager.createNamedQuery("Account.findOne", Account.class);
		query.setParameter("id", entity.getId());
		query.setParameter("password", entity.getPassword());
		Account accountEntityRtn = (Account)query.getSingleResult();
		
		entityManager.clear();
		
		return accountEntityRtn;
	}

	/* (”ñ Javadoc)
	 * @see com.plat4u.ghost.account.biz.dao.AccountDao#update(com.plat4u.ghost.account.biz.entity.Account)
	 */
	public Account update(Account entity) {
		
		entityManager.merge(entity);
		
		TypedQuery<Account> query = entityManager.createNamedQuery("Account.findOne", Account.class);
		query.setParameter("id", entity.getId());
		query.setParameter("password", entity.getPassword());
		Account accountEntityRtn = (Account)query.getSingleResult();
		
		entityManager.clear();
		
		return accountEntityRtn;
	}

}
