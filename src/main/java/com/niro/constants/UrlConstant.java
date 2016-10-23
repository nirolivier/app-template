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
 * @author Olivier nirina
 * @since 1.0
 */
public final class UrlConstant {
    
    /**
     * URL that is used to point to the root path. The value of this constant is {@value}
     */
    public static final String ROOT = "/";
    
    /**
     * URL used to point to the API path. The value of this constant is {@value}
     */
    public static final String API = "/api";
    
    /**
     * URL that is used to login. The value of this constant is {@value}
     */
    public static final String SIGN_IN = "/signin";
    
    /**
     * URL that is used to process the authentication. The value of this constant is {@value}
     */
    public static final String AUTH = "/auth";
    
    /**
     * URL that is used to sign up. The value of this constant is {@value}
     */
    public static final String SIGN_UP = "/signup";
    
    /**
     * URL that is used to point to the account root path. The value of this constant is {@value}
     */
    public static final String ACCOUNT = "/account";
    
    /**
     * URL that is used to view user profile. The value of this constant is {@value}
     */
    public static final String PROFILE = "/viewprofile";
    
    /**
     * URL that is used to reset password. The value of this constant is {@value}
     */
    public static final String PWD_RESET = "/pwdreset/init";
    
    /**
     * URL that is used to validate password reset. The value of this constant is {@value}
     */
    public static final String PWD_VALIDATE = "/pwdreset/validate";

    /**
     * URL that is used to access the administrator root path. The value of this constant is {@value}
     */
    public static final String ADMIN = "/adm";
    
    /**
     * URL that is used to logout. The value of this constant is {@value}
     */
    public static final String LOGOUT = "/api/logout";

    /**
     * the call attempt of this constructor will throw an exception.
     * @throws IllegalCallException if this constructor is called.
     */
    private UrlConstant() {
        throw new IllegalCallException("The constructor of this class cannot be invoked.");
    }
}
