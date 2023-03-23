package com.example.gameapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameapp.databinding.ShowGamesCustomItemBinding;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.GamesViewHolder> {

    private ArrayList<DB_TB_gameInfo> games;

    public recyclerViewAdapter(ArrayList<DB_TB_gameInfo> games) {
        this.games = games;
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.show_games_custom_item, null,false);

        GamesViewHolder viewHolder=new GamesViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {

        DB_TB_gameInfo game_info=games.get(position);
        holder.userName.setText(game_info.getUsername());
        holder.date.setText(game_info.getGame_date());
        holder.score.setText(game_info.getScore());


    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    class GamesViewHolder extends RecyclerView.ViewHolder{
       public TextView userName, score, date;
        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);

            userName=itemView.findViewById(R.id.RVCustomItem_tv_user_Name);
            score=itemView.findViewById(R.id.RVCustomItem_tv_score);
            date=itemView.findViewById(R.id.RVCustomItem_tv_gamedate);

        }
    }
}
