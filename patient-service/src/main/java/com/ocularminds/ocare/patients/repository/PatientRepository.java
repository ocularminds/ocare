/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.repository;

import com.ocularminds.ocare.patients.model.Patient;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author text_
 */
public interface PatientRepository extends JpaRepository<Patient, String> {

    Optional<Patient> findById(String id);

    Optional<Patient> findByCode(String code);

    List<Patient> findBySurnameOrFirstName(String surname, String firstName);

    List<Patient> findByDateBetween(Date from, Date to);

    List<Patient> findByDateBetweenAndCode(Date from, Date to, String number);
}
