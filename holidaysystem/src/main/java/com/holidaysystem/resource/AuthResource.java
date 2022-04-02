package com.holidaysystem.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.status;

@Path("/auth")
public class AuthResource {

    //@Inject
    //Logger LOGGER;

   // @Inject
    //private SecurityContext securityContext;

    //@Inject
    //@Authenticated
    //UserInfo userInfo;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login() {
        //LOGGER.log(Level.INFO, "login");
        /*
        if (securityContext.getCallerPrincipal() != null) {
            JsonObject result = Json.createObjectBuilder()
                .add("user", securityContext.getCallerPrincipal().getName())
                .build();
            return ok(result).build();
        }
        */
        return status(UNAUTHORIZED).build();
    }

    @GET
    @Path("userinfo")
    public Response userInfo() {
        //LOGGER.log(Level.INFO, "user info:{0}", userInfo);
        /*
        if (securityContext.getCallerPrincipal() != null) {
            return ok(userInfo).build();
        }
        */
        return status(UNAUTHORIZED).build();
    }

}
