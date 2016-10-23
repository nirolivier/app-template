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

package com.niro.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niro.constants.UrlConstant;
import com.niro.services.UserService;
import com.niro.web.models.UserModel;

/**
 * This class provides methods for user account. To manage the user refer to the
 * {@link UserRestController}.
 * 
 * @author Olivier nirina
 * @since 1.0
 * @see UserRestController
 */
@RestController
@RequestMapping(value = UrlConstant.API)
public class UserAccountRestController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user account and returns a status of the registration.
     * 
     * @param request
     *            a http request
     * @param model
     *            the user model
     * @return the registration status
     */
    @RequestMapping(value = UrlConstant.SIGN_UP, method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> signup(HttpServletRequest request, @Valid @RequestBody UserModel model) {
        return userService.findOneByUsername(model.getUsername())
                .map(user -> new ResponseEntity<>("UserName already is used", HttpStatus.BAD_REQUEST))
                .orElseGet(() -> userService.findOneByEmail(model.getEmail())
                        .map(user -> new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST))
                        .orElseGet(() -> {
                            userService.createUser(model.getUsername(), model.getPassword(), model.getFirstName(),
                                    model.getLastName(), model.getEmail(), model.getLangKey());
                            return new ResponseEntity<>(HttpStatus.CREATED);
                        }));

    }

}
