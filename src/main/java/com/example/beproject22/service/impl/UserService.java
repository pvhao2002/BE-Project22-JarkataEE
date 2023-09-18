package com.example.beproject22.service.impl;


import com.example.beproject22.model.User;
import com.example.beproject22.service.DBConnect;
import com.example.beproject22.service.IUserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public Object register(User user) {
        // to do
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "INSERT INTO users(username, password, fullname, role_id) VALUES(?, ?, ?, ?)";
                ps = cnt.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.setLong(4, user.getRoleId());
                ps.executeUpdate();
            }
            return "success";
        } catch (Exception e) {

            return String.format("failed: %s", e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object update(User user) {
        // to do
        return null;
    }

    @Override
    public void delete(String username) {
        // to do
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM users WHERE is_deleted = 0";
                ps = cnt.prepareStatement(sql);
                var rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFullName(rs.getString("fullname"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setIsDeleted(rs.getBoolean("is_deleted"));
                    users.add(user);
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
        return users;
    }

    @Override
    public User getById(Integer id) {
        User user = null;
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM users WHERE id = ?";
                ps = cnt.prepareStatement(sql);
                ps.setInt(1, id);
                user = getUser(ps);
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
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM users WHERE username = ?";
                ps = cnt.prepareStatement(sql);
                ps.setString(1, username);
                user = getUser(ps);
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
        return user;
    }

    private User getUser(PreparedStatement ps) {
        User user = null;
        try {
            var rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullname"));
                user.setRoleId(rs.getInt("role_id"));
                user.setIsDeleted(rs.getBoolean("is_deleted"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
