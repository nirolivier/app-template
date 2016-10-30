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



package com.niro.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.niro.domain.Authority;
import com.niro.domain.User;

/**
 * The class that contains information from the domain.
 * @author Olivier nirina
 * @since 1.0
 */
public class UserDto {

    @NotNull
    private String username;
    
    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    @Email
    @NotNull
    private String email;

    private String langKey;

    private boolean activated;

    private Set<String> authorities;

    public UserDto() {
    }

    /**
     * Create a new UserDto based on the {@link User} object.
     */
    public UserDto(User user) {
        if(user != null){
            init(user.getUsername(), user.getPassword(), user.getFirstName(),user.getLastName(),user.getEmail(),user.getLangKey(),user.isActivated(),user.getAuthorities().stream().map(Authority::getValue).collect(Collectors.toSet()));
        }
    }
    
    private void init(String username, String password, String firstName, String lastName, String email,String langKey, boolean activated, Set<String> authorities){
        this.username=username;
        this.firstName = firstName;
        this.password=password;
        this.lastName=lastName;
        this.email=email;
        this.langKey=langKey;
        this.activated=activated;

        if(authorities == null){
            this.authorities = new HashSet<String>();
        } else {
            this.authorities = authorities;
        }
    }
    
    
    /**
     * Create a new UserDto based on the {@link User} object.
     */
    public UserDto(String username, String password, String firstName, String lastName, String email,String langKey, boolean activated, Set<String> authorities ) {
        init(username, password, firstName, lastName, email, langKey, activated, authorities);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the langKey
     */
    public String getLangKey() {
        return langKey;
    }

    /**
     * @return the activated
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * @return the authorities
     */
    public Set<String> getAuthorities() {
        return authorities;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    
}
