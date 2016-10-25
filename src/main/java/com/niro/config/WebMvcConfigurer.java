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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * This class represents an extension of {@link WebMvcConfigurer} which aims to
 * provides methods to configure the web MVC.
 * 
 * @author Olivier nirina
 * @since 1.0
 */
@Configuration
@ComponentScan(basePackages={"com.niro"})
@EnableWebMvc
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    private final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);
    }
    
    /**
     * Intercepts any request.
     * @return
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        handlerMapping.setInterceptors(localeChangeInterceptor);
        return handlerMapping;
    }
    
    /**
     * Configure the message properties.
     * @return
     */
    @Bean("messageSource")
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setBasenames("classpath:com/niro/i18n/logging", "classpath:com/niro/i18n/message");
        return bundleMessageSource;
    }
    
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("NG_TRANSLATE_LANG_KEY");
        return cookieLocaleResolver;
    }

}
