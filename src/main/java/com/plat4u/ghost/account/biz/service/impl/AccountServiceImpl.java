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
package com.plat4u.ghost.account.biz.service.impl;

import java.sql.Timestamp;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plat4u.ghost.account.biz.dao.AccountDao;
import com.plat4u.ghost.account.biz.dao.PasswordHistoryDao;
import com.plat4u.ghost.account.biz.entity.Account;
import com.plat4u.ghost.account.biz.entity.PasswordHistory;
import com.plat4u.ghost.account.biz.service.AccountService;
import com.plat4u.ghost.common.biz.value.Password;
import com.plat4u.ghost.common.exception.AuthenticationException;
import com.plat4u.ghost.common.exception.DuplicateException;
import com.plat4u.ghost.common.util.RandomStringUtils;

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
	 * @see com.plat4u.ghost.account.biz.service.AccountService#authenticate(com.plat4u.ghost.account.biz.entity.Account)
	 */
	public Account authenticate(Account account) throws AuthenticationException {
		
		Account accountResult = null;
		try {
			String id = account.getId();
			String password = account.getPassword();
			Password passwordValue = new Password(id, password);
			String stretchedPassword = passwordValue.stretch();
			account.setPassword(stretchedPassword);
			
			accountResult = accountDao.findOne(account);
			accountResult.setPassword(passwordValue.mask());

		} catch (NoResultException e) {
			throw new AuthenticationException(e.getMessage(), e);
			
		}
		
		return accountResult;
		
	}

	/* (”ñ Javadoc)
	 * @see com.plat4u.ghost.account.biz.service.AccountService#create(com.plat4u.ghost.account.biz.entity.Account)
	 */
	public Account create(Account account) throws  DuplicateException {
		
		String accountId = account.getId();
		String password = account.getPassword();
		Password passwordValue = new Password(accountId, password);
		String stretchedPassword = passwordValue.stretch();
		account.setPassword(stretchedPassword);
		
		Account accountResult = accountDao.insert(account);
		
		String id = RandomStringUtils.alphanumeric(12);
		String registeredId = account.getId();
		Timestamp registeredDate = new Timestamp(System.currentTimeMillis());
		
		PasswordHistory passwordHistory = new PasswordHistory();
		passwordHistory.setId(id);
		passwordHistory.setAccountId(accountId);
		passwordHistory.setPassword(stretchedPassword);
		passwordHistory.setRegisteredId(registeredId);
		passwordHistory.setRegistrationDate(registeredDate);
		passwordHistoryDao.insert(passwordHistory);
		
		accountResult.setPassword(passwordValue.mask());
		
		return accountResult;
		
	}

	/* (”ñ Javadoc)
	 * @see com.plat4u.ghost.account.biz.service.AccountService#updatePassword(com.plat4u.ghost.account.biz.entity.Account)
	 */
	public Account updatePassword(Account account) throws DuplicateException {
		
		String accountId = account.getId();
		String password = account.getPassword();
		Password passwordValue = new Password(accountId, password);
		String stretchedPassword = passwordValue.stretch();
		account.setPassword(stretchedPassword);
		
		Account accountResult = accountDao.update(account);
		
		String id = RandomStringUtils.alphanumeric(12);
		String registeredId = account.getId();
		Timestamp registeredDate = new Timestamp(System.currentTimeMillis());
		
		PasswordHistory passwordHistory = new PasswordHistory();
		passwordHistory.setId(id);
		passwordHistory.setAccountId(accountId);
		passwordHistory.setPassword(stretchedPassword);
		passwordHistory.setRegisteredId(registeredId);
		passwordHistory.setRegistrationDate(registeredDate);
		passwordHistoryDao.insert(passwordHistory);
		
		accountResult.setPassword(passwordValue.mask());
		
		return accountResult;
		
	}

}
