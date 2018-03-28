/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.ocularminds.ocare.accounts.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Festus Jejelowo
 */

public class Journal implements Serializable {
    
    private String id;
    
    private String name;
    
    private String reference;
    
    private BigDecimal total;
    
    private Set<LedgerEntry> entries;
    
    private Date posted;

    private transient int serial;

    private Boolean closed;

    public Journal() {
        this("", "");
    }

    public Journal(String ref, String nam) {
        this( nam,  ref, BigDecimal.ZERO, new Date());
    }

    public Journal(String nam, String refid, BigDecimal tot, Date date) {
        this.reference = refid;
        this.name = nam;
        this.posted = date;
        this.total = tot;
        this.closed = true;
        this.serial = 0;
    }

    public void add(LedgerEntry entry) {
        if (entries == null) {
            entries = new LinkedHashSet<>();
        }
        if (!entries.contains(entry)) {
            this.serial++;
            entry.setSerial(serial);
            if (entry.getType().equalsIgnoreCase("D")) {
                setTotal(getTotal().add(entry.getAmount()));
            }
            getEntries().add(entry);
            entry.setJournal(this);
        }
    }

    /**
     * @return journal name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A closed journal should not accept transactions
     *
     * @param closed true if this journal is closed.
     */
    public void setClosed(boolean closed) {
        this.setClosed((Boolean) closed);
    }

    /**
     * @return true if this journal has a <code>closed</code> indicator
     */
    public boolean isClosed() {
        return getClosed();
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
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the entries
     */
    public Set<LedgerEntry> getEntries() {
        return entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(Set<LedgerEntry> entries) {
        this.entries = entries;
    }

    /**
     * @return the posted
     */
    public Date getPosted() {
        return posted;
    }

    /**
     * @param posted the posted to set
     */
    public void setPosted(Date posted) {
        this.posted = posted;
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
     * @return the closed
     */
    public Boolean getClosed() {
        return closed;
    }

    /**
     * @param closed the closed to set
     */
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
