package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.*;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.entidades.User;
import com.teste.estudo.utils.BancoDeDados;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    BancoDeDados banco;
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
                //conexão com o banco de dados:
                banco = Room.databaseBuilder(getApplicationContext(),BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();

                String user_name = username.getText().toString().trim();
                String e_mail = email.getText().toString().trim();
                String pass_word = password.getText().toString();
                String pass_word2 = confirmPassword.getText().toString();
                if(isValidUsername(user_name)){
                    if(isValidEmail(e_mail)){
                        if(isValidPassword(pass_word)){
                            if(pass_word.equals(pass_word2)){
                                //Toast.makeText(CreateAccount.this, getText(R.string.accountCreated), Toast.LENGTH_SHORT).show();
                                User user1 = new User();
                                user1.nome = user_name;
                                user1.email = e_mail;
                                user1.senha = pass_word;
                                UserDAO userDAO = banco.userDAO();
                                userDAO.insertAll(user1);
                                //Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                                //startActivity(intent);
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
    private boolean isValidEmail(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    public static boolean isValidUsername(String target) {
        return(!(target.length() < 3));
    }
    public static boolean isValidPassword(String target) {
        return(!(target.length() < 4));
    }
}