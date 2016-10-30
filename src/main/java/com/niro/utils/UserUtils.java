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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Olivier nirina
 * @since 1.0
 */
public final class UserUtils {

    /**
     * Default constructor
     */
    private UserUtils(){
        throw new IllegalCallException("Cannot invoke the constructor of this class");
    }

    /**
     * Retrieves the username of current authenticated user from the security context holder
     * {@link org.springframework.security.core.context.SecurityContextHolder}.
     * @return the user name
     */
    public static String getCurrentUserName(){
        String username = null;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if(authentication == null){
            return null;
        }

        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        } else if(principal instanceof String){
            username = (String) principal;
        }
        return username;
    }
}
