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
 *
 * @author text_
 */
@Entity(name="tbl_doctor")
public class Doctor {

    @Id
    @Column(name = "doc_id", length = 20)
    private String id;
    
    @Column(name = "name", length = 26)
    private String name;
    
    @Column(name = "surname", length = 26)
    private String surname;
    
    @Column(name = "addr", length = 40)
    private String address;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "email", length = 40)
    private String email;
    
    @Column(name = "qualification", length = 35)
    private String qualification;
    
    @Column(name = "specialty", length = 35)
    private String specialization;
    
    @Column(name = "sex", length = 7)
    private String sex;
    
    @Column(name = "blood_group", length = 10)
    private String bloodGroup;

    @Column(name = "date_visited", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    
    public Doctor(){}

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return the qualification
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
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
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * @param specialization the specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @PrePersist
    void onPersist() throws Exception {
        if (getId() == null || getId().isEmpty()) {
            setId(new Identifier(Identifier.Type.SHORT).next());
            setDate(new Date());
        }
    }

}
