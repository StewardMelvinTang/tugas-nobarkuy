package com.example.ptsganjil202111rpl1melvin30;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AboutActivity extends AppCompatActivity {

    ImageView img_mtglogo;
    ImageButton imgbtn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        img_mtglogo = findViewById(R.id.img_logomtg);
        imgbtn_back = findViewById(R.id.imgbtn_back);

        Picasso.get()
                .load("https://i.ibb.co/PTGt26W/Melvin-Tang-Games-Logo-PNG-textembooss.png")
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(img_mtglogo);



        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}