/**
 * 
 */
package com.niro.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * This class represents the configuration class for internationalization and localization
 * @author Olivier nirina
 * @since 1.0
 */
@Configuration
public class I18nI10nConfiguration {

    /**
     * Configure the message properties.
     * @return
     */
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource  bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setBasenames("classpath:com/niro/i18n/logging","classpath:com/niro/i18n/message");
        return bundleMessageSource;
    }
    
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("NG_TRANSLATE_LANG_KEY");
        return cookieLocaleResolver;
    }
}
