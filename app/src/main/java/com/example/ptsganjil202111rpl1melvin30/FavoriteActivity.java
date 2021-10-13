package com.example.ptsganjil202111rpl1melvin30;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class FavoriteActivity extends AppCompatActivity {

    Helper helper;
    RecyclerView rv_list;
    ArrayList<TeamModel> dataModel = new ArrayList<>();
    ImageButton imgbtn_back, imgbtn_delete;
    TextView txt_results;
    ArrayList<String> namelist;
    FavoriteAdapter favoriteAdapter;
    CoordinatorLayout coordinatorLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        namelist = new ArrayList<>();
        helper = new Helper(FavoriteActivity.this);
        txt_results = findViewById(R.id.txt_resultcount);
        rv_list = findViewById(R.id.list);
        imgbtn_back = findViewById(R.id.imgbtn_back);
        imgbtn_delete = findViewById(R.id.imgbtn_delete);
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        //on click
        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgbtn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dataModel.size() > 0) {

                    //munculin konfirmasi delete all
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FavoriteActivity.this);
                    dialogBuilder.setTitle("Remove All");
                    dialogBuilder.setMessage("Are you sure want to remove all favorite movies? This can't be undone");
                    dialogBuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //close dialog
                        }
                    });
                    dialogBuilder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (i = 0; i < dataModel.size(); i++) {
                                helper.deleteFilm(dataModel.get(i).getTitle());


                            }
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
                    dialogBuilder.show();

                }
            }
        });


        setData();



    }

    public void setData() {
        dataModel = helper.tampilDataFilm();
        if (dataModel.size() == 0) {
            rv_list.setVisibility(View.GONE);
            txt_results.setText("No Favorite Film yet");
        } else {
            rv_list.setVisibility(View.VISIBLE);
            favoriteAdapter = new FavoriteAdapter(getApplicationContext(), dataModel);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            rv_list.setLayoutManager(layoutManager);
            rv_list.setAdapter(favoriteAdapter);
            txt_results.setText("Showing " + dataModel.size()+ " Results");


            favoriteAdapter.setOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intentdetail = new Intent(getApplicationContext(), MovieDetail.class);
                    intentdetail.putExtra("isfromfavorite", true);
                    intentdetail.putExtra("title", dataModel.get(position).getTitle());
                    intentdetail.putExtra("description", dataModel.get(position).getDesc());
                    intentdetail.putExtra("imageURL", dataModel.get(position).getImage());
                    intentdetail.putExtra("voteAverage", dataModel.get(position).getVoteAverage());
                    intentdetail.putExtra("releaseDate", dataModel.get(position).getReleaseDate());
                    intentdetail.putExtra("isfav", dataModel.get(position).getFavorite());
                    intentdetail.putExtra("revenue", dataModel.get(position).getRevenue());
                    intentdetail.putExtra("budget", dataModel.get(position).getBudget());
                    intentdetail.putExtra("runtime", dataModel.get(position).getRuntime());
                    //favorite
                    startActivity(intentdetail);
                    finish();

                }
            });
        }
    }

    //end
}