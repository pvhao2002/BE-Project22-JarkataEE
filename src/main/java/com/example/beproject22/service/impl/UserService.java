package com.example.beproject22.service.impl;


import com.example.beproject22.model.Role;
import com.example.beproject22.model.User;
import com.example.beproject22.service.DBConnect;
import com.example.beproject22.service.IUserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    private final String SUCCESS = "Success";
    private final String ERROR = "Error";

    @Override
    public Object login(String username, String password) {
        var user = getByUsername(username);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
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
    public Object update(User user) {
        // to do
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "UPDATE users SET password = ?, fullname = ?, role_id = ? WHERE username = ?";
                ps = cnt.prepareStatement(sql);
                ps.setString(1, user.getPassword());
                ps.setString(2, user.getFullName());
                ps.setLong(3, user.getRoleId());
                ps.setString(4, user.getUsername());
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
    public Object delete(String username) {
        // to do
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "UPDATE users SET is_deleted = 1 WHERE username = ?";
                ps = cnt.prepareStatement(sql);
                ps.setString(1, username);
                ps.executeUpdate();
            }
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
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
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        try (Connection cnt = DBConnect.getConnection()) {
            if (cnt != null) {
                String sql = "SELECT * FROM users INNER JOIN role ON users.role_id = role.id";
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
                    Role role = new Role();
                    role.setId(rs.getInt("role.id"));
                    role.setName(rs.getString("role.name"));
                    user.setRole(role);
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
                String sql = "SELECT * FROM users WHERE id = ? AND is_deleted = 0";
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
                String sql = "SELECT * FROM users WHERE username = ? AND is_deleted = 0";
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
