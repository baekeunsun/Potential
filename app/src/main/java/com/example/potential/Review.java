package com.example.potential;

public class Review {
    private String content;
    private String username;
    private String counseiling;
    private String rating;
    public Review(){}

    public String getContent() {
        return content;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getcounseiling() {
        return counseiling;
    }

    public void setcounseiling(String counseiling) {
        this.counseiling = counseiling;
    }


}
