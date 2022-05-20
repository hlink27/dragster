package com.teste.estudo.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.teste.estudo.DAO.CarDAO;
import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.entidades.Car;
import com.teste.estudo.entidades.User;

@Database(entities = {User.class, Car.class}, version= 1)
public abstract class BancoDeDados extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract CarDAO carDAO();

}