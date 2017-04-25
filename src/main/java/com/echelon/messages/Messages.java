package com.echelon.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    public String getMsg(String code) {
        try {

            return accessor.getMessage(code);
        } catch (NoSuchMessageException ex) {
            return "no message found with this id";
        }
    }
    public String getMsg(String code, Object... args) {
        try {

            return accessor.getMessage(code, args);
        } catch (NoSuchMessageException ex) {
            return "no message found with this id";
        }
    }

}