package me.marttos.helloworld.model.helper;

import java.util.HashMap;
import java.util.LinkedHashMap;

import me.marttos.helloworld.model.User;

public enum UserHelper {
    INSTANCE;

    HashMap<String, User> users = new LinkedHashMap<>();

    public User find(String email) {
        return users.get(email);
    }

    public void add(User user) {
        users.put(user.email, user);
    }

    public User getUserByEmail(String email)
    {
        return users.get(email);
    }
}
