/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@Entity(name = "tbl_medical_rec")
public class MedicalRecord implements Serializable {

    @Id
    @Column(name = "rec_id", length = 20)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "record", cascade = CascadeType.ALL)
    private Vitals vitals;

    private String complaint;

    private String diagnosis;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "record")
    private List<Treatment> treatments;

    @Column(name = "created", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the vitals
     */
    public Vitals getVitals() {
        return vitals;
    }

    /**
     * @param vitals the vitals to set
     */
    public void setVitals(Vitals vitals) {
        this.vitals = vitals;
    }

    /**
     * @return the complaint
     */
    public String getComplaint() {
        return complaint;
    }

    /**
     * @param complaint the complaint to set
     */
    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    /**
     * @return the diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * @param diagnosis the diagnosis to set
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * @return the treatments
     */
    public List<Treatment> getTreatments() {
        return treatments;
    }

    /**
     * @param treatments the treatments to set
     */
    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    /**
     * @return the created
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param created the created to set
     */
    public void setDate(Date created) {
        this.date = created;
    }

    public void add(Treatment treatment) {
        treatment.setRecord(this);
        getTreatments().add(treatment);
    }

    public void delete(Treatment treatment) {
        getTreatments().remove(treatment);
    }
}
