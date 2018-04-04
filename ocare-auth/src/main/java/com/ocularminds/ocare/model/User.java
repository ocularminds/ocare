package com.ocularminds.ocare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ocularminds.ocare.Identifier;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name = "tbl_user")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "username")
    private String login;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Permission permission;
    
    @Column(name="expiry")
    private int expiry;

    @Column(name = "created", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String username) {
        this.login = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(Permission r) {
        this.permission = r;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    /**
     * @return the expiry
     */
    public int getExpiry() {
        return expiry;
    }

    /**
     * @param expiry the expiry to set
     */
    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    @PrePersist
    void onPersist() throws Exception {
        if (id == null || id.isEmpty()) {
            id = new Identifier(Identifier.Type.LONG).next();
            date = new Date();
            setExpiry(90);
        }
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (getLogin() == null) {
            if (other.getLogin() != null) {
                return false;
            }
        } else if (!login.equals(other.login)) {
            return false;
        }
        return true;
    }
}
