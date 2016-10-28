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

package com.niro.config;

import static com.niro.constants.UrlConstant.ACCOUNT;
import static com.niro.constants.UrlConstant.ADMIN;
import static com.niro.constants.UrlConstant.API;
import static com.niro.constants.UrlConstant.PROFILE;
import static com.niro.constants.UrlConstant.PWD_RESET;
import static com.niro.constants.UrlConstant.PWD_VALIDATE;
import static com.niro.constants.UrlConstant.SIGN_IN;
import static com.niro.constants.UrlConstant.SIGN_UP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.niro.constants.AuthorityConstant;
import com.niro.constants.CookiesConstant;
import com.niro.constants.I18nConstant;
import com.niro.constants.UrlConstant;
import com.niro.repository.RememberMePersistentTokenRepository;
import com.niro.repository.SimplePersistentTokenRepository;

/**
 * This class represents the security configuration.
 * 
 * @author Olivier nirina
 * @since 22 Oct 2016
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.niro")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true,jsr250Enabled=true)
@Import(I18nI10nConfiguration.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    @Qualifier("restAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    @Qualifier("restAuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    @Qualifier("restInitXsrfCookiesAccessDeniedHandler")
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private SimplePersistentTokenRepository persistentTokenRepository;
    
    
    /**
     * Instead of using the {@link #configure(AuthenticationManagerBuilder)
     * configure} method from {@link WebSecurityConfigurerAdapter}, this method
     * is an specific method which helps to configure the authentication
     * mechanism.
     * 
     * 
     * @param auth
     *            the provided authentication object from the parent class.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(I18nConstant.AUTH_MANAGER_CONFIG_ERROR);
        }
    }
    
    /**
     * This bean provides an object type of {@link BCryptPasswordEncoder} that
     * can be used to encodes a password
     * 
     * @return an encoded password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
        .and()
            .authorizeRequests()
            .antMatchers(API + SIGN_IN).permitAll()
            .antMatchers(API + SIGN_UP).permitAll()
            .antMatchers(API + ACCOUNT + PWD_RESET).permitAll()
            .antMatchers(API + ACCOUNT + PWD_VALIDATE).permitAll()
            .antMatchers(API + ACCOUNT + PROFILE).permitAll()
            .antMatchers(API + "/**").authenticated()
            .antMatchers(ADMIN + "/**").hasAuthority(AuthorityConstant.ADMIN)
            .anyRequest().authenticated()
        .and()
            .logout()
            .logoutUrl(UrlConstant.LOGOUT)
            .logoutSuccessHandler(logoutSuccessHandler)
            .deleteCookies(CookiesConstant.XSRF_TOKEN, CookiesConstant.SESSION_ID)
            .permitAll()
        .and()
            .formLogin()
            .loginProcessingUrl(UrlConstant.AUTH)
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .rememberMe()
            .rememberMeParameter("remember-me")
            .tokenRepository(rememberMePersistentTokenRepository());
    }

    /**
     * Remember-me persistent token repository bean
     * @return
     */
    @Bean(name="rememberMePersistentTokenRepository")
    public RememberMePersistentTokenRepository rememberMePersistentTokenRepository(){
        return new RememberMePersistentTokenRepository(persistentTokenRepository);
    }
    
    /**
     * This bean provides Spring Data integration that allows referring to the
     * current user within your queries. It is not only useful but necessary to
     * include the user in the queries to support paged results since filtering
     * the results afterwards would not scale <br>
     * Example:
     * 
     * <pre>
     * {@code @Repository 
     * public interface MessageRepository extends PagingAndSortingRepository<Message,Long> { 
     *          &#64;Query("select m from Message m where m.to.id = ?#{ principal?.id }") 
     *          Page<Message> findInbox(Pageable pageable); 
     * }
     * </pre>
     * 
     * @return
     */
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
  
}
