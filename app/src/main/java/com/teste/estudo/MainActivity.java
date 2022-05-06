package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;
    TextView crtAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        //Find screen views
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        crtAccount = (TextView) findViewById(R.id.txtCreateAccount);
        btnLogin = (Button) findViewById(R.id.btnLogIn);

        //Login Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = checkInput();
                if (check) {
                    String loginUsername = username.getText().toString();
                    String loginPassword = password.getText().toString();
                    if (loginUsername.equals("admin") && loginPassword.equals("admin")) {
                        Toast.makeText(MainActivity.this, getText(R.string.successLogIn) + loginUsername, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainMenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, getText(R.string.incorrectInput), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, getText(R.string.noLogInInput), Toast.LENGTH_SHORT).show();
                }
            }
        });

        crtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }

    //Validation
    private boolean checkInput() {
        if (username.length() < 3) {
            username.setError(getText(R.string.errorInputLogin));
            return false;
        }
        if (password.length() < 3) {
            password.setError(getText(R.string.errorPasswordLogin));
            return false;
        } else {
            return true;
        }
    }
}