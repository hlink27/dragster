package com.teste.estudo.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedule")
public class Schedule {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String date;
    @ColumnInfo
    public String time;
    @ColumnInfo
    public String description;
    @ColumnInfo
    public String user;
    @ColumnInfo
    public boolean promocao;
}
