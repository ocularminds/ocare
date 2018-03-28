package com.ocularminds.ocare.model;

import com.ocularminds.ocare.Identifier;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * To-Do list with name (up to 255 chars) and a set of resources
 */
@Entity
@Table(name = "tbl_role")
public class Role implements Serializable {

    @Id
    @Column(name = "role_id", unique = true)
    private String id;

    @NotNull
    @Size(min = 1)
    @Column(name = "role_name", unique = true)
    private String name;

    @Column(name = "date_created", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private Set<ResourceLink> resources = new HashSet<>();

    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ResourceLink> getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(Set<ResourceLink> resources) {
        this.resources = resources;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void add(ResourceLink entry) {
        entry.setRole(this);
        getResources().add(entry);
    }

    public void delete(ResourceLink entry) {
        getResources().remove(entry);
    }

    @PrePersist
    void onPersist() throws Exception {
        if (getId() == null || getId().isEmpty()) {
            setId(new Identifier(Identifier.Type.SHORT).next());
            setDate(new Date());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getId() != null) {
            sb.append(getId());
        }
        if (getName() != null) {
            return getName();
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
        return Objects.equals(getId(), that.getId());

    }

    @Override
    public int hashCode() {
        int result = (getId() != null) ? (int) (getId().hashCode() ^ (getId().hashCode() >>> 32)) : 0;
        result = (getName() != null) ? (31 * result + getName().hashCode()) : result;
        return result;
    }

}
