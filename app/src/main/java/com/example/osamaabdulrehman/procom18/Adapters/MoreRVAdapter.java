package com.example.osamaabdulrehman.procom18.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.osamaabdulrehman.procom18.Activities.ContactActivity;
import com.example.osamaabdulrehman.procom18.Activities.TeamActivity;
import com.example.osamaabdulrehman.procom18.Models.Option;
import com.example.osamaabdulrehman.procom18.R;

import java.util.List;

public class MoreRVAdapter extends RecyclerView.Adapter<MoreRVAdapter.ViewHolder> {

    private Context context;
    private List<Option> optionList;

    public MoreRVAdapter(Context context, List<Option> optionsList){
        this.context = context;
        this.optionList = optionsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.more_list_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Option option = optionList.get(position);

        holder.optionTextView.setText(option.getName());
        Glide.with(context).load(option.getImageId()).into(holder.optionImageView);

        holder.optionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option.getName().contentEquals("Contact Us")){
//                    Toast.makeText(context, "Contacts", Toast.LENGTH_SHORT).show();

                    context.startActivity(new Intent(context, ContactActivity.class));
                }else{
//                    Toast.makeText(context, "Team", Toast.LENGTH_SHORT).show();

                    context.startActivity(new Intent(context, TeamActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView optionTextView;
        public ImageView optionImageView;
        public LinearLayout optionLayout;

        public static final int ACTION_BAR_PLUS_TABS_HEIGHT = 228;

        public ViewHolder(View itemView) {
            super(itemView);

            //Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirNextLTPro_Regular.otf");

            optionTextView = (TextView) itemView.findViewById(R.id.optionName);
            optionImageView = (ImageView) itemView.findViewById(R.id.optionImageView);
            optionLayout = (LinearLayout) itemView.findViewById(R.id.optionLayout);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;

            optionImageView.getLayoutParams().height = height/2-ACTION_BAR_PLUS_TABS_HEIGHT;
        }


    }
}
