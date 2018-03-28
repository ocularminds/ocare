/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare;

import com.ocularminds.ocare.error.DuplicateIdentityException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
public interface Routes {

    ResponseEntity<ValidationError> duplicateException(final DuplicateIdentityException e);

    ValidationErrors processValidationError(MethodArgumentNotValidException ex);

    default ResponseEntity<ValidationError> error(final Exception e, final HttpStatus status, final String ref) {
        final String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        return new ResponseEntity<>(new ValidationError(ref, message), status);
    }

    default ValidationErrors validate(List<FieldError> fieldErrors, MessageSource source) {
        ValidationErrors errors = new ValidationErrors();
        fieldErrors.forEach((fieldError) -> {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError, source);
            errors.add(fieldError.getField(), localizedErrorMessage);
        });
        return errors;
    }

    /**
     * If the message was not found, returns the most accurate field error code
     * instead.
     *
     * @param fieldError
     * @param source
     * @return
     */
    default String resolveLocalizedErrorMessage(FieldError fieldError, MessageSource source) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = source.getMessage(fieldError, currentLocale);
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }
}
