package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.entidades.User;
import com.teste.estudo.utils.BancoDeDados;

public class MainMenu extends AppCompatActivity {
    Button repairBtn, vehicleBtn, contactBtn, stampBtn, profileBtn, admBtn;
    EditText username;
    TextView usernameShow;
    BancoDeDados banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        repairBtn = (Button) findViewById(R.id.repairButton);
        vehicleBtn = (Button) findViewById(R.id.vehicleButton);
        contactBtn = (Button) findViewById(R.id.contacButton);
        stampBtn = (Button) findViewById(R.id.stampButton);
        profileBtn = (Button) findViewById(R.id.profileButton);
        admBtn = (Button) findViewById(R.id.adminButton);

        repairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        vehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, ViewCar.class);
                startActivity(intent);
            }
        });

        admBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // if (validateAdmin())
                /*Intent intent = new Intent(MainMenu.this,MainAdminConfig.class);
                    startActivity(intent);*/

            }

        });

        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        /*public boolean isAdm(String lgnUsername){
            banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
            UserDAO userDAO = banco.userDAO();
            User[] users = userDAO.validateAdmin(lgnUsername, true);
            if(users.length == 0){
                return false;
            } else {
                return true;
            }
        }*/
       // public boolean admin( String username){}
    }
}