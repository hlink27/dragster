package com.teste.estudo.menuAdm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.teste.estudo.DAO.CarDAO;
import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.R;
import com.teste.estudo.entidades.User;
import com.teste.estudo.utils.BancoDeDados;

import java.util.ArrayList;
import java.util.List;

public class AdminViewUserAdapter extends RecyclerView.Adapter<AdminViewUserAdapter.ItemLista> {
    List<User> user;
    LayoutInflater inflater;
    BancoDeDados banco;
    public AdminViewUserAdapter(Context context, List<User> user){
        inflater = LayoutInflater.from(context);
        this.user = user;
    }
    @NonNull
    @Override
    public ItemLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemXML = inflater.inflate(R.layout.admin_view_user_recycler, parent, false);
        return new ItemLista(itemXML);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemLista ItemLista, @SuppressLint("RecyclerView") int position) {
        ItemLista.setUsername(user.get(position).nome);
        ItemLista.setEmail(user.get(position).email);
        ItemLista.setLvlUser(String.valueOf(user.get(position).tipoUser));
        ItemLista.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banco = Room.databaseBuilder(ItemLista.username.getContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
                UserDAO userdao = banco.userDAO();
                CarDAO cardao = banco.carDAO();
                userdao.deleteUser(user.get(position).getId());
                cardao.deleteAllVehiclesOf(user.get(position).nome);
                user.remove(position);
                notifyDataSetChanged();
            }
        });

    }
    @Override
    public int getItemCount() {
        return user.size();
    }

    public static class ItemLista extends RecyclerView.ViewHolder{
        TextView username, email, btnDelete, lvlUser;

        public ItemLista(View itemXML) {
            super(itemXML);
            username = itemXML.findViewById(R.id.username_recycle);
            email = itemXML.findViewById(R.id.email_recycle);
            btnDelete = itemXML.findViewById(R.id.btnDeleteUser);
            lvlUser = itemXML.findViewById(R.id.lvlUser);
        }
        public void setUsername(String username) { this.username.setText(username); }
        public void setEmail(String email) { this.email.setText(email);}
        public void setLvlUser(String lvlUser) { this.lvlUser.setText(lvlUser);}
    }
}
