package com.teste.estudo.menuClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.teste.estudo.R;
import com.teste.estudo.utils.BancoDeDados;

public class StampsActivity extends AppCompatActivity {
    RecyclerView rvLista;
    BancoDeDados banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamps);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode
        rvLista = findViewById(R.id.rvStamps);
        rvLista.setLayoutManager(new GridLayoutManager(this, 3));
        rvLista.setAdapter(new StampsActivityAdapter(this));
    }
}