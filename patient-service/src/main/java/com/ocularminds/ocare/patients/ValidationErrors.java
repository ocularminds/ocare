/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare;

import java.util.ArrayList;
import java.util.List;

/**
 * Collections of invalid input fields for json request annotated with @Valid
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
public class ValidationErrors {

    private List<ValidationError> errors = new ArrayList<>();

    public ValidationErrors() {

    }

    public void add(String path, String message) {
        ValidationError error = new ValidationError(path, message);
        getErrors().add(error);
    }

    /**
     * @return the errors
     */
    public List<ValidationError> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}
