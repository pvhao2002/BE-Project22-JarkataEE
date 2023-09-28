package com.example.beproject22.service.impl;

import com.example.beproject22.model.Product;
import com.example.beproject22.model.Role;
import com.example.beproject22.model.User;
import com.example.beproject22.service.DBConnect;
import com.example.beproject22.service.IProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private final String SUCCESS = "Success";
    private final String ERROR = "Error";

    @Override
    public List<Product> getAll() throws Exception {
        List<Product> list = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM product;";
                ps = cnt.prepareStatement(sql);
                var rs = ps.executeQuery();
                while (rs.next()) {
                    Product item = new Product();
                    item.setId(rs.getInt("id"));
                    item.setProductName(rs.getString("product_name"));
                    item.setDescription(rs.getString("description"));
                    item.setCategory(rs.getString("category"));
                    item.setImage(rs.getString("image"));
                    item.setAuthor(rs.getString("author"));
                    item.setPrice(rs.getDouble("price"));
                    item.setIsDeleted(rs.getBoolean("is_deleted"));
                    list.add(item);
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
        return list;
    }

    @Override
    public Object add(Product product) throws Exception {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "INSERT INTO product (product_name, description, category, author, price, image) VALUES(?, ?, ?, ?, ?, ?)";
                ps = cnt.prepareStatement(sql);
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getDescription());
                ps.setString(3, product.getCategory());
                ps.setString(4, product.getAuthor());
                ps.setDouble(5, product.getPrice());
                ps.setString(6, product.getImage());
                ps.executeUpdate();
            }
            return SUCCESS;
        } catch (Exception e) {
            return String.format("%s: %s", ERROR, e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object update(Product product) throws Exception {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "UPDATE product SET product_name = ?, description = ?, category = ?, author = ?, price = ?, image = ?, is_deleted = ? WHERE id = ?";
                ps = cnt.prepareStatement(sql);
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getDescription());
                ps.setString(3, product.getCategory());
                ps.setString(4, product.getAuthor());
                ps.setDouble(5, product.getPrice());
                ps.setString(6, product.getImage());
                ps.setBoolean(7, product.getIsDeleted());
                ps.setInt(8, product.getId());
                ps.executeUpdate();
            }
            return SUCCESS;
        } catch (Exception e) {
            return String.format("%s: %s", ERROR, e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object delete(int id) throws Exception {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "UPDATE product SET is_deleted = 1 WHERE id = ?";
                ps = cnt.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            return SUCCESS;
        } catch (Exception e) {
            return String.format("%s: %s", ERROR, e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Product getById(int id) throws Exception {
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM product WHERE id = ?";
                ps = cnt.prepareStatement(sql);
                ps.setInt(1, id);
                var rs = ps.executeQuery();
                if (rs.next()) {
                    Product item = new Product();
                    item.setId(rs.getInt("id"));
                    item.setProductName(rs.getString("product_name"));
                    item.setDescription(rs.getString("description"));
                    item.setCategory(rs.getString("category"));
                    item.setImage(rs.getString("image"));
                    item.setAuthor(rs.getString("author"));
                    item.setPrice(rs.getDouble("price"));
                    item.setIsDeleted(rs.getBoolean("is_deleted"));
                    return item;
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
        return null;
    }

    @Override
    public List<Product> getNewProduct() throws Exception {
        // get top 12 new product
        List<Product> list = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM product WHERE is_deleted = 0 ORDER BY id DESC LIMIT 12;";
                ps = cnt.prepareStatement(sql);
                var rs = ps.executeQuery();
                while (rs.next()) {
                    Product item = new Product();
                    item.setId(rs.getInt("id"));
                    item.setProductName(rs.getString("product_name"));
                    item.setDescription(rs.getString("description"));
                    item.setCategory(rs.getString("category"));
                    item.setImage(rs.getString("image"));
                    item.setAuthor(rs.getString("author"));
                    item.setPrice(rs.getDouble("price"));
                    item.setIsDeleted(rs.getBoolean("is_deleted"));
                    list.add(item);
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
        return list;
    }
}
