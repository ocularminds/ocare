package com.ocularminds.ocare.patients.repository;

import com.ocularminds.ocare.patients.model.Ward;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {

    List<Ward> findByType(String type);

    Optional<Ward> findById(String id);
    
     Optional<Ward> findByName(String name);
}
