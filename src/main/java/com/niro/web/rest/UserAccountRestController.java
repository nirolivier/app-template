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

import com.niro.services.UserService;
import com.niro.web.models.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.niro.constants.UrlConstant.*;

/**
 * This class provides methods for user account. To manage the user refer to the
 * {@link UserRestController}.
 * 
 * @author Olivier nirina
 * @since 1.0
 * @see UserRestController
 */
@RestController
@RequestMapping(value = API)
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
    @RequestMapping(value = SIGN_UP, method = RequestMethod.POST, produces = {
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


    /**
     * Activate an account using the activation key. The key can be retrieved from the URL with the parameter <code>actKey.</code>
     * @param activationkey the activation key. If an error occurs, the status 500 is returned. Otherwise, a status 200 is returned.
     * @return a status of the request.
     */
    @RequestMapping(value = ACCOUNT + ACTIVATE, method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> activateAccount(@RequestParam(value = "actkey") String activationkey){
        return userService.activateAccount(activationkey)
                .map(userDto -> new ResponseEntity<String>(HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = ACCOUNT + PWD_UPDATE, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> updatePassword(@RequestBody String newPassword){
        if(StringUtils.isNotEmpty(newPassword)){
            return userService.updatePassword(newPassword)
                    .map(userDto -> new ResponseEntity<String>(HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = ACCOUNT + PWD_RESET_INIT, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public  ResponseEntity<?> resetPassword(@RequestBody String email, HttpServletRequest request){
        return userService.resetPassword(email)
                .map(userDto -> new ResponseEntity<String>(HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = ACCOUNT + PWD_RESET_VALIDATE, method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> validateResetPassword(@RequestBody String newPassword, @RequestBody String resetPasswordKey){

        if(StringUtils.isNotEmpty(resetPasswordKey) && StringUtils.isNotEmpty(newPassword)) {
            return userService.validateResetPassword(newPassword, resetPasswordKey)
                    .map(userDto -> new ResponseEntity<String>(HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = AUTH, method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String isAuthenticated(HttpServletRequest request){
        return request.getRemoteUser();
    }

}
