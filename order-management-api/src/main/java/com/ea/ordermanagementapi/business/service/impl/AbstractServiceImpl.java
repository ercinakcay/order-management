package com.ea.ordermanagementapi.business.service.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import com.ea.ordermanagementapi.constant.DefaultConstants;

public abstract class AbstractServiceImpl
{
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    @Qualifier("messageResources")
    private MessageSource messageResources;

    /**
     * Getting specified session attribute with defined attribute name and type of object.
     *
     * @param sessionAttributeName		    Attribute name that will store at session.
     */
    @SuppressWarnings("unchecked")
    protected <T> T getSessionAttribute(String sessionAttributeName)
    {
        return (T) session.getAttribute(sessionAttributeName);
    }

    @SuppressWarnings( "unchecked" )
    protected <T> T getSessionAttribute(String sessionAttributeName, T defaultValue)
    {
        return null != session.getAttribute(sessionAttributeName) ? (T) session.getAttribute(sessionAttributeName) : defaultValue;
    }

    /**
     * Method get current session and sets an attribute by attribute name and object that is meant to store at session.
     *
     * @param sessionAttributeName		    attribute name for store and fetch by it.
     * @param object						object which is stored with given attribute name.
     */
    protected void setSessionAttribute(String sessionAttributeName, Object object)
    {
        session.setAttribute(sessionAttributeName, object);
    }

    /**
     * Gets message for defined key value.
     *
     * @param key                           defined in resources/messages_[LOCALE].properties
     */
    protected String getMessage(String key)
    {
        return messageResources.getMessage(key, null, DefaultConstants.DEFAULT_LOCALE);
    }

    /**
     * Gets message for defined key and language name value.
     *
     * @param key                           defined in resources/messages_[LOCALE].properties
     * @param languageName                  LanguageNames.LANGUAGE_NAME_VALUES
     */
    protected String getMessage(String key, String languageName)
    {
        return messageResources.getMessage(key, null, new Locale(languageName));
    }
}
