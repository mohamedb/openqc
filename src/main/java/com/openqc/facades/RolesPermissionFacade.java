/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.facades;

import com.openqc.entities.RolesPermission;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ABCD-user
 */
@Stateless
public class RolesPermissionFacade extends AbstractFacade<RolesPermission> implements RolesPermissionFacadeLocal {
    @PersistenceContext(unitName = "com.openqc_OpenQC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesPermissionFacade() {
        super(RolesPermission.class);
    }
    
}
