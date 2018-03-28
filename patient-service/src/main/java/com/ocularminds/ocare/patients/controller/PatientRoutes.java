package com.ocularminds.ocare.patients.controller;

/**
 *
 * @author text_
 */
import com.google.gson.Gson;
import com.ocularminds.ocare.Fault;
import com.ocularminds.ocare.Routes;
import com.ocularminds.ocare.ValidationError;
import com.ocularminds.ocare.ValidationErrors;
import com.ocularminds.ocare.error.DuplicateIdentityException;
import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.patients.model.InPatient;
import com.ocularminds.ocare.patients.model.MedicalRecord;
import com.ocularminds.ocare.patients.model.OutPatient;
import com.ocularminds.ocare.patients.model.Patient;
import com.ocularminds.ocare.patients.repository.OutPatientRepository;
import com.ocularminds.ocare.patients.repository.InPatientRepository;
import com.ocularminds.ocare.patients.service.PatientService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/patient")
public class PatientRoutes implements Routes {

    private final PatientService service;

    private final MessageSource messageSource;

    private final OutPatientRepository outPatientRepository;

    private final InPatientRepository inPatientRepository;

    Gson gson;

    @Autowired
    public PatientRoutes(PatientService psvc, MessageSource src, OutPatientRepository outRepo, InPatientRepository ipRepo) {
        this.service = psvc;
        this.messageSource = src;
        this.outPatientRepository = outRepo;
        this.inPatientRepository = ipRepo;
        gson = new Gson();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatients() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatient(@PathVariable("id") String id, @AuthenticationPrincipal Principal principal) {
        return service.patient(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody @Valid Patient patient) {
        return service.save(patient);
    }

    @RequestMapping(value = "/record/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicalRecord getRecord(@PathVariable("id") String id, @AuthenticationPrincipal Principal principal) {
        return service.record(id);
    }

    @RequestMapping(value = "/record/{patientId}", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecord createRecord(@PathVariable("patientId") String patientId,
            @RequestBody @Valid MedicalRecord record) {
        return service.save(patientId, record);
    }

    @RequestMapping(value = "/record/{recordId}", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecord(@PathVariable("recordId") String recordId) {
        service.deleteRecord(recordId);
    }

    @RequestMapping(value = "/in", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<InPatient> getInPatients() {
        return inPatientRepository.findAll();
    }

    @RequestMapping(value = "/in/{id}")
    public InPatient getInPatient(@PathVariable("id") String id, @AuthenticationPrincipal Principal principal) {
        return inPatientRepository.getOne(id);
    }

    @RequestMapping(value = "/in", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String createInPatient(@RequestBody String json) {
        InPatient record = gson.fromJson(json, InPatient.class);
        inPatientRepository.save(record);
        return gson.toJson(new Fault("00", "Ok"));
    }

    @RequestMapping(value = "/out")
    @ResponseStatus(HttpStatus.OK)
    public List<OutPatient> getOutPatients() {
        return outPatientRepository.findAll();
    }

    @RequestMapping(value = "/out/{id}")
    public OutPatient getOutPatient(@PathVariable("id") String id, @AuthenticationPrincipal Principal principal) {
        return outPatientRepository.getOne(id);
    }

    @RequestMapping(value = "/out", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OutPatient createOutPatient(@RequestBody String json, @AuthenticationPrincipal Principal principal) {
        OutPatient record = gson.fromJson(json, OutPatient.class);
        return outPatientRepository.save(record);
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
