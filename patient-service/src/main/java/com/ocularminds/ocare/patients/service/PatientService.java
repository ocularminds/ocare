/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.service;

import com.ocularminds.ocare.patients.model.MedicalRecord;
import com.ocularminds.ocare.patients.model.Patient;
import com.ocularminds.ocare.patients.model.Treatment;
import com.ocularminds.ocare.patients.model.Vitals;
import java.util.List;

/**
 * The patient visits a provider. Patient's medical record is then updated and
 * electronically recorded - date, trialage,diagnosis, treatment, and other
 * information.
 *
 * From the medical record, a five-digit code is assigned to the procedure and
 * entered into the database; another code identifies the diagnosis. * These two
 * codes are the critical pieces that are used in processing medical billing -
 * either to an insurance company or to the patient.
 *
 * Codes are transmitted to the insurance carrier and/or translated to verbiage
 * for a patient bill. In most instances, both types of communicators are
 * developed, so that the patient has an understanding of the costs of each
 * item: BillingItem - office visit, tests, details of hospital care, dispensed
 * medications, etc. Patients with insurance will receive these statements from
 * the insurance carrier, along with an explanation of the paid benefits. When
 * codes are sent to insurance companies, they are usually transmitted using an
 * ANSI 837 file, and the claim is then processed for payment. Likewise,
 * Medicare and Medicaid claims are transmitted via the CMS 1500 file. When
 * claims are coded correctly, the pre-negotiated payment amount is then paid to
 * the provider, and the patient is billed for that which is his/her
 * responsibility.
 *
 *
 * Smart Claim Processing: To streamline all the communication and activity
 * during the billing process mentioned above, our developers can create an
 * automatic claim validation mechanism and code checking to verify the data
 * before any claim is sent. This will help you reduce the risks of unpaid
 * claims and save your staff time on dealing with rejected/incomplete ones.
 *
 * Eligibility Verifications: Certain customers may come unaware of their lost
 * insurance status. This product feature will allow you to obtain the current
 * patient's insurance information and make respective inquiries with the
 * patient before the appointment. In this case, both your facility and the
 * patient will avoid frustrating confrontation.
 *
 * Auto-backups: To avoid any data loss your solution should be enabled with
 * scheduled data backups, which would either download the critical data on hard
 * drivers or send it to the cloud.
 *
 * Custom electronic bills which would include comprehensive information on the
 * scope of services provided to the patient, can be personalised/modified in a
 * few clicks. Different doctors can choose to create custom forms for charge
 * slips that a processed online. Again, this will save you hours of sorting out
 * all the bills. Payment reminders: you may want to incorporate a set of soft
 * reminders on overdue payments, which could be automatically sent to the
 * patients in the set amount of days after the payment was due. Again, that
 * would save a lot of time on making personal calls or sending letters
 * manually. Customizable report functionality: your web app will organize all
 * the billing data in the format you specify and could be sorted by different
 * values for simpler monitoring. Additionally, you could have a smart analytics
 * tool, which will compare billings for different periods, make financial
 * forecasts and provide other types of insight at hotkey access.
 * 
 * Activities schedule. Morning: personal hygiene of the patient, 
 * monitoring of clinical parameters and taking of blood samples, 
 * breakfast service, medical examination and treatment of wounds, 
 * instrumental, rehabilitative and therapeutic diagnostic evaluation,
 * possible discharge and admission, lunch service.
 *
 * @author festus.jejelowo@ocularminds.com
 */
public interface PatientService {

    /**
     * Select a patient by id. Return not found exception if no record is
     * available
     *
     * @param patientId
     * @return
     */
    public Patient patient(String patientId);

    /**
     * Selects all patients
     *
     * @return
     */
    public List<Patient> findAll();

    /**
     * Deletes a patient with the given Id. Throws NotFoundException if no
     * record is available.
     *
     * @param patientId
     */
    public void delete(String patientId);

    /**
     * Saves new patient record. Throws InvalidArguementException if any field
     * is not correctly validated.
     *
     * @param patient
     * @return
     */
    public Patient save(Patient patient);

    /**
     * Pulls a medication record for a patient given the record id. Throws
     * NotFoundException of the record does not exist.
     *
     * @param recordId
     * @return
     */
    public MedicalRecord record(String recordId);
    
    /**
     * Pulls all medical records for every visit of a patient to the hospital.
     * Throws NotFoundException if the given patient id record is not found.
     *
     * @param patientId
     * @return
     */
    public List<MedicalRecord> findRecordByPatientId(String patientId);

    /**
     * Deletes medical record for a given record. Throws NotFoundException if
     * the recordId is not associated with any medical record.
     *
     * @param recordId
     */
    public void deleteRecord(String recordId);

    /**
     * Saves the new medical record. Throws not foundException if the Patient Id
     * is not found.
     *
     * @param patientId
     * @param record
     * @return
     */
    public MedicalRecord save(String patientId, MedicalRecord record);

    /**
     * Updates vitals from a medical record for a given Id. Throw
     * NotFoundException if not exist
     *
     * @param recordId
     * @param vitals Newly captured vitals
     */
    public void update(String recordId,Vitals vitals);

    /**
     * List treatments for a medical record with given Id
     *
     * @param recordId
     * @return
     */
    public List<Treatment> findTreatmentByRecordId(String recordId);

    /**
     * List all treatments given to a certain patient with given patentId;
     *
     * @param patientId
     * @return
     */
    public List<Treatment> findTreatmentByPatientId(String patientId);

    /**
     * Deletes a treat from a given medical record.
     *
     * @param id
     */
    public void deleteTreatment(String id);

    /**
     * select treatment details for a given treatment id
     *
     * @param id
     * @return
     */
    public Treatment treatment(String id);

    /**
     * Add treatment to a a medical record for a given patient. Throws
     * NotFoundException if record does not exist.
     *
     * @param recordId
     * @param treatment
     * @return
     */
    public Treatment add(String recordId, Treatment treatment);
}
