package com.example.osamaabdulrehman.procom18.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.osamaabdulrehman.procom18.Adapters.CompetitionRVAdapter;
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

public class CompetitionActivity extends AppCompatActivity {

    private RecyclerView competitionRecyclerView;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private CompetitionRVAdapter competitionRVAdapter;

    private List<Competition> competitionList;

    public String[] refArray = {Utils.CS_CATEGORY_REFERENCE, Utils.GENERAL_CATEGORY_REFERENCE, Utils.EE_CATEGORY_REFERENCE, Utils.BBA_CATEGORY_REFERENCE, Utils.GAMING_CATEGORY_REFERENCE};

    private Toolbar competitionToolBar;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        Intent intent = getIntent();
        final String key = intent.getStringExtra(Utils.INTENT_CATEGORY_KEY);
//        Toast.makeText(this, key, Toast.LENGTH_SHORT).show();

//        Log.i("INTENT-KEY", key);

        if (key != null) {
            competitionToolBar = (Toolbar) findViewById(R.id.competitionToolBar);
            setSupportActionBar(competitionToolBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            searchEditText = (EditText) findViewById(R.id.searchEditText);
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString().toLowerCase());
                }
            });

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = mFirebaseDatabase.getReference().child(Utils.CATEGORY_REFERENCE);

            competitionRecyclerView = (RecyclerView) findViewById(R.id.competitionActivityRecyclerView);

            competitionList = new ArrayList<>();

            competitionRVAdapter = new CompetitionRVAdapter(this, competitionList, key);

            mDatabaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.i("FIR-KEY", dataSnapshot.getKey());

                    if(dataSnapshot.getKey().equals(key)){
                        Category category = dataSnapshot.getValue(Category.class);

                        List<Competition> competitions = category.getCompetitionList();

                        for(int i=0; i<competitions.size(); i++){
                            competitionList.add(competitions.get(i));
                            Log.i("Competition", competitions.get(i).getCompetitionName());
                        }

                        competitionRVAdapter.setCompetitionsList(competitionList);
                        competitionRVAdapter.notifyDataSetChanged();
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

            competitionRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            competitionRecyclerView.setHasFixedSize(true);
            competitionRecyclerView.setAdapter(competitionRVAdapter);
        }
    }

    public void filter(String text){
        List<Competition> temp = new ArrayList();

        Log.i("Text",text);

        for(Competition d: competitionList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            Log.i("Competition-Name",d.getCompetitionName());


            if(d.getCompetitionName().toLowerCase().contains(text)){
                temp.add(d);
            }
        }
        //update recyclerview
        competitionRVAdapter.updateList(temp);
    }
}
