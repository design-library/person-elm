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
package com.plat4u.ghost.account.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.lang.reflect.InvocationTargetException;
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

import com.plat4u.ghost.account.biz.entity.Account;
import com.plat4u.ghost.account.biz.service.AccountService;
import com.plat4u.ghost.account.helper.AccountHelper;
import com.plat4u.ghost.account.web.msg.AccountMsg;
import com.plat4u.ghost.common.exception.AuthenticationException;
import com.plat4u.ghost.common.exception.DuplicateException;
import com.plat4u.ghost.common.exception.PathVariableException;

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
	public AccountMsg login(
			@RequestHeader Map<String, String> requestHeader, 
			@Valid @RequestBody AccountMsg messageCarrier) 
					throws AuthenticationException, IllegalAccessException, InvocationTargetException 
	{
		
		Account account = AccountHelper.toAccount(messageCarrier);
		
		Account accountResult = accountService.authenticate(account);

		AccountMsg messageCarrierResult = AccountHelper.toAccountMsg(accountResult);
		
		return messageCarrierResult;
		
	}
	
	@RequestMapping(method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountMsg create(
			@RequestHeader Map<String, String> requestHeader, 
			@Valid @RequestBody AccountMsg messageCarrier) 
					throws DuplicateException, IllegalAccessException, InvocationTargetException
	{
		
		Account account = AccountHelper.toAccount(messageCarrier);
		
		Account accountResult = accountService.create(account);

		AccountMsg messageCarrierResult = AccountHelper.toAccountMsg(accountResult);
		
		return messageCarrierResult;
		
	}
	
	@RequestMapping(path="/{id}", method=PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccountMsg updatePassword(
			@PathVariable String id,
			@RequestHeader Map<String, String> requestHeader, 
			@Valid @RequestBody AccountMsg messageCarrier) 
					throws PathVariableException, DuplicateException, IllegalAccessException, InvocationTargetException
	{
		
		if (messageCarrier.getId().equals(id)) {
			
			Account account = AccountHelper.toAccount(messageCarrier);
			Account accountResult = accountService.updatePassword(account);

			AccountMsg messageCarrierResult = AccountHelper.toAccountMsg(accountResult);
					
			return messageCarrierResult;
			
		} else {
			throw new PathVariableException("Pathvariable is worng.");
			
		}
		
	}

}
