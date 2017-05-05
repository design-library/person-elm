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
package com.plat4u.ghost.account.web.helper;

import com.plat4u.ghost.account.biz.entity.Account;
import com.plat4u.ghost.account.web.msg.AccountMsg;

/**
 * AccountHelper
 *
 * @author plat4u.com
 * @version 1.0
 */
public class AccountHelper {
	
	public static Account toAccount(AccountMsg messageCarrier) {
		
		String id = messageCarrier.getId();
		String password = messageCarrier.getPassword();
		
		Account account = new Account();
		account.setId(id);
		account.setPassword(password);
		
		return account;
		
	}
	
	public static AccountMsg toAccountMsg(Account account) {
		AccountMsg messageCarrier = new AccountMsg();
		messageCarrier.setId(account.getId());
		messageCarrier.setPassword(account.getPassword());
		return messageCarrier;
	}

}
