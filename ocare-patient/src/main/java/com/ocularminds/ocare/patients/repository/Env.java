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
package com.ocularminds.ocare.repository;

import java.io.Serializable;

/**
 * Env models application configuration values for transaction processing
 *
 * @author Festus B. Jejelowo
 * @author Ocular-Minds Software
 */
public class Env implements Serializable {

    private String id;

    private String subscriberId;

    private String fundReceivable;

    /**
     * The deposit(money) receivable account
     */
    private String accountPayable;

    private String commissionReceivable;

    private String processorExpense;

    private String domains;

    private String clientId;

    private String frontEndPartner;

    private String clientSecret;

    private String env;
    public static final String ENV_SANDBOX = "SANDBOX";
    public static final String ENV_PROD = "PRODUCTION";
    public static final String ENV_DEV = "DEVELOPMENT";

    public Env() {
        this.clientId = "106";
        this.frontEndPartner = "LES";
        this.domains = "127.0.0.1:81,app.dmoney.com";
    }

    public Env(String client, String secret, String partner, String subscriber, String env) {
        this.clientId = client;
        this.clientSecret = secret;
        this.frontEndPartner = partner;
        this.env = env;
        this.subscriberId = subscriber;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the id
     */
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the domains
     */
    public String getDomains() {
        return domains;
    }

    /**
     * @param domains the domains to set
     */
    public void setDomains(String domains) {
        this.domains = domains;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @return the fundReceivable
     */
    public String getFundReceivable() {
        return fundReceivable;
    }

    /**
     * @param cash the fundReceivable to set
     */
    public void setFundReceivable(String cash) {
        this.fundReceivable = cash;
    }

    /**
     * @return the commissionReceivable
     */
    public String getCommissionReceivable() {
        return commissionReceivable;
    }

    /**
     * @param commissionReceivable the commissionReceivable to set
     */
    public void setCommissionReceivable(String commissionReceivable) {
        this.commissionReceivable = commissionReceivable;
    }

    /**
     * @return the processorExpense
     */
    public String getProcessorExpense() {
        return processorExpense;
    }

    /**
     * @param expense the processorExpense to set
     */
    public void setProcessorExpense(String expense) {
        this.processorExpense = expense;
    }

    /**
     * @return the accountPayable
     */
    public String getAccountPayable() {
        return accountPayable;
    }

    /**
     * @param accountPayable the accountPayable to set
     */
    public void setAccountPayable(String accountPayable) {
        this.accountPayable = accountPayable;
    }

    /**
     * @return the frontEndPartner
     */
    public String getFrontEndPartner() {
        return frontEndPartner;
    }

    /**
     * @param frontEndPartner the frontEndPartner to set
     */
    public void setFrontEndPartner(String frontEndPartner) {
        this.frontEndPartner = frontEndPartner;
    }

    /**
     * @return the subscriberId
     */
    public String getSubscriberId() {
        return subscriberId;
    }

    /**
     * @param subscriberId the subscriberId to set
     */
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    /**
     * @return the env
     */
    public String getEnv() {
        return env;
    }

    /**
     * @param env the env to set
     */
    public void setEnv(String env) {
        this.env = env;
    }

}
