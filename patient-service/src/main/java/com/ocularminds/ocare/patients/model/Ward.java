/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocularminds.ocare.patients.model;

import com.ocularminds.ocare.Identifier;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author text_
 */
@Entity(name = "tbl_ward")
public class Ward implements Serializable {

    //select WardName as 'Ward Name',WardType as 'Ward Type',NoOfbeds as 'No Of Beds',Charges from Ward
    public enum Type {
        GENERAL, PRIVATE;
    }

    @Id
    @Column(length = 20,name="ward_id")
    private String id;
   
    @NotEmpty
    @Size(min = 3, max = 26)
    @Column(length = 26)
    private String name;

    @NotNull
    @Column(name = "typ", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    private int beds;
    private BigDecimal surcharge;

    public Ward() {
        this("");
    }

    public Ward(String name) {
        this.name = name;
        this.surcharge = BigDecimal.ZERO;
        this.type = Type.GENERAL;
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
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return the beds
     */
    public int getBeds() {
        return beds;
    }

    /**
     * @param beds the beds to set
     */
    public void setBeds(int beds) {
        this.beds = beds;
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

    @PrePersist
    void onPersist() throws Exception {
        if (id == null || id.isEmpty()) {
            setId(new Identifier(Identifier.Type.SHORT).next());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (id != null) {
            sb.append(id);
        }
        if (name != null) {
            sb.append(name);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ward that = (Ward) o;
        return id.equals(that.getId());

    }

    @Override
    public int hashCode() {
        int result = (id != null) ? (int) (id.hashCode() ^ (id.hashCode() >>> 32)) : 0;
        result = (name != null) ? (31 * result + name.hashCode()) : result;
        result = (type != null) ? (31 * result + type.hashCode()) : result;
        return result;
    }
}
