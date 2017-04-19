package me.marttos.helloworld.model;

import android.content.Context;

import java.util.Date;

import me.marttos.helloworld.dao.UserDAO;

public class User
{
    public Long id;
    public String name;
    public String email;
    public String password;
    public Date created;

    public void save(Context context)
    {
        UserDAO dao = new UserDAO(context);

        dao.insert(this);
    }

    public static User find(Context context, String email)
    {
        UserDAO dao = new UserDAO(context);

        return dao.getModel(email);
    }

    public static boolean exists(Context context, String email)
    {
        return find(context, email) != null;
    }
}
