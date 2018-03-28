/*
 * Copyright (c) 2016-2017 Ocular-Minds Software Limited
 *
 * Permission is hereby granted to Vatebra Limited, to any person representing Vatebra in a copy of
 * this software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to
 * whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY FOR 6-MONTH SUPPORT AFTER INITIAL HANDING OVER
 * OF THE SOURCE CODES
 */
package com.ocularminds.ocare.accounts.model;

import com.ocularminds.ocare.Identifier;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;

@Entity(name = "tbl_trans")
public class Transaction implements java.io.Serializable {

    @Id
    @Column(name = "tid", nullable = false)
    private String id;

    @Column(name = "ref", nullable = false)
    private String reference;

    @Column(name = "ref_old")
    private String originalReference;

    private String type;

    private String debit;

    private String credit;

    private BigDecimal amount;

    private BigDecimal surcharge;
    private BigDecimal fee;
    private String phone;
    private String status;
    private String source;
    private String target;

    private String narration;

    @Column(name = "posted", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    /**
     * Default constructor
     */
    public Transaction() { // jpa only
        this.amount = BigDecimal.ZERO;
        this.surcharge = BigDecimal.ZERO;
        this.date = new Date();
    }

    /**
     * New Instance with parameters
     *
     * @param debit Debit account
     * @param credit Credit account
     * @param amt Transaction value amount
     */
    public Transaction(String debit, String credit, BigDecimal amt) {
        this.debit = debit;
        this.credit = credit;
        this.amount = amt;
        this.date = new Date();
        this.surcharge = BigDecimal.ZERO;
    }

    /**
     * New Instance with parameters
     *
     * @param ref The reference id
     * @param desc Transaction description
     * @param amt Transaction value amount
     * @param dat Posting date
     */
    public Transaction(String ref, String desc, BigDecimal amt, Date dat) {
        this.reference = ref;
        this.date = dat;
        this.amount = amt;
        this.narration = desc;
        this.surcharge = BigDecimal.ZERO;
    }

    /**
     *
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param ref The reference
     */
    public void setReference(String ref) {
        this.reference = ref;
    }

    /**
     * @return the originalReference
     */
    public String getOriginalReference() {
        return originalReference;
    }

    /**
     * @param originalReference the originalReference to set
     */
    public void setOriginalReference(String originalReference) {
        this.originalReference = originalReference;
    }

    /**
     *
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type The transaction processing code
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return The debit
     */
    public String getDebit() {
        return debit;
    }

    /**
     *
     * @param debit The debit
     */
    public void setDebit(String debit) {
        this.debit = debit;
    }

    /**
     *
     * @return The credit
     */
    public String getCredit() {
        return credit;
    }

    /**
     *
     * @param credit The credit
     */
    public void setCredit(String credit) {
        this.credit = credit;
    }

    /**
     *
     * @return The amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     *
     * @param amount The amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the surcharge
     */
    public BigDecimal getSurcharge() {
        return surcharge;
    }

    /**
     * @param surcharge the surcharge to set
     */
    public void setSurcharge(BigDecimal surcharge) {
        this.surcharge = surcharge;
    }

    /**
     *
     * @return The narration
     */
    public String getNarration() {
        return narration;
    }

    /**
     *
     * @param narration The narration
     */
    public void setNarration(String narration) {
        this.narration = narration;
    }

    /**
     *
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date The date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee the fee to set
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
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
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
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

    @PrePersist
    void onPersist() throws Exception {
        if (id == null || id.isEmpty()) {
            id = new Identifier(Identifier.Type.LONG).next();
        }
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.getId() != null ? this.getId().hashCode() : 0);
        hash = 97 * hash + (this.getDebit() != null ? this.getDebit().hashCode() : 0);
        hash = 97 * hash + (this.getCredit() != null ? this.getCredit().hashCode() : 0);
        hash = 97 * hash + (this.getAmount() != null ? this.getAmount().hashCode() : 0);
        hash = 97 * hash + (this.getType() != null ? this.getType().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Transaction) == false) {
            return false;
        }
        return this.toString().equals(((Transaction) other).toString());
    }
}
