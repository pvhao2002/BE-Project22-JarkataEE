package com.example.beproject22.service;

import com.example.beproject22.model.PaymenResponse;

import java.io.UnsupportedEncodingException;

public interface IPaymentService {
    public PaymenResponse createPayment(Double money) throws UnsupportedEncodingException;
}
