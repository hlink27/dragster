package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactActivity extends AppCompatActivity {

    ImageView whatsapp, googleMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        whatsapp = (ImageView) findViewById(R.id.imgWhatsapp);
        googleMaps = (ImageView) findViewById(R.id.imgGoogleMaps);

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=557132762177&text=Ol%C3%A1!"));


            }
        });
    }
}