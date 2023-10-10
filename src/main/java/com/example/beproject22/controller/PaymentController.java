package com.example.beproject22.controller;

import com.example.beproject22.config.ConfigVNPAY;
import com.example.beproject22.model.PaymentData;
import com.example.beproject22.service.IPaymentService;
import com.example.beproject22.service.impl.PaymentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Path("/payment")
public class PaymentController {
    private final IPaymentService paymentService = new PaymentService();
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @GET
    @Path("/vnpay/{money}")
    public Object vnpay(@PathParam("money") String money) {
        try {
            var result = paymentService.createPayment(Double.parseDouble(money));
            if (result != null) {
                return gson.toJson(result);
            } else {
                return gson.toJson(null);
            }
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }
}
