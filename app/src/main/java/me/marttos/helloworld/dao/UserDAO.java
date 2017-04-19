package me.marttos.helloworld.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

import me.marttos.helloworld.model.User;

public class UserDAO implements DAO<User>
{
    public static final String TABLE_NAME = "users";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CREATED = "created";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    " _id INTEGER PRIMARY KEY," +
                    COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                    COLUMN_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    COLUMN_CREATED + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private Database database;

    public UserDAO(Context context)
    {
        this.database = new Database(context);
    }

    private Database db()
    {
        return this.database;
    }

    public boolean insert(User model)
    {
        SQLiteDatabase db = db().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, model.name);
        values.put(COLUMN_EMAIL, model.email);
        values.put(COLUMN_PASSWORD, model.password);
        values.put(COLUMN_CREATED, model.created.getTime());

        model.id = db.insert(TABLE_NAME, null, values);

        return model.id != -1;
    }

    public void update(User model)
    {
        SQLiteDatabase db = db().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, model.name);
        values.put(COLUMN_EMAIL, model.email);
        values.put(COLUMN_PASSWORD, model.password);

        db.update(TABLE_NAME, values, "_id = " + model.id, null);
    }

    public void delete(User model)
    {
        SQLiteDatabase db = db().getWritableDatabase();

        model.id = (long) db.delete(TABLE_NAME, "_id = ?", new String[] { "" + model.id });
    }

    public User getModel(Long id)
    {
        SQLiteDatabase db = db().getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE _id = " + id, null);

        if (c == null) {
            return null;
        }

        c.moveToFirst();
        db.close();

        return toModel(c);
    }

    public User getModel(String email)
    {
        SQLiteDatabase db = db().getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = \"" + email + "\"", null);

        if (c == null) {
            return null;
        }

        c.moveToFirst();

        db.close();

        return toModel(c);
    }

    public User toModel(Cursor c)
    {
        if (c.getCount() == 0)
        {
            return null;
        }

        User model = new User();

        model.id = c.getLong(0);
        model.name = c.getString(1);
        model.email = c.getString(2);
        model.password = c.getString(3);
        model.created = null;

        return model;
    }
}
