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

/**
 * Model class for transaction fee
 * <p>
 * This is also known as surcharge or checkout fee, is an extra fee charged by a
 * merchant on defined payment or transaction type. This could be a
 * FIXED,VARIABLE or BOTH fee or percentage of the transaction amount.
 *
 * @author Festus B. Jejelowo
 * @author Ocular-Minds Software
 * @author festus.jejelowo@ocularminds.com
 */
public class Fee implements Serializable {
    
    private String id;

    /**
     * Fee name
     */
    private String name;

    /**
     * Fee category [product ,transaction, processor]
     */
    private String category;

    /**
     * Receiving account where revenue should be credited
     */
    private String account;

    /**
     * Transaction type code
     */
    private String transaction;

    /**
     * Transaction type code
     */
    private String processor;

    /**
     * Fee type
     */
    private FeeType type;

    /**
     * Fee amount
     */
    private BigDecimal fixed;

    /**
     * Fee amount
     */
    private BigDecimal varied;

    /**
     * Fee amount
     */
    private BigDecimal capped;

    /**
     * Minimum amount
     */
    private BigDecimal minimum;

    /**
     * Maximum amount
     */
    private BigDecimal maximum;

    public Fee() {
        this("", "FLAT", null);
    }

    public Fee(String nam, String typ, String cod) {
        this.name = nam;
        this.category = cod;
        this.type = FeeType.valueOf(typ.toUpperCase());
        this.fixed = BigDecimal.ZERO;
        this.varied = BigDecimal.ZERO;
        this.capped = BigDecimal.ZERO;
        this.minimum = BigDecimal.ZERO;
        this.maximum = BigDecimal.ZERO;
    }
    
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
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The transaction processing category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param cat The transaction processing category
     */
    public void setCategory(String cat) {
        this.category = cat;
    }

    /**
     *
     * @return The type
     */
    public FeeType getType() {
        return type;
    }

    /**
     *
     * @param type The fee_type
     */
    public void setType(FeeType type) {
        this.type = type;
    }

    /**
     *
     * @return The transaction code
     */
    public String getTransaction() {
        return transaction;
    }

    /**
     *
     * @param mct The transaction code
     */
    public void setTransaction(String mct) {
        this.transaction = mct;
    }

    /**
     * @return the processor
     */
    public String getProcessor() {
        return processor;
    }

    /**
     * @param processor the processor to set
     */
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    /**
     *
     * @return The amount
     */
    public BigDecimal getMinimum() {
        return minimum;
    }

    /**
     *
     * @param min The maximum
     */
    public void setMinimum(BigDecimal min) {
        this.minimum = min;
    }

    /**
     *
     * @return The amount
     */
    public BigDecimal getMaximum() {
        return maximum;
    }

    /**
     *
     * @param max The amount
     */
    public void setMaximum(BigDecimal max) {
        this.maximum = max;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the fixed
     */
    public BigDecimal getFixed() {
        return fixed;
    }

    /**
     * @param fixed the fixed to set
     */
    public void setFixed(BigDecimal fixed) {
        this.fixed = fixed;
    }

    /**
     * @return the varied
     */
    public BigDecimal getVaried() {
        return varied;
    }

    /**
     * @param varied the varied to set
     */
    public void setVaried(BigDecimal varied) {
        this.varied = varied;
    }

    /**
     * @return the capped
     */
    public BigDecimal getCapped() {
        return capped;
    }

    /**
     * @param capped the capped to set
     */
    public void setCapped(BigDecimal capped) {
        this.capped = capped;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getId() != null) {
            sb.append(getId());
        }
        if (getName() != null) {
            sb.append(getName());
        }
        if (getCategory() != null) {
            sb.append(getCategory());
        }
        if (getFixed() != null) {
            sb.append(getFixed());
        }
        if (getVaried() != null) {
            sb.append(getVaried());
        }
        if (getCapped() != null) {
            sb.append(getCapped());
        }
        if (getType() != null) {
            sb.append(getType());
        }
        if (getMinimum() != null) {
            sb.append(getMinimum());
        }
        if (getMaximum() != null) {
            sb.append(getMaximum());
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Fee)) {
            return false;
        } else {
            Fee rhs = (Fee) other;
            return toString().equals(rhs.toString());
        }
    }
}
