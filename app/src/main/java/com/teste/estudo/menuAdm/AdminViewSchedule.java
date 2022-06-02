package com.teste.estudo.menuAdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.teste.estudo.DAO.ScheduleDAO;
import com.teste.estudo.R;
import com.teste.estudo.utils.BancoDeDados;

public class AdminViewSchedule extends AppCompatActivity {

    RecyclerView recycleViewSchedule;
    BancoDeDados banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_schedule);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode
        banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
        ScheduleDAO scheduleDAO = banco.scheduleDAO();

        recycleViewSchedule = findViewById(R.id.recyclerViewSchedule);
        recycleViewSchedule.setLayoutManager(new LinearLayoutManager(this));
        recycleViewSchedule.setAdapter(new AdminViewScheduleAdapter(this, scheduleDAO.getAll()));
    }
}