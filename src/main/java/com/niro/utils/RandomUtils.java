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

import com.niro.exceptions.IllegalCallException;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Olivier nirina
 * @since 1.0
 */
public final class RandomUtils {

    private static  final int KEY_LENGTH = 30;

    private RandomUtils(){
        throw new IllegalCallException("Cannot invoke the constructor of this class.");
    }

    /**
     * Generates the password reset key. The key has a length of 30.
     * @return a key
     */
    public static String generatePasswordResetKey(){
        return RandomStringUtils.randomNumeric(KEY_LENGTH);
    }
}
