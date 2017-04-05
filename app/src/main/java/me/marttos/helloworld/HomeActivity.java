package me.marttos.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.marttos.helloworld.model.User;
import me.marttos.helloworld.model.helper.UserHelper;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String email_logged = getIntent().getStringExtra("email");

        User user = UserHelper.INSTANCE.find(email_logged);

        if (user == null)
        {
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
        else
        {
            ((TextView) findViewById(R.id.lblHomeNome)).setText(user.name);
        }
    }
}
