package com.ocularminds.ocare.patients.repository;

import com.ocularminds.ocare.patients.model.Treatment;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, String> {

    Optional<Treatment> findById(String id);

    List<Treatment> findAllByRecordId(String recordId);

    List<Treatment> findAllByRecordPatientId(String patientId);
}
