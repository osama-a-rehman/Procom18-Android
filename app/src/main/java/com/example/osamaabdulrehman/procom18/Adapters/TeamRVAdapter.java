package com.example.osamaabdulrehman.procom18.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.osamaabdulrehman.procom18.Models.Team;
import com.example.osamaabdulrehman.procom18.R;

import java.util.List;


public class TeamRVAdapter extends RecyclerView.Adapter<TeamRVAdapter.ViewHolder> {
    private Context context;
    private List<Team> teamList;

    public TeamRVAdapter(Context context, List<Team> teamList) {
        this.context = context;
        this.teamList = teamList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.team_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = teamList.get(position);

        Glide.with(context).load(team.getTeamImage()).into(holder.teamImageView);
        holder.teamNameTextView.setText(team.getTeamName());
        holder.teamDesignationTextView.setText(team.getTeamDesignation());
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView teamImageView;
        public TextView teamNameTextView;
        public TextView teamDesignationTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            teamImageView = (ImageView) itemView.findViewById(R.id.teamImageView);
            teamNameTextView = (TextView) itemView.findViewById(R.id.teamNameTextView);
            teamDesignationTextView = (TextView) itemView.findViewById(R.id.teamDesignationTextView);
        }
    }
}
