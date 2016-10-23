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

package com.niro.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.niro.constants.AuthorityConstant;
import com.niro.constants.LoggingCode;
import com.niro.domain.Authority;
import com.niro.domain.User;
import com.niro.dto.UserDto;
import com.niro.repository.AuthorityRepository;
import com.niro.repository.UserRepository;
import com.niro.services.UserService;

/**
 * @author Olivier nirina
 * @since 1.0
 */
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto createUser(String username, String password, String firstName, String lastName, String email,
            String langKey) {
        LOG.debug("Creating a new user...");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLangKey(langKey);
        newUser.setActivated(false);
        newUser.setActivationKey(RandomStringUtils.random(30));
        Set<Authority> authorities = new HashSet<>();
        Authority userAuthority = authorityRepository.findOne(AuthorityConstant.USER);
        authorities.add(userAuthority);
        newUser.setAuthorities(authorities);
        return new UserDto(userRepository.save(newUser));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDto> findOneByActivationKey(String activationKey) {
        String message = messageSource.getMessage(LoggingCode.DEB_0001.name(), new String[] { activationKey },
                LocaleContextHolder.getLocale());
        LOG.debug(message);
        return userRepository.findOneByActivationKey(activationKey).map(user -> new UserDto(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDto> findOneByResetKey(String resetKey) {
        String message = messageSource.getMessage(LoggingCode.DEB_0002.name(), new String[] { resetKey },
                LocaleContextHolder.getLocale());
        LOG.debug(message);
        return userRepository.findOneByResetKey(resetKey).map(user -> new UserDto(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDto> findOneByEmail(String email) {
        String message = messageSource.getMessage(LoggingCode.DEB_0003.name(), new String[] { email },
                LocaleContextHolder.getLocale());
        LOG.debug(message);
        return userRepository.findOneByEmail(email).map(user -> new UserDto(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDto> findOneByUsername(String username) {
        String message = messageSource.getMessage(LoggingCode.DEB_0004.name(), new String[] { username },
                LocaleContextHolder.getLocale());
        LOG.debug(message);
        return userRepository.findOneByUsername(username).map(user -> new UserDto(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDto> findOneById(String userId) {
        String message = messageSource.getMessage(LoggingCode.DEB_0005.name(), new String[] { userId },
                LocaleContextHolder.getLocale());
        LOG.debug(message);
        return userRepository.findOneById(userId).map(user -> new UserDto(user));
    }

}
