package com.teste.estudo.menuAdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.R;
import com.teste.estudo.utils.BancoDeDados;

import java.util.ArrayList;

public class AdminEditUserLvl extends AppCompatActivity {

    Spinner spinner1, spinner2;
    BancoDeDados banco;
    Button confirmLvl;
    String tipoUser, nome;

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public String getNome() {
        return nome;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user_lvl);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode
        banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();

        spinner1 = findViewById(R.id.spinnerUsers);
        UserDAO userDAO = banco.userDAO();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.color_spinner_layout, new ArrayList<>());
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropsown_layout);
        spinnerAdapter.addAll(userDAO.getAllNome());
        spinnerAdapter.notifyDataSetChanged();
        spinner1.setOnItemSelectedListener(new Spinner1());
        spinner1.setAdapter(spinnerAdapter);

        spinner2 = findViewById(R.id.spinnerCrt);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipoUser, R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropsown_layout);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new Spinner2());

        /*confirmLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tipoUser1 = getTipoUser();
                String nome1 = getNome();
                userDAO.updateLevel(tipoUser1, nome1);
                finish();
            }
        });*/
    }

    class Spinner1 implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String tipoUser = adapterView.getItemAtPosition(i).toString();
            setTipoUser(tipoUser);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class Spinner2 implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String nome = adapterView.getItemAtPosition(i).toString();
            setNome(nome);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}