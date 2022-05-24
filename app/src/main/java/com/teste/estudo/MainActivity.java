package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.entidades.User;
import com.teste.estudo.utils.BancoDeDados;
import com.teste.estudo.utils.Sessao;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    TextView usernameShow;
    Button btnLogin;
    TextView crtAccount, textViewAdmin;
    BancoDeDados banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        //Find screen views
        username = (EditText) findViewById(R.id.username);
        usernameShow = (TextView) findViewById(R.id.usernameShow);
        password = (EditText) findViewById(R.id.password);
        crtAccount = (TextView) findViewById(R.id.txtCreateAccount);
        btnLogin = (Button) findViewById(R.id.btnLogIn);
        textViewAdmin = (TextView) findViewById(R.id.textViewAdmin);

        textViewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start Admin user
                //REMOVER APÓS PROTÓTIPO APRESENTADO
                banco = Room.databaseBuilder(getApplicationContext(),BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
                User user1 = new User();
                user1.nome = "admin";
                user1.email = "admin@gmail.com";
                user1.senha = "admin";
                user1.stamps = 0;
                user1.tipoUser = User.TipoUser.ADMIN;
                UserDAO userDAO = banco.userDAO();
                userDAO.insertAll(user1);
                Toast.makeText(MainActivity.this, "admin criado", Toast.LENGTH_SHORT).show();
            }
        });

        //Login Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInput()) {
                    String loginUsername = username.getText().toString();
                    String loginPassword = password.getText().toString();
                    if (login(loginUsername, loginPassword)) {
                        //usernameShow.setText(loginUsername);
                        Intent intent = new Intent(MainActivity.this, MainMenu.class);
                        startActivity(intent);
                        finish();
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
    public boolean login(String username, String password){
        banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
        UserDAO userDAO = banco.userDAO();
        User[] users = userDAO.validateLogin(username, password);
        if(users.length == 0)return false;
        //salva na sessao o usuario logado
        Sessao.getInstance().setUserLogado(users[0]);
        return true;
    }


}