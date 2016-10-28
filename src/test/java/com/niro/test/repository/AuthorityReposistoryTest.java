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

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.niro.domain.Authority;
import com.niro.repository.AuthorityRepository;

/**
 * Test class related to the {@link AuthorityReposistory} class.
 * @author Olivier nirina
 * @since 1.0
 */
public class AuthorityReposistoryTest extends AbstractRepositoryTest{

    private @Autowired
    AuthorityRepository authorityRepository;

    @Test
    public void testCreateAuthority(){
        Authority existingAuthority = authorityRepository.findOne("ROLE_ANONYMOUS");
        if(existingAuthority == null){
            Authority authority = new Authority();
            authority.setValue("ROLE_ANONYMOUS");
            existingAuthority = authorityRepository.save(authority);
        }

        Assert.assertNotNull(existingAuthority);
        Assert.assertNotNull(existingAuthority.getValue());
    }
}
