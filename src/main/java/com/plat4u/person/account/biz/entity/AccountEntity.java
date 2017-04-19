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
package com.plat4u.person.account.biz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AccountEntity
 *
 * @author plat4u.com
 * @version 1.0
 */
@Entity
@Table(name="Account")
public class AccountEntity {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "password")	
	private String password;

	/**
	 * Get the id .
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the id.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the password .
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
