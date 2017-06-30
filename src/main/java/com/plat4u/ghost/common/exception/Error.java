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
package com.plat4u.ghost.common.exception;

/**
 * Error
 *
 * @author plat4u.com
 * @version 1.0
 */
public class Error {
	
	private String status;
	
	private String message;
	
	public Error(String status, String message) {
		this.status = status;
		this.message = message;
		
	}

	/**
	 * Get the status .
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the status.
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get the message .
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message.
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
