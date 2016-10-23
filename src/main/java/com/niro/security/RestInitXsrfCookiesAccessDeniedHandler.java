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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.stereotype.Component;

import com.niro.constants.CookiesConstant;

/**
 * This class clears the XSRF token used by angularJs if an denied access is happened. 
 * It will sends a 403 (SC_FORBIDDEN ) HTTP code.
 * @author Olivier nirina
 * @since 1.0
 */
@Component("restInitXsrfCookiesAccessDeniedHandler")
public class RestInitXsrfCookiesAccessDeniedHandler implements AccessDeniedHandler {

    private final AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        if(!response.isCommitted() && accessDeniedException instanceof CsrfException){
            Cookie cookie = new Cookie(CookiesConstant.XSRF_TOKEN, StringUtils.EMPTY);
            cookie.setMaxAge(0);
            cookie.setHttpOnly(false);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        }
        
        accessDeniedHandler.handle(request, response, accessDeniedException);
    }
    
    /**
     * Delegates the registration of the error page to the
     * {@link AccessDeniedHandlerImpl} because it is called during the denied
     * access process. <br>
     * 
     * The error page to use. Must begin with a "/" and is interpreted relative
     * to the current context root.
     * 
     * @param errorPage
     *            the dispatcher path to display
     *
     * @throws IllegalArgumentException
     *             if the argument doesn't comply with the above limitations
     */
    public void setErrorPage(String errorPage) {
        accessDeniedHandler.setErrorPage(errorPage);
    }

}
