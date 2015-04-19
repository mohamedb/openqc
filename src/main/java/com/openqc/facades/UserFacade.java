/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.facades;

import com.openqc.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 *
 * @author ABCD-user
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "com.openqc_OpenQC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> users =  em
                //.createQuery("from User where email = :email")
                .createQuery("Select u From User as u Where u.email= :email")
                .setParameter("email", email)
                .getResultList();
        return users.isEmpty()?null:users.get(0);
    }
    /**
     * Attention: Username = Email 
     * @todo : changer ce comportement!
     * @param email
     * @param username
     * @param password
     * @return 
     */
    @Override
    public User register(String email, String username, String password) {
        User user = new User();
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        Object salt = rng.nextBytes();
        String hashedPasswordBase64 = new Sha256Hash(password, salt,1024).toBase64();
        user.setPassword(hashedPasswordBase64);
        user.setSalt(salt.toString());
        user.setEmail(username);
        user.setUsername(username);
        em.persist(user);
        em.flush();
        return user;
    }

}
