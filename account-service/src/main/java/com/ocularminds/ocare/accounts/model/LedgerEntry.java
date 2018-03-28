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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * LedgerEntry holds the General Ledger transaction journal record item
 *
 * @author Festus B. Jejelowo
 * @author Ocular-Minds Software
 */
public class LedgerEntry implements Serializable {

    private String id;

    private Journal journal;

    private String code;

    private Account account;

    private String type;

    private BigDecimal amount;

    private String reference;

    private BigDecimal balance;

    private String narration;

    private Integer serial;

    private Date date;

    public LedgerEntry() {
        this(null, null, BigDecimal.ZERO);
    }

    public LedgerEntry(Account acct, String typ, BigDecimal amt) {
        this.account = acct;
        this.amount = amt;
        this.type = typ;
        this.date = new Date();
    }

    public LedgerEntry(LedgerEntry entry, String ref, String details) {
        this(entry.getAccount(), entry.getType(), entry.getAmount());
        this.reference = ref;
        this.narration = details;
        serial = 0;
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
     * @return the journal
     */
    public Journal getJournal() {
        return journal;
    }

    /**
     * @param journal the journal to set
     */
    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return the narration
     */
    public String getNarration() {
        return narration;
    }

    /**
     * @param narration the narration to set
     */
    public void setNarration(String narration) {
        this.narration = narration;
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
     * @return the serial
     */
    public Integer getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "code:" + code + ",ref:" + reference + ",acid:" + account.getId() + ",type:" + type
                + ",amount:" + amount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 97 * hash + (this.reference != null ? this.reference.hashCode() : 0);
        hash = 97 * hash + (this.account != null ? this.account.hashCode() : 0);
        hash = 97 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 97 * hash + this.amount.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LedgerEntry)) {
            return false;
        } else {
            LedgerEntry rhs = (LedgerEntry) other;
            return toString().equals(rhs.toString());
        }
    }
}
