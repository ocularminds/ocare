/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@Entity(name="tbl_medical_treat")
public class Treatment implements Serializable{

    @Id
    @Column(name="trid",length=20)
    private String id;
    
    @NotNull
    @Column(name="descr",length=300)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mrid", nullable = false)
    @JsonIgnore
    private MedicalRecord record;   
    
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the record
     */
    public MedicalRecord getRecord() {
        return record;
    }

    /**
     * @param record the record to set
     */
    public void setRecord(MedicalRecord record) {
        this.record = record;
    }
}
