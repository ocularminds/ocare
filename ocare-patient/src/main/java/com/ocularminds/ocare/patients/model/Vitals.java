/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author text_
 */
@Entity(name = "tbl_medical_vital")
public class Vitals implements Serializable {

    public enum Tags {
        RED, YELLOW, GREEN, WHITE, BLACK;
    }

    @Id
    @Column(name = "vital_id", length = 20)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "vital_id")
    private MedicalRecord record;

    @Min(value = 10, message = "Heart beat value cannot be less than 10.0")
    @Max(value = 400, message = "Heart beat should not be greater than 400.00")
    private float heart;

    @Min(value = 10, message = "Respiratory value cannot be less than 10.0")
    @Max(value = 400, message = "respiratory beat should not be greater than 400.00")
    private float respiratory;

    @Min(value = 20, message = "Heart beat value cannot be less than 20.0")
    @Max(value = 100, message = "Heart beat should not be greater than 100.00")
    private float temperature;

    /**
     * Blood Pressure
     */
    @Min(value = 10, message = "Blood pressure value cannot be less than 10.0")
    @Max(value = 400, message = "Blood pressure should not be greater than 400.00")
    private float pressure;

    public Vitals() {
    }

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
     * @return the heart
     */
    public float getHeart() {
        return heart;
    }

    /**
     * @param heart the heart to set
     */
    public void setHeart(float heart) {
        this.heart = heart;
    }

    /**
     * @return the respiratory
     */
    public float getRespiratory() {
        return respiratory;
    }

    /**
     * @param respiratory the respiratory to set
     */
    public void setRespiratory(float respiratory) {
        this.respiratory = respiratory;
    }

    /**
     * @return the temperature
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the blood pressure
     */
    public float getPressure() {
        return pressure;
    }

    /**
     * @param bp the blood pressure of the patient to set
     */
    public void setPressure(float bp) {
        this.pressure = bp;
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
