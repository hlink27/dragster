package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.teste.estudo.DAO.ScheduleDAO;
import com.teste.estudo.entidades.Schedule;
import com.teste.estudo.utils.BancoDeDados;
import com.teste.estudo.utils.Sessao;

public class ScheduleActivity extends AppCompatActivity {

    BancoDeDados banco;
    EditText editTextDate, editTextTime;
    Button btnRepain;
    TextInputEditText textDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        textDescricao = findViewById(R.id.textInput);
        btnRepain = findViewById(R.id.btnRepair);

        btnRepain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
                String date = editTextDate.getText().toString().trim();
                String time = editTextTime.getText().toString().trim();
                String text = textDescricao.getText().toString();
                Schedule schedule = new Schedule();
                schedule.date = date;
                schedule.time = time;
                schedule.description = text;
                schedule.user = Sessao.getInstance().getUserLogado().nome;
                if (Sessao.getInstance().getUserLogado().stamps == 10){
                    schedule.promocao = true;
                    //zerar selos do usuário

                } else {
                    schedule.promocao = false;
                    //adicionar mais um sela a coleção do usuario
                }
                ScheduleDAO scheduledao = banco.scheduleDAO();
                scheduledao.insertAll(schedule);
                Toast.makeText(ScheduleActivity.this, getText(R.string.scheduleComplete), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}