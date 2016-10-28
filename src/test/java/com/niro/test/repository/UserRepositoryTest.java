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

package com.niro.test.repository;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.niro.domain.Authority;
import com.niro.domain.User;
import com.niro.repository.AuthorityRepository;
import com.niro.repository.UserRepository;

/**
 * An user repository test class.
 * @author Olivier nirina
 * @since 1.0
 */
public class UserRepositoryTest extends AbstractRepositoryTest{

    private @Autowired
    UserRepository userRepository;
    private @Autowired
    AuthorityRepository authorityRepository;

    @Test
    public void createUserTest(){
        User createdUser = createUser();
        Assert.assertNotNull(createdUser);
        Assert.assertNotNull(createdUser.getId());
    }

    private User createUser(){
        Authority existingAuthority = authorityRepository.findOne("ROLE_ANONYMOUS");
        if(existingAuthority == null){
            Authority authority = new Authority();
            authority.setValue("ROLE_ANONYMOUS");
            existingAuthority = authorityRepository.save(authority);
        }

        Set<Authority> authorities = new HashSet<>();
        authorities.add(existingAuthority);

        User user = new User();
        user.setFirstName("user1");
        user.setActivated(false);
        user.setActivationKey(RandomStringUtils.randomAlphanumeric(20));
        user.setAuthorities(authorities);
        user.setEmail("user1@gmail.com");
        user.setLangKey("en");
        user.setLastName("user1 last name");
        user.setPassword("user1");
        user.setUsername("user1");
        return userRepository.save(user);
    }

    @Test
    public void findOneByActivationKeyTest(){
        User createdUser = createUser();
        User foundUser = userRepository.findOneByActivationKey(createdUser.getActivationKey()).orElse(null);
        Assert.assertNotNull(foundUser);

        foundUser = userRepository.findOneByActivationKey("GhostUser").orElse(null);
        Assert.assertNull(foundUser);
    }

    @Test
    public void findOneByPasswordResetKeyTest(){
        User createdUser = createUser();
        User foundUser = userRepository.findOneByPasswordResetKey(createdUser.getPasswordResetKey()).orElse(null);
        Assert.assertNotNull(foundUser);

        foundUser = userRepository.findOneByPasswordResetKey("FakePwdKey").orElse(null);
        Assert.assertNull(foundUser);
    }

    @Test
    public void findOneByEmailTest(){
        User createdUser = createUser();
        User foundUser = userRepository.findOneByEmail(createdUser.getEmail()).orElse(null);
        Assert.assertNotNull(foundUser);

        foundUser = userRepository.findOneByEmail("FakeEmail@gmail.com").orElse(null);
        Assert.assertNull(foundUser);
    }

    @Test
    public void findOneByUsernameTest(){
        User createdUser = createUser();
        User foundUser = userRepository.findOneByUsername(createdUser.getUsername()).orElse(null);
        Assert.assertNotNull(foundUser);

        foundUser = userRepository.findOneByUsername("FakeEmail@gmail.com").orElse(null);
        Assert.assertNull(foundUser);
    }

    @Test
    public void findOneByIdTest(){
        User createdUser = createUser();
        User foundUser = userRepository.findOneById(createdUser.getId()).orElse(null);
        Assert.assertNotNull(foundUser);

        foundUser = userRepository.findOneById(RandomStringUtils.randomAlphabetic(10)).orElse(null);
        Assert.assertNull(foundUser);
    }
}
