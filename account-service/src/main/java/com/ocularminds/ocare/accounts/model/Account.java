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
import javax.persistence.Entity;
import javax.persistence.Id;
import com.ocularminds.ocare.Identifier;
import javax.persistence.Column;
import javax.persistence.PrePersist;

/**
 *
 * @author text_
 */
@Entity(name = "tbl_accts")
public class Account implements Serializable {

    @Id
    @Column(name = "acid", nullable = false)
    private String id;

    @Column(name = "acct_no", nullable = false)
    private String number;

    @Column(name = "acct_name", nullable = false)
    private String name;

    @Column(name = "balance", nullable = true, precision = 22, scale = 2)
    private BigDecimal balance;

    @Column(name = "ledger", nullable = true, precision = 22, scale = 2)
    private BigDecimal ledger;

    @Column(name = "debits", nullable = true, precision = 22, scale = 2)
    private BigDecimal debits;

    @Column(name = "credits", nullable = true, precision = 22, scale = 2)
    private BigDecimal credits;

    @Column(name = "acct_typ", nullable = false)
    private String type;

    @Column(name = "acct_grp", nullable = false)
    private String group;

    public Account() { // jpa only
        this(null, null, null, "GL");
    }

    public Account(String typ, String grp) {
        this(null, null, typ, grp);
    }

    public Account(String num, String nam, String typ, String grp) {
        this(num, nam, typ, grp, new BigDecimal(0.0D), new BigDecimal(0.0D));
    }

    public Account(String num, String nam, String typ, String grp, BigDecimal bal, BigDecimal led) {
        this.type = typ;
        this.number = num;
        this.name = nam;
        this.balance = bal;
        this.ledger = led;
        this.credits = new BigDecimal(0.0D);
        this.debits = new BigDecimal(0.0D);
        this.group = grp;
    }

    public enum Type {
        ASSET_10, LIABILITY_20, REVENUE_30, EXPENSE_40, EQUITY_50;
    };

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String num) {
        this.number = num;
    }

    public String getName() {
        return name;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the debits
     */
    public BigDecimal getDebits() {
        return debits;
    }

    /**
     * @param debits the debits to set
     */
    public void setDebits(BigDecimal debits) {
        this.debits = debits;
    }

    /**
     * @return the credits
     */
    public BigDecimal getCredits() {
        return credits;
    }

    /**
     * @param credits the credits to set
     */
    public void setCredits(BigDecimal credits) {
        this.credits = credits;
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

    public boolean isDebitBalance() {
        return getType().equals("10") || getType().equals("40");
    }

    /**
     * @return the ledger
     */
    public BigDecimal getLedger() {
        return ledger;
    }

    /**
     * @param ledger the ledger to set
     */
    public void setLedger(BigDecimal ledger) {
        this.ledger = ledger;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isForcedPost() {
        return group == null || group.equals("LA");
    }

    @PrePersist
    void onPersist() throws Exception {
        if (id == null || id.isEmpty()) {
            id = new Identifier(Identifier.Type.SHORT).next();
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 7 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 7 * hash + (this.number != null ? this.number.hashCode() : 0);
        hash = 7 * hash + (this.balance != null ? this.balance.hashCode() : 0);
        hash = 7 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 7 * hash + (this.group != null ? this.group.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Account)) {
            return false;
        } else {
            return toString().equals(((Account) other).toString());
        }
    }
}
