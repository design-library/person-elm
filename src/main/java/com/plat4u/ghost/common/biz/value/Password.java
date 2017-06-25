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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password
 *
 * @author plat4u.com
 * @version 1.0
 */
public class Password {

	private static final int STRETCH_COUNT = 1000;
	
	private String id;
	
	private String password;
	
	public Password(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	/**
	 * mask password.
	 * @return masked password
	 */
	public String mask() {
		String mask = "";
		for (int i = 0;i < password.length();i++) {
			mask += "*";
		}
		return mask;
	}
	
	/**
	 * salt password.
	 * @return salted password
	 */
	public String salt() {
		String salt = getSha256(id);
		return getSha256(salt + password);
	}
	
	/**
	 * stretch password.
	 * @return stretched password
	 */
	public String stretch() {
		String salt = getSha256(id);
		String hash = "";
		
		for(int i = 0;i < STRETCH_COUNT;i++) {
			hash = getSha256(hash + salt + password);
		}
		
		return hash;
	}

	private String getSha256(String target) {
		MessageDigest md = null;
		StringBuffer buf = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(target.getBytes());
			byte[] digest = md.digest();
			
			for(int i = 0;i < digest.length;i++) {
				buf.append(String.format("%02x", digest[i]));
				
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
		}
		return buf.toString();
		
	}

}
