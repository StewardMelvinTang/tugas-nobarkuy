package com.example.ptsganjil202111rpl1melvin30;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class Helper {

    private Context context;
    private Realm realm;
    private RealmResults<TeamModel> realmResults;


    private static final String TAG = "RealmHelper";

    public Helper(Context context) {
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void inputDataAwal() {
        TeamModel data = new TeamModel();
        data.setTitle("Judul Film");
        data.setDesc("Deskripsi Film");
        data.setVoteAverage("10.0");
        data.setReleaseDate("10-10-2010");
        data.setImage("https://googleimage.com");
        data.setOriginalLanguage("en-US");
        data.setFavorite(false);
        data.setBudget(100000);
        data.setRevenue(100000);
        data.setRuntime(120);

        realm.beginTransaction();
        realm.copyToRealm(data);
        realm.commitTransaction();
    }

    public ArrayList<TeamModel> tampilDataFilm() {
        ArrayList<TeamModel> dataR = new ArrayList<>();
        realmResults = realm.where(TeamModel.class).findAll();
        realmResults.sort("title", Sort.ASCENDING);

        if (realmResults.size() > 0) {

            for (int i = 0; i < realmResults.size(); i++) {
                TeamModel data = new TeamModel();
                data.setOriginalLanguage(realmResults.get(i).getOriginalLanguage());
                data.setImage(realmResults.get(i).getImage());
                data.setReleaseDate(realmResults.get(i).getReleaseDate());
                data.setVoteAverage(realmResults.get(i).getVoteAverage());
                data.setDesc(realmResults.get(i).getDesc());
                data.setTitle(realmResults.get(i).getTitle());
                data.setRuntime(realmResults.get(i).getRuntime());
                data.setBudget(realmResults.get(i).getBudget());
                data.setRevenue(realmResults.get(i).getRevenue());

                dataR.add(data);
            }
        }
        return dataR;
    }

    public void tambahFilm(String title, String desc, String originalLanguage, String imageURL, String releaseDate, String voteAverage, Boolean favorite, int revenue, int budget, int runtime) {
        TeamModel datafilm = new TeamModel();
        datafilm.setTitle(title);
        datafilm.setDesc(desc);
        datafilm.setVoteAverage(voteAverage);
        datafilm.setReleaseDate(releaseDate);
        datafilm.setImage(imageURL);
        datafilm.setOriginalLanguage(originalLanguage);
        datafilm.setFavorite(favorite);
        datafilm.setRevenue(revenue);
        datafilm.setBudget(budget);
        datafilm.setRuntime(runtime);

        realm.beginTransaction();
        realm.copyToRealm(datafilm);
        realm.commitTransaction();
        //sudah digantikan dengan snackk bar id moviedetail
        //Toast.makeText(context, "Film has been added to favorite list", Toast.LENGTH_SHORT).show();
    }

    public void deleteFilm(String title) {
        realm.beginTransaction();
        RealmResults<TeamModel> siswa = realm.where(TeamModel.class).equalTo("title", title).findAll();
        siswa.deleteAllFromRealm();
        realm.commitTransaction();


        //sudah digantikan dengan snackk bar id moviedetail
        //Toast.makeText(context, "Film has been removed from favorite list", Toast.LENGTH_SHORT).show();
    }
//end
}
