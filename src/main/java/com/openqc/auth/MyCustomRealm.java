package com.openqc.auth;

import com.openqc.entities.Permission;
import com.openqc.entities.User;
import com.openqc.entities.UserRole;
import com.openqc.facades.PermissionFacadeLocal;
import com.openqc.facades.RoleFacadeLocal;
import com.openqc.facades.UserFacadeLocal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;

public class MyCustomRealm extends JdbcRealm {
    PermissionFacadeLocal permissionFacade = lookupPermissionFacadeLocal();
    RoleFacadeLocal roleFacade = lookupRoleFacadeLocal();
    UserFacadeLocal userFacade = lookupUserFacadeLocal();
    
    

    @Override
    protected Set<String> getRoleNamesForUser(Connection conn, String username) throws SQLException {
        Set<String> roleNames = new LinkedHashSet<>();
        User user=userFacade.getUserByEmail(username);
        Collection<UserRole> roles =user.getUserRoles();
        for(UserRole userRole:roles){
            roleNames.add(userRole.getRole().getName());
        }
        return roleNames;
    }
    
    @Override
    protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames) throws SQLException {
        Set<String> permissionNames = new LinkedHashSet<String>();
        for(String roleName:roleNames){
            List<Permission> permissionEntities = permissionFacade.getAllByRoleName(roleName);
            for(Permission permissionEntity: permissionEntities){
                permissionNames.add(permissionEntity.getName());
            }
        }
        return permissionNames; 
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        // identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        final String username = userPassToken.getUsername();

        if (username == null) {
            System.out.println("Username is null.");
            return null;
        }

        // read password hash and salt from db
        try {
            final User user = userFacade.getUserByEmail(username);
            if (user == null) {
                System.out.println("No account found for user [" + username + "]");
                return null;
            }
            // return salted credentials
            SaltedAuthenticationInfo info = new MySaltedAuthentificationInfo(
                    username, user.getPassword(), user.getSalt());
            return info;
        } finally {
            System.out.println("end Try getuserByEmail!");
        }

    }

    private UserFacadeLocal lookupUserFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (UserFacadeLocal) c.lookup("java:global/OpenQC/UserFacade!com.openqc.facades.UserFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught: cant lookup UserFacade ! see MyCustomRealm class", ne);
            throw new RuntimeException(ne);
        }
    }

    private RoleFacadeLocal lookupRoleFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (RoleFacadeLocal) c.lookup("java:global/OpenQC/RoleFacade!com.openqc.facades.RoleFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught: cant lookup RoleFacade ! see MyCustomRealm class", ne);
            throw new RuntimeException(ne);
        }
    }

    private PermissionFacadeLocal lookupPermissionFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PermissionFacadeLocal) c.lookup("java:global/OpenQC/PermissionFacade!com.openqc.facades.PermissionFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught: cant lookup PermissionFacade ! see MyCustomRealm class", ne);
            throw new RuntimeException(ne);
        }
    }
}
