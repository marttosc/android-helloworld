package me.marttos.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.marttos.helloworld.model.helper.UserHelper;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        String email_logged = intent.getStringExtra("email");

        ((TextView) findViewById(R.id.lblHomeNome)).setText(UserHelper.INSTANCE.find(email_logged).name);
    }
}
