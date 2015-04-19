package com.openqc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RolesPermission implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String permission;

    @Column
    private String roleName;
    
    public RolesPermission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getPermission() {
        return permission;
    }

    public String getRoleName() {
        return roleName;
    }
 

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
