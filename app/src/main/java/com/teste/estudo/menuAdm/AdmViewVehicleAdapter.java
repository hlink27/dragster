package com.teste.estudo.menuAdm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.teste.estudo.DAO.CarDAO;
import com.teste.estudo.R;
import com.teste.estudo.entidades.Car;
import com.teste.estudo.utils.BancoDeDados;
import java.util.List;

public class AdmViewVehicleAdapter extends RecyclerView.Adapter<AdmViewVehicleAdapter.ItemVehicle> {
    List<Car> car;
    LayoutInflater inflater;
    BancoDeDados banco;
    public AdmViewVehicleAdapter(Context context, List<Car> car){
        inflater = LayoutInflater.from(context);
        this.car = car;
    }
    @NonNull
    @Override
    public ItemVehicle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemxml = inflater.inflate(R.layout.view_car_item_adm, parent, false);
        return new ItemVehicle(itemxml);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVehicle ItemVehicle, @SuppressLint("RecyclerView") int position) {
        ItemVehicle.setPlaca(car.get(position).placa);
        ItemVehicle.setModelo(car.get(position).modelo);
        ItemVehicle.setMarca(car.get(position).marca);
        ItemVehicle.setDono(car.get(position).dono);
        ItemVehicle.delCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banco = Room.databaseBuilder(ItemVehicle.placa.getContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
                CarDAO cardao = banco.carDAO();
                cardao.deleteCar(car.get(position).id);
                car.remove(position);
                notifyDataSetChanged();
            }
        });
        ItemVehicle.editCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AdminEditCar.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return car.size();
    }
    public static class ItemVehicle extends RecyclerView.ViewHolder{
        TextView placa, modelo, marca, dono, delCar, editCar;
        public ItemVehicle(View itemxml){
            super(itemxml);
            placa = itemxml.findViewById(R.id.placaRv);
            modelo = itemxml.findViewById(R.id.modeloRv);
            marca = itemxml.findViewById(R.id.marcaRv);
            dono = itemxml.findViewById(R.id.donoRv);
            delCar = itemxml.findViewById(R.id.delCar);
            editCar = itemxml.findViewById(R.id.editCar);
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

        public void setDono(String dono) {
            this.dono.setText(dono);
        }
    }
}
