/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.facades;

import com.openqc.entities.UserRole;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ABCD-user
 */
@Local
public interface UserRoleFacadeLocal {
    
    List<UserRole> getUserRolesByEmail(String email);
    
    public void insert(UserRole r);

    void create(UserRole userRole);

    void edit(UserRole userRole);

    void remove(UserRole userRole);

    UserRole find(Object id);

    List<UserRole> findAll();

    List<UserRole> findRange(int[] range);

    int count();
    
}
