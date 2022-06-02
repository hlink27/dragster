package com.teste.estudo.menuClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.R;
import com.teste.estudo.entidades.User;
import com.teste.estudo.utils.BancoDeDados;
import com.teste.estudo.utils.Sessao;

public class EditActivity extends AppCompatActivity {

    EditText oldPassword, newPassword, newPassword2;
    Button btnNewPassword;
    BancoDeDados banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        newPassword2 = findViewById(R.id.newPassword2);
        btnNewPassword = findViewById(R.id.btnNewPassword);

        btnNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banco = Room.databaseBuilder(getApplicationContext(),BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
                String passOld = oldPassword.getText().toString();
                String username = Sessao.getInstance().userLogado.nome;
                String newPasswd = newPassword.getText().toString();
                String newPasswd2 = newPassword2.getText().toString();
                if(login(username, passOld)){
                    if(newPasswd.equals(newPasswd2)){
                        UserDAO userdao = banco.userDAO();
                        userdao.updateSenha(newPasswd, username);
                        Toast.makeText(EditActivity.this, R.string.passwordChanged, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        newPassword2.setError(getText(R.string.passwordNoMatch));
                    }
                } else {
                    oldPassword.setError(getText(R.string.incorrectInput));
                }
            }
        });
    }
    public static boolean isValidPassword(String target) {
        return(!(target.length() < 4));
    }
    public boolean login(String username, String password){
        banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
        UserDAO userDAO = banco.userDAO();
        User[] users = userDAO.validateLogin(username, password);
        if(users.length == 0)return false;
        return true;
    }
}