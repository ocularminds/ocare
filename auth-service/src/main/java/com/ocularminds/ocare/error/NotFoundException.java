package com.ocularminds.ocare.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3915153143490977128L;

    public NotFoundException(String msg) {
        super(msg);
    }
}
