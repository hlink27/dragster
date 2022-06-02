package com.teste.estudo.menuAdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teste.estudo.R;

public class AdminMenu extends AppCompatActivity {

    Button admUsers, admCar, seeRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        admUsers = findViewById(R.id.admUsers);
        admCar = findViewById(R.id.addCar);
        seeRepair = findViewById(R.id.seeRepair);

        admUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenu.this, AdminViewUser.class);
                startActivity(intent);
            }
        });
        admCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenu.this, AdmViewVehicle.class);
                startActivity(intent);

            }
        });
        seeRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenu.this, AdminViewSchedule.class);
                startActivity(intent);
            }
        });
    }
}