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

import com.teste.estudo.DAO.ScheduleDAO;
import com.teste.estudo.R;
import com.teste.estudo.entidades.Schedule;
import com.teste.estudo.utils.BancoDeDados;

import java.util.List;

public class AdminViewScheduleAdapter extends RecyclerView.Adapter<AdminViewScheduleAdapter.ItemSchedule> {

    List<Schedule> schedules;
    LayoutInflater inflater;
    BancoDeDados banco;
    public AdminViewScheduleAdapter(Context context, List<Schedule> schedules){
        inflater = LayoutInflater.from(context);
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ItemSchedule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemSchedule = inflater.inflate(R.layout.schedule_recycle, parent, false);
        return new ItemSchedule(itemSchedule);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSchedule ItemSchedule, @SuppressLint("RecyclerView") int position) {
        ItemSchedule.setSchedule_username(schedules.get(position).user);
        ItemSchedule.setSchedule_date(schedules.get(position).date);
        ItemSchedule.setSchedule_hour(schedules.get(position).time);
        ItemSchedule.setSchedule_description(schedules.get(position).description);
        ItemSchedule.setSchedule_userLvl(String.valueOf(schedules.get(position).tipoUser));
        if(schedules.get(position).promocao){
            ItemSchedule.setSchedule_promocao("10 SELOS");
        } else {
            ItemSchedule.setSchedule_promocao("Sem promoção");
        }
        ItemSchedule.schedule_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banco = Room.databaseBuilder(ItemSchedule.schedule_username.getContext(),
                        BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();
                ScheduleDAO scheduleDAO = banco.scheduleDAO();
                scheduleDAO.deleteSchedule(schedules.get(position).id);
                schedules.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public static class ItemSchedule extends RecyclerView.ViewHolder {
        TextView schedule_username, schedule_date, schedule_hour, schedule_description, schedule_delete, schedule_userLvl, schedule_promocao;
        public ItemSchedule(View itemSchedule){
            super(itemSchedule);
            schedule_username = itemSchedule.findViewById(R.id.schedule_username);
            schedule_date = itemSchedule.findViewById(R.id.schedule_date);
            schedule_hour = itemSchedule.findViewById(R.id.schedule_hour);
            schedule_description = itemSchedule.findViewById(R.id.schedule_description);
            schedule_userLvl = itemSchedule.findViewById(R.id.schedule_userLvl);
            schedule_promocao = itemSchedule.findViewById(R.id.schedule_promocao);
            schedule_delete = itemSchedule.findViewById(R.id.schedule_delete);
        }

        public void setSchedule_username(String schedule_username) {
            this.schedule_username.setText(schedule_username);
        }

        public void setSchedule_date(String schedule_date) {
            this.schedule_date.setText(schedule_date);
        }

        public void setSchedule_hour(String schedule_hour) {
            this.schedule_hour.setText(schedule_hour);
        }

        public void setSchedule_description(String schedule_description) {
            this.schedule_description.setText(schedule_description);
        }

        public void setSchedule_userLvl(String schedule_userLvl) {
            this.schedule_userLvl.setText(schedule_userLvl);
        }

        public boolean setSchedule_promocao(String schedule_promocao) {
            this.schedule_promocao.setText(schedule_promocao);
            return false;
        }
    }
}
