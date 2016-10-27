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



package com.niro.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niro.domain.User;
import com.niro.exceptions.UserNotActivatedException;
import com.niro.repository.UserRepository;

/**
 * A custom user details service that load the user by his username to retrieve the details.
 * @author Olivier nirina
 * @since 1.0
 */
@Service
public class SimpleUserDetailsService implements UserDetailsService {
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly= true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        LOG.debug("Authenticating the user {0} ", username);
        Optional<User> loadedUser = userRepository.findOneByUsername(username);
        return loadedUser.map(user -> {
            if(!user.isActivated()){
                throw new UserNotActivatedException("User " + username + " was not activated;");
            }
            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getValue())).collect(Collectors.toList());
            
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the " +
                "database"));
    }

}
