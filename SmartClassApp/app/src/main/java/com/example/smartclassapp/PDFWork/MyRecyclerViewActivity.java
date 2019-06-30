package com.example.smartclassapp.PDFWork;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.smartclassapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MyRecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    private static final String TAG = "MyRecyclerViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_recycler_view);

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

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyRecyclerViewActivity.this));
        MyAdapter myAdapter=new MyAdapter(recyclerView,MyRecyclerViewActivity.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);

    }

}
