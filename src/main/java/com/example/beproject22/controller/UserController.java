package com.example.beproject22.controller;


import com.example.beproject22.model.ResponseModel;
import com.example.beproject22.model.User;
import com.example.beproject22.service.IUserService;
import com.example.beproject22.service.impl.UserService;
import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserController {

    private final IUserService userService = new UserService();

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        var result = userService.getByUsername(user.getUsername());
        String status = "";
        if (result == null) {
            var rs = userService.register(user);
            status = rs.toString();

        } else {
            if (result.getIsDeleted() == Boolean.TRUE) {
                var rs = userService.update(user);
                status = rs.toString();
            } else {
                ResponseModel errorModel = new ResponseModel("400", "Username already exists", null);
                String errorJsonResponse = new Gson().toJson(errorModel);
                return Response.status(Response.Status.BAD_REQUEST).entity(errorJsonResponse).build();
            }
        }
        if (status.contains("failed")) {
            ResponseModel errorModel = new ResponseModel("400", "Failed", null);
            String errorJsonResponse = new Gson().toJson(errorModel);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorJsonResponse).build();
        }
        ResponseModel responseModel = new ResponseModel("200", "Success", null);
        String jsonResponse = new Gson().toJson(responseModel);
        return Response.ok(jsonResponse).build();
    }

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var result = userService.getAll();
        ResponseModel responseModel = new ResponseModel("200", "Success", result);
        String jsonResponse = new Gson().toJson(responseModel);
        return Response.ok(jsonResponse).build();
    }
}
