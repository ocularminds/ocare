package com.ocularminds.ocare.controller;

import com.ocularminds.ocare.Route;
import com.ocularminds.ocare.error.DuplicateNameException;
import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.error.ValidationError;
import com.ocularminds.ocare.error.ValidationErrors;
import com.ocularminds.ocare.model.Client;
import com.ocularminds.ocare.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestController
@RequestMapping("/api/clients")
public class ClientRoutes implements Route{   

    private final ClientService clientService;
    private final MessageSource messageSource;

    @Autowired
    public ClientRoutes(ClientService service, MessageSource src) {
        this.clientService = service;
        this.messageSource = src;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> listClient(){
        return clientService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Client create(@RequestBody @Valid Client client){
        return clientService.save(client);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") String id){
        clientService.delete(id);
        return "success";
    }   

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
        // No-op, return empty 404
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFoundException() {
        // No-op, return empty 404
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Override
    public ValidationErrors processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return validate(fieldErrors, messageSource);
    }
    
    /**
     *
     * @param e Duplicate Exception
     * @return
     */
    @ExceptionHandler(DuplicateNameException.class)
    @Override
    public ResponseEntity<ValidationError> duplicateException(final DuplicateNameException e) {
        return error(e, HttpStatus.TOO_MANY_REQUESTS, e.getMessage());
    }

}
