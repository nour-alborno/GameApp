package com.example.gameapp;

public class DB_TB_gameInfo {

   private int id;
   private String username;
   private String score;
   private String game_date;


    public DB_TB_gameInfo(String username, String score, String game_date) {
        this.username = username;
        this.score = score;
        this.game_date = game_date;
    }

    public DB_TB_gameInfo(int id, String username, String score, String game_date) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.game_date = game_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGame_date() {
        return game_date;
    }

    public void setGame_date(String game_date) {
        this.game_date = game_date;
    }
}
