/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import com.ocularminds.ocare.Identifier;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;

/**
 * Simple class to represent a patient. Its main purpose is to store all of the
 * data associated with each patient during the simulation.
 *
 * @author text_
 */
@Entity(name = "tbl_out_patient")
public class OutPatient {

    @Id
    private String id;
    private long number;
    private long arrivalTime;
    private long serviceTime;
    private long startServiceTime;
    private long waitTime;
    private long endServiceTime;
    private long inSystemTime;
    private String patient;
    private String complaint;
    private String priority;
    private String status;

    @Column(name = "date_visited", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    
    public OutPatient(){}

    public OutPatient(long newid, long arr) {
        number = newid;
        arrivalTime = arr;
    }

    /**
     * @return the id
     */
    public long getNumber() {
        return number;
    }

    /**
     * @param id the id to set
     */
    public void setNumber(long id) {
        this.number = id;
    }

    /**
     * @return the arrivalTime
     */
    public long getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return the serviceTime
     */
    public long getServiceTime() {
        return serviceTime;
    }

    /**
     * @param serviceTime the serviceTime to set
     */
    public void setServiceTime(long serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * @return the startServiceTime
     */
    public long getStartServiceTime() {
        return startServiceTime;
    }

    /**
     * @param startServiceTime the startServiceTime to set
     */
    public void setStartServiceTime(long startServiceTime) {
        this.startServiceTime = startServiceTime;
    }

    /**
     * @return the endServiceTime
     */
    public long getEndServiceTime() {
        return endServiceTime;
    }

    /**
     * @param endServiceTime the endServiceTime to set
     */
    public void setEndServiceTime(long endServiceTime) {
        this.endServiceTime = endServiceTime;
    }

    /**
     * @return the inSystemTime
     */
    public long getInSystemTime() {
        return inSystemTime;
    }

    /**
     * @param inSystemTime the inSystemTime to set
     */
    public void setInSystemTime(long inSystemTime) {
        this.inSystemTime = inSystemTime;
    }

    /**
     * @return the waitTing
     */
    public long getWaitTime() {
        return waitTime;
    }

    /**
     * @param waitTime the waitTing to set
     */
    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
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

    @PrePersist
    void onPersist() throws Exception {
        if (getId() == null || getId().isEmpty()) {
            setId(new Identifier(Identifier.Type.LONG).next());
            setDate(new Date());
        }
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
     * @return the patient
     */
    public String getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(String patient) {
        this.patient = patient;
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
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
