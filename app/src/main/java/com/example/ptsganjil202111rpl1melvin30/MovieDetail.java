package com.example.ptsganjil202111rpl1melvin30;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieDetail extends AppCompatActivity {

    ImageButton imgbtn_back, imgbtn_share;
    String title, releasedate, rating, description, imageuRL, originalLanguage;
    TextView txt_title3, txt_releasedate2, txt_rating2;
    TextView txt_title1, txt_title2, txt_releasedate, txt_rating, txt_description, txt_fav, txt_runtime, txt_revenue, txt_budget;
    ImageView img_thumbnail, img_favorite;
    int revenue, budget, runtime;
    boolean isfavorit;
    Bundle extras;
    Helper helper;
    ArrayList<TeamModel> dataModel = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    Realm realm;
    CoordinatorLayout coordinatorLayout;
    Boolean isfromFavorite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgbtn_back = findViewById(R.id.imgbtn_back);
        txt_title1 = findViewById(R.id.txt_movietitle);
        txt_title2 = findViewById(R.id.txt_title);
        txt_title3 = findViewById(R.id.txt_movietitle3);
        txt_releasedate = findViewById(R.id.txt_releasedate);
        txt_rating = findViewById(R.id.txt_rating);
        txt_description = findViewById(R.id.txt_description);
        img_thumbnail = findViewById(R.id.img_thumbnail);
        imgbtn_share = findViewById(R.id.imgbtn_share);
        img_favorite = findViewById(R.id.img_favorite);
        txt_fav = findViewById(R.id.txt_fav);
        txt_releasedate2 = findViewById(R.id.txt_releasedate2);
        txt_rating2 = findViewById(R.id.txt_rating2);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        helper = new Helper(MovieDetail.this);
        extras = getIntent().getExtras();

        isfromFavorite = extras.getBoolean("isfromfavorite");
        title = extras.getString("title");
        description = extras.getString("description");
        rating = extras.getString("voteAverage");
        releasedate = extras.getString("releaseDate");
        imageuRL = extras.getString("imageURL");
        originalLanguage = extras.getString("original_language");
        revenue = extras.getInt("revenue");
        budget = extras.getInt("budget");
        runtime = extras.getInt("runtime");


        txt_title1.setText(title);
        txt_title2.setText(title);
        txt_title3.setText("Movie Title : "+title);
        txt_releasedate2.setText("Release Date : " + releasedate);
        txt_rating2.setText("Rating : " + rating +" / 10.0");
        txt_rating.setText("Rating : " + rating +" / 10.0");
        txt_description.setText(description);
        txt_releasedate.setText("Release Date : " + releasedate);
        //txt_runtime.setText("Duration : "+String.valueOf(runtime) + "m");
        //txt_revenue.setText("Revenue : $" + String.valueOf(revenue));
        //txt_budget.setText("Budget : $" + String.valueOf(budget));



        Picasso.get()
                .load(imageuRL)
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(img_thumbnail);


        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isfromFavorite){
                    Intent intentkefav = new Intent(getApplicationContext(), FavoriteActivity.class);
                    startActivity(intentkefav);
                    finish();


                }else {finish();}

            }
        });

        imgbtn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Nonton Bareng Film " + title + " Hanya Di NobarKuy! Download di https://googleplay.com/nobarkuy");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
        });

        img_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isfavorit){

                            helper.deleteFilm(title);
                            img_favorite.setImageResource(R.drawable.vector_favoriteborder);
                            Snackbar.make(coordinatorLayout, "This movie has been removed from favorites", Snackbar.LENGTH_SHORT)
                                    .show();
                            isfavorit = false;
                            img_favorite.setTooltipText("Add to Favorite");




                }else{
                    img_favorite.setImageResource(R.drawable.vector_favoritefill);
                    helper.tambahFilm(title, description, originalLanguage, imageuRL, releasedate, rating, true, revenue, budget,runtime);
                    Snackbar.make(coordinatorLayout, "This movie has been added to favorites", Snackbar.LENGTH_SHORT)
                            .show();
                        isfavorit = true;
                    img_favorite.setTooltipText("Remove from Favorite");

            }}
        });

        checkforFavorite();

    }

    private void checkforFavorite(){
        dataModel = helper.tampilDataFilm();

        nameList.clear();
        for (int i = 0; i < dataModel.size(); i++) {
            nameList.add(dataModel.get(i).getTitle());
        }

        if (nameList.contains(title)) {
            isfavorit = true;
           img_favorite.setImageResource(R.drawable.vector_favoritefill);
            img_favorite.setTooltipText("Remove from Favorite");

        } else {
            isfavorit = false;
            img_favorite.setImageResource(R.drawable.vector_favoriteborder);
            img_favorite.setTooltipText("Add to Favorite");



        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isfromFavorite){
            Intent intentkefav = new Intent(getApplicationContext(), FavoriteActivity.class);
            startActivity(intentkefav);
            finish();


        }else {finish();}
    }

    //end
    }
