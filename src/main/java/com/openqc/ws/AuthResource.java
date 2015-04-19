/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.ws;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author ABCD-user
 */
@Path("/auth")
public class AuthResource {

    @Context
    private UriInfo context;
    private final Subject currentUser = SecurityUtils.getSubject(); 
ObjectMapper mapper = new ObjectMapper();
    /**
     * Creates a new instance of AuthsResource
     */
    public AuthResource() {
    }

    /**
     * Retrieves representation of an instance of com.openqc.ws.AuthResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of AuthResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @Path("/check")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String isAuthenticated(String content) {
        String jsonResponse=null;
        try {
            jsonResponse = mapper.writeValueAsString(currentUser.isAuthenticated());
        } catch (IOException ex) {
            Logger.getLogger(EtudiantsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
         return jsonResponse;
    }
    @Path("/current")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public String getCurrentUser() {
        String jsonResponse=null;
        try {
            jsonResponse = mapper.writeValueAsString(currentUser.getPrincipal());
        } catch (IOException ex) {
            Logger.getLogger(EtudiantsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
         return jsonResponse;
    }

     
}
