package com.example.ptsganjil202111rpl1melvin30;

import io.realm.RealmObject;

public class TeamModel extends RealmObject {
    private String title, image, desc, releaseDate, voteAverage, originalLanguage;
    int budget, revenue, runtime;
    private Boolean isFavorite;


    public TeamModel() {

    }



    public TeamModel(String image, String title, String desc, String releaseDate, String voteAverage, String originalLanguage, Boolean isFavorite, Integer revenue, Integer budget, Integer runtime) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.originalLanguage = originalLanguage;
        this.isFavorite = isFavorite;
        this.budget = budget;
        this.revenue = revenue;
        this.runtime = runtime;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) { this.image = image; }

    public String getImage() { return image; }

    public void setDesc(String desc) { this.desc = desc; }

    public String getDesc() { return desc; }
}
