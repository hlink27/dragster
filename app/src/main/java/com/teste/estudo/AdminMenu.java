package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMenu extends AppCompatActivity {

    Button admCreateAcc, admEditAcc, vehicleAdd, vehicleEdit, seeRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        admCreateAcc = findViewById(R.id.admCreateAcc);
        admEditAcc = findViewById(R.id.admEditAcc);
        vehicleAdd = findViewById(R.id.vehicleAdd);
        vehicleEdit = findViewById(R.id.vehicleEdit);
        seeRepair = findViewById(R.id.seeRepair);

        admCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenu.this, AdminCreateAcc.class);
                startActivity(intent);
            }
        });
    }
}