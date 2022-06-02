package com.teste.estudo.menuClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.teste.estudo.R;

public class ContactActivity extends AppCompatActivity {

    ImageView whatsapp, googleMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        whatsapp = (ImageView) findViewById(R.id.imgWhatsapp);
        googleMaps = (ImageView) findViewById(R.id.imgGoogleMaps);

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=557191577883&text=Ol%C3%A1!"));
                startActivity(intent);
            }
        });
        googleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.google.com.br/maps/place/Oficina+Dragster/@-12.9936927,-38.496404,19z/data=!3m1!4b1!4m5!3m4!1s0x71604b0757322bf:0xe2e8fc60081fe4!8m2!3d-12.9936936!4d-38.4952823"));
                startActivity(intent);
            }
        });
    }
}