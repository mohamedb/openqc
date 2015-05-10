/**
 * Copyright Mohamed Boullouz <mohamed.boullouz@gmail.com>
 * This file is part of OpenQC Project
 *
 */
package com.openqc.facades;

import com.openqc.entities.RolePermission;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mohamed
 */
@Local
public interface RolePermissionFacadeLocal {

    void create(RolePermission rolePermission);

    void edit(RolePermission rolePermission);

    void remove(RolePermission rolePermission);

    RolePermission find(Object id);

    List<RolePermission> findAll();

    List<RolePermission> findRange(int[] range);

    int count();
    
}
