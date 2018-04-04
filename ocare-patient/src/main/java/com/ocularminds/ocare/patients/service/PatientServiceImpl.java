/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.service;

import com.ocularminds.ocare.error.NotFoundException;
import com.ocularminds.ocare.patients.model.MedicalRecord;
import com.ocularminds.ocare.patients.model.Patient;
import com.ocularminds.ocare.patients.model.Treatment;
import com.ocularminds.ocare.patients.model.Vitals;
import com.ocularminds.ocare.patients.repository.PatientRepository;
import com.ocularminds.ocare.patients.repository.RecordRepository;
import com.ocularminds.ocare.patients.repository.TreatmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;
    private final RecordRepository recordRepository;
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository repository, RecordRepository records,
            TreatmentRepository treatments) {
        this.repository = repository;
        this.recordRepository = records;
        this.treatmentRepository = treatments;
    }

    @Override
    public Patient patient(String patientId) {
        return repository.findById(patientId).orElseThrow(
                () -> new NotFoundException(notFound("Patient:", patientId))
        );
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String patientId) {
        Patient patient = patient(patientId);
        repository.delete(patient);
    }

    @Override
    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public MedicalRecord record(String recordId) {
        return recordRepository.findById(recordId).orElseThrow(
                () -> new NotFoundException(notFound("Medical Record:", recordId))
        );
    }

    @Override
    public List<MedicalRecord> findRecordByPatientId(String patientId) {
        Patient patient = patient(patientId);
        return recordRepository.findAllByPatientId(patient.getId());
    }

    @Override
    public void deleteRecord(String recordId) {
        MedicalRecord record = record(recordId);
        recordRepository.delete(record);
    }

    @Override
    public MedicalRecord save(String patientId, MedicalRecord record) {
        Patient patient = patient(patientId);
        record.setPatient(patient);
        return recordRepository.save(record);
    }

    @Override
    public void update(String recordId, Vitals vitals) {
        MedicalRecord record = record(recordId);
        record.setVitals(vitals);
        recordRepository.save(record);
    }

    @Override
    public List<Treatment> findTreatmentByRecordId(String recordId) {
        return treatmentRepository.findAllByRecordId(recordId);
    }

    @Override
    public List<Treatment> findTreatmentByPatientId(String patientId) {
       return treatmentRepository.findAllByRecordPatientId(patientId);
    }

    @Override
    public void deleteTreatment(String id) {
       Treatment treatment = treatment(id);
       treatmentRepository.delete(treatment);
    }

    @Override
    public Treatment treatment(String id) {
        return treatmentRepository.findById(id).orElseThrow(
                () -> new NotFoundException(notFound("Treatment: ", id))
        );
    }

    @Override
    public Treatment add(String recordId, Treatment treatment) {
        MedicalRecord record = record(recordId);
        record.add(treatment);
        return treatmentRepository.save(treatment);
    }

    private String notFound(String object, String id) {
        return String.format("%s with id %s not found.", object, id);
    }
}
