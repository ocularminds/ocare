package com.ocularminds.ocare.patients.repository;

import com.ocularminds.ocare.patients.model.MedicalRecord;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<MedicalRecord, String> {

    List<MedicalRecord> findAllByPatientId(String patientId);

    List<MedicalRecord> findAllByPatientIdAndDateBetween(@Param("dFrom") Date from, @Param("dTo") Date to, @Param("patientId") String patientId);

    Optional<MedicalRecord> findOneByPatientIdAndDate(@Param("dFrom") Date date, @Param("patientId") String patientId);

    Optional<MedicalRecord> findById(String id);
}
