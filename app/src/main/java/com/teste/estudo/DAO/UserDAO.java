package com.teste.estudo.DAO;

import androidx.room.*;
import com.teste.estudo.entidades.User;
import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    List<Long> insertAll(User... users);

    @Query("SELECT * FROM user WHERE nome = :username AND senha = :password")
    public User[] validateLogin(String username, String password);

    //Admin Check
    @Query("SELECT * FROM user WHERE nome = :username AND :admin")
    public User[] validateAdmin(String username, boolean admin);
}
