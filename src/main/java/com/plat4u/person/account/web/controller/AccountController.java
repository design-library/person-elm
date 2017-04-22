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
package com.plat4u.person.account.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.plat4u.person.account.biz.domain.Account;
import com.plat4u.person.account.biz.service.AccountService;
import com.plat4u.person.account.web.model.AccountModel;
import com.plat4u.person.exception.AuthenticationException;
import com.plat4u.person.exception.PathVariableException;

/**
 * AccountController
 *
 * @author plat4u.com
 * @version 1.0
 */
@Controller
@RequestMapping("/api/v1.0/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(path="/login", method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountModel login(
			@RequestHeader Map<String, String> requestHeader, 
			@Valid @RequestBody AccountModel model) throws AuthenticationException 
	{
		
		Account account = new Account(
				model.getAccountId(), model.getPassword());
		Account accountRtn = accountService.authenticate(account);

		AccountModel modelRtn = toAccountModel(accountRtn);
		
		return modelRtn;
		
	}
	
	@RequestMapping(method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountModel create(
			@RequestHeader Map<String, String> requestHeader, 
			@Valid @RequestBody AccountModel model)
	{
		
		Account account = new Account(
				model.getAccountId(), model.getPassword());
		Account accountRtn = accountService.create(account);

		AccountModel modelRtn = toAccountModel(accountRtn);
		
		return modelRtn;
		
	}
	
	@RequestMapping(path="/{accountId}", method=PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountModel updatePassword(
			@PathVariable String accountId,
			@RequestHeader Map<String, String> requestHeader, 
			@Valid @RequestBody AccountModel model) throws PathVariableException
	{
		
		if (model.getAccountId().equals(accountId)) {
			
			Account account = new Account(
					accountId, model.getPassword());
			Account accountRtn = accountService.updatePassword(account);

			AccountModel modelRtn = toAccountModel(accountRtn);
					
			return modelRtn;
			
		} else {
			throw new PathVariableException("Pathvariable is worng.");
			
		}
		
	}
	
	private AccountModel toAccountModel(Account account) {
		AccountModel model = new AccountModel();
		model.setAccountId(account.id().id());
		model.setPassword(account.password().mask());
		return model;
	}

}
