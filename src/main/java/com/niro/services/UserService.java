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



package com.niro.services;

import java.util.Optional;

import com.niro.dto.UserDto;

/**
 * An interface that defines user method.
 * @author Olivier nirina
 * @since 1.0
 */
public interface UserService {
    
    /**
     * Create a new user.
     * @return an user
     */
    UserDto createUser(String username, String password, String firstName, String lastName, String email,
            String langKey);
    /**
     * Find an user by his activation key.
     * @param activationKey the activation key.
     * @return An empty optional if no result was found
     */
    Optional<UserDto> findOneByActivationKey(String activationKey);

    /**
     * Find an user by his reset key.
     * @param resetKey the reset key.
     * @return An empty optional if no result was found
     */
    Optional<UserDto> findOneByResetKey(String resetKey);

    /**
     * Find an user by his email.
     * @param email the email
     * @return An empty optional if no result was found
     */
    Optional<UserDto> findOneByEmail(String email);

    /**
     * Find an user by the username.
     * @param username the username key.
     * @return An empty optional if no result was found
     */
    Optional<UserDto> findOneByUsername(String username);

    /**
     * Find an user by id
     * @param userId the user id
     * @return An empty optional if no result was found
     */
    Optional<UserDto> findOneById(String userId);
}
