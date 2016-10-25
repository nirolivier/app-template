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

import com.niro.constants.LoggingCode;
import com.niro.domain.User;
import com.niro.domain.UserPersistentRememberMeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * The remember-me function repository class. It delegates the persistence mechanism to
 * the {@link SimplePersistentTokenRepository} class.
 *
 * @author Olivier nirina
 * @see SimplePersistentTokenRepository
 * @since 1.0
 */
public class RememberMePersistentTokenRepository implements PersistentTokenRepository {
    private static final Logger LOG = LoggerFactory.getLogger(RememberMePersistentTokenRepository.class);

    @Autowired
    private MessageSource  messageSource;
    private SimplePersistentTokenRepository persistentTokenRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Default constructor
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
        String message = messageSource.getMessage(LoggingCode.DEB_0006.name(), new Object[]{null}, LocaleContextHolder.getLocale());
        LOG.debug(message);

        // retrieve user by username;
        User user = userRepository.findOneByUsername(token.getUsername()).orElse(null);

        // Create a new remember-me token for the user.
       // FIXME UserPersistentRememberMeToken rememberMeToken = new UserPersistentRememberMeToken(user,token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String message = messageSource.getMessage(LoggingCode.DEB_0007.name(), new Object[]{null}, LocaleContextHolder.getLocale());
        LOG.debug(message);
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
