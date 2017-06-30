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
package com.plat4u.ghost.common.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.plat4u.ghost.common.exception.AuthenticationException;
import com.plat4u.ghost.common.exception.DuplicateException;
import com.plat4u.ghost.common.exception.Errors;
import com.plat4u.ghost.common.exception.PathVariableException;

/**
 * ExceptionControllerAdvice
 *
 * @author plat4u.com
 * @version 1.0
 */
@Component
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Errors handleException(MethodArgumentNotValidException e) {
		BindingResult results = e.getBindingResult();
		List<FieldError> fieldErrors = results.getFieldErrors();
		Errors errors = new Errors();
		for (FieldError fieldError : fieldErrors) {
			String defaultMessage = "Bad Request. " + fieldError.getDefaultMessage() + " (" + fieldError.getField() + ")";
			String[] placeHolder = {fieldError.getField()};
			String message = messageSource.getMessage(
					"com.plat4u.ghost.account.biz.controller.AccountController.login", 
					placeHolder, 
					defaultMessage, 
					LocaleContextHolder.getLocale());
			errors.add("400", message);
			
		}
		return errors;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Errors handleException(PathVariableException e) {
		Errors errors = new Errors();
		String defaultMessage = "Bad Request. " + e.getMessage();
		String message = messageSource.getMessage(
				"com.plat4u.ghost.account.biz.service.AccountController.updatePassword", 
				null, 
				defaultMessage, 
				LocaleContextHolder.getLocale());
		errors.add("400", message);
		
		return errors;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public Errors handleException(DuplicateException e) {
		Errors errors = new Errors();
		String defaultMessage = "Conflict. " + e.getMessage();
		String message = messageSource.getMessage(
				"com.plat4u.ghost.account.biz.service.AccountController.create", 
				null, 
				defaultMessage, 
				LocaleContextHolder.getLocale());
		errors.add("409", message);
		
		return errors;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public Errors handleException(AuthenticationException e) {
		Errors errors = new Errors();
		String defaultMessage = "Unauthorized. ";
		String message = messageSource.getMessage(
				"com.plat4u.ghost.account.biz.service.AccountService.authenticate.fail", 
				null, 
				defaultMessage, 
				LocaleContextHolder.getLocale());
		errors.add("401", message);
		
		return errors;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Errors handleException(IllegalAccessException e) {
		Errors errors = new Errors();
		String defaultMessage = "Internal Server Error. ";
		String message = messageSource.getMessage(
				"com.plat4u.ghost.account.biz.service.AccountService", 
				null, 
				defaultMessage, 
				LocaleContextHolder.getLocale());
		errors.add("500", message);
		
		return errors;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Errors handleException(InvocationTargetException e) {
		Errors errors = new Errors();
		String defaultMessage = "Internal Server Error. ";
		String message = messageSource.getMessage(
				"com.plat4u.ghost.account.biz.service.AccountService", 
				null, 
				defaultMessage, 
				LocaleContextHolder.getLocale());
		errors.add("500", message);
		
		return errors;
	}
}
