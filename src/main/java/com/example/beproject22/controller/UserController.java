package com.example.beproject22.controller;


import com.example.beproject22.model.ResponseModel;
import com.example.beproject22.model.User;
import com.example.beproject22.service.IUserService;
import com.example.beproject22.service.impl.UserService;
import com.example.beproject22.utils.MessageResponse;
import com.example.beproject22.utils.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserController {
    private final IUserService userService = new UserService();
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object login(User user) {
        var result = userService.login(user.getUsername(), user.getPassword());
        if (result != null) {
            var rs = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
            return gson.toJson(rs);
        } else {
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.INCORRECT_USERNAME_OR_PASSWORD, null));
        }
    }

    @Path("/getByUsername")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUsername(User user) {
        var result = userService.getByUsername(user.getUsername());
        if (result != null) {
            ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
            String jsonResponse = new Gson().toJson(responseModel);
            return Response.ok(jsonResponse).build();
        } else {
            ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
            String errorJsonResponse = new Gson().toJson(errorModel);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorJsonResponse).build();
        }
    }

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
                ResponseModel errorModel = new ResponseModel(StatusResponse.ERROR, MessageResponse.USERNAME_ALREADY_EXISTS, null);
                String errorJsonResponse = new Gson().toJson(errorModel);
                return Response.status(Response.Status.BAD_REQUEST).entity(errorJsonResponse).build();
            }
        }
        return getResponse(status);
    }

    @Path("/update")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(User user) {
        var result = userService.getByUsername(user.getUsername());
        String status = "";
        if (result != null) {
            var rs = userService.update(user);
            status = rs.toString();
        } else {
            ResponseModel errorModel = new ResponseModel(StatusResponse.ERROR, MessageResponse.USERNAME_NOT_FOUND, null);
            String errorJsonResponse = new Gson().toJson(errorModel);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorJsonResponse).build();
        }
        return getResponse(status);
    }

    private Response getResponse(String status) {
        String STATUS_FAILED = "Error";
        if (status.contains(STATUS_FAILED)) {
            ResponseModel errorModel = new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null);
            String errorJsonResponse = new Gson().toJson(errorModel);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorJsonResponse).build();
        }
        ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, null);
        String jsonResponse = new Gson().toJson(responseModel);
        return Response.ok(jsonResponse).build();
    }

    @Path("/delete")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(User user) {
        var result = userService.getByUsername(user.getUsername());
        String status = "";
        if (result != null) {
            var rs = userService.delete(user.getUsername());
            status = rs.toString();
        } else {
            ResponseModel errorModel = new ResponseModel(StatusResponse.ERROR, MessageResponse.USERNAME_NOT_FOUND, null);
            String errorJsonResponse = new Gson().toJson(errorModel);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorJsonResponse).build();
        }
        return getResponse(status);
    }

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var result = userService.getAll();
        ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
        String jsonResponse = new Gson().toJson(responseModel);
        return Response.ok(jsonResponse).build();
    }
}
