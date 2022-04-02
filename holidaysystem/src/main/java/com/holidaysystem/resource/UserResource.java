package com.holidaysystem.resource;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.User;
import com.holidaysystem.model.UserRequest;
import com.holidaysystem.repository.IUserRepository;
import com.holidaysystem.repository.UserRepository;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@Stateless
public class UserResource {

    @Inject
    UserRepository userRepository;

    @GET
    @Path("/all")
    public Response getUsers() {
    	List<User> users = userRepository.getUsers();
        return Response.ok(users).build();
    }
    
    
    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") UUID id) {
    	System.out.println("userId = " + id);
    	User user = userRepository.findById(id);
        return Response.ok(user).build();
    }
    
    @POST()
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRequest userRequest) {
    	
    	User user = new User();
    	user.setId(UUID.randomUUID());
    	user.setEmail(userRequest.getEmail());
    	user.setFirstname(userRequest.getFirstName());
    	user.setLastname(userRequest.getLastName());
    	
        return Response.ok(user).build();
    }
}
