/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author ABCD-user
 */

public class EtudiantResource {

    private String id;

    /**
     * Creates a new instance of EtudiantResource
     */
    private EtudiantResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the EtudiantResource
     */
    public static EtudiantResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of EtudiantResource class.
        return new EtudiantResource(id);
    }

    /**
     * Retrieves representation of an instance of com.openqc.ws.EtudiantResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        
        return "etudiantResource";
    }

    /**
     * PUT method for updating or creating an instance of EtudiantResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource EtudiantResource
     */
    @DELETE
    public void delete() {
    }
}
