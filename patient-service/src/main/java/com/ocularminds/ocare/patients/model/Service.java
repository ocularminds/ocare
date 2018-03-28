/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import java.util.Date;

/**
 *
 * @author text_
 */
public class Service {
    //"select ServiceID as 'Service ID', ServiceName as 'Service Name',ServiceDate as 'Service Date',PatientRegistration.PatientID as 'Patient ID',PatientName as 'Patient Name',ServiceCharges as 'Service Charges' from PatientRegistration,Services where Services.PatientID=PatientRegistration.PatientID order by PatientName";
    private String id;
    String name;
    Date date;
    String visitId;
    //MedicalVisit;
}
