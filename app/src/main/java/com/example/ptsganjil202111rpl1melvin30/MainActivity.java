package com.example.ptsganjil202111rpl1melvin30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.printservice.CustomPrinterIconCallback;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView cv_allmovies, cv_favorites, cv_profile, cv_about, cv_allmovies2, cv_popular, cv_latest, cv_nowplaying, cv_toprated, cv_upcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv_allmovies = findViewById(R.id.cv_allmovies);
        cv_favorites = findViewById(R.id.cv_favorites);
        cv_allmovies2 = findViewById(R.id.cv_allmovies2);
        cv_about = findViewById(R.id.cv_about);
        cv_popular = findViewById(R.id.cv_popular);
        cv_latest = findViewById(R.id.cv_latest);
        cv_nowplaying = findViewById(R.id.cv_nowplaying);
        cv_upcoming = findViewById(R.id.cv_upcoming);
        cv_toprated = findViewById(R.id.cv_toprated);


        cv_allmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(MainActivity.this, AllMovies.class);
                move.putExtra("popularonly", false);
                move.putExtra("latestonly", false);
                move.putExtra("nowplayingonly", false);
                move.putExtra("topratedonly", false);
                move.putExtra("upcomingonly", false);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });
        cv_allmovies2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent move = new Intent(MainActivity.this, AllMovies.class);
                move.putExtra("popularonly", false);
                move.putExtra("latestonly", false);
                move.putExtra("nowplayingonly", false);
                move.putExtra("topratedonly", false);
                move.putExtra("upcomingonly", false);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });
        cv_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });
        cv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });
        cv_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(MainActivity.this, AllMovies.class);
                move.putExtra("popularonly", true);
                move.putExtra("latestonly", false);
                move.putExtra("nowplayingonly", false);
                move.putExtra("topratedonly", false);
                move.putExtra("upcomingonly", false);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });
        cv_latest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(MainActivity.this, AllMovies.class);
                move.putExtra("popularonly", false);
                move.putExtra("latestonly", true);
                move.putExtra("nowplayingonly", false);
                move.putExtra("topratedonly", false);
                move.putExtra("upcomingonly", false);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });
        cv_nowplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(MainActivity.this, AllMovies.class);
                move.putExtra("popularonly", false);
                move.putExtra("latestonly", false);
                move.putExtra("nowplayingonly", true);
                move.putExtra("topratedonly", false);
                move.putExtra("upcomingonly", false);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });
        cv_toprated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(MainActivity.this, AllMovies.class);
                move.putExtra("popularonly", false);
                move.putExtra("latestonly", false);
                move.putExtra("nowplayingonly", false);
                move.putExtra("topratedonly", true);
                move.putExtra("upcomingonly", false);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });
        cv_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(MainActivity.this, AllMovies.class);
                move.putExtra("popularonly", false);
                move.putExtra("latestonly", false);
                move.putExtra("nowplayingonly", false);
                move.putExtra("topratedonly", false);
                move.putExtra("upcomingonly", true);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );

            }
        });


    }
}