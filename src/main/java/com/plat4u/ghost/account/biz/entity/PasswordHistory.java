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
package com.plat4u.ghost.account.biz.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PasswordHistory
 *
 * @author plat4u.com
 * @version 1.0
 */
@Entity
@Table(name="Password_History")
public class PasswordHistory {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "account_id")
	private String accountId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "registered_id")
	private String registeredId;
	
	@Column(name = "registration_date")
	private Timestamp registrationDate;

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
	 * Get the accountId .
	 * @return accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * Set the accountId.
	 * @param accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	/**
	 * Get the registeredId .
	 * @return registeredId
	 */
	public String getRegisteredId() {
		return registeredId;
	}

	/**
	 * Set the registeredId.
	 * @param registeredId
	 */
	public void setRegisteredId(String registeredId) {
		this.registeredId = registeredId;
	}

	/**
	 * Get the registrationDate .
	 * @return registrationDate
	 */
	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * Set the registrationDate.
	 * @param registrationDate
	 */
	public void setRegistrationDate(Timestamp registeredDate) {
		this.registrationDate = registeredDate;
	}

}
