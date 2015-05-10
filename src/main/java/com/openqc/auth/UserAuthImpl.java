/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.auth;

import java.util.Arrays;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
 

/**
 *
 * @author Mohamed
 */
public class UserAuthImpl implements UserAuth{
    
    private  Subject currentUser = SecurityUtils.getSubject();

    public UserAuthImpl() {
    }
    
    
    
    @Override
    public boolean login(UAuthObject uAuthObject) {        
        if (!currentUser.isAuthenticated()) {
            // collect user principals and credentials in a gui specific manner
            // such as username/password html form, X509 certificate, OpenID,
            // etc.
            // We'll use the username/password example here since it is the most
            // common.
            UsernamePasswordToken token = new UsernamePasswordToken(uAuthObject.getUsername(), uAuthObject.getPassword(), uAuthObject.isRememberMe());
            // this is all you have to do to support 'remember me' (no config -
            // built in!):
            token.setRememberMe(uAuthObject.isRememberMe());
            try {
                currentUser.login(token);
                currentUser.getSession().setAttribute("username", token.getPrincipal());
                return true;
            } catch (UnknownAccountException uae) {
                 
                System.out.println("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("Password for account " + token.getPrincipal()
                        + " was incorrect! \n\t Given: " + Arrays.toString(token.getPassword()));
            } catch (LockedAccountException lae) {
                System.out.println("The account for username " + token.getPrincipal() + " is locked.  "
                        + "Please contact your administrator to unlock it.");
            }
        } else {
            return true; // already logged in
        }
        return false;
    }

    @Override
    public boolean checkUserAuth(UAuthObject uAuthObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkUserAuthByEmail(String userEmail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
