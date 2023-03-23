package com.example.gameapp;

import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class methods {

    public static boolean email_validation(String email) {
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher mat = pat.matcher(email);

        return mat.find();
    }

    public static boolean valid_string(String str) {
        return ((!str.equals(""))
                && (str.length()<30)
                && (str.length()>8)
                && (str.matches("[a-zA-Z ]*")));
    }

    public static boolean pass_strength(String pass){

               return (pass.matches( "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$"));

    }

    public static boolean date_validation (String date){

        return (date.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$"));
    }

    public static boolean input_correct(String email, String fullName, String userName, String pass1, String pass2,
                                        String birthdate, Spinner country, String pic_uri){

        return email_validation(email) && valid_string(fullName) && valid_string(userName) && pass_strength(pass1) && date_validation(birthdate)
                && (!email.isEmpty() && !fullName.isEmpty() && !userName.isEmpty() && !pass1.isEmpty() && !pass2.isEmpty()
                && !birthdate.isEmpty() && !pic_uri.isEmpty())
                && country.getSelectedItemPosition() > 0;
    }


    public static  Question generateQuestion(){
        String [][] x= new String[3][3];
        int startNumber=(int)(Math.random()*10)+1;
        int incstartNumber=(int) (Math.random()*5)+1;
        int stredNumber;
        int number=-1;

        for (int i=0; i<x.length; i++){
            for (int j=0; j <x[i].length; j++ ){
                stredNumber=startNumber+incstartNumber;

               if (i==1 && j==1){
                    x [i][j]="??";
                    number=stredNumber;
                } else {
                    x[i][j]=stredNumber+"";
                }

                incstartNumber+=2;
                startNumber=stredNumber;
            }
    }
        return  new Question(x, number);
}}
