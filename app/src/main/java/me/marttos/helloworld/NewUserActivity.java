package me.marttos.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import me.marttos.helloworld.model.User;

public class NewUserActivity extends AppCompatActivity {

    protected Button btnSave;
    protected SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        btnSave = (Button) findViewById(R.id.btnSalvar);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUserActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                User user = new User();

                user.name = ((EditText) findViewById(R.id.txtNome)).getText().toString();
                user.email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
                user.password = ((EditText) findViewById(R.id.txtSenha)).getText().toString();
                user.created = new Date();

                if (User.exists(getApplicationContext(), user.email))
                {
                    Toast.makeText(NewUserActivity.this, "E-mail já cadastrado.", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("email", user.email);

                    user.save(getApplicationContext());

                    saveSession(user.email);

                    NewUserActivity.this.startActivity(intent);
                }
            }
        });

        sharedPrefs = getApplicationContext().getSharedPreferences(
                "session", Context.MODE_PRIVATE);
    }

    private void saveSession(String email)
    {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putBoolean("logged", true);
        editor.putString("email", email);
        editor.commit();
    }
}
