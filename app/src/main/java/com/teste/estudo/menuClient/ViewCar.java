package com.teste.estudo.menuClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.teste.estudo.DAO.CarDAO;
import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.R;
import com.teste.estudo.utils.BancoDeDados;
import com.teste.estudo.utils.Sessao;

import java.util.Arrays;

public class ViewCar extends AppCompatActivity {

    RecyclerView recyclerViewCar;
    BancoDeDados banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode
        banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
        CarDAO cardao = banco.carDAO();

        recyclerViewCar = findViewById(R.id.recyclerViewCar);
        recyclerViewCar.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCar.setAdapter(new ViewCarAdapter(this, Arrays.asList(cardao.buscaDono(Sessao.getInstance().userLogado.nome))));
    }
}