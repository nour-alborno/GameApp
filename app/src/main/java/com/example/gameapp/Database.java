package com.example.gameapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

                public static final String DB_name="Game_DB";
                public static final int DB_version=1;

                public static final String TB1_NAME="Game_info";
                public static final String TB1_CLN_ID= "id";
                public static final String TB1_CLN_USERNAME= "username";
                public static final String TB1_CLN_GAMEDATE="Game_date";
                public static final String TB1_CLN_SCORE="Score";




                public Database(Context context){
                    super(context,DB_name,null,DB_version);
                }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("Create table "+TB1_NAME+" ("+TB1_CLN_ID+" integer primary key autoincrement," +
                    ""+TB1_CLN_USERNAME+" text, "+TB1_CLN_GAMEDATE+" text, "+TB1_CLN_SCORE+" text )");


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

                    sqLiteDatabase.execSQL("drop table if exists " + TB1_NAME);

                    onCreate(sqLiteDatabase);

        }

        //CRED Methods

        public boolean insertGameInfo(DB_TB_gameInfo game){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(TB1_CLN_USERNAME,game.getUsername());
            values.put(TB1_CLN_GAMEDATE,game.getGame_date());
            values.put(TB1_CLN_SCORE,game.getScore());
            long result=db.insert(TB1_NAME,null,values);

            return result != -1;

        }


       public ArrayList<DB_TB_gameInfo> getGamesinfo(){
                    ArrayList<DB_TB_gameInfo> games=new ArrayList<>();

                    SQLiteDatabase db=getReadableDatabase();
                    Cursor c =db.rawQuery("select * from "+ TB1_NAME,null);

                    if( c != null && c.moveToFirst()){
                       do {
                           String username=c.getString(c.getColumnIndexOrThrow(TB1_CLN_USERNAME));
                           String date=c.getString(c.getColumnIndexOrThrow(TB1_CLN_GAMEDATE));
                           String score=c.getString(c.getColumnIndexOrThrow(TB1_CLN_SCORE));


                           DB_TB_gameInfo game=new DB_TB_gameInfo(username,score,date);
                           games.add(game);

                       } while (c.moveToNext());
                       c.close();

                    }
           return games;
       }

      public String getLastgameDate (){
                    String Last_Game_Date;

           SQLiteDatabase db=getReadableDatabase();
           Cursor c=db.rawQuery("select " + TB1_CLN_GAMEDATE +" from "+TB1_NAME ,null );
           if (c.moveToLast()) {
               Last_Game_Date = c.getString(c.getColumnIndexOrThrow(TB1_CLN_GAMEDATE));
               c.close();

           } else {
               c.close();
               Last_Game_Date="00/00/0000 00:00:00";
          }return Last_Game_Date ;}


    public String getLastgameScore (){
        String Last_Game_Score;

        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select " + TB1_CLN_SCORE +" from "+TB1_NAME ,null );
        if (c.moveToLast()) {
            Last_Game_Score = c.getString(c.getColumnIndexOrThrow(TB1_CLN_SCORE));
            c.close();

        } else {
            c.close();
            Last_Game_Score="0";
        }return Last_Game_Score ;}





      public boolean deleteHistory(){
            SQLiteDatabase db=getWritableDatabase();
            int result=db.delete(TB1_NAME,null,null);
            return result>0;
       }






}
