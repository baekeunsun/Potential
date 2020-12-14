package com.example.potential;

public class User implements  Comparable<User> {
    private String star;
    private String name;
    private String terri;
    private String pro;
    private String number;
    private String addr;
    private String rating;
    private String board;
    public User(){}

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public  int compareTo(User user){ return user.rating.compareTo(this.rating);}
}



