package me.marttos.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.marttos.helloworld.model.User;
import me.marttos.helloworld.model.helper.UserHelper;

public class NewUserActivity extends AppCompatActivity {

    protected Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        btnSave = (Button) findViewById(R.id.btnSalvar);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUserActivity.this, HomeActivity.class);

                User user = new User();

                user.name = ((EditText) findViewById(R.id.txtNome)).getText().toString();
                user.email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
                user.password = ((EditText) findViewById(R.id.txtSenha)).getText().toString();

                intent.putExtra("email", user.email);

                UserHelper.INSTANCE.add(user.email, user);

                NewUserActivity.this.startActivity(intent);
            }
        });
    }
}
