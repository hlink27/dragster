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
    @ColumnInfo
    public TipoUser tipoUser;

    public enum TipoUser{
        ADMIN(0), NORMAL(1), FIDELIZADO(2);
        private int valor;
        private TipoUser(int valor){
            this.valor = valor;
        }
    }
}