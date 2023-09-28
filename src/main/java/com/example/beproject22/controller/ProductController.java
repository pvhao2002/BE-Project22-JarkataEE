package com.example.beproject22.controller;


import com.example.beproject22.model.Product;
import com.example.beproject22.model.ResponseModel;
import com.example.beproject22.service.IProductService;
import com.example.beproject22.service.impl.ProductService;
import com.example.beproject22.utils.MessageResponse;
import com.example.beproject22.utils.StatusResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/product")
public class ProductController {
    private final IProductService productService = new ProductService();
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Path("/getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getAll() {
        try {
            var result = productService.getAll();
            if (result != null) {
                ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
                return gson.toJson(responseModel);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("/getNewProduct")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getNewProduct() {
        try {
            var result = productService.getNewProduct();
            if (result != null) {
                ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
                return gson.toJson(responseModel);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object add(Product product) {
        try {
            var result = productService.add(product);
            if (result != null) {
                ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
                return gson.toJson(responseModel);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("/update")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object update(Product product) {
        try {
            product.setIsDeleted(false);
            var result = productService.update(product);
            if (result != null) {
                ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
                return gson.toJson(responseModel);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }

    @Path("/delete/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object delete(@PathParam("id") Integer id) {
        try {
            var result = productService.delete(id);
            if (result != null) {
                ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
                return gson.toJson(responseModel);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }


    @Path("/getById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getById(@PathParam("id") Integer id) {
        try {
            var result = productService.getById(id);
            if (result != null) {
                ResponseModel responseModel = new ResponseModel(StatusResponse.OK, MessageResponse.STATUS_SUCCESS, result);
                return gson.toJson(responseModel);
            } else {
                ResponseModel errorModel = new ResponseModel(StatusResponse.NO_CONTENT, MessageResponse.USERNAME_NOT_FOUND, null);
                return gson.toJson(errorModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseModel(StatusResponse.ERROR, MessageResponse.STATUS_ERROR, null));
        }
    }
}
