package com.teste.estudo.entidades;

import androidx.room.*;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String nome;
    @ColumnInfo
    public String email;
    @ColumnInfo
    public String senha;
    @ColumnInfo
    public int stamps;
    @ColumnInfo
    public boolean admin;
}