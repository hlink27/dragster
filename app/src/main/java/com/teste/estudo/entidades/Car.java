package com.teste.estudo.entidades;

import androidx.room.*;

@Entity(tableName = "car")
public class Car {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String placa;
    @ColumnInfo
    public String marca;
    @ColumnInfo
    public String modelo;
    @ColumnInfo
    public int ano;
    @ColumnInfo
    public String id_dono;
}
