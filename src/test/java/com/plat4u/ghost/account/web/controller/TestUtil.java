/**
  * Copyright 2017 plat4u.com.
 * 
 * This file is part of person composition.
 *
 *  person composition is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  person composition is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with person composition.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.plat4u.ghost.account.web.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TestUtil<br/>
 * From http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
 * @author plat4u.com
 * @version 1.0
 * 
 */
public class TestUtil {
	
    public static final MediaType APPLICATION_JSON_UTF8 = 
    		new MediaType(
    				MediaType.APPLICATION_JSON.getType(), 
    				MediaType.APPLICATION_JSON.getSubtype(), 
    				Charset.forName("utf8"));

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
    	
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
        
    }

/*    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < length; index++) {
            builder.append("a");
        }
        return builder.toString();
    }*/
}
