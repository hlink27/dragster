package com.teste.estudo.menuClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teste.estudo.R;
import com.teste.estudo.entidades.User;
import com.teste.estudo.menuAdm.AdminViewUserAdapter;
import com.teste.estudo.utils.Sessao;

public class StampsActivityAdapter extends RecyclerView.Adapter<StampsActivityAdapter.ItemStamp> {

    int num_stamps;
    LayoutInflater inflater;
    public StampsActivityAdapter(Context context){
        inflater = LayoutInflater.from(context);
        num_stamps = Sessao.getInstance().getUserLogado().stamps;
    }

    @NonNull
    @Override
    public ItemStamp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemStamp = inflater.inflate(R.layout.list_stamp, parent, false);
        return new StampsActivityAdapter.ItemStamp(itemStamp);
    }
    @Override
    public void onBindViewHolder(@NonNull StampsActivityAdapter.ItemStamp holder, int position) {}

    @Override
    public int getItemCount() {
        return num_stamps;
    }
    public static class ItemStamp extends RecyclerView.ViewHolder{
        public ItemStamp(View itemStamp) {
            super(itemStamp);
        }
    }
}
