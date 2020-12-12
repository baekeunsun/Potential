package com.example.potential;

import android.graphics.Movie;

public class User implements Comparable<User>  {
    private String star;
    private String name;
    private String terri;
    private String pro;

    public User(){}

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerri() {
        return terri;
    }

    public void setTerri(String terri) {
        this.terri = terri;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public int compareTo(User user) {
        return this.star.compareTo(user.star);
    }
}



