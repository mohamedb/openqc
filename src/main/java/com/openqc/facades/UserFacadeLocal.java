/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.facades;

import com.openqc.entities.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ABCD-user
 */
@Local
public interface UserFacadeLocal {
    
    User getUserByEmail(String email);
    
    User register(String email,String username,String password);

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
}
