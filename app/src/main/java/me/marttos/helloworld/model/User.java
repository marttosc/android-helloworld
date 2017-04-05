package me.marttos.helloworld.model;

import me.marttos.helloworld.model.helper.UserHelper;

public class User
{
    public String name;
    public String email;
    public String password;

    public static User login(String email, String password)
    {
        User user = UserHelper.INSTANCE.getUserByEmail(email);

        if (user != null)
        {
            if (user.password.contentEquals(password))
            {
                return user;
            }
        }

        return null;
    }

    public void save()
    {
        UserHelper.INSTANCE.add(this);
    }
}
