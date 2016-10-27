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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class holds the users informations.
 *
 * @author Olivier nirina
 * @since 1.0
 */
@Document(collection = "T_USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean activated = false;

    @Id
    private String id;

    @NotNull
    private String username;

    @JsonIgnore
    @NotNull
    private String password;
    private String firstName;
    private String lastName;

    @Email
    @NotNull
    private String email;
    private String langKey;
    private String activationKey;
    private String passwordResetKey;

    @JsonIgnore
    @DBRef
    private Set<Authority> authorities = new HashSet<Authority>();

    public User() {
    }

    /**
     * Returns the id of this user.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Assigns a new id for this user.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the current password of this user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set a new password of this user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the firstname of this user.
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of this user.
     *
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of this user.
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets this last name of this user.
     *
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the email of this user.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of this user.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the lang of this user.
     *
     * @return the langKey
     */
    public String getLangKey() {
        return langKey;
    }

    /**
     * Sets the lang of this user.
     *
     * @param langKey the langKey to set
     */
    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    /**
     * Retrieves the activation key of this user.
     *
     * @return the activationKey
     */
    public String getActivationKey() {
        return activationKey;
    }

    /**
     * Sets the activation key of this user.
     *
     * @param activationKey the activationKey to set
     */
    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    /**
     * Returns the password reset key requested by this user.
     *
     * @return the passwordResetKey
     */
    public String getPasswordResetKey() {
        return passwordResetKey;
    }

    /**
     * Sets the new password reset key requested by this user.
     *
     * @param passwordResetKey the passwordResetKey to set
     */
    public void setPasswordResetKey(String passwordResetKey) {
        this.passwordResetKey = passwordResetKey;
    }

    /**
     * Indicates if the user account is activated.
     *
     * @return true if the condition is met.
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Activates or not this user account.
     *
     * @param activated a flag
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * Returns the user name of this user which is used as a login.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new user name for this user. It is used as a login.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the granted authorities of this user.
     *
     * @return the user authorities
     */
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    /**
     * Grants the authorities to this user.
     *
     * @param authorities the authorities to set
     */
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id)
                .append(", username=").append(username)
                .append(", password=").append(password)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", email=").append(email)
                .append(", langKey=").append(langKey)
                .append(", activationKey=").append(activationKey)
                .append(", passwordResetKey=").append(passwordResetKey)
                .append(", authorities=").append(authorities).append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(activated, user.activated)
                .append(id, user.id)
                .append(username, user.username)
                .append(password, user.password)
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .append(email, user.email)
                .append(langKey, user.langKey)
                .append(activationKey, user.activationKey)
                .append(passwordResetKey, user.passwordResetKey)
                .append(authorities, user.authorities)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(activated)
                .append(id)
                .append(username)
                .append(password)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(langKey)
                .append(activationKey)
                .append(passwordResetKey)
                .append(authorities)
                .toHashCode();
    }
}
