package com.teste.estudo.DAO;

import androidx.room.*;
import com.teste.estudo.entidades.Car;
import java.util.List;

@Dao
public interface CarDAO {
    @Insert
    List<Long> insertAll(Car... cars);

    @Query("SELECT * FROM car WHERE dono = :dono")
    public Car[] buscaDono(String dono);

    @Query("SELECT * FROM car")
    List<Car> getAll();

    @Query("DELETE FROM car WHERE dono = :dono")
    void deleteAllVehiclesOf(String dono);

    @Query("DELETE FROM car WHERE id = :id")
    void deleteCar(int id);
}
