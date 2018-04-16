package com.example.osamaabdulrehman.procom18.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.osamaabdulrehman.procom18.Activities.CompetitionActivity;
import com.example.osamaabdulrehman.procom18.Activities.CompetitionDetailsActivity;
import com.example.osamaabdulrehman.procom18.Models.Category;
import com.example.osamaabdulrehman.procom18.Models.Competition;
import com.example.osamaabdulrehman.procom18.R;
import com.example.osamaabdulrehman.procom18.Utilites.Utils;

import java.util.List;


public class CompetitionRVAdapter extends RecyclerView.Adapter<CompetitionRVAdapter.ViewHolder> {
    private Context context;
    private List<Competition> competitionsList;
    private String categoryKey;

    public CompetitionRVAdapter(Context context, List<Competition> competitionsList, String categoryKey) {
        this.context = context;
        this.competitionsList = competitionsList;
        this.categoryKey = categoryKey;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.competition_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Competition competition = competitionsList.get(position);

        Glide.with(context).load(competition.getCompetitionURL()).into(holder.competitionImageView);
        holder.competitionName.setText(competition.getCompetitionName());
        holder.competitionSponsoredBy.setText(competition.getCompetitionSponsoredBy());

        holder.competitionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CompetitionDetailsActivity.class);
                intent.putExtra(Utils.INTENT_CATEGORY_KEY, categoryKey);
                intent.putExtra(Utils.INTENT_COMPETITION_KEY, competition.getCompetitionId());
                context.startActivity(intent);
            }
        });
    }

    public void setCompetitionsList(List<Competition> competitionsList) {
        this.competitionsList = competitionsList;
    }

    public void updateList(List<Competition> list){
        competitionsList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return competitionsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView competitionImageView;
        public TextView competitionName;
        public TextView competitionSponsoredBy;
        public LinearLayout competitionLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            competitionImageView = (ImageView) itemView.findViewById(R.id.competitionImageView);
            competitionName = (TextView) itemView.findViewById(R.id.competitionName);
            competitionSponsoredBy = (TextView) itemView.findViewById(R.id.competitionSponsoredBy);
            competitionLayout = (LinearLayout) itemView.findViewById(R.id.competitionLayout);
        }
    }

}
