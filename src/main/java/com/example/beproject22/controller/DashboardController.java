package com.example.beproject22.controller;


import com.example.beproject22.service.IDashBoard;
import com.example.beproject22.service.impl.DashboardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/dashboard")
public class DashboardController {
    private final IDashBoard iDashBoard = new DashboardService();
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Path("")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String dashboard() {
        try {
            var result = iDashBoard.getDashboard();
            if (result != null) {
                return gson.toJson(result);
            } else {
                return gson.toJson(null);
            }
        } catch (Exception e) {
            return gson.toJson(e.getCause());
        }
    }
}
