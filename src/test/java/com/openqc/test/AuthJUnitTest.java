/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.test;

import com.openqc.auth.UAuthObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mohamed
 */
public class AuthJUnitTest {

    static String BASE_URI;
    static Client client;

    @Test
    public void logInWithUsernameAsEmailAndPassword() {
        WebTarget target = client.target(BASE_URI + "/login");
        UAuthObject u = new UAuthObject("med@med.com", "med", true);
        Entity<UAuthObject> userEntity = Entity.entity(u, MediaType.APPLICATION_JSON);
        Response response = target.request().post(userEntity);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("OK", response.readEntity(String.class));
        
    }
    
    @Test
    public void mustUserRoleEqualToAdmin(){
        logInWithUsernameAsEmailAndPassword();
        WebTarget target = client.target(BASE_URI + "/current");        
        Response response = target.queryParam("", "med@med.com").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(true, response.readEntity(String.class));
    }


    @BeforeClass
    public static void setUpClass() {
        BASE_URI = "http://localhost:8080/openqc/ws/auth";
        client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .build();
      
    }

    @AfterClass
    public static void tearDownClass() {
    }
}
