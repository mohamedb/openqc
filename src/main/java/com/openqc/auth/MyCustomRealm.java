package com.openqc.auth;

import com.openqc.entities.User;
import com.openqc.facades.UserFacadeLocal;
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

    UserFacadeLocal userFacade = lookupUserFacadeLocal();

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
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
