package com.ocularminds.ocare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ocularminds.ocare.Identifier;
import java.util.Date;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * To-Do entry with id, role it is assigned to and up to 16k long description
 */
@Entity
@Table(name = "tbl_role_res")
public class ResourceLink {

    @Id
    @Column(name = "resource_id", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnore
    private Role role;

    @NotNull
    @Size(min = 1, max = 16000)
    private String description;

    public ResourceLink() {
    }

    public ResourceLink(String desc) {
        this.description = desc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (getId() != null) {
            sb.append(getId());
        }
        if (getDescription() != null) {
            return getDescription();
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
        Role that = (Role) o;
        return getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        int result = (getId() != null) ? (int) (getId().hashCode() ^ (getId().hashCode() >>> 32)) : 0;
        result = (getDescription() != null) ? (31 * result + getDescription().hashCode()) : result;
        return result;
    }
}
