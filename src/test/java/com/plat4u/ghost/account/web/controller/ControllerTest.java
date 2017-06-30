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

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * ControllerTest
 *
 * @author plat4u.com
 * @version 1.0
 */
@ContextConfiguration(locations={
		"file:src/test/resources/META-INF/spring/beans-webmvc.xml",
		"file:src/test/resources/META-INF/spring/beans-biz.xml"})
@WebAppConfiguration
public class ControllerTest {
	
	@Autowired
	protected WebApplicationContext ctx;
	
	protected MockMvc mockMvc;
	
	@BeforeClass
	public static void init() throws Exception {
		
		String file = "src/test/resources/META-INF/jdbc.properties";
		Properties prop = new Properties();
		InputStream is = new FileInputStream(file);
		prop.load(is);
		is.close();
		
		String driverClassName = prop.getProperty("jdbc.driverClassName");
		String url = prop.getProperty("jdbc.url");
		String username = prop.getProperty("jdbc.username");
		String password = prop.getProperty("jdbc.password");
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driverClassName).newInstance();
			con = DriverManager.getConnection(url + "&user=" + username + "&password=" + password);
			
			String queryPh = "delete from password_history";
			ps = con.prepareStatement(queryPh);
			ps.executeUpdate();
			ps.close();
			
			String queryA = "delete from account";
			ps = con.prepareStatement(queryA);
			ps.executeUpdate();
			ps.close();
			
			String queryPnh = "delete from person_history";
			ps = con.prepareStatement(queryPnh);
			ps.executeUpdate();
			ps.close();
			
			String queryP = "delete from person";
			ps = con.prepareStatement(queryP);
			ps.executeUpdate();
			ps.close();
			
		} catch(SQLException e) {
			throw e;
			
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
