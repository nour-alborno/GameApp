package com.example.gameapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gameapp.databinding.ActivityCustomToastViewBinding;
import com.example.gameapp.databinding.ActivityGameBinding;

import java.util.Date;


public class game extends AppCompatActivity {

    private ActivityGameBinding binding;
   public SharedPreferences sp;
   public SharedPreferences.Editor edit;
   public Database db ;
   public static int score;
   public static String date;
   private MediaPlayer sound;

   // onPressesBack variables
   private long pressedTime;
   private Toast backToast;


    //sp keys//
    public static final String sp_context_name="register_activity";
    public static final String sp_user_name="username";
    public static final String sp_age="age";



    // sp storage variables
  private   String sp_username;
  private   String  sp_userage;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.gamMaterialToolbar);


        db = new Database(this);
        sp=getSharedPreferences(sp_context_name, MODE_PRIVATE);
        edit=sp.edit();


       // age and name section
        sp_username=sp.getString(sp_user_name,"User Name");
        sp_userage=sp.getString(sp_age,"User age");


       binding.gamTvUsernameAndAge.setText(sp_username.concat(" ").concat(sp_userage));

                  //////




            Question questions = methods.generateQuestion();
            String [][] s=questions.getData();
            int b =questions.getHiddenNumber();

            binding.gamGlTv1.setText(s[0][0]);
            binding.gamGlTv2.setText(s[0][1]);
            binding.gamGlTv3.setText(s[0][2]);

            binding.gamGlTv4.setText(s[1][0]);
            binding.gamGlTv5.setText(s[1][1]);
            binding.gamGlTv6.setText(s[1][2]);

            binding.gamGlTv7.setText(s[2][0]);
            binding.gamGlTv8.setText(s[2][1]);
            binding.gamGlTv9.setText(s[2][2]);





                  score=Integer.parseInt(db.getLastgameScore());
                  binding.gamTvScore.setText(getString(R.string.gam_tv_score).concat(" ").concat(String.valueOf(score)));

              //checking answer
        binding.gamBtnCheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.gamEtAnswer.getText().toString().equals(String.valueOf(b))) {

                    score++;
                    binding.gamTvScore.setText(getString(R.string.gam_tv_score).concat(" ").concat(String.valueOf( score )));


                    //Custom Toast
                    ActivityCustomToastViewBinding binding_2 = ActivityCustomToastViewBinding.inflate(getLayoutInflater());
                    binding_2.textView.setBackgroundResource(R.color.custom_T_green);
                    binding_2.textView.setText(R.string.CT_game_True);
                    binding_2.textView.setTextColor(getResources().getColor(R.color.white));

                    Toast toast = new Toast(getBaseContext());
                    toast.setView(binding_2.getRoot());
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();

                    //adding sound when winning
                    sound=MediaPlayer.create(game.this,R.raw.achievement_bell);
                    sound.start();

                    // emptying answer field and making the ability to use it once
                    binding.gamEtAnswer.setEnabled(false);
                    binding.gamEtAnswer.getText().clear();

                } else {
                    binding.gamTvScore.setText(getString(R.string.gam_tv_score).concat(" ").concat(String.valueOf( score )));

                    //Custom Toast
                    ActivityCustomToastViewBinding binding_2 = ActivityCustomToastViewBinding.inflate(getLayoutInflater());
                    binding_2.textView.setBackgroundResource(R.color.custom_T_red);
                    binding_2.textView.setText(R.string.CT_game_False);
                    binding_2.textView.setTextColor(getResources().getColor(R.color.white));

                    Toast toast = new Toast(getBaseContext());
                    toast.setView(binding_2.getRoot());
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();



                    //  Adding sound when losing
                    sound=MediaPlayer.create(game.this,R.raw.lose);
                    sound.start();



                    // emptying answer field and making the ability to use it once
                    binding.gamEtAnswer.setEnabled(false);
                    binding.gamEtAnswer.getText().clear();
                }





            }
        });





                        //New Game
        binding.gamBtnReshuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.gamEtAnswer.setEnabled(true);

                Question questions = methods.generateQuestion();
                String [][] s=questions.getData();
                int b =questions.getHiddenNumber();

                binding.gamGlTv1.setText(s[0][0]);
                binding.gamGlTv2.setText(s[0][1]);
                binding.gamGlTv3.setText(s[0][2]);

                binding.gamGlTv4.setText(s[1][0]);
                binding.gamGlTv5.setText(s[1][1]);
                binding.gamGlTv6.setText(s[1][2]);

                binding.gamGlTv7.setText(s[2][0]);
                binding.gamGlTv8.setText(s[2][1]);
                binding.gamGlTv9.setText(s[2][2]);




                binding.gamBtnCheckAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (binding.gamEtAnswer.getText().toString().equals(String.valueOf(b))) {
                            score++;
                            binding.gamTvScore.setText(getString(R.string.gam_tv_score).concat(" ").concat(String.valueOf( score )));


                            //Custom Toast
                            ActivityCustomToastViewBinding binding_2 = ActivityCustomToastViewBinding.inflate(getLayoutInflater());
                            binding_2.textView.setBackgroundResource(R.color.custom_T_green);
                            binding_2.textView.setText(R.string.CT_game_True);
                            binding_2.textView.setTextColor(getResources().getColor(R.color.white));

                            Toast toast = new Toast(getBaseContext());
                            toast.setView(binding_2.getRoot());
                            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();

                            //adding sound when winning
                            sound=MediaPlayer.create(game.this,R.raw.achievement_bell);
                            sound.start();

                            // emptying answer field and making the ability to use it once
                            binding.gamEtAnswer.setEnabled(false);
                            binding.gamEtAnswer.getText().clear();

                        } else {
                            binding.gamTvScore.setText(getString(R.string.gam_tv_score).concat(" ").concat(String.valueOf( score )));

                            //Custom Toast
                            ActivityCustomToastViewBinding binding_2 = ActivityCustomToastViewBinding.inflate(getLayoutInflater());
                            binding_2.textView.setBackgroundResource(R.color.custom_T_red);
                            binding_2.textView.setText(R.string.CT_game_False);
                            binding_2.textView.setTextColor(getResources().getColor(R.color.white));

                            Toast toast = new Toast(getBaseContext());
                            toast.setView(binding_2.getRoot());
                            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();



                            //  Adding sound when losing
                            sound=MediaPlayer.create(game.this,R.raw.lose);
                            sound.start();



                            // emptying answer field and making the ability to use it once
                            binding.gamEtAnswer.setEnabled(false);
                            binding.gamEtAnswer.getText().clear();
                        }





                    }
                });




            }
        });


            }





                    @Override
                    public boolean onCreateOptionsMenu (Menu menu){

                        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
                        return true;
                    }

                    @Override
                    public boolean onOptionsItemSelected (@NonNull MenuItem item){

                        switch (item.getItemId()) {

                            case R.id.toolbar_menu_Logout:

                               boolean r= db.deleteHistory();
                                edit.clear();
                                edit.apply();

                            //    Log.d("test",String.valueOf(r));

                                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                                finish();

                                return true;


                            case R.id.toolbar_menu_settings:
                                startActivity(new Intent(getBaseContext(), settings.class));


                                return true;
                        }

                        return false;
                    }



    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            finish();
        } else {
            backToast=Toast.makeText(getBaseContext(), getString(R.string.gam_pressBack), Toast.LENGTH_SHORT);
            backToast.show();
        }
        pressedTime = System.currentTimeMillis();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        date =java.text.DateFormat.getDateTimeInstance().format(new Date());
        DB_TB_gameInfo game_information = new DB_TB_gameInfo(sp_username, String.valueOf(score), date);
        db.insertGameInfo(game_information);


    }


}



