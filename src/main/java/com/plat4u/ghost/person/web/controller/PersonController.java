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
package com.plat4u.ghost.person.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.plat4u.ghost.common.exception.DuplicateException;
import com.plat4u.ghost.common.exception.NotFoundException;
import com.plat4u.ghost.common.exception.PathVariableException;
import com.plat4u.ghost.common.exception.RequestParamException;
import com.plat4u.ghost.person.biz.entity.Person;
import com.plat4u.ghost.person.biz.service.PersonService;
import com.plat4u.ghost.person.helper.PersonHelper;
import com.plat4u.ghost.person.web.msg.PersonMsg;

/**
 * PersonController
 *
 * @author plat4u.com
 * @version 1.0
 */
@Controller
@RequestMapping("/api/v1.0/persons")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(method=GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public PersonMsg find(
			@RequestHeader Map<String, String> requestHeader, 
			@RequestParam("id") String id,
			@Valid @RequestBody PersonMsg messageCarrier) 
					throws NotFoundException, RequestParamException, IllegalAccessException, InvocationTargetException
	{
		PersonMsg personMsgResult = null;
		if (messageCarrier.getId().equals(id)) {
			
			Person person = PersonHelper.toPerson(messageCarrier);
			
			Person personResult = personService.findOne(person);
			
			personMsgResult = PersonHelper.toPersonMsg(personResult);
			
		} else {
			throw new RequestParamException("RequestParam is worng.");
			
		}
		return personMsgResult;
		
	}
	
	@RequestMapping(method=POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public PersonMsg create(
			@RequestHeader Map<String, String> requestHeader, 
			@Valid @RequestBody PersonMsg messageCarrier) 
					throws DuplicateException, IllegalAccessException, InvocationTargetException
	{
		Person person = PersonHelper.toPerson(messageCarrier);
		
		Person personResult = personService.create(person);
		
		PersonMsg personMsgResult = PersonHelper.toPersonMsg(personResult);
		
		return personMsgResult;
		
	}
	
	@RequestMapping(path="/{id}", method=PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public PersonMsg update(
			@RequestHeader Map<String, String> requestHeader, 
			@PathVariable String id,
			@Valid @RequestBody PersonMsg messageCarrier) 
					throws PathVariableException, DuplicateException, IllegalAccessException, InvocationTargetException
	{
		PersonMsg personMsgResult = null;
		if (messageCarrier.getId().equals(id)) {
			Person person = PersonHelper.toPerson(messageCarrier);
			
			Person personResult = personService.update(person);
			
			personMsgResult = PersonHelper.toPersonMsg(personResult);
			
		} else {
			throw new PathVariableException("Pathvariable is worng.");
			
		}
		return personMsgResult;
		
	}

}
