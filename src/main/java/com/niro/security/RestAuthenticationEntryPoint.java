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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * This class represents an REST authentication entry point which is called by
 * {@link org.springframework.security.web.access.ExceptionTranslationFilter
 * ExceptionTranslationFilter} to begin the authentication mechanism if an
 * exception is thrown, this filter sends an status 401 which indicates an
 * unauthorized access. Such exceptions will generally be thrown by an
 * {@link org.springframework.security.access.intercept.AbstractSecurityInterceptor
 * AbstractSecurityInterceptor}, which is the main provider of authorization
 * services.
 * 
 * @author Olivier nirina
 * @since 1.0
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOG = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);
    
    /**
     * When an authentication exception is thrown, this method sends a 401 (SC_UNAUTHORIZED) HTTP code
     * with the error message.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        LOG.error("An authentication gets fail. Unauthorized access!", authException);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}
