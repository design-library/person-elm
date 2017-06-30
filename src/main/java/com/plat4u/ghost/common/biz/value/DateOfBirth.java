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
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;

/**
 * DateOfBirth
 *
 * @author plat4u.com
 * @version 1.0
 */
public class DateOfBirth implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LocalDate dateOfBirth;
	
	private LocalDate now;
	
	private Period age;
	
	private DateTimeFormatter formatter;
	
	public DateOfBirth(int year, int month, int day) {
		// default format is "yyyy/mm/dd"
		this(year, month, day, "yyyy-MM-dd");
	}
	
	public DateOfBirth(int year, int month, int day, String format) {
		this.formatter = DateTimeFormatter.ofPattern(format);
		this.dateOfBirth = LocalDate.of(year, month, day);
		now = LocalDate.now();
		age = Period.between(dateOfBirth, now);
	}
	
	/**
	 * format pattern "Gy�NM��d��"
	 * @return
	 */
	public String japaneseFormat() {
		DateTimeFormatter japaneseFormatter = formatter.withChronology(JapaneseChronology.INSTANCE);
		JapaneseDate japaneseDate = JapaneseDate.from(dateOfBirth);
		return japaneseDate.format(japaneseFormatter);
	}
	
	@Override
	public String toString() {
		return dateOfBirth.format(formatter);
	}
	
	public Date toDate() {
		return Date.valueOf(dateOfBirth);
	}
	
	/**
	 * age
	 * @return
	 */
	public int ageYears() {
		return age.getYears();
	}
	
	/**
	 * Fraction month of the age
	 * @return
	 */
	public int ageMonths() {
		return age.getMonths();
	}
}
