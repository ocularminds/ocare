/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import java.math.BigDecimal;

/**
 *
 * @author text_
 */
public class Discharge {
   // Select ID as 'Discharge ID', AdmitPatient_Ward.AdmitID as 'Admit ID',PatientRegistration.PatientID as 'Patient ID',
    //PatientRegistration.PatientName as 'Patient Name',PatientRegistration.Gen as 'Gender',
    //PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Ward.Wardname as 'Ward Name',
    //Charges as 'Bed Charges',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',
    //DischargeDate as 'Discharge Date',DP_Remarks as 'Remarks' from Ward,Doctor,PatientRegistration,
    //AdmitPatient_Ward,DischargePatient_Ward where Ward.Wardname=AdmitPatient_Ward.Wardname 
   //and Doctor.DoctorID=AdmitPatient_Ward.DoctorID and PatientRegistration.PatientID=AdmitPatient_Ward.PatientID 
    //and AdmitPatient_Ward.admitID= DischargePatient_Ward.admitID order by Dischargedate";
    
    // String sql="Select ID as 'Discharge ID', AdmitPatient_Ward.AdmitID as 'Admit ID',PatientRegistration.PatientID as 'Patient ID',PatientRegistration.PatientName as 'Patient Name',PatientRegistration.Gen as 'Gender',PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Ward.Wardname as 'Ward Name',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',DischargeDate as 'Discharge Date',DP_Remarks as 'Remarks' from Ward,Doctor,PatientRegistration,AdmitPatient_Ward,DischargePatient_Ward where Ward.Wardname=AdmitPatient_Ward.Wardname and Doctor.DoctorID=AdmitPatient_Ward.DoctorID and PatientRegistration.PatientID=AdmitPatient_Ward.PatientID  and AdmitPatient_Ward.admitID= DischargePatient_Ward.admitID order by Dischargedate";
               
    //s 'Patient ID',PatientRegistration.PatientName as 'Patient Name',PatientRegistration.Gen as 'Gender',PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Ward.Wardname as 'Ward Name',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',DischargeDate as 'Discharge Date',DP_Remarksfrom Ward,Doctor,PatientRegistration,AdmitPatient_Ward,DischargePatient_Ward
    private String id;
    
    private String admission;
    
    private String patient;
    
    private int days;
    
    private BigDecimal bill;
    
    private String remarks;
    
    public Discharge(){}

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
     * @return the admission
     */
    public String getAdmission() {
        return admission;
    }

    /**
     * @param admission the admission to set
     */
    public void setAdmission(String admission) {
        this.admission = admission;
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
     * @return the days
     */
    public int getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * @return the bill
     */
    public BigDecimal getBill() {
        return bill;
    }

    /**
     * @param bill the bill to set
     */
    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
             
}
