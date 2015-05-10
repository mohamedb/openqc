/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openqc.auth.UAuthObject;
import com.openqc.auth.UserAuth;
import com.openqc.auth.UserAuthImpl;
import com.openqc.entities.User;
import com.openqc.facades.UserFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;

/**
 * REST Web Service
 *
 * @author ABCD-user
 */
@Path("/auth")
public class AuthResource {

    @Context
    private HttpServletRequest request;

    @Context
    private UriInfo context;
    //private  Subject currentUser = SecurityUtils.getSubject();
    private UserAuth userAuth = new UserAuthImpl();

    /**
     * Creates a new instance of AuthsResource
     */
    public AuthResource() {
    }

    /**
     * Login using username/email and password and rememberMe=true
     *
     * @see UAuthObject construction, because we use Jakson to perform
     * Ser/deseialization
     * @param content
     * @return
     */
    @Path("/login")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(UAuthObject content) {
        if (userAuth.login(content)) {
            return Response.status(Response.Status.OK).entity("OK").build();
        }
        return Response.status(Response.Status.FORBIDDEN).entity("BadCredentials").build();
    }

    /**
     * POST method for checking whether a user is auth and is in the Session
     * variable created by Shiro auth framework
     *
     * @see com.openqc.auth.UserAuthImpl#login(com.openqc.auth.UAuthObject)
     * @param userNameParam
     * @return content with the same username if found in the session var
     */
    @Path("/check/{username}")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response isAuthenticated(@PathParam("username") String userNameParam) {
        /* check that the session contains the username */

        String userName = (String) request.getSession().getAttribute("username");
        if (userName != null && !userName.trim().equals("") && userName.equalsIgnoreCase(userNameParam)) {
            return Response.status(Response.Status.OK).entity(userNameParam).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("NOT_FOUND").build();
    }

    @RequiresAuthentication
    @Path("/current")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println("\n ========= Entred ==========");
        boolean hasRole = currentUser.hasRole("admin");
        UserFacadeLocal userFacade = lookupUserFacadeLocal();
        List<User> users = userFacade.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        try {
            str = mapper.writeValueAsString(users);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(AuthResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.status(Response.Status.OK).entity(users).build();
    }

    private UserFacadeLocal lookupUserFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UserFacadeLocal) c.lookup("java:global/OpenQC/UserFacade!com.openqc.facades.UserFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught: cant lookup UserFacade ! see MyCustomRealm class", ne);
            throw new RuntimeException(ne);
        }
    }

}
