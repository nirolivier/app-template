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
 * An interface that defines user services method.
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
    Optional<UserDto> findOneByPasswordResetKey(String resetKey);

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

    /**
     * Activates the user account having the specified unique key.
     * @param activationkey the user activation key
     * @return the activated user.
     */
    Optional<UserDto> activateAccount(String activationkey);

    /**
     * Update the user password.
     * @param newPassword the new password
     * @return the updated user with new password.
     */
    Optional<UserDto> updatePassword(String newPassword);

    /**
     * Resets the password of the user having the email address.
     * @param email the user email address
     * @return the user with reset password.
     */
    Optional<UserDto> resetPassword(String email);

    /**
     * Validates the reset password. Set the reset key and reset date to null.
     * @param newPassword the new password
     * @param resetPasswordKey the reset key
     * @return
     */
    Optional<UserDto> validateResetPassword(String newPassword, String resetPasswordKey);
}
