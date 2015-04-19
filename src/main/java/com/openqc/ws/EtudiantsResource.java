/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.ws;
 
import com.openqc.facades.UserFacadeLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author ABCD-user
 */
@Path("/etudiants")
public class EtudiantsResource {
    UserFacadeLocal userFacade = lookupUserFacadeLocal();
    
    
    @Context
    private UriInfo context;
    private final Subject currentUser = SecurityUtils.getSubject(); 
    
    /**
     * Creates a new instance of EtudiantsResource
     */
    public EtudiantsResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.openqc.ws.EtudiantsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        
        if(!currentUser.isAuthenticated()){
            return "Not auth !";
        }
        //currentUser.checkRole("etudiant");
        if(!currentUser.hasRole("etudiant")){
            return "Acces refusé: <b>vous devez avoir le role Etudiant pour continuer !</b>";
        }
         
        ObjectMapper mapper = new ObjectMapper();
        String jsonReponse = null;
        try {
            jsonReponse = mapper.writeValueAsString(userFacade.findAll());
        } catch (IOException ex) {
            Logger.getLogger(EtudiantsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonReponse;

    }

    /**
     * POST method for creating an instance of EtudiantResource
     *
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public EtudiantResource getEtudiantResource(@PathParam("id") String id) {
        return EtudiantResource.getInstance(id);
    }

    private UserFacadeLocal lookupUserFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UserFacadeLocal) c.lookup("java:global/OpenQC/UserFacade!com.openqc.facades.UserFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
 
 
}
