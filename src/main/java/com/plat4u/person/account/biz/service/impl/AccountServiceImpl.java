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
package com.plat4u.person.account.biz.service.impl;

import java.sql.Timestamp;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plat4u.person.account.biz.dao.AccountDao;
import com.plat4u.person.account.biz.dao.PasswordHistoryDao;
import com.plat4u.person.account.biz.domain.Account;
import com.plat4u.person.account.biz.entity.AccountEntity;
import com.plat4u.person.account.biz.entity.PasswordHistoryEntity;
import com.plat4u.person.account.biz.service.AccountService;
import com.plat4u.person.exception.AuthenticationException;
import com.plat4u.person.exception.DuplicateException;
import com.plat4u.person.util.RandomStringUtils;

/**
 * AccountServiceImpl
 *
 * @author plat4u.com
 * @version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private PasswordHistoryDao passwordHistoryDao;
	
	/* (”ñ Javadoc)
	 * @see com.plat4u.person.account.biz.service.AccountService#authenticate(com.plat4u.person.account.biz.domain.Account)
	 */
	public Account authenticate(Account account) throws AuthenticationException {
		
		// create AccountEntity.
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(account.id());
		accountEntity.setPassword(account.password().stretch());
		
		// execute.
		AccountEntity accountEntityRtn = null;
		try {
			accountEntityRtn = accountDao.findOne(accountEntity);
		} catch (NoResultException e) {
			throw new AuthenticationException(e.getMessage(), e);
		}
		// create Account
		Account accountRtn = new Account(accountEntityRtn.getId(), account.password().mask());
		
		return accountRtn;
	}

	/* (”ñ Javadoc)
	 * @see com.plat4u.person.account.biz.service.AccountService#create(com.plat4u.person.account.biz.domain.Account)
	 */
	public Account create(Account account) throws  DuplicateException {
		
		String id = RandomStringUtils.alphanumeric(12);
		String accountId = account.id();
		String registeredId = account.id();
		String stretchedPassword = account.password().stretch();
		Timestamp registeredDate = new Timestamp(System.currentTimeMillis());
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(accountId);
		accountEntity.setPassword(stretchedPassword);
		AccountEntity accountEntityRtn = accountDao.insert(accountEntity);
		
		PasswordHistoryEntity passwordHistoryEntity = new PasswordHistoryEntity();
		passwordHistoryEntity.setId(id);
		passwordHistoryEntity.setAccountId(accountId);
		passwordHistoryEntity.setPassword(stretchedPassword);
		passwordHistoryEntity.setRegisteredId(registeredId);
		passwordHistoryEntity.setRegistrationDate(registeredDate);
		passwordHistoryDao.insert(passwordHistoryEntity);
		
		Account accountRtn = new Account(accountEntityRtn.getId(), account.password().mask());
		
		return accountRtn;
	}

	/* (”ñ Javadoc)
	 * @see com.plat4u.person.account.biz.service.AccountService#updatePassword(com.plat4u.person.account.biz.domain.Account)
	 */
	public Account updatePassword(Account account) throws DuplicateException {
		
		String id = RandomStringUtils.alphanumeric(12);
		String accountId = account.id();
		String registeredId = account.id();
		String stretchedPassword = account.password().stretch();
		Timestamp registeredDate = new Timestamp(System.currentTimeMillis());
		
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setId(accountId);
		accountEntity.setPassword(stretchedPassword);
		AccountEntity accountEntityRtn = accountDao.update(accountEntity);
		
		PasswordHistoryEntity passwordHistoryEntity = new PasswordHistoryEntity();
		passwordHistoryEntity.setId(id);
		passwordHistoryEntity.setAccountId(accountId);
		passwordHistoryEntity.setPassword(stretchedPassword);
		passwordHistoryEntity.setRegisteredId(registeredId);
		passwordHistoryEntity.setRegistrationDate(registeredDate);
		passwordHistoryDao.insert(passwordHistoryEntity);
		
		Account accountRtn = new Account(accountEntityRtn.getId(), account.password().mask());
		
		return accountRtn;
	}

}
