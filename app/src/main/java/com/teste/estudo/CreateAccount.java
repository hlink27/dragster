package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {
    EditText username, email, password, confirmPassword;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        username = (EditText) findViewById(R.id.txtCreateUsername);
        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.createPassword);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        btnCreate = (Button) findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString().trim();
                String e_mail = email.getText().toString().trim();
                String pass_word = password.getText().toString();
                String pass_word2 = confirmPassword.getText().toString();
                if(isValidUsername(user_name)){
                    if(isValidEmail(e_mail)){
                        if(isValidPassword(pass_word)){
                            if(pass_word.equals(pass_word2)){
                                //CREATE ROW ON DATABASE
                            } else {
                                confirmPassword.setError(getText(R.string.passwordNoMatch));
                            }
                        } else {
                            password.setError(getText(R.string.passwordError));
                        }
                    } else {
                        email.setError(getText(R.string.emailError));
                    }
                } else {
                    username.setError(getText(R.string.usernameError));
                }
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isValidUsername(CharSequence target) {
        return(!(target.length() < 3));
    }
    public static boolean isValidPassword(CharSequence target) {
        return(!(target.length() < 6));
    }
}