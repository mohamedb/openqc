/**
 * Copyright Mohamed Boullouz <mohamed.boullouz@gmail.com>
 * This file is part of OpenQC Project
 *
 */
package com.openqc.facades;

import com.openqc.entities.Role;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mohamed
 */
@Local
public interface RoleFacadeLocal {
    
    Role findByName(String roleName);

    void create(Role role);

    void edit(Role role);

    void remove(Role role);

    Role find(Object id);

    List<Role> findAll();

    List<Role> findRange(int[] range);

    int count();
    
}
