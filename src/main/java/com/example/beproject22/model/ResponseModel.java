package com.example.beproject22.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {
    private String status;
    private String message;
    private Object data;


    // result with json format
    @Override
    public String toString() {
        return String.format("{\n" + "  \"status\": \"%s\",\n" + "  \"message\": \"%s\",\n" + "  \"data\": %s\n" + "}", status, message, data);
    }
}
