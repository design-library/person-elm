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

import com.plat4u.ghost.account.web.msg.AccountMsg;

/**
 * AccountControllerTest
 *
 * @author plat4u.com
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class AccountControllerTest extends ControllerTest {
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void test_1_success_account_touroku() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("test@plat4u.com");
		messageCarrier.setPassword("testo");
		
		mockMvc.perform(post("/api/v1.0/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("id", is("test@plat4u.com")))
        .andExpect(jsonPath("password", is("*****")));

	}
	
	@Test
	public void test_2_fail_account_touroku_duplicate() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("test@plat4u.com");
		messageCarrier.setPassword("testo");
		
		mockMvc.perform(post("/api/v1.0/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isConflict());

	}
	
	@Test
	public void test_3_fail_account_koushin_password() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("test@plat4u.com");
		messageCarrier.setPassword("test");
		
		mockMvc.perform(put("/api/v1.0/accounts/test100@plat4u.com")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void test_4_success_account_koushin_password() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("test@plat4u.com");
		messageCarrier.setPassword("test");
		
		mockMvc.perform(put("/api/v1.0/accounts/test@plat4u.com")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("id", is("test@plat4u.com")))
        .andExpect(jsonPath("password", is("****")));

	}
	
	@Test
	public void test_5_success_account_login() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("test@plat4u.com");
		messageCarrier.setPassword("test");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("id", is("test@plat4u.com")))
        .andExpect(jsonPath("password", is("****")));

	}
	
	@Test
	public void test_6_fail_account_login_id() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("testX@plat4u.com");
		messageCarrier.setPassword("test");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	public void test_7_fail_account_login_password() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("test@plat4u.com");
		messageCarrier.setPassword("testX");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	public void test_8_fail_account_login_id_format() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("test_plat4u.com");
		messageCarrier.setPassword("test");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void test_9_fail_account_login_no_id() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("");
		messageCarrier.setPassword("test");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void test_10_fail_account_login_no_password() throws Exception {
		
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId("test@plat4u.com");
		messageCarrier.setPassword("");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(messageCarrier))
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
