package com.teste.estudo.DAO;

import androidx.room.*;

import com.teste.estudo.entidades.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    List<Long> insertAll(User... users);


}
