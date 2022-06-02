package com.teste.estudo.menuClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.teste.estudo.DAO.CarDAO;
import com.teste.estudo.R;
import com.teste.estudo.entidades.Car;
import com.teste.estudo.utils.BancoDeDados;
import com.teste.estudo.utils.Sessao;

import java.util.ArrayList;
import java.util.List;

public class ViewCarAdapter extends RecyclerView.Adapter<ViewCarAdapter.ItemCar> {

    List<Car> carros;
    LayoutInflater inflater;

    public ViewCarAdapter(Context context, List<Car> carro){
        inflater = LayoutInflater.from(context);
        this.carros = carro;
    }

    @NonNull
    @Override
    public ItemCar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemCar = inflater.inflate(R.layout.view_car_item, parent, false);
        return new ItemCar(itemCar);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCar ItemCar, int position) {
        ItemCar.setPlaca(carros.get(position).placa);
        ItemCar.setModelo(carros.get(position).modelo);
        ItemCar.setMarca(carros.get(position).marca);
    }

    @Override
    public int getItemCount() {
        return carros.size();
    }
    public static class ItemCar extends RecyclerView.ViewHolder{
        TextView placa, modelo, marca;

        public ItemCar(View itemCar) {
            super(itemCar);
            placa = itemCar.findViewById(R.id.placaRv);
            modelo = itemCar.findViewById(R.id.modeloRv);
            marca = itemCar.findViewById(R.id.marcaRv);
        }

        public void setPlaca(String placa) {
            this.placa.setText(placa);
        }

        public void setModelo(String modelo) {
            this.modelo.setText(modelo);
        }

        public void setMarca(String marca) {
            this.marca.setText(marca);
        }
    }
}
