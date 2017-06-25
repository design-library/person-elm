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
package com.plat4u.ghost.common.biz.value;

import java.io.Serializable;

/**
 * Address
 *
 * @author plat4u.com
 * @version 1.0
 */
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String postalCode;
	
	private String stateProvince;
	
	private String city;
	
	private String building;
	
	private String address1;
	
	private String address2;
	
	private String address3;
	
	public Address(String postalCode, String stateProvince, String city, 
			String building, String address1, String address2, String address3) {
		this.postalCode = postalCode;
		this.stateProvince = stateProvince;
		this.city = city;
		this.building = building;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
	}
	
	public String postalCode() {
		return postalCode;
	}
	
	public String stateProvince() {
		return stateProvince;
	}
	
	public String city() {
		return city;
	}
	
	public String building() {
		return building;
	}
	
	public String address1() {
		return address1;
	}
	
	public String address2() {
		return address2;
	}
	
	public String address3() {
		return address3;
	}
}
