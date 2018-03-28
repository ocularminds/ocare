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

import com.ocularminds.ocare.accounts.Fault;
import java.math.BigDecimal;

/**
 *
 * @author Jejelowo B. Festus <festus.jejelowo@ocularminds.com>
 */
public enum FeeType {
    FLAT {
        @Override
        public BigDecimal evaluate(Fee fee, BigDecimal amount) {
            return fee.getFixed();
        }

        @Override
        public Fault validate(Fee fee) {
            return validate(fee.getFixed());
        }

    },
    PERCENT {
        @Override
        public BigDecimal evaluate(Fee fee, BigDecimal amount) {
            BigDecimal var = fee.getVaried().movePointLeft(2).multiply(amount);
            return var.compareTo(fee.getCapped()) == 1 ? fee.getCapped() : var;
        }

        @Override
        public Fault validate(Fee fee) {
            Fault fault = validate(fee.getVaried());
            if (!fault.isSuccess()) {
                return fault;
            }
            fault = validate(fee.getCapped());
            if (!fault.isSuccess()) {
                return new Fault("F10", "This fee type should be capped.");
            }

            if (fee.getVaried().compareTo(new BigDecimal("50")) == 1) {
                return new Fault("F10", "Fee can not be more than "
                        + "50 percent of transaction amount");
            }
            return fault;
        }

    },
    BOTH {
        @Override
        public BigDecimal evaluate(Fee fee, BigDecimal amount) {
            BigDecimal var;
            var = fee.getVaried().movePointLeft(2).multiply(amount);
            var = var.add(fee.getFixed());
            return var.compareTo(fee.getCapped()) == 1 ? fee.getCapped() : var;
        }

        @Override
        public Fault validate(Fee fee) {
            Fault fault = validate(fee.getVaried());
            if (!fault.isSuccess()) {
                return fault;
            }
            fault = validate(fee.getFixed());
            if (!fault.isSuccess()) {
                return fault;
            }

            if (fee.getVaried().compareTo(new BigDecimal("50")) == 1) {
                return new Fault("F10", "Fee can not be more than "
                        + "50 percent of transaction amount");
            }
            fault = validate(fee.getCapped());
            if (!fault.isSuccess()) {
                return new Fault("F10", "This fee type should be capped.");
            }
            return fault;
        }
    };

    /**
     * Computes the total charge payable for selected fee
     *
     * @param fee Select fee
     * @param amount Transaction amount
     * @return The computed fee
     */
    public abstract BigDecimal evaluate(Fee fee, BigDecimal amount);

    /**
     * Validates the parameters for the requested fee is correct especially in
     * terms of amount
     *
     * @param fee
     * @return
     */
    public abstract Fault validate(Fee fee);

    public Fault validate(BigDecimal amount) {
        if (amount == null || amount.equals(BigDecimal.ZERO)) {
            return new Fault("F4", "Zero amount not valid");
        } else if (BigDecimal.ZERO.compareTo(amount) == 1) {
            return new Fault("F5", "Negative amount not accepted.");
        } else {
            return new Fault("00", "Amount successfully validated.");
        }
    }
}
