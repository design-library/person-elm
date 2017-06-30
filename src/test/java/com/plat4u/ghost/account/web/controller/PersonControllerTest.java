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

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.plat4u.ghost.person.web.msg.PersonMsg;

/**
 * PersonControllerTest
 *
 * @author plat4u.com
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class PersonControllerTest extends ControllerTest {
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void test_1_fail_person_touroku_required() throws Exception {
		
		PersonMsg messageCarrier = new PersonMsg();
		messageCarrier.setFirstName("");
		messageCarrier.setLastName("iwamoto");
		messageCarrier.setMiddleName("ghost");
		messageCarrier.setDateOfBirth("1978-08-15");
		
		mockMvc.perform(post("/api/v1.0/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
	
	@Test
	public void test_2_fail_person_touroku_date() throws Exception {
		
		PersonMsg messageCarrier = new PersonMsg();
		messageCarrier.setFirstName("");
		messageCarrier.setLastName("iwamoto");
		messageCarrier.setMiddleName("ghost");
		messageCarrier.setDateOfBirth("1978-08-99");
		
		mockMvc.perform(post("/api/v1.0/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
	
	@Test
	public void test_3_success_person_touroku() throws Exception {
		
		PersonMsg messageCarrier = new PersonMsg();
		messageCarrier.setFirstName("shinya");
		messageCarrier.setLastName("iwamoto");
		messageCarrier.setMiddleName("ghost");
		messageCarrier.setDateOfBirth("1978-08-15");
		messageCarrier.setPostalCode("123-0015");
		messageCarrier.setStateProvince("Tokyo");
		messageCarrier.setCity("Katsushika-ku");
		messageCarrier.setAddress1("Minamimizumoto1-11-21");
		messageCarrier.setAddress2("");
		messageCarrier.setAddress3("");
		messageCarrier.setBuilding("Syatol Kanamachi 303");
		messageCarrier.setMailAddress1("sendmail-is@nifty.com");
		messageCarrier.setMailAddress2("net.shinya@gmail.com");
		messageCarrier.setPhoneNumber1("090-1845-9844");
		messageCarrier.setPhoneNumber2("");
		
		mockMvc.perform(post("/api/v1.0/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("firstName", is("shinya")))
		.andExpect(jsonPath("lastName", is("iwamoto")))
		.andExpect(jsonPath("middleName", is("ghost")))
		.andExpect(jsonPath("dateOfBirth", is("1978-08-15")))
		.andExpect(jsonPath("postalCode", is("123-0015")))
		.andExpect(jsonPath("stateProvince", is("Tokyo")))
		.andExpect(jsonPath("city", is("Katsushika-ku")))
		.andExpect(jsonPath("address1", is("Minamimizumoto1-11-21")))
		.andExpect(jsonPath("address2", is("")))
		.andExpect(jsonPath("address3", is("")))
		.andExpect(jsonPath("building", is("Syatol Kanamachi 303")))
		.andExpect(jsonPath("mailAddress1", is("sendmail-is@nifty.com")))
		.andExpect(jsonPath("mailAddress2", is("net.shinya@gmail.com")))
		.andExpect(jsonPath("phoneNumber1", is("090-1845-9844")))
		.andExpect(jsonPath("phoneNumber2", is("")));

	}
/*
	@Test
	public void test_4_fail_person_koushin_telephon() throws Exception {
		
		PersonMsg messageCarrier = new PersonMsg();
		messageCarrier.setFirstName("shinya");
		messageCarrier.setLastName("iwamoto");
		messageCarrier.setMiddleName("ghost");
		messageCarrier.setDateOfBirth("1978-08-15");
		messageCarrier.setPostalCode("123-0015");
		messageCarrier.setStateProvince("Tokyo");
		messageCarrier.setCity("Katsushika-ku");
		messageCarrier.setAddress1("Minamimizumoto1-11-21");
		messageCarrier.setAddress2("");
		messageCarrier.setAddress3("");
		messageCarrier.setBuilding("Syatol Kanamachi 303");
		messageCarrier.setMailAddress1("sendmail-is@nifty.com");
		messageCarrier.setMailAddress2("net.shinya@gmail.com");
		messageCarrier.setPhoneNumber1("090-1845-9844000");
		messageCarrier.setPhoneNumber2("");
		
		mockMvc.perform(put("/api/v1.0/persons/*****")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
	
	@Test
	public void test_5_success_person_koushin() throws Exception {
		
		PersonMsg messageCarrier = new PersonMsg();
		messageCarrier.setFirstName("shinya");
		messageCarrier.setLastName("iwamoto");
		messageCarrier.setMiddleName("ghost");
		messageCarrier.setDateOfBirth("1978-08-15");
		messageCarrier.setPostalCode("123-0015");
		messageCarrier.setStateProvince("Tokyo");
		messageCarrier.setCity("Katsushika-ku");
		messageCarrier.setAddress1("Minamimizumoto1-11-21");
		messageCarrier.setAddress2("");
		messageCarrier.setAddress3("");
		messageCarrier.setBuilding("");
		messageCarrier.setMailAddress1("sendmail-is@nifty.com");
		messageCarrier.setMailAddress2("net.shinya@gmail.com");
		messageCarrier.setPhoneNumber1("090-1845-8944");
		messageCarrier.setPhoneNumber2("");
		
		mockMvc.perform(put("/api/v1.0/persons/*****")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("firstName", is("shinya")))
		.andExpect(jsonPath("lastName", is("iwamoto")))
		.andExpect(jsonPath("middleName", is("ghost")))
		.andExpect(jsonPath("dateOfBirth", is("1978-08-15")))
		.andExpect(jsonPath("postalCode", is("123-0015")))
		.andExpect(jsonPath("stateProvince", is("Tokyo")))
		.andExpect(jsonPath("city", is("Katsushika-ku")))
		.andExpect(jsonPath("address1", is("Minamimizumoto1-11-21")))
		.andExpect(jsonPath("address2", is("")))
		.andExpect(jsonPath("address3", is("")))
		.andExpect(jsonPath("building", is("")))
		.andExpect(jsonPath("mailAddress1", is("sendmail-is@nifty.com")))
		.andExpect(jsonPath("mailAddress2", is("net.shinya@gmail.com")))
		.andExpect(jsonPath("phoneNumber1", is("090-1845-8944")))
		.andExpect(jsonPath("phoneNumber2", is("")));

	}
*/
}
