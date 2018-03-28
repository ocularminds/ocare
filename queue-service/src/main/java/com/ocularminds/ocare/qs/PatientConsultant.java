/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.qs;

import com.ocularminds.ocare.qs.model.OutPatient;

/**
 * Simple class to represent the consultant. It simply stores the current
 * patient and a busy flag.
 *
 * @author text_
 */
public class PatientConsultant {

    private boolean busy;
    private OutPatient currentPatient;

    public PatientConsultant() {
        busy = false;
        currentPatient = null;
    }

    public boolean isConsulting() {
        return busy;
    }

    public void addPatient(OutPatient c) {
        currentPatient = c;
        busy = true;
    }

    public OutPatient removePatient() {
        OutPatient t = currentPatient;
        currentPatient = null;
        busy = false;
        return t;
    }
}
