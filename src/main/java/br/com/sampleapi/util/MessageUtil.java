package br.com.sampleapi.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@Slf4j
public class MessageUtil {

    private static MessageSource staticMessageSource;

    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    private void initStaticMessageSource(){
        staticMessageSource = messageSource;
    }

    public static String getMessage(String messageKey, Object ... args) {

        try {
            String message = staticMessageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
            return MessageFormat
                    .format(message, args);
        }catch (NoSuchMessageException ex){
            log.warn("No message found for key {}:", messageKey, ex);
            return null;
        }
    }

}


