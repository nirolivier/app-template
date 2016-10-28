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



package com.niro.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.niro.domain.User;
import com.niro.domain.UserPersistentRememberMeToken;

/**
 * @author Olivier nirina
 * @since 1.0
 */
public interface SimplePersistentTokenRepository extends MongoRepository<UserPersistentRememberMeToken, String> {

    /**
     * Retrieves the token by series.
     * @param series the series
     * @return the token related to the series.
     */
    Optional<UserPersistentRememberMeToken> findOneBySeries(String series);

    /**
     * Retrieves the token of the given username.
     * @return a token
     */
    Optional<UserPersistentRememberMeToken> findOneByUser(User user);

    void deleteByUser(User user);
}
