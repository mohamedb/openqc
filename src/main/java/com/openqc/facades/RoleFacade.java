/**
 * Copyright Mohamed Boullouz <mohamed.boullouz@gmail.com>
 * This file is part of OpenQC Project
 *
 */
package com.openqc.facades;

import com.openqc.entities.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> implements RoleFacadeLocal {
    @PersistenceContext(unitName = "com.openqc_OpenQC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }
    
    /**
     * By default this MUST find and return a single result
     * otherwise something is broken
     * @param roleName
     * @return 
     */
    @Override
    public Role findByName(String roleName) {
      return (Role) em.createQuery("select R from Role R where R.name= :roleName")
                .setParameter("roleName", roleName)
                .getSingleResult();
    }
    
}
