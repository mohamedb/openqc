/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.facades;

import com.openqc.entities.UserRole;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ABCD-user
 */
@Stateless
public class UserRoleFacade extends AbstractFacade<UserRole> implements UserRoleFacadeLocal {
    @PersistenceContext(unitName = "com.openqc_OpenQC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRoleFacade() {
        super(UserRole.class);
    }

    @Override
    public List<UserRole> getUserRolesByEmail(String email)
	{
		 
		List<UserRole> roles = em
				//.createQuery("from UserRole where email = :email")
				.createQuery("Select ur From UserRole as ur Where ur.email= :email")
				.setParameter("email", email)
				.getResultList();
		return roles;
	}

    @Override
	public void insert(UserRole r)
	{
		em.persist(r);
	}
    
}
