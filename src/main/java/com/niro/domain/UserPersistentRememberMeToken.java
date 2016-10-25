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
package com.niro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * This class holds the information about the remember-me functionality.
 * When the user needs to remember his login, this should be done per machine
 * he signs in. Thus this class contains the ip address of the machine and an optional the user agent field.
 *
 * @author Olivier nirina
 * @since 1.0
 */
@Document(collection = "T_PERSISTENT_TOKENS")
public class UserPersistentRememberMeToken implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAX_USER_AGENT_LEN = 255;

    @Id
    private String id;
    private final String series;
    private final String tokenValue;
    private final Date date;
    private final String ipAddress;
    private final String userAgent;

    @JsonIgnore
    @DBRef
    private final User user;

    /**
     * Constructs an object of this class base on the fields in parameter.
     * @param user the logged user
     * @param ipAddress the ip address the user is logging.
     * @param userAgent the user agent
     * @param series the token series
     * @param tokenValue the token value
     * @param date the user remember date
     */
    public UserPersistentRememberMeToken(User user, String ipAddress, String userAgent, String series, String tokenValue, Date date) {
        this.user = user;
        this.series = series;
        this.tokenValue = tokenValue;
        this.date = date;
        this.ipAddress = ipAddress;

        if (userAgent != null && userAgent.length() >= MAX_USER_AGENT_LEN) {
            this.userAgent = userAgent.substring(0, MAX_USER_AGENT_LEN - 1);
        } else {
            this.userAgent = userAgent;
        }
    }

    /**
     * This method constructs an object based on {@link PersistentRememberMeToken} object,
     * the user, ip address and the user agent.
     * @param user the logged user
     * @param ipAddress the ip address the user is logging.
     * @param userAgent the user agent
     * @param token the provided remember token object
     * @see UserPersistentRememberMeToken contructor.
     */
    public UserPersistentRememberMeToken(User user, String ipAddress, String userAgent, PersistentRememberMeToken token) {
        this(user, ipAddress, userAgent, token.getSeries(), token.getTokenValue(), token.getDate());
    }

    /**
     * Retrieves the id of this persistent token object.
     * @return an id
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the series of this persistent token object. The value of series is unique.
     * @return a token series
     */
    public String getSeries() {
        return series;
    }

    /**
     * Retrieves the token value of this persistent token object.
     * @return a token
     */
    @JsonIgnore
    @NotNull
    public String getTokenValue() {
        return tokenValue;
    }

    /**
     * Retrieves the user remember date.
     * @return the user remember date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Retrieves the ip address of the machine the flag was set.
     * @return an ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Retrieves the user agent.
     * @return the user agent.
     */
    public String getUserAgent() {
        return userAgent;
    }


}
