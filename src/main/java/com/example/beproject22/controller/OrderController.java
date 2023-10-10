package com.example.beproject22.controller;


import com.example.beproject22.model.Order;
import com.example.beproject22.model.ResponseModel;
import com.example.beproject22.service.IOrderService;
import com.example.beproject22.service.impl.OrderService;
import com.example.beproject22.utils.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/order")
public class OrderController {
    private final IOrderService orderService = new OrderService();
    private  final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object add(Order order) {
        try {
            var result = orderService.add(order);
            if (result != null) {
                return gson.toJson(result);
            } else {
                return gson.toJson(null);
            }
        } catch (Exception e) {
            return gson.toJson(e.getCause());
        }
    }


    @Path("/getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getAll() {
        try {
            var result = orderService.getAll();
            if (result != null) {
                return gson.toJson(result);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, "Không có hóa đơn nào", null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            return gson.toJson(e.getCause());
        }
    }

    // get all order of user, user id is param
    @Path("/getAllOrderOfUser/{userId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object getAllOrderOfUser(@PathParam("userId") Integer userId) {
        try {
            var result = orderService.getAllOrderOfUser(userId);
            if (result != null) {
                return gson.toJson(result);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, "Không có hóa đơn nào", null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            return gson.toJson(e.getCause());
        }
    }
}
