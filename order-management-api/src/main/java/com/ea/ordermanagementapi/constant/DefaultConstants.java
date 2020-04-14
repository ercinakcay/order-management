package com.ea.ordermanagementapi.constant;

import java.util.Locale;

public interface DefaultConstants
{

    Locale DEFAULT_LOCALE = new Locale(LanguageNames.DEFAULT_LANGUAGE_NAME);
    Locale ENGLISH_LOCALE = new Locale(LanguageNames.ENGLISH_LANGUAGE_NAME);

    interface LanguageNames
    {
        String DEFAULT_LANGUAGE_NAME = "tr";
        String ENGLISH_LANGUAGE_NAME = "en";
    }

    interface HTTP_HEADERS
    {
        String ACCEPT_ENCODING = "Accept-Encoding";
        String ACCEPT = "Accept";
        String CONTENT_TYPE = "Content-Type";
        String ACCEPT_LANGUAGE = "Accept-Language";
    }
}
