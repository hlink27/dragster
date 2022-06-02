package com.teste.estudo.menuAdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.teste.estudo.DAO.CarDAO;
import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.R;
import com.teste.estudo.entidades.Car;
import com.teste.estudo.utils.BancoDeDados;

import java.util.ArrayList;

public class AdminCreateCar extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText marca, modelo, ano, placa;
    Button btnCrtCar;
    BancoDeDados banco;
    Car car1 = new Car();
    Spinner spinner;
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_car);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode
        banco = Room.databaseBuilder(getApplicationContext(),BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
        marca = findViewById(R.id.marca);
        modelo = findViewById(R.id.modelo);
        ano = findViewById(R.id.ano);
        placa = findViewById(R.id.placa);
        btnCrtCar = findViewById(R.id.btnCrtCar);

        UserDAO userDAO = banco.userDAO();
        spinner = findViewById(R.id.spinnerCrtCar);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.color_spinner_layout, new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropsown_layout);
        spinnerAdapter.addAll(userDAO.getAllNome());
        spinnerAdapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(spinnerAdapter);

        btnCrtCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
                String marca1 = marca.getText().toString().trim();
                String modelo1 = modelo.getText().toString().trim();
                String ano1 = ano.getText().toString();
                String placa1 = placa.getText().toString().trim();
                car1.marca = marca1;
                car1.modelo = modelo1;
                car1.ano = ano1;
                car1.placa = placa1;
                CarDAO carDAO = banco.carDAO();
                carDAO.insertAll(car1);
                Toast.makeText(AdminCreateCar.this, getText(R.string.carCreated), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        car1.dono = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}