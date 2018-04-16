package com.example.osamaabdulrehman.procom18.Fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osamaabdulrehman.procom18.Activities.MainActivity;
import com.example.osamaabdulrehman.procom18.Adapters.CategoryRVAdapter;
import com.example.osamaabdulrehman.procom18.Models.Category;
import com.example.osamaabdulrehman.procom18.Models.Competition;
import com.example.osamaabdulrehman.procom18.R;
import com.example.osamaabdulrehman.procom18.Utilites.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompetitionsFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;

    public List<Competition> generalCompetitionList = new ArrayList<>();
    public List<Competition> csCompetitionList = new ArrayList<>();
    public List<Competition> eeCompetitionList = new ArrayList<>();
    public List<Competition> bbaCompetitionList = new ArrayList<>();
    public List<Competition> gamingCompetitionList = new ArrayList<>();

    public static final int NUM_OF_CATEGORIES = 2;
    private int categoriesUploaded = 0;

    public List<Category> categoryList;

    public CompetitionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_competitions, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.competitionRecyclerView);

        categoryList = new ArrayList<>();

        final CategoryRVAdapter categoryRVAdapter = new CategoryRVAdapter(getContext(), categoryList);

        generalCompetitionList.add(new Competition("0", "Spelling Bee", getString(R.string.spelling_bee_desc), "", "", "1", "No URL"));
        generalCompetitionList.add(new Competition("1", "Scrabble", getString(R.string.scrabble_desc), "", "", "1", "No URL"));
        generalCompetitionList.add(new Competition("2", "Spoken Words", getString(R.string.spoken_words_desc), "", "", "1", "No URL"));
        generalCompetitionList.add(new Competition("3", "General Knowledge", getString(R.string.general_knowledge_desc), "", "", "3-3", "No URL"));
        generalCompetitionList.add(new Competition("4", "Tug of War", getString(R.string.tug_of_war_desc), "", "", "5-8", "No URL"));

        csCompetitionList.add(new Competition("0", "Database Design", getString(R.string.db_design_desc), "Systems Limited", "", "2-3", "No URL"));
        csCompetitionList.add(new Competition("1", "Speed Programming", getString(R.string.sp_desc), "Venture Dive", "", "2-3", "No URL"));
        csCompetitionList.add(new Competition("2", "Speed Debugging", getString(R.string.sd_desc), "", "", "2-3", "No URL"));
        csCompetitionList.add(new Competition("3", "Mobile Application Development", getString(R.string.mb_desc), "Contour", "", "2-3", "No URL"));

//        eeCompetitionList.add(new Competition("Database Design", getString(R.string.db_design_desc), "Systems Limited", "", "2-3", "No URL"));
//        eeCompetitionList.add(new Competition("Speed Programming", getString(R.string.sp_desc), "Venture Dive", "", "2-3", "No URL"));
//        eeCompetitionList.add(new Competition("Speed Debugging", getString(R.string.sd_desc), "", "", "2-3", "No URL"));
//        eeCompetitionList.add(new Competition("Mobile Application Development", getString(R.string.mb_desc), "Contour", "", "2-3", "No URL"));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(Utils.CATEGORY_REFERENCE);

        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference().child(Utils.GENERAL_CATEGORY_REFERENCE);

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Category category = dataSnapshot.getValue(Category.class);
                categoryList.add(category);

                categoryRVAdapter.setCategoryList(categoryList);
                categoryRVAdapter.notifyDataSetChanged();
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

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(categoryRVAdapter);

        startUploading();

        return rootView;
    }

    public void startUploading(){
        mDatabaseReference = mFirebaseDatabase.getReference().child(Utils.CATEGORY_REFERENCE).child(Utils.GENERAL_CATEGORY_REFERENCE);
        mStorageReference = mFirebaseStorage.getReference().child(Utils.GENERAL_CATEGORY_REFERENCE);
        uploadCategory("general", Uri.parse("android.resource://com.example.osamaabdulrehman.procom18/drawable/general"), generalCompetitionList);
    }

    public void uploadCategory(final String name, Uri uri, final List<Competition> competitionList){
        StorageReference photoStorageRef = mStorageReference.child(name);

        UploadTask uploadTask = photoStorageRef.putFile(uri);

        uploadTask.addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed to upload a photo!", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "Photo uploaded successfully!", Toast.LENGTH_SHORT).show();

                String downloadURI = taskSnapshot.getDownloadUrl().toString();

                Category category = new Category(name.toUpperCase(), downloadURI, competitionList);

                mDatabaseReference.setValue(category);

                categoriesUploaded++;

                if(categoriesUploaded == 1){
                    mDatabaseReference = mFirebaseDatabase.getReference().child(Utils.CATEGORY_REFERENCE).child(Utils.CS_CATEGORY_REFERENCE);
                    mStorageReference = mFirebaseStorage.getReference().child(Utils.CS_CATEGORY_REFERENCE);
                    uploadCategory("cs", Uri.parse("android.resource://com.example.osamaabdulrehman.procom18/drawable/cs"), csCompetitionList);
                }else if(categoriesUploaded == 2){
                    // TODO: Upload other competitions
                    //mDatabaseReference = mFirebaseDatabase.getReference().child(Utils.CATEGORY_REFERENCE).child(Utils.CS_CATEGORY_REFERENCE);
                    //mStorageReference = mFirebaseStorage.getReference().child(Utils.CS_CATEGORY_REFERENCE);
                    //uploadCategory("ee", Uri.parse("android.resource://com.example.osamaabdulrehman.procom18/drawable/ee"), eeCompetitionList);
                }else if(categoriesUploaded == 3){
                    // TODO: Upload other competitions
                    //mDatabaseReference = mFirebaseDatabase.getReference().child(Utils.CATEGORY_REFERENCE).child(Utils.CS_CATEGORY_REFERENCE);
                    //mStorageReference = mFirebaseStorage.getReference().child(Utils.CS_CATEGORY_REFERENCE);
                    //uploadCategory("bba", Uri.parse("android.resource://com.example.osamaabdulrehman.procom18/drawable/bba"), bbaCompetitionList);
                }else if(categoriesUploaded == 4){
                    // TODO: Upload other competitions
                    //mDatabaseReference = mFirebaseDatabase.getReference().child(Utils.CATEGORY_REFERENCE).child(Utils.CS_CATEGORY_REFERENCE);
                    //mStorageReference = mFirebaseStorage.getReference().child(Utils.CS_CATEGORY_REFERENCE);
                    //uploadCategory("gaming", Uri.parse("android.resource://com.example.osamaabdulrehman.procom18/drawable/gaming"), gamingCompetitionList);
                }
            }
        });
    }

}
