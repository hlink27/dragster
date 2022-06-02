package com.teste.estudo.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.teste.estudo.entidades.Schedule;

import java.util.List;

@Dao
public interface ScheduleDAO {
    @Insert
    List<Long> insertAll(Schedule... schedules);

    @Query("SELECT * FROM schedule")
    List<Schedule> getAll();
}
