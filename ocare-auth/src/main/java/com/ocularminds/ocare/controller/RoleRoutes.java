package com.ocularminds.ocare.controller;

import com.ocularminds.ocare.Route;
import com.ocularminds.ocare.error.DuplicateNameException;
import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.error.ValidationError;
import com.ocularminds.ocare.error.ValidationErrors;
import com.ocularminds.ocare.model.ResourceLink;
import com.ocularminds.ocare.model.Role;
import com.ocularminds.ocare.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("api/roles")
public class RoleRoutes implements Route{

    private final RoleService roleService;
    private final MessageSource messageSource;

    @Autowired
    public RoleRoutes(RoleService service, MessageSource src) {
        this.roleService = service;
        this.messageSource = src;
    }

    /**
     * Returns 201 and new entity if operation successful or 400 if invalid data
     * supplied.
     *
     * @param list
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createList(@RequestBody @Valid Role list) {
        return roleService.save(list);
    }

    /**
     * Returns 200 if successful, 404 if no such list id is found
     *
     * @param roleId
     */
    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteList(@PathVariable String roleId) {
        roleService.delete(roleId);
    }

    /**
     * Returns available lists with code 200
     *
     * @return
     */
    @GetMapping
    public List<Role> getLists() {
        return roleService.findAll();
    }

    /**
     * Returns 201 and new entity if operation successful or 400 if invalid data
     * supplied. Note that creating to do entries with description longer than
     * 16k chars is not allowed!
     *
     * @param roleId
     * @param entry
     * @return ResourceLink
     */
    @PostMapping("/{roleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceLink createEntry(@PathVariable String roleId, @RequestBody @Valid ResourceLink entry) {
        return roleService.add(roleId, entry);
    }

    /**
     * Deletes given entry if list and entry is valid. Return 404 if ether list
     * or entry id is incorrect. Return 400 if specified entry ID does not
     * belong to the list.
     *
     * @param roleId
     * @param resourceLinkId
     */
    @DeleteMapping("/{resourceLinkId}/{roleId}")
    public void deleteEntry(@PathVariable String roleId, @PathVariable String resourceLinkId) {
        roleService.delete(roleId, resourceLinkId);
    }

    /**
     * Lists all entries in the specified list, 404 if list not found
     *
     * @param roleId Id of the Role of which entries are to be retrieved
     * @return List of ToDOEntry
     */
    @GetMapping("/{roleId}")
    public List<ResourceLink> getListEntries(@PathVariable String roleId) {
        return roleService.getResources(roleId);
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
