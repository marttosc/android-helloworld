package me.marttos.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.marttos.helloworld.model.User;
import me.marttos.helloworld.model.helper.UserHelper;

public class MainActivity extends AppCompatActivity {

    protected Button btnLogin;
    protected Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
                String senha = ((EditText) findViewById(R.id.txtSenha)).getText().toString();

                User user = UserHelper.INSTANCE.find(email);

                if (user != null) {
                    if (!user.password.equals(senha)) {
                        Toast.makeText(MainActivity.this, "Autenticação inválida.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);

                        intent.putExtra("email", user.email);

                        MainActivity.this.startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Email não cadastrado.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister = (Button) findViewById(R.id.btnRegistrar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewUserActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });
    }
}
