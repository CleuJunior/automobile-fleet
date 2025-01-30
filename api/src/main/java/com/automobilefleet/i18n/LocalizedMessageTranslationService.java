package com.automobilefleet.i18n;

import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@RequiredArgsConstructor
@Service
public class LocalizedMessageTranslationService {
    private final MessageSource source;

    public String translateMessage(NotFoundException exception) {
        var locale = LocaleContextHolder.getLocale();
        return source.getMessage(exception.getMessage(), exception.getArgs(), locale);
    }

    public String translateMessage(String messageKey, String... arguments) {
        var locale = LocaleContextHolder.getLocale();
        return source.getMessage(messageKey, arguments, locale);
    }

    public String translateMessage(FieldError fieldError) {
        var locale = LocaleContextHolder.getLocale();
        return source.getMessage(fieldError, locale);
    }
}
