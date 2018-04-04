/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.repository;

import com.ocularminds.ocare.patients.model.InPatient;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author text_
 */
public interface InPatientRepository extends JpaRepository<InPatient, String> {

    Optional<InPatient> findByPatientCode(String code);

    List<InPatient> findByDateBetween(Date from, Date to);
    
    List<InPatient> findByDateBetweenAndStatus(Date from, Date to,String status);

    List<InPatient> findByStatus(String status);
}
