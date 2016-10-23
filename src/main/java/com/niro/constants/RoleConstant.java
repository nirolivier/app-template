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
 * This class contains user roles. 
 * <br><br><b>Note:</b> The value of each role in this class <b>is not prefixed by {@code ROLE}</b>.
 * @author Olivier nirina
 * @since 1.0
 */
public final class RoleConstant {
    
    /**
     * The value of this constant is {@value}.
     */
    public static final String ADMIN = "ADMIN";
    
    /**
     * The value of this constant is {@value}.
     */
    public static final String ANONYMOUS = "ANONYMOUS";
    
    /**
     * The value of this constant is {@value}.
     */
    public static final String USER = "USER";

    /**
     * the call attempt of this constructor will throw an exception.
     * @throws IllegalCallException if this constructor is called.
     */
    private RoleConstant() {
        throw new IllegalCallException("The constructor of this class cannot be called.");
    }
}
