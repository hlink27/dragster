package com.teste.estudo.DAO;

import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.*;
import com.teste.estudo.entidades.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    List<Long> insertAll(User... users);

    @Query("SELECT * FROM user WHERE nome = :username AND senha = :password")
    public User[] validateLogin(String username, String password);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT nome FROM user")
    List<String> getAllNome();

}
