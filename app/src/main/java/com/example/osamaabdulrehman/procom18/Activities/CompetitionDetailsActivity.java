package com.example.osamaabdulrehman.procom18.Activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.osamaabdulrehman.procom18.Models.Category;
import com.example.osamaabdulrehman.procom18.Models.Competition;
import com.example.osamaabdulrehman.procom18.R;
import com.example.osamaabdulrehman.procom18.Utilites.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CompetitionDetailsActivity extends AppCompatActivity {

    private TextView competitionDetailsName;
    private TextView competitionDetailsMembers;
    private TextView competitionDetailsDesc;
    private TextView competitionDetailsSponsoredBy;

    private Button btnCompetitionDetailsParticipate;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private Competition competition;

    public String[] refArray = {Utils.CS_CATEGORY_REFERENCE, Utils.GENERAL_CATEGORY_REFERENCE, Utils.EE_CATEGORY_REFERENCE, Utils.BBA_CATEGORY_REFERENCE, Utils.GAMING_CATEGORY_REFERENCE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_details);

        Intent intent = getIntent();
        final String key = intent.getStringExtra(Utils.INTENT_CATEGORY_KEY);
        final String position = intent.getStringExtra(Utils.INTENT_COMPETITION_KEY);
//        Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();

        Log.i("POSITION", position + "");

        if(key != null && position != null){
            competitionDetailsName = (TextView) findViewById(R.id.competitionDetailsName);
            competitionDetailsMembers = (TextView) findViewById(R.id.competitionDetailsMembers);
            competitionDetailsDesc = (TextView) findViewById(R.id.competitionDetailsDesc);
            competitionDetailsSponsoredBy = (TextView) findViewById(R.id.competitionDetailsSponsoredBy);

            btnCompetitionDetailsParticipate = (Button) findViewById(R.id.btnCompetitionDetailsParticipate);

            btnCompetitionDetailsParticipate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an explicit intent for an Activity in your app
                    Intent intent = new Intent(CompetitionDetailsActivity.this, TeamActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(CompetitionDetailsActivity.this, 0, intent, 0);

                    NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(CompetitionDetailsActivity.this)
                            .setSmallIcon(R.drawable.procom)
                            .setContentTitle("Congrats")
                            .setContentText("Your team is registered")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            // Set the intent that will fire when the user taps the notification
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(CompetitionDetailsActivity.this);

                    // notificationId is a unique int for each notification that you must define
                    notificationManager.notify(1, mBuilder.build());
                }
            });

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = mFirebaseDatabase.getReference().child(Utils.CATEGORY_REFERENCE);
            mDatabaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if(dataSnapshot.getKey().equals(key)){
                        Category category = dataSnapshot.getValue(Category.class);

                        List<Competition> competitions = category.getCompetitionList();

                        for (int i=0; i<competitions.size(); i++){
                            if(competitions.get(i).getCompetitionId().equals(position)){
                                competition = competitions.get(i);

                                competitionDetailsName.setText(competition.getCompetitionName());
                                competitionDetailsMembers.setText("Members Allowed: " + competition.getTotalMembersAllowed());
                                competitionDetailsDesc.setText(competition.getCompetitionDescription());
                                competitionDetailsSponsoredBy.setText(competition.getCompetitionSponsoredBy());

                                setTitle(competition.getCompetitionName());

                                break;
                            }
                        }
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
