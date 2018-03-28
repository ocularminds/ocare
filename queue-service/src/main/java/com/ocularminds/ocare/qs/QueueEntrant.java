/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.qs;

import java.util.Date;

/**
 *
 * @author text_
 */
public class QueueEntrant {

    private String id;
    private String number;
    private int clock;
    private String customerId;
    private Date arrive;
    private String tstatus;
    private String meanTime;
    
    public QueueEntrant(){
        
    }
    
    public QueueEntrant(int x){
        this.id = Integer.toString(x);
        clock = x;
    }
    
    public int getCurrent(int current){
	return clock-current;
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
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the arrive
     */
    public Date getArrive() {
        return arrive;
    }

    /**
     * @param arrive the arrive to set
     */
    public void setArrive(Date arrive) {
        this.arrive = arrive;
    }

    /**
     * @return the tstatus
     */
    public String getTstatus() {
        return tstatus;
    }

    /**
     * @param tstatus the tstatus to set
     */
    public void setTstatus(String tstatus) {
        this.tstatus = tstatus;
    }

    /**
     * @return the meanTime
     */
    public String getMeanTime() {
        return meanTime;
    }

    /**
     * @param meanTime the meanTime to set
     */
    public void setMeanTime(String meanTime) {
        this.meanTime = meanTime;
    }
}
