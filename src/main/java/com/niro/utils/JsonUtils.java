/*
 * Copyright 2016 Olivier nirina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.niro.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niro.exceptions.IllegalCallException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Olivier nirina
 * @since 1.0
 */
public final class JsonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
    /**
     * Default constructor
     */
    private JsonUtils() {
        throw new IllegalCallException("Cannot invoke the constructor of this class");
    }

    /**
     * Convert an object to an json byte.
     * @param object the object to convert
     * @return an empty byte array if an error occurred.
     */
    public static byte[] convertObjectToByte(Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            LOG.error("Json could not convert the object in parameter to byte array.", e);
        }
        return new byte[0];
    }

    /**
     * Convert an object to an json string.
     * @param object the object to convert
     * @return an empty string if an error occurred.
     */
    public static String convertObjectToString(Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("Json could not convert the object in parameter to byte array.", e);
        }
        return StringUtils.EMPTY;
    }
}
