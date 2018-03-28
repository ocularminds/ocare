/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.repository;

import com.ocularminds.ocare.patients.model.OutPatient;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author text_
 */
public interface OutPatientRepository extends JpaRepository<OutPatient, String> {

    OutPatient findByPatient(String login);

    List<OutPatient> findByDateBetween(Date from, Date to);

    List<OutPatient> findByDateBetweenAndNumber(Date from, Date to, long number);

    List<OutPatient> findByStatus(String status);
}
