package com.example.gameapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.example.gameapp.databinding.ActivitySettingsBinding;

public class settings extends AppCompatActivity {
    private ActivitySettingsBinding binding;
    public Database database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database=new Database(this);




        binding.setBtnShowgames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),show_all_game_activity.class));

            }
        });

        binding.setBtnClearhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder delete_message=new AlertDialog.Builder(settings.this);
                delete_message.setTitle(getString(R.string.set_Dialoge_title))
                        .setMessage(getString(R.string.set_Dialoge_message))
                        .setPositiveButton(getString(R.string.Dialoge_postive_btn), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.deleteHistory();
                            }
                        })

                        .setNegativeButton(getString(R.string.Dialoge_negative_btn), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog dialog=delete_message.create();
                dialog.show();

            }
        });

        binding.setBtnLastgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(settings.this, database.getLastgameDate(), Toast.LENGTH_LONG).show();

            }
        });

        binding.setBtnChangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getBaseContext(),ChangePasswordActivity.class));


            }
        });
    }
}