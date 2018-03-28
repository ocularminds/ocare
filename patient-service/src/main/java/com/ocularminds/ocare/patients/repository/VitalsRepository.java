package com.ocularminds.ocare.patients.repository;

import com.ocularminds.ocare.patients.model.Vitals;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalsRepository extends JpaRepository<Vitals, String> {

    Optional<Vitals> findById(String id);

    List<Vitals> findAllByRecordId(String recordId);
}
