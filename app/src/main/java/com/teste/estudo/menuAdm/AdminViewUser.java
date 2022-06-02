package com.teste.estudo.menuAdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.R;
import com.teste.estudo.entidades.User;
import com.teste.estudo.utils.BancoDeDados;

public class AdminViewUser extends AppCompatActivity{

    FloatingActionButton addUser, editUserLvl;
    RecyclerView lista;
    BancoDeDados banco;
    TextView btnDeleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode
        banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
        UserDAO userdao = banco.userDAO();

        addUser = findViewById(R.id.addUser);
        lista = findViewById(R.id.recyclerViewUser);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);
        editUserLvl = findViewById(R.id.editUserLvl);

        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(new AdminViewUserAdapter(this, userdao.getAll()));

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminViewUser.this, AdminCreateAcc.class);
                startActivity(intent);
            }
        });
        editUserLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminViewUser.this, AdminEditUserLvl.class);
                startActivity(intent);
            }
        });

    }
}