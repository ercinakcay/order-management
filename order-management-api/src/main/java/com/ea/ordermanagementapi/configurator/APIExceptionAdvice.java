package com.ea.ordermanagementapi.configurator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ea.ordermanagementapi.exception.APIException;
import com.ea.ordermanagementapi.util.rest.Response;

@ControllerAdvice("com.ea.nextordermanagementapi")
public class APIExceptionAdvice extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(APIException.class)
    protected ResponseEntity<Object> handleAPIException(RuntimeException ex, WebRequest request)
    {
        // -----

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        logger.error(ex.getMessage(), ex);

        // -----

        return handleGenericException(ex, request, status);
    }

    private ResponseEntity<Object> handleGenericException(RuntimeException ex, WebRequest request, HttpStatus status)
    {
        return handleExceptionInternal(ex, new Response(ex), new HttpHeaders(), status, request);
    }
}
