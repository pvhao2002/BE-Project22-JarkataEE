package com.example.beproject22.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class PaymenResponse implements Serializable {
    private String status;
    private String message;
    private String URL;
}
