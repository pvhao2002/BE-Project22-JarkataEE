package com.example.beproject22.service.impl;

import com.example.beproject22.model.Dashboard;
import com.example.beproject22.model.Product;
import com.example.beproject22.service.DBConnect;
import com.example.beproject22.service.IDashBoard;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DashboardService implements IDashBoard {
    // count number of active account, inactive account, total product, total orders
    final static String GET_ACTIVE_ACCOUNT = "SELECT COUNT(1) AS total FROM users WHERE is_deleted = 0";
    final static String GET_INACTIVE_ACCOUNT = "SELECT COUNT(1) AS total FROM users WHERE is_deleted = 1";
    final static String GET_TOTAL_PRODUCT = "SELECT COUNT(1) AS total FROM product";
    final static String GET_TOTAL_ORDER = "SELECT COUNT(1) AS total FROM `order`";
    final static String GET_TOTAL_PRODUCT_SOLD = "SELECT SUM(quantity) as total FROM order_item;";
    final static String GET_TOTAL_REVENUE = "SELECT SUM(total) AS total FROM `order`;";

    public Integer getTotal(String sql) {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(sql);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Double getRevenue() {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                ps = cnt.prepareStatement(GET_TOTAL_REVENUE);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (double) 0;
    }


    @Override
    public Object getDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.setTotalActiveUser(getTotal(GET_ACTIVE_ACCOUNT));
        dashboard.setTotalInactiveUser(getTotal(GET_INACTIVE_ACCOUNT));
        dashboard.setTotalProduct(getTotal(GET_TOTAL_PRODUCT));
        dashboard.setTotalOrders(getTotal(GET_TOTAL_ORDER));
        dashboard.setTotalProductSales(getTotal(GET_TOTAL_PRODUCT_SOLD));
        dashboard.setTotalRevenue(getRevenue());
        return dashboard;
    }
}
