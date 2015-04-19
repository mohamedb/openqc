/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.facades;

import com.openqc.entities.RolesPermission;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ABCD-user
 */
@Local
public interface RolesPermissionFacadeLocal {

    void create(RolesPermission rolesPermission);

    void edit(RolesPermission rolesPermission);

    void remove(RolesPermission rolesPermission);

    RolesPermission find(Object id);

    List<RolesPermission> findAll();

    List<RolesPermission> findRange(int[] range);

    int count();
    
}
