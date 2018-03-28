/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.accounts.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author text_
 */
public class Bill {

    //Select BillNo as 'Bill No',DisChargePatient_Ward.ID as 'Discharge ID', AdmitPatient_Ward.AdmitID as 'Admit ID',PatientRegistration.PatientID as 'Patient ID',PatientRegistration.PatientName as 'Patient Name',PatientRegistration.Gen as 'Gender',PatientRegistration.BG as 'Blood Group',Disease,AdmitDate as 'Admit Date',Ward.WardName as 'Ward Name',Doctor.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',DischargeDate as 'Discharge Date',
    //Bill_Ward.BedCharges as 'Bed Charges',Bill_Ward.ServiceCharges as 'Service Charges',Bill_Ward.BillingDate as 'Billing Date',PaymentMode as 'Payement Mode',
    //PaymentModeDetails as 'Payment Mode Details',TotalCharges as 'Total Charges',ChargesPaid as 'Charges Paid',
    //DueCharges as 'Due Charges',NoOfDays as 'No Of Days',TotalBedCharges as 'Total Bed Charges' from Ward,Doctor,PatientRegistration,AdmitPatient_Ward,DischargePatient_Ward,Bill_Ward where Ward.WardName=AdmitPatient_Ward.WardName and Doctor.DoctorID=AdmitPatient_Ward.DoctorID and PatientRegistration.PatientID=AdmitPatient_Ward.PatientID  and AdmitPatient_Ward.admitID= DischargePatient_Ward.admitID and Bill_Ward.DischargeID=DischargePatient_Ward.ID  order by Billingdate";
    private String id;
    private Discharge discharge;
    private BigDecimal bedCharges;
    private BigDecimal serviceCharge;
    private String paymentDetails;
    private BigDecimal totalCharges;
    private BigDecimal paid;
    private int days;
    private BigDecimal due;
    private String paymentMode;
    private Date date;
    
    public Bill(){}

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
     * @return the discharge
     */
    public Discharge getDischarge() {
        return discharge;
    }

    /**
     * @param discharge the discharge to set
     */
    public void setDischarge(Discharge discharge) {
        this.discharge = discharge;
    }

    /**
     * @return the bedCharges
     */
    public BigDecimal getBedCharges() {
        return bedCharges;
    }

    /**
     * @param bedCharges the bedCharges to set
     */
    public void setBedCharges(BigDecimal bedCharges) {
        this.bedCharges = bedCharges;
    }

    /**
     * @return the serviceCharge
     */
    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    /**
     * @param serviceCharge the serviceCharge to set
     */
    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    /**
     * @return the paymentDetails
     */
    public String getPaymentDetails() {
        return paymentDetails;
    }

    /**
     * @param paymentDetails the paymentDetails to set
     */
    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    /**
     * @return the totalCharges
     */
    public BigDecimal getTotalCharges() {
        return totalCharges;
    }

    /**
     * @param totalCharges the totalCharges to set
     */
    public void setTotalCharges(BigDecimal totalCharges) {
        this.totalCharges = totalCharges;
    }

    /**
     * @return the paid
     */
    public BigDecimal getPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(BigDecimal paid) {
        this.paid = paid;
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
     * @return the due
     */
    public BigDecimal getDue() {
        return due;
    }

    /**
     * @param due the due to set
     */
    public void setDue(BigDecimal due) {
        this.due = due;
    }

    /**
     * @return the paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
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
}
