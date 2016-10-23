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

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.util.Assert;

import com.niro.domain.PersistentToken;

/**
 * @author Olivier nirina
 * @since 1.0
 */
public class RememberMePersistentTokenRepository implements PersistentTokenRepository {
    

    private SimplePersistentTokenRepository persistentTokenRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 
     */
    public RememberMePersistentTokenRepository(SimplePersistentTokenRepository persistentTokenRepository) {
        Assert.notNull(persistentTokenRepository, "Persistent token repository must be set.");
        this.persistentTokenRepository = persistentTokenRepository;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentToken persistentToken = new PersistentToken();
        persistentToken.setSeries(token.getSeries());
        persistentToken.setTokenValue(token.getTokenValue());
        persistentToken.setTokenDate(token.getDate());
        persistentToken.setUser(userRepository.findOneByUsername(token.getUsername()).orElse(null));
        persistentTokenRepository.save(persistentToken);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUserTokens(String username) {
        // TODO Auto-generated method stub

    }

}
