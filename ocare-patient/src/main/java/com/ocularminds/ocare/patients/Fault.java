package com.ocularminds.ocare;

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
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * The Fault class is processing error wrapper object for holding details result
 * after certain operations.
 *
 * The fault basically contains the error(error code) and the fault(error
 * description) with arbitrary object which is any additional data to be
 * returned along with the Fault.
 *
 * @author Jejelowo B. Festus
 * @author festus.jejelowo@ocularminds.com
 */
public class Fault implements Serializable {

    /**
     * The error code
     */
    private String error;

    /**
     * The error description
     */
    private String fault;

    /**
     * Error group
     */
    private String group;

    /**
     * Optional data
     */
    private String value;

    /**
     * Optional data
     */
    private Object data;

    /**
     * Optional prams
     */
    private Object params;

    private static final Map<String, String> CODES = Fault.load();

    public Fault() {
    }

    public Fault(String error, String fault) {
        this(error, fault, null);
    }

    /**
     * Primary constructor for the Fault class
     *
     * @param error Error code
     * @param fault Error details or description
     * @param data Optional data returned with the error if successful
     *
     */
    public Fault(String error, String fault, Object data) {
        this.error = error;
        this.fault = fault;
        this.data = data;
    }

    public Fault(String value, Long data) {
        this.value = value;
        this.data = data;
    }

    /**
     * @return Compares the error code to '00' to check is they are equal
     */
    public boolean isSuccess() {
        return this.error.equals("00") || this.error.equals("200") || this.error.equals("201");
    }

    /**
     * Checks is the error code meant failure. That is not equals 00
     *
     * @return boolean result of comparing error with 00
     */
    public boolean isFailed() {
        return !this.isSuccess();
    }

    /**
     * For optional data returned with the error
     *
     * @return Optional data
     */
    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return The optional data group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Assigns new error group
     *
     * @param group String error group which also acts as optional data
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     *
     * @return String error code
     */
    public String getError() {
        return error;
    }

    /**
     * Assigns new error code
     *
     * @param error String error code
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return String the error description
     */
    public String getFault() {
        return fault;
    }

    /**
     * Assigns error details
     *
     * @param fault String error description
     */
    public void setFault(String fault) {
        this.fault = fault;
    }

    /**
     *
     * @return Optional data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data value
     *
     * @param o Optional data
     */
    public void setData(Object o) {
        data = o;
    }

    /**
     *
     * @return Optional data
     */
    public Object getParams() {
        return params;
    }

    /**
     * Sets data value
     *
     * @param o Optional data
     */
    public void setParams(Object o) {
        params = o;
    }

    /**
     * ISO Error code mapper
     *
     * @param err String error code
     * @return Error description
     */
    public static String error(String err) {
        return CODES.get(err) != null ? CODES.get(err) : "Unknown error";
    }

    private static Map<String, String> load() {
        Map<String, String> responses = new HashMap<>();
        responses.put("00", "Successful approval");
        responses.put("01", "Refer to card issuer");
        responses.put("02", "Refer to card issuer, special condition");
        responses.put("03", "Invalid merchant or service provider");
        responses.put("04", "Pickup card");
        responses.put("05", "Do not honor");
        responses.put("06", "Error");
        responses.put("07", "Pickup card, special condition");
        responses.put("10", "Partial Approval");
        responses.put("11", "V.I.P. approval");
        responses.put("12", "Invalid transaction");
        responses.put("13", "Invalid amount ");
        responses.put("14", "Invalid account number");
        responses.put("15", "No such issuer");
        responses.put("17", "Customer cancellation");
        responses.put("19", "Re-enter transaction");
        responses.put("20", "Invalid response");
        responses.put("21", "No action taken ");
        responses.put("22", "Suspected Malfunction");
        responses.put("25", "Unable to locate record in file");
        responses.put("28", "File is temporarily unavailable");
        responses.put("30", "Format Error");
        responses.put("41", "Pickup card (lost card)");
        responses.put("43", "Pickup card (stolen card)");
        responses.put("51", "Insufficient funds");
        responses.put("52", "No checking account");
        responses.put("53", "No savings account");
        responses.put("54", "Expired card");
        responses.put("55", "Incorrect PIN");
        responses.put("57", "Transaction not permitted to cardholder");
        responses.put("58", "Transaction not allowed at terminal");
        responses.put("59", "Suspected fraud");
        responses.put("61", "Activity amount limit exceeded");
        responses.put("62", "Restricted card ");
        responses.put("63", "Security violation");
        responses.put("65", "Activity count limit exceeded");
        responses.put("68", "Response received too late");
        responses.put("75", "Allowable number of PIN-entry tries exceeded");
        responses.put("76", "Unable to locate previous message");
        responses.put("77", "Previous message located for a repeat or reversal");
        responses.put("78", "Blocked, card not activated");
        responses.put("80", "Visa transactions: credit issuer unavailable");
        responses.put("81", "PIN cryptographic error found");
        responses.put("82", "Negative CAM, dCVV, iCVV, or CVV");
        responses.put("83", "Unable to verify PIN");
        responses.put("85", "No reason to decline a request ");
        responses.put("91", "Issuer unavailable ");
        responses.put("92", "Destination cannot be found for routing");
        responses.put("93", "Transaction cannot be completed");
        responses.put("94", "Duplicate Transmission");
        responses.put("95", "Reconcile error");
        responses.put("96", "System malfunction");
        responses.put("B1", "Surcharge amount not permitted");
        responses.put("N0", "Force STIP");
        responses.put("N3", "Cash service not available");
        responses.put("N4", "Cashback request exceeds issuer limit");
        responses.put("N7", "Decline for CVV2 failure");
        responses.put("P2", "Invalid biller information");
        responses.put("P5", "PIN Change/Unblock request declined");
        responses.put("P6", "Unsafe PIN");
        responses.put("Q1", "Card Authentication failed");
        responses.put("R0", "Stop Payment Order");
        responses.put("R1", "Revocation of Authorization Order");
        responses.put("R3", "Revocation of All Authorizations Order");
        responses.put("XA", "Forward to issuer");
        responses.put("XD", "Forward to issuer");
        responses.put("Z3", "Unable to go online");
        return responses;

    }
}
