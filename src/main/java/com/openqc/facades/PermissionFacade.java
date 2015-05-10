/**
 * Copyright Mohamed Boullouz <mohamed.boullouz@gmail.com>
 * This file is part of OpenQC Project
 *
 */
package com.openqc.facades;

import com.openqc.entities.Permission;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mohamed
 */
@Stateless
public class PermissionFacade extends AbstractFacade<Permission> implements PermissionFacadeLocal {
    @PersistenceContext(unitName = "com.openqc_OpenQC_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermissionFacade() {
        super(Permission.class);
    }

    @Override
    public List<Permission> getAllByRoleName(String roleName) {
        List<Permission> permissions;
        permissions= em.createQuery(" SELECT P FROM Permission as P, Role as R, RolePermission as RP  WHERE RP.role.id = R.id AND RP.permission.id=P.id AND R.name= :roleName ")
                    .setParameter("roleName", roleName).getResultList();
       return permissions;
    }
    
}
