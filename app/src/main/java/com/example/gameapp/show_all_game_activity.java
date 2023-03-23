package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.gameapp.databinding.ActivityShowAllGameBinding;

import java.util.ArrayList;


public class show_all_game_activity extends AppCompatActivity {

   private ActivityShowAllGameBinding binding;
    private recyclerViewAdapter RVAadapter;
    public Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShowAllGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        database=new Database(this);


        ArrayList<DB_TB_gameInfo> gemes_arraylist=database.getGamesinfo();

        RVAadapter= new recyclerViewAdapter(gemes_arraylist);
        binding.gameInfoRV.setAdapter(RVAadapter);
        RecyclerView.LayoutManager LM=new LinearLayoutManager(this);
        binding.gameInfoRV.setLayoutManager(LM);
        binding.gameInfoRV.setHasFixedSize(true);








    }
}