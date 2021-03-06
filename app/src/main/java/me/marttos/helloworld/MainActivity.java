package me.marttos.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.marttos.helloworld.model.User;

public class MainActivity extends AppCompatActivity {

    protected Button btnLogin;
    protected Button btnRegister;
    protected SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        btnRegister = (Button) findViewById(R.id.btnRegistrar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });

        sharedPrefs = getApplicationContext().getSharedPreferences(
                "session", Context.MODE_PRIVATE);

        if (checkSession())
        {
            goHome();
        }
    }

    private void doLogin()
    {
        String email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
        String senha = ((EditText) findViewById(R.id.txtSenha)).getText().toString();

        User user = User.find(getApplicationContext(), email);

        if (user == null) {
            Toast.makeText(MainActivity.this, "Usuário não encontrado.", Toast.LENGTH_SHORT).show();
        } else if (!user.password.contentEquals(senha)) {
            Toast.makeText(MainActivity.this, "Autenticação inválida.", Toast.LENGTH_SHORT).show();
        } else {
            saveSession(email);
            goHome();
        }
    }

    private void saveSession(String email)
    {
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putBoolean("logged", true);
        editor.putString("email", email);
        editor.commit();
    }

    private void goHome()
    {
        String email = sharedPrefs.getString("email", "");

        if (!email.contentEquals(""))
        {
            Intent intent = new Intent(this, HomeActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("email", email);

            startActivity(intent);
        }
    }

    private void goRegister()
    {
        Intent intent = new Intent(this, NewUserActivity.class);

        startActivity(intent);
    }

    private boolean checkSession()
    {
        return sharedPrefs.getBoolean("logged", false);
    }
}
