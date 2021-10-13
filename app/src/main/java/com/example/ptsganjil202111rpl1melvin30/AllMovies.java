package com.example.ptsganjil202111rpl1melvin30;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class AllMovies extends AppCompatActivity {

    ImageButton imgbtn_back, imgbtn_fav;
    private RecyclerView recyclerView;
    private String URLUpcoming = "https://api.themoviedb.org/3/movie/upcoming";
    private String URLPopular = "https://api.themoviedb.org/3/movie/popular";
    private String URLNowPlaying = "https://api.themoviedb.org/3/movie/now_playing";
    private String URLTopRated = "https://api.themoviedb.org/3/movie/top_rated";
    private String URLLatest = "https://api.themoviedb.org/3/movie/now_playing";
    ArrayList<TeamModel> arrayList;
    TeamModel teamModel;
    Realm realm;
    Helper realmHelper;
    ArrayList<String> namelist;
    ArrayList<String> favoritenamelist;
    TeamAdapter adapter;
    TextView txt_resultcount, txt_activitytitle;
    int arraylength0 = 0;
    Bundle extras;
    Boolean popularonly = false, latestonly= false, nowplayingonly= false, topratedonly= false, upcomingonly= false;
    final String APIKEY = "83cf93f8ba6451e96abbda1edddb1838";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);


        realmHelper = new Helper(this);

        recyclerView = findViewById(R.id.list);
        imgbtn_back = findViewById(R.id.imgbtn_back);
        arrayList = new ArrayList<>();
        namelist = new ArrayList<>();
        txt_resultcount = findViewById(R.id.txt_resultcount);
        teamModel = (TeamModel) getIntent().getSerializableExtra("title");
        imgbtn_fav = findViewById(R.id.imgbtn_fav);
        txt_activitytitle = findViewById(R.id.txt_activitytitle);

        extras = getIntent().getExtras();

        popularonly = extras.getBoolean("popularonly");
        latestonly = extras.getBoolean("latestonly");
        nowplayingonly = extras.getBoolean("nowplayingonly");
        topratedonly = extras.getBoolean("topratedonly");
        upcomingonly = extras.getBoolean("upcomingonly");



        imgbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgbtn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(AllMovies.this, FavoriteActivity.class);
                startActivity(move);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );
            }
        });

        if (popularonly){
            getDataPopular();
            txt_activitytitle.setText("Popular Movies");
        } else {
            if (latestonly) {
                getDataLatest();
                txt_activitytitle.setText("Latest Movies");
            } else {
                if (nowplayingonly) {
                    getDataNowPlaying();
                    txt_activitytitle.setText("Now Playing Movies");
                } else {
                    if (topratedonly) {
                        getDataTopRated();
                        txt_activitytitle.setText("Top Rated Movies");
                    } else {
                        if (upcomingonly) {
                            getDataUpcoming();
                            txt_activitytitle.setText("Upcoming Movies");
                        } else {
                            getDataUpcoming();
                            getDataPopular();
                            getDataTopRated();
                            getDataLatest();
                            getDataNowPlaying();
                            txt_activitytitle.setText("All Movies");
                        }
                    }
                }
            }
        }





    }

    private void getDataUpcoming() {
        AndroidNetworking.get(URLUpcoming)
                .addQueryParameter("api_key", APIKEY)
                .addQueryParameter("language", "en-US")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray teamsArray = response.getJSONArray("results");
                            for (int i = 0; i < teamsArray.length(); i++) {
                                JSONObject teamObject = teamsArray.getJSONObject(i);
                                String title = teamObject.getString("title");
                                String desc = teamObject.getString("overview");
                                String voteAverage = teamObject.getString("vote_average");
                                String releaseDate = teamObject.getString("release_date");
                                String originalLanguage = teamObject.getString("original_language");

                                // Gak bisa keambil data 3 int ini
                                int revenue = 1000;
                                //Toast.makeText(AllMovies.this, "Revenue "+ revenue, Toast.LENGTH_SHORT).show();
                                int budget =1000; //teamObject.getString("budget");
                                int runtime  =99; //teamObject.getString("runtime");

                                //Boolean isFavorite =teamModel.getFavorite();
                                String image = "https://image.tmdb.org/t/p/w500/".concat(teamObject.getString("poster_path"));




                                if (namelist.contains(title)) {
                                } else {

                                    arrayList.add(new TeamModel(image, title, desc, releaseDate, voteAverage, originalLanguage, false, revenue, budget, runtime));
                                    adapter = new TeamAdapter(getApplicationContext(), arrayList);
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);
                                    namelist.add(title);


                                    //final RealmResults<TeamModel> model = realm.where(TeamModel.class).equalTo("title", title).findAll();
                                    //if (!model.isEmpty()) {
                                     //   arrayList.get(i).setFavorite(true);
                                    //}


                                    arraylength0 = arraylength0 + 1;
                                    txt_resultcount.setText("Showing " + String.valueOf(arraylength0) + " Results");


                                    adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            Intent intentdetail = new Intent(getApplicationContext(), MovieDetail.class);
                                            intentdetail.putExtra("title", arrayList.get(position).getTitle());
                                            intentdetail.putExtra("description", arrayList.get(position).getDesc());
                                            intentdetail.putExtra("imageURL", arrayList.get(position).getImage());
                                            intentdetail.putExtra("voteAverage", arrayList.get(position).getVoteAverage());
                                            intentdetail.putExtra("releaseDate", arrayList.get(position).getReleaseDate());
                                            intentdetail.putExtra("isfav", arrayList.get(position).getFavorite());
                                            intentdetail.putExtra("revenue", arrayList.get(position).getRevenue());
                                            intentdetail.putExtra("budget", arrayList.get(position).getBudget());
                                            intentdetail.putExtra("runtime", arrayList.get(position).getRuntime());
                                            //favorite
                                            startActivity(intentdetail);


                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        txt_resultcount.setText("Please check your network connection before trying again...");
                    }
                });
    }
    private void getDataPopular() {
        AndroidNetworking.get(URLPopular)
                .addQueryParameter("api_key", APIKEY)
                .addQueryParameter("language", "en-US")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray teamsArray = response.getJSONArray("results");
                            for (int i = 0; i < teamsArray.length(); i++) {
                                JSONObject teamObject = teamsArray.getJSONObject(i);
                                String title = teamObject.getString("title");
                                String desc = teamObject.getString("overview");
                                String voteAverage = teamObject.getString("vote_average");
                                String releaseDate = teamObject.getString("release_date");
                                String originalLanguage = teamObject.getString("original_language");

                                // Gak bisa keambil data 3 int ini
                                int revenue = 1000;
                                //Toast.makeText(AllMovies.this, "Revenue "+ revenue, Toast.LENGTH_SHORT).show();
                                int budget =1000; //teamObject.getString("budget");
                                int runtime  =99; //teamObject.getString("runtime");

                                //Boolean isFavorite =teamModel.getFavorite();
                                String image = "https://image.tmdb.org/t/p/w500/".concat(teamObject.getString("poster_path"));




                                if (namelist.contains(title)) {
                                } else {

                                    arrayList.add(new TeamModel(image, title, desc, releaseDate, voteAverage, originalLanguage, false, revenue, budget, runtime));
                                    adapter = new TeamAdapter(getApplicationContext(), arrayList);
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);
                                    namelist.add(title);


                                    //final RealmResults<TeamModel> model = realm.where(TeamModel.class).equalTo("title", title).findAll();
                                    //if (!model.isEmpty()) {
                                     //   arrayList.get(i).setFavorite(true);
                                    //}


                                    arraylength0 = arraylength0 + 1;
                                    txt_resultcount.setText("Showing " + String.valueOf(arraylength0) + " Results");


                                    adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            Intent intentdetail = new Intent(getApplicationContext(), MovieDetail.class);
                                            intentdetail.putExtra("title", arrayList.get(position).getTitle());
                                            intentdetail.putExtra("description", arrayList.get(position).getDesc());
                                            intentdetail.putExtra("imageURL", arrayList.get(position).getImage());
                                            intentdetail.putExtra("voteAverage", arrayList.get(position).getVoteAverage());
                                            intentdetail.putExtra("releaseDate", arrayList.get(position).getReleaseDate());
                                            intentdetail.putExtra("isfav", arrayList.get(position).getFavorite());
                                            intentdetail.putExtra("revenue", arrayList.get(position).getRevenue());
                                            intentdetail.putExtra("budget", arrayList.get(position).getBudget());
                                            intentdetail.putExtra("runtime", arrayList.get(position).getRuntime());
                                            //favorite
                                            startActivity(intentdetail);


                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        txt_resultcount.setText("Please check your network connection before trying again...");
                    }
                });
    }
    private void getDataLatest() {
        AndroidNetworking.get(URLLatest)
                .addQueryParameter("api_key", APIKEY)
                .addQueryParameter("language", "en-US")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray teamsArray = response.getJSONArray("results");
                            for (int i = 0; i < teamsArray.length(); i++) {
                                JSONObject teamObject = teamsArray.getJSONObject(i);
                                String title = teamObject.getString("title");
                                String desc = teamObject.getString("overview");
                                String voteAverage = teamObject.getString("vote_average");
                                String releaseDate = teamObject.getString("release_date");
                                String originalLanguage = teamObject.getString("original_language");

                                // Gak bisa keambil data 3 int ini
                                int revenue = 1000;
                                //Toast.makeText(AllMovies.this, "Revenue "+ revenue, Toast.LENGTH_SHORT).show();
                                int budget =1000; //teamObject.getString("budget");
                                int runtime  =99; //teamObject.getString("runtime");

                                //Boolean isFavorite =teamModel.getFavorite();
                                String image = "https://image.tmdb.org/t/p/w500/".concat(teamObject.getString("poster_path"));




                                if (namelist.contains(title)) {
                                } else {

                                    arrayList.add(new TeamModel(image, title, desc, releaseDate, voteAverage, originalLanguage, false, revenue, budget, runtime));
                                    adapter = new TeamAdapter(getApplicationContext(), arrayList);
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);
                                    namelist.add(title);


                                    //final RealmResults<TeamModel> model = realm.where(TeamModel.class).equalTo("title", title).findAll();
                                    //if (!model.isEmpty()) {
                                    //   arrayList.get(i).setFavorite(true);
                                    //}


                                    arraylength0 = arraylength0 + 1;
                                    txt_resultcount.setText("Showing " + String.valueOf(arraylength0) + " Results");


                                    adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            Intent intentdetail = new Intent(getApplicationContext(), MovieDetail.class);
                                            intentdetail.putExtra("title", arrayList.get(position).getTitle());
                                            intentdetail.putExtra("description", arrayList.get(position).getDesc());
                                            intentdetail.putExtra("imageURL", arrayList.get(position).getImage());
                                            intentdetail.putExtra("voteAverage", arrayList.get(position).getVoteAverage());
                                            intentdetail.putExtra("releaseDate", arrayList.get(position).getReleaseDate());
                                            intentdetail.putExtra("isfav", arrayList.get(position).getFavorite());
                                            intentdetail.putExtra("revenue", arrayList.get(position).getRevenue());
                                            intentdetail.putExtra("budget", arrayList.get(position).getBudget());
                                            intentdetail.putExtra("runtime", arrayList.get(position).getRuntime());
                                            //favorite
                                            startActivity(intentdetail);


                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        txt_resultcount.setText("Please check your network connection before trying again...");
                    }
                });
    }
    private void getDataNowPlaying() {
        AndroidNetworking.get(URLNowPlaying)
                .addQueryParameter("api_key", APIKEY)
                .addQueryParameter("language", "en-US")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray teamsArray = response.getJSONArray("results");
                            for (int i = 0; i < teamsArray.length(); i++) {
                                JSONObject teamObject = teamsArray.getJSONObject(i);
                                String title = teamObject.getString("title");
                                String desc = teamObject.getString("overview");
                                String voteAverage = teamObject.getString("vote_average");
                                String releaseDate = teamObject.getString("release_date");
                                String originalLanguage = teamObject.getString("original_language");

                                // Gak bisa keambil data 3 int ini
                                int revenue = 1000;
                                //Toast.makeText(AllMovies.this, "Revenue "+ revenue, Toast.LENGTH_SHORT).show();
                                int budget =1000; //teamObject.getString("budget");
                                int runtime  =99; //teamObject.getString("runtime");

                                //Boolean isFavorite =teamModel.getFavorite();
                                String image = "https://image.tmdb.org/t/p/w500/".concat(teamObject.getString("poster_path"));




                                if (namelist.contains(title)) {
                                } else {

                                    arrayList.add(new TeamModel(image, title, desc, releaseDate, voteAverage, originalLanguage, false, revenue, budget, runtime));
                                    adapter = new TeamAdapter(getApplicationContext(), arrayList);
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);
                                    namelist.add(title);


                                    //final RealmResults<TeamModel> model = realm.where(TeamModel.class).equalTo("title", title).findAll();
                                    //if (!model.isEmpty()) {
                                    //   arrayList.get(i).setFavorite(true);
                                    //}


                                    arraylength0 = arraylength0 + 1;
                                    txt_resultcount.setText("Showing " + String.valueOf(arraylength0) + " Results");


                                    adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            Intent intentdetail = new Intent(getApplicationContext(), MovieDetail.class);
                                            intentdetail.putExtra("title", arrayList.get(position).getTitle());
                                            intentdetail.putExtra("description", arrayList.get(position).getDesc());
                                            intentdetail.putExtra("imageURL", arrayList.get(position).getImage());
                                            intentdetail.putExtra("voteAverage", arrayList.get(position).getVoteAverage());
                                            intentdetail.putExtra("releaseDate", arrayList.get(position).getReleaseDate());
                                            intentdetail.putExtra("isfav", arrayList.get(position).getFavorite());
                                            intentdetail.putExtra("revenue", arrayList.get(position).getRevenue());
                                            intentdetail.putExtra("budget", arrayList.get(position).getBudget());
                                            intentdetail.putExtra("runtime", arrayList.get(position).getRuntime());
                                            //favorite
                                            startActivity(intentdetail);


                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        txt_resultcount.setText("Please check your network connection before trying again...");
                    }
                });
    }
    private void getDataTopRated() {
        AndroidNetworking.get(URLTopRated)
                .addQueryParameter("api_key", APIKEY)
                .addQueryParameter("language", "en-US")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray teamsArray = response.getJSONArray("results");
                            for (int i = 0; i < teamsArray.length(); i++) {
                                JSONObject teamObject = teamsArray.getJSONObject(i);
                                String title = teamObject.getString("title");
                                String desc = teamObject.getString("overview");
                                String voteAverage = teamObject.getString("vote_average");
                                String releaseDate = teamObject.getString("release_date");
                                String originalLanguage = teamObject.getString("original_language");

                                // Gak bisa keambil data 3 int ini
                                int revenue = 1000;
                                //Toast.makeText(AllMovies.this, "Revenue "+ revenue, Toast.LENGTH_SHORT).show();
                                int budget =1000; //teamObject.getString("budget");
                                int runtime  =99; //teamObject.getString("runtime");

                                //Boolean isFavorite =teamModel.getFavorite();
                                String image = "https://image.tmdb.org/t/p/w500/".concat(teamObject.getString("poster_path"));




                                if (namelist.contains(title)) {
                                } else {

                                    arrayList.add(new TeamModel(image, title, desc, releaseDate, voteAverage, originalLanguage, false, revenue, budget, runtime));
                                    adapter = new TeamAdapter(getApplicationContext(), arrayList);
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);
                                    namelist.add(title);


                                    //final RealmResults<TeamModel> model = realm.where(TeamModel.class).equalTo("title", title).findAll();
                                    //if (!model.isEmpty()) {
                                    //   arrayList.get(i).setFavorite(true);
                                    //}


                                    arraylength0 = arraylength0 + 1;
                                    txt_resultcount.setText("Showing " + String.valueOf(arraylength0) + " Results");


                                    adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            Intent intentdetail = new Intent(getApplicationContext(), MovieDetail.class);
                                            intentdetail.putExtra("title", arrayList.get(position).getTitle());
                                            intentdetail.putExtra("description", arrayList.get(position).getDesc());
                                            intentdetail.putExtra("imageURL", arrayList.get(position).getImage());
                                            intentdetail.putExtra("voteAverage", arrayList.get(position).getVoteAverage());
                                            intentdetail.putExtra("releaseDate", arrayList.get(position).getReleaseDate());
                                            intentdetail.putExtra("isfav", arrayList.get(position).getFavorite());
                                            intentdetail.putExtra("revenue", arrayList.get(position).getRevenue());
                                            intentdetail.putExtra("budget", arrayList.get(position).getBudget());
                                            intentdetail.putExtra("runtime", arrayList.get(position).getRuntime());
                                            //favorite
                                            startActivity(intentdetail);


                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        txt_resultcount.setText("Please check your network connection before trying again...");
                    }
                });
    }



    // end
}