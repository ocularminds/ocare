/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.controller;

import com.google.gson.Gson;
import com.ocularminds.ocare.Routes;
import com.ocularminds.ocare.ValidationError;
import com.ocularminds.ocare.ValidationErrors;
import com.ocularminds.ocare.error.DuplicateIdentityException;
import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.patients.model.Room;
import com.ocularminds.ocare.patients.model.Ward;
import com.ocularminds.ocare.patients.service.RoomService;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@RestController
@RequestMapping("/api/ward")
public class RoomRoutes implements Routes {

    private final RoomService service;

    private final MessageSource messageSource;

    Gson gson;

    @Autowired
    public RoomRoutes(RoomService svc, MessageSource src) {
        this.service = svc;
        this.messageSource = src;
        gson = new Gson();
    }

    @ApiOperation(value = "Get records of all wards")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Ward> getWards() {
        return service.getWards();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Ward getWard(@PathVariable("id") String id, @AuthenticationPrincipal Principal principal) {
        return service.ward(id);
    }

    @ApiOperation(value = "Create a new Ward")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Ward createWard(@RequestBody @Valid Ward ward) {
        return service.save(ward);
    }

    @ApiOperation(value = "Get Ward with id: ")
    @RequestMapping(value = "/{wardId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteWard(@PathVariable("wardId") String wardId) {
        service.deleteWard(wardId);
    }

    @RequestMapping(value = "/{wardId}/room", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Room> getRooms(@PathVariable("wardId") String wardId, @AuthenticationPrincipal Principal principal) {
        return service.findRoomByWardId(wardId);
    }

    @RequestMapping(value = "/{wardId}/room", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@PathVariable("wardId") String wardId, @RequestBody @Valid Room room) {
        return service.save(wardId, room);
    }

    @RequestMapping(value = "/{wardId}/room/{roomId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Room getRoom(@PathVariable("wardId") String wardId, @PathVariable("roomId") String roomId) {
        Ward ward = service.ward(wardId);
        Room room = service.room(roomId);
        if (!room.getWard().equals(ward)) {
            throw new IllegalArgumentException();
        }
        return room;
    }

    @RequestMapping(value = "/{wardId}/room/{roomId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoom(@PathVariable("wardId") String wardId, @PathVariable("roomId") String roomId) {
        service.delete(wardId, roomId);
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
    @ExceptionHandler(DuplicateIdentityException.class)
    @Override
    public ResponseEntity<ValidationError> duplicateException(final DuplicateIdentityException e) {
        return error(e, HttpStatus.TOO_MANY_REQUESTS, e.getMessage());
    }
}
