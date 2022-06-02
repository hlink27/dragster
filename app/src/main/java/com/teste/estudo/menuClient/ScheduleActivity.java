package com.teste.estudo.menuClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.teste.estudo.DAO.ScheduleDAO;
import com.teste.estudo.R;
import com.teste.estudo.entidades.Schedule;
import com.teste.estudo.entidades.User;
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
                schedule.tipoUser = Sessao.getInstance().userLogado.tipoUser;
                schedule.user = Sessao.getInstance().getUserLogado().nome;
                if (Sessao.getInstance().getUserLogado().stamps == 10){
                    schedule.promocao = true;
                    //Sessao.getInstance().getUserLogado().stamps = 0;

                } else {
                    schedule.promocao = false;
                    //Sessao.getInstance().getUserLogado().stamps += 1;
                }
                ScheduleDAO scheduledao = banco.scheduleDAO();
                scheduledao.insertAll(schedule);
                Toast.makeText(ScheduleActivity.this, getText(R.string.scheduleComplete), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}