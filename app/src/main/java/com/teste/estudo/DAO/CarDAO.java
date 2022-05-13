package com.teste.estudo.DAO;

import androidx.room.*;
import com.teste.estudo.entidades.Car;
import java.util.List;

@Dao
public interface CarDAO {
    @Insert
    List<Long> insertAll(Car... cars);
}
