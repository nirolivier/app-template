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

    @Id
    private String id;
    private String series;
    private String tokenValue;
    private Date date;

    @JsonIgnore
    @DBRef
    private User user;

    public UserPersistentRememberMeToken() {
    }

    public User getUser() {
        return user;
    }

    /**
     * Sets a new persistent token id.
     * @param id the id of the token
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets a new persistent token eries
     * @param series the series to set
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * Sets a new persistent token value.
     * @param tokenValue the token value
     */
    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    /**
     * Sets the data of the persistent token.
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets the user of this token.
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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

}
