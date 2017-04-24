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
package com.plat4u.person.account.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.plat4u.person.account.web.model.AccountModel;

/**
 * AccountControllerTest
 *
 * @author plat4u.com
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"file:src/test/resources/META-INF/spring/beans-webmvc.xml",
		"file:src/test/resources/META-INF/spring/beans-biz.xml"})
@WebAppConfiguration
public class AccountControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	/**
	 * success.
	 */
	@Test
	public void test_ログイン認証成功() throws Exception {
		
		AccountModel model = new AccountModel();
		model.setAccountId("test@plat4u.com");
		model.setPassword("test");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(model))
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("accountId", is("test@plat4u.com")))
        .andExpect(jsonPath("password", is("****")));

	}
	
	/**
	 * id is fail.<br/>
	 * bad request.
	 * @throws Exception
	 */
	@Test
	public void test_ログイン認証失敗() throws Exception {
		
		AccountModel model = new AccountModel();
		model.setAccountId("test@plat4u.com");
		model.setPassword("tests");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(model))
				)
		.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	/**
	 * id is fail.<br/>
	 * bad request.
	 * @throws Exception
	 */
	@Test
	public void test_アカウント情報入力失敗() throws Exception {
		
		AccountModel model = new AccountModel();
		model.setAccountId("test_plat4u.com");
		model.setPassword("test");
		
		mockMvc.perform(post("/api/v1.0/accounts/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(model))
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	/**
	 * success.
	 */
/*	@Test
	public void test_アカウント登録成功() throws Exception {
		
		AccountModel model = new AccountModel();
		model.setAccountId("test3@plat4u.com");
		model.setPassword("test3");
		
		mockMvc.perform(post("/api/v1.0/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(model))
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("accountId", is("test3@plat4u.com")))
        .andExpect(jsonPath("password", is("*****")));

	}
*/	
	/**
	 * success.
	 */
	@Test
	public void test_アカウント二重登録失敗() throws Exception {
		
		AccountModel model = new AccountModel();
		model.setAccountId("test@plat4u.com");
		model.setPassword("test");
		
		mockMvc.perform(post("/api/v1.0/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(model))
				)
		.andExpect(MockMvcResultMatchers.status().isConflict());

	}
}
