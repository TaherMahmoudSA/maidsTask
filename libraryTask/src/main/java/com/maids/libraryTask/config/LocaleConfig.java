package com.maids.libraryTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration
@PropertySources({
        @PropertySource("classpath:bundle/messages.properties"),
        @PropertySource("classpath:application.yaml")
})
public class LocaleConfig implements WebMvcConfigurer {

    public static final Locale US_LOCALE = Locale.US;
    public static final Locale LOGS_LOCALE = US_LOCALE;


    @Bean
    public LocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setSupportedLocales(Arrays.asList(Locale.forLanguageTag("ar-EG"), Locale.US));
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

}
