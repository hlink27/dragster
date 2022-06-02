package com.teste.estudo.menuClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.teste.estudo.R;
import com.teste.estudo.entidades.User;
import com.teste.estudo.menuAdm.AdminMenu;
import com.teste.estudo.utils.BancoDeDados;
import com.teste.estudo.utils.Sessao;

public class MainMenu extends AppCompatActivity {
    Button repairBtn, vehicleBtn, contactBtn, stampBtn, profileBtn, admBtn;
    TextView usernameShow, accShow;
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
        usernameShow = findViewById(R.id.usernameShow);
        accShow = findViewById(R.id.accShow);

        usernameShow.setText(getText(R.string.welcome) + " " + Sessao.getInstance().getUserLogado().nome);
        accShow.setText("" + Sessao.getInstance().getUserLogado().tipoUser);

        if(Sessao.getInstance().getUserLogado().tipoUser == User.TipoUser.FIDELIZADO){
            stampBtn.setEnabled(false); //Desabilita o botão caso usuário for fidelizado
        }
        if(Sessao.getInstance().getUserLogado().tipoUser != User.TipoUser.ADMIN){
            admBtn.setEnabled(false);
        }

        repairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        vehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, ViewCar.class);
                startActivity(intent);
            }
        });

        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        stampBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, StampsActivity.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, EditActivity.class);
                startActivity(intent);
            }
        });

        admBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, AdminMenu.class);
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