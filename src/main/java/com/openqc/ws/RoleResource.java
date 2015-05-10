/**
 * Copyright Mohamed Boullouz <mohamed.boullouz@gmail.com>
 * This file is part of OpenQC Project
 *
 */
package com.openqc.ws;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 *
 * @author Mohamed
 */

@RequiresAuthentication
@RequiresPermissions("read")
public class RoleResource {
    @RequiresAuthentication
    @GET
    @Consumes("application/json")
    @Produces("application/json")
     public List<String> sayHello()
     {   
         List<String> strs= new ArrayList<>();
         strs.add("Welcome "); strs.add("This is a long "); strs.add("Hellooooooo");
         return strs;
     }
    
}
