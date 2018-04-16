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
import com.example.osamaabdulrehman.procom18.Models.Category;
import com.example.osamaabdulrehman.procom18.R;
import com.example.osamaabdulrehman.procom18.Utilites.Utils;

import java.util.List;

/**
 * Created by Osama Abdul Rehman on 4/12/2018.
 */

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private Context context;
    private List<Category> categoryList;

    public CategoryRVAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.competition_category_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Category category = categoryList.get(position);

        Glide.with(context).load(category.getImageURL()).into(holder.competitionCategoryImageView);
        holder.competitionCategoryName.setText(category.getName());

        holder.competitionCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CompetitionActivity.class);
                intent.putExtra(Utils.INTENT_CATEGORY_KEY, category.getName().toLowerCase());
                context.startActivity(intent);
            }
        });
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView competitionCategoryImageView;
        public TextView competitionCategoryName;
        public LinearLayout competitionCategoryLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            competitionCategoryImageView = (ImageView) itemView.findViewById(R.id.competitionCategoryImageView);
            competitionCategoryName = (TextView) itemView.findViewById(R.id.competitionCategoryName);
            competitionCategoryLayout = (LinearLayout) itemView.findViewById(R.id.competitionCategoryLayout);
        }
    }

}
