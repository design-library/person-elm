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
package com.plat4u.person.account.biz.domain;

import java.io.Serializable;

/**
 * Account
 *
 * @author plat4u.com
 * @version 1.0
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String password;
	
	public Account(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String id() {
		return id;
	}
	
	public Password password() {
		return new Password(id, password);
	}

}
