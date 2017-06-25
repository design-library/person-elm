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
package com.plat4u.ghost.person.biz.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Person
 *
 * @author plat4u.com
 * @version 1.0
 */
@Entity
@Table(name="Person")
public class Person {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "state_province")
	private String stateProvince;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "building")
	private String building;
	
	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;
	
	@Column(name = "address3")
	private String address3;
	
	@Column(name = "phone_number1")
	private String phoneNumber1;
	
	@Column(name = "phone_number2")
	private String phoneNumber2;
	
	@Column(name = "mail_address1")
	private String mailAddress1;
	
	@Column(name = "mail_address2")
	private String mailAddress2;

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
	 * Get the lastName .
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the lastName.
	 * @param lastName
	 */
	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	/**
	 * Get the middleName .
	 * @return middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Set the middleName.
	 * @param middleName
	 */
	public void setMiddleName(String middle_name) {
		this.middleName = middle_name;
	}

	/**
	 * Get the firstName .
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the firstName.
	 * @param firstName
	 */
	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	/**
	 * Get the dateOfBirth .
	 * @return dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Set the dateOfBirth.
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Get the postalCode .
	 * @return postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Set the postalCode.
	 * @param postalCode
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Get the stateProvince .
	 * @return stateProvince
	 */
	public String getStateProvince() {
		return stateProvince;
	}

	/**
	 * Set the stateProvince.
	 * @param stateProvince
	 */
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	/**
	 * Get the city .
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set the city.
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Get the building .
	 * @return building
	 */
	public String getBuilding() {
		return building;
	}

	/**
	 * Set the building.
	 * @param building
	 */
	public void setBuilding(String building) {
		this.building = building;
	}

	/**
	 * Get the address1 .
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * Set the address1.
	 * @param address1
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * Get the address2 .
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * Set the address2.
	 * @param address2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * Get the address3 .
	 * @return address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * Set the address3.
	 * @param address3
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * Get the phoneNumber1 .
	 * @return phoneNumber1
	 */
	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	/**
	 * Set the phoneNumber1.
	 * @param phoneNumber1
	 */
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	/**
	 * Get the phoneNumber2 .
	 * @return phoneNumber2
	 */
	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	/**
	 * Set the phoneNumber2.
	 * @param phoneNumber2
	 */
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	/**
	 * Get the mailAddress1 .
	 * @return mailAddress1
	 */
	public String getMailAddress1() {
		return mailAddress1;
	}

	/**
	 * Set the mailAddress1.
	 * @param mailAddress1
	 */
	public void setMailAddress1(String mailAddress1) {
		this.mailAddress1 = mailAddress1;
	}

	/**
	 * Get the mailAddress2 .
	 * @return mailAddress2
	 */
	public String getMailAddress2() {
		return mailAddress2;
	}

	/**
	 * Set the mailAddress2.
	 * @param mailAddress2
	 */
	public void setMailAddress2(String mailAddress2) {
		this.mailAddress2 = mailAddress2;
	}

}
