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

    @Query("DELETE FROM user WHERE id = :id")
    void deleteUser(int id);

    @Query("UPDATE user SET senha = :senha WHERE nome = :nome")
    void updateSenha(String senha, String nome);

    @Query("UPDATE user SET tipoUser = :tipoUser WHERE nome = :nome")
    void updateLevel(String tipoUser, String nome);
}
