package com.maids.libraryTask.utilities;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class LocaleUtils {

    private final MessageSource messageSource;

    public String getBundleMessage(String key){
        return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());
    }
    public Locale getLocal(){

        return LocaleContextHolder.getLocale();
    }

//    public String localizeDate(final Date date, Boolean isLog) {
//        DateFormat df;
//        if(isLog)
//            df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, LocaleConfig.LOGS_LOCALE);
//        else
//            df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, LocaleContextHolder.getLocale());
//        return df.format(date);
//    }

}
