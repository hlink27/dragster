package com.teste.estudo.menuAdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.teste.estudo.DAO.CarDAO;
import com.teste.estudo.R;
import com.teste.estudo.utils.BancoDeDados;

public class AdmViewVehicle extends AppCompatActivity {

    FloatingActionButton addVehicle;
    RecyclerView recyclerViewVehicle;
    BancoDeDados banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_view_vehicle);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode
        banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
        CarDAO cardao = banco.carDAO();

        addVehicle = findViewById(R.id.addVehicle);
        recyclerViewVehicle = findViewById(R.id.recyclerViewVehicle);
        recyclerViewVehicle.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewVehicle.setAdapter(new AdmViewVehicleAdapter(this, cardao.getAll()));


        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmViewVehicle.this, AdminCreateCar.class);
                startActivity(intent);
            }
        });
    }
}