/**
 * Copyright Mohamed Boullouz <mohamed.boullouz@gmail.com>
 * This file is part of OpenQC Project
 *
 */
package com.openqc.facades;

import com.openqc.entities.Permission;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mohamed
 */
@Local
public interface PermissionFacadeLocal {
    
    List<Permission> getAllByRoleName(String roleName);

    void create(Permission permission);

    void edit(Permission permission);

    void remove(Permission permission);

    Permission find(Object id);

    List<Permission> findAll();

    List<Permission> findRange(int[] range);

    int count();
    
}
