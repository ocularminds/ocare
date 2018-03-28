/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import com.ocularminds.ocare.Identifier;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jejelowo .B. Festus <festus.jejelowo@ocularminds.com>
 */
@Entity(name = "tbl_patient")
public class Patient implements Serializable {

    //insert into tbl_patiens(PatientID,Patientname,FatherName,Email,ContactNo,Age,Remarks,Gen,BG,Address
    @Id
    @Column(name = "patient_id", nullable = false, length = 20)
    private String id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<MedicalRecord> records = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
    private Address address;

    @Size(min = 2, max=26,message = "Patient first name cannot be less than 3 characters")
    @Column(name = "first_name", length = 26)
    private String firstName;

    @Column(name = "code", length = 16)
    private String code;

    @Column(length = 26)
    @NotNull
    @Size(min = 2, max=26,message = "Patient surnamee cannot be less than 3 characters")
    private String surname;

    @Column(length = 26)
    private String otherName;

    @Column(length = 45)
    private String email;

    @Column(length = 20)
    @Min(value=11,message="Mobile number cannot be less than 11 characters")
    @Max(value=20,message="Mobile number cannot be more than 20 characters")
    private String mobile;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;

    private int age;
    private String remarks;
    private String sex;
    private String bloodGroup;

    //{ "O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-" }
    @Column(name = "created", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    public Patient() {
        this(null, null, null, null);
    }

    public Patient(String firstname, String lastname, String phone, String email) {
        this.address = new Address();
        this.firstName = firstname;
        this.surname = lastname;
        this.mobile = phone;
        this.email = email;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the otherName
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * @param otherName the otherName to set
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
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

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param bloodGroup the bloodGroup to set
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * @return the Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(Address Address) {
        this.address = Address;
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
     * @return the records
     */
    public Set<MedicalRecord> getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(Set<MedicalRecord> records) {
        this.records = records;
    }

    @PrePersist
    void onPersist() throws Exception {
        if (getId() == null || getId().isEmpty()) {
            setId(new Identifier(Identifier.Type.LONG).next());
            setDate(new Date());
            address.setPatient(this);
        }
    }
}
