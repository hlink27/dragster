package com.teste.estudo.entidades;

import androidx.room.*;

import java.util.ArrayList;

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
    public TipoUser tipoUser;

    public enum TipoUser{
        NORMAL(0), ADMIN(1), FIDELIZADO(2);
        private int valor;
        private TipoUser(int valor){
            this.valor = valor;
        }
    }

    public int getId() {
        return id;
    }
}