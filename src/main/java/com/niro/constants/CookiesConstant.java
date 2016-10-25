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



package com.niro.constants;

import com.niro.exceptions.IllegalCallException;

/**
 * This class contains constants that are used as cookies fields. 
 * @author Olivier nirina
 * @since 1.0
 */
public final class CookiesConstant {
    
    /**
     * An user client id cookies session. 
     */
    public static final String SESSION_ID = "JSESSIONID";
    
    /**
     * Angular cross-site request forgery protection.
     */
    public static final String XSRF_TOKEN = "XSRF-TOKEN";

    /**
     * the call attempt of this constructor will throw an exception.
     * @throws IllegalCallException if this constructor is called.
     */
    private CookiesConstant() {
        throw new IllegalCallException("The constructor of this class cannot be invoked.");
    }
}
