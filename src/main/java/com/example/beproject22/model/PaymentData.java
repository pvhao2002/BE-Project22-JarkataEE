package com.example.beproject22.model;

import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class PaymentData {
    @FormParam("vnp_PayDate")
    private String vnpPayDate;

    @FormParam("vnp_ResponseCode")
    private String vnpResponseCode;

    @FormParam("vnp_TmnCode")
    private String vnpTmnCode;

    @FormParam("vnp_TransactionNo")
    private String vnpTransactionNo;

    @FormParam("vnp_TransactionStatus")
    private String vnpTransactionStatus;

    @FormParam("vnp_Amount")
    private String vnpAmount;

    @FormParam("vnp_BankCode")
    private String vnpBankCode;

    @FormParam("vnp_CardType")
    private String vnpCardType;

    @FormParam("vnp_OrderInfo")
    private String vnpOrderInfo;

    @FormParam("vnp_TxnRef")
    private String vnpTxnRef;

    @FormParam("vnp_SecureHash")
    private String vnpSecureHash;
}
