package me.marttos.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import me.marttos.helloworld.model.User;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        prefs = getApplicationContext().getSharedPreferences(
                "session", Context.MODE_PRIVATE
        );

        boolean logged = prefs.getBoolean("logged", false);
        String email_logged = prefs.getString("email", "");

        User user = User.find(getApplicationContext(), email_logged);

        if (email_logged.contentEquals("") || !logged || user == null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            resetSession();

            startActivity(intent);
        }
        else
        {
            ((TextView) findViewById(R.id.lblHomeNome)).setText(user.name);
        }
    }

    private void resetSession()
    {
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean("logged", false);
        editor.putString("email", null);
        editor.commit();
    }
}
