package com.example.beproject22.service;

import com.example.beproject22.model.User;

import java.util.List;

public interface IUserService {
    public boolean login(String username, String password);

    public Object register(User user);

    public Object update(User user);

    public void delete(String username);

    public List<User> getAll();

    public User getById(Integer id);

    public User getByUsername(String username);
}
