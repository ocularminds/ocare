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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author text_
 */
@Entity(name = "tbl_room")
public class Room implements Serializable {

    public enum Type {
        GENERAL, STANDARD, DELUX;
    }

    public enum Status {
        AVAILABLE, BOOKED;
    }
    //"select RoomNo as 'Room No.',RoomType as 'Room Type', RoomCharges as 'Room Charges',
    //RoomStatus as 'Room Status' from Room";
    @Id
    @Column(name = "room_id", length = 20)
    private String id;

    @NotNull
    @Size(min = 3, max = 26)
    @Column(length = 26)
    private String name;

    @Column(name = "typ", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    private BigDecimal surcharge;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    private Ward ward;

    public Room() {
        this("");
    }

    public Room(String name) {
        this.name = name;
        this.surcharge = BigDecimal.ZERO;
        this.type = Type.GENERAL;
        this.status = Status.AVAILABLE;
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
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the ward
     */
    public Ward getWard() {
        return ward;
    }

    /**
     * @param ward the ward to set
     */
    public void setWard(Ward ward) {
        this.ward = ward;
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

    @PrePersist
    void onPersist() throws Exception {
        if (getId() == null || getId().isEmpty()) {
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
