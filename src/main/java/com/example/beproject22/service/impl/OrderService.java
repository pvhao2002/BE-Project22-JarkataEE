package com.example.beproject22.service.impl;

import com.example.beproject22.model.Order;
import com.example.beproject22.model.OrderItem;
import com.example.beproject22.model.Product;
import com.example.beproject22.model.User;
import com.example.beproject22.service.DBConnect;
import com.example.beproject22.service.IOrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    private static final String INSERT_ORDER_SQL = "INSERT INTO `order` (user_id, address, phone, status, note, total) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ORDER_ITEM_SQL = "INSERT INTO order_item (order_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ORDER_BY_USER_ID =
            "SELECT\n" +
                    "    o.id AS order_id,\n" +
                    "    o.status,\n" +
                    "    p.id AS product_id,\n" +
                    "    p.product_name,\n" +
                    "    p.price,\n" +
                    "    p.image,\n" +
                    "    oi.id AS order_item_id,\n" +
                    "    oi.quantity,\n" +
                    "    oi.total_price,\n" +
                    "    o.createAt\n" +
                    "FROM order_item oi\n" +
                    "INNER JOIN product p ON p.id = oi.product_id and p.is_deleted  = 0\n" +
                    "INNER JOIN `order` o ON o.id = oi.order_id\n" +
                    "WHERE o.user_id = ?;";
    private static final String SELECT_ORDER = "SELECT o.*, u.fullname\n" +
            "FROM `order` o\n" +
            "LEFT JOIN users u ON u.id = o.user_id AND u.is_deleted = 0;";

    @Override
    public Object add(Order order) throws Exception {
        try (Connection connection = DBConnect.getConnection()) {
            // Thêm hóa đơn
            try (PreparedStatement orderStatement = connection.prepareStatement(INSERT_ORDER_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                orderStatement.setInt(1, order.getUserId());
                orderStatement.setString(2, order.getAddress());
                orderStatement.setString(3, order.getPhone());
                orderStatement.setString(4, order.getStatus());
                orderStatement.setString(5, order.getNote());
                orderStatement.setDouble(6, order.getTotal());
                orderStatement.executeUpdate();
                int orderId;
                try (var generatedKeys = orderStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    } else {
                        return "Creating order failed, no ID obtained.";
                    }
                }
                for (OrderItem item : order.getOrderItems()) {
                    try (PreparedStatement orderItemStatement = connection.prepareStatement(INSERT_ORDER_ITEM_SQL)) {
                        orderItemStatement.setInt(1, orderId);
                        orderItemStatement.setInt(2, item.getProductId());
                        orderItemStatement.setInt(3, item.getQuantity());
                        orderItemStatement.setDouble(4, item.getPrice());
                        orderItemStatement.executeUpdate();
                    }
                }
                return "Success";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<Order> getAllOrderOfUser(int userId) throws Exception {
        List<Order> list = new ArrayList<>();
        int orderId = 0;
        try (Connection connection = DBConnect.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_USER_ID)) {
                statement.setInt(1, userId);
                var rs = statement.executeQuery();
                while (rs.next()) {
                    if (orderId != rs.getInt("order_id")) {
                        Order order = new Order();
                        order.setId(rs.getInt("order_id"));
                        order.setStatus(rs.getString("status"));
                        order.setCreatedAt(rs.getDate("createAt"));
                        orderId = rs.getInt("order_id");
                        list.add(order);
                    }
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImage(rs.getString("image"));
                    OrderItem item = new OrderItem();
                    item.setId(rs.getInt("order_item_id"));
                    item.setPrice(rs.getDouble("total_price"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setProduct(product);
                    list.get(list.size() - 1).getOrderItems().add(item);
                }
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Order> getAll() throws Exception {
        List<Order> list = new ArrayList<>();
        try (Connection connection = DBConnect.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER)) {
                var rs = statement.executeQuery();
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setAddress(rs.getString("address"));
                    order.setPhone(rs.getString("phone"));
                    order.setStatus(rs.getString("status"));
                    order.setNote(rs.getString("note"));
                    order.setTotal(rs.getDouble("total"));
                    order.setCreatedAt(rs.getDate("createAt"));
                    var user  = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setFullName(rs.getString("fullname"));
                    order.setUser(user);
                    list.add(order);
                }
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
}
