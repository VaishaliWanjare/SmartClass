package com.example.smartclassapp.PDFWork;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartclassapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentNotes extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    private static final String TAG = "MyRecyclerViewActivity";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);


        databaseReference= FirebaseDatabase.getInstance().getReference();
        //databaseReference.child("uploads");
        databaseReference.child("uploads").addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String fileName;//=dataSnapshot.getKey();//return the fileName
                //String url=  "" + dataSnapshot.getValue();// return url for fileName

                //called for indiv item at the database ref and store in data snapshot
                item obj = dataSnapshot.getValue(item.class);

                Log.d(TAG, "onChildAdded: " + obj);
                fileName = obj.getName();
                String url = obj.getFile();

                Log.d(TAG, "onChildAdded: " + url);
                ((MyAdapter) recyclerView.getAdapter()).update(fileName, url);
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyAdapter myAdapter=new MyAdapter(recyclerView,getContext(),new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);


        return view;
    }
}
