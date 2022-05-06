package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        EditText username, password;
        Button btnLogin;

        //Find screen views
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogIn);

        //Login Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsername = username.getText().toString();
                String loginPassword = password.getText().toString();
                if(loginUsername.equals("admin") && loginPassword.equals("admin")){
                    Toast.makeText(MainActivity.this, "passou", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "não passou", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}